/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * QuestionServiceImpl.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Nov 06, 2012		Chalam Pavuluri				Created
 * *************************************************************
 */
package org.sourcetree.interview.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.sourcetree.interview.dao.CategoryDAO;
import org.sourcetree.interview.dao.QuestionDAO;
import org.sourcetree.interview.dto.CategoryDTO;
import org.sourcetree.interview.dto.ListProp;
import org.sourcetree.interview.dto.QuestionDTO;
import org.sourcetree.interview.entity.Category;
import org.sourcetree.interview.entity.Question;
import org.sourcetree.interview.support.CoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Question service implementation
 * 
 * @author Chalam Pavuluri
 */
@Service
@Transactional(readOnly = true)
public class QuestionServiceImpl implements QuestionService
{

	protected static final Logger LOG = Logger
			.getLogger(QuestionServiceImpl.class);

	private static final int QUESTIONID_CELLNUM = 0;
	private static final int QUESTION_CELLNUM = 1;
	private static final int ANSWER_CELLNUM = 2;
	private static final int CATEGORIES_CELLNUM = 3;

	@Autowired
	private QuestionDAO questionDAO;

	@Autowired
	private CategoryDAO categoryDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = false)
	public void create(QuestionDTO questionDTO)
	{
		Question question = copyDTOtoEntity(questionDTO, null);

		if (question != null)
		{
			question.setCreatedDate(new Date());
			questionDAO.save(question);
			return;
		}

		throw new IllegalArgumentException("Invalid Data: Null");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = false)
	public void update(QuestionDTO questionDTO)
	{
		if (questionDTO.getId() != null)
		{
			Question question = findQuestionById(questionDTO.getId());
			if (question != null)
			{
				question = copyDTOtoEntity(questionDTO, question);

				question.setModifiedDate(new Date());
				questionDAO.update(question);
			}
			return;
		}

		throw new IllegalArgumentException("Invalid ID for the Question.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = false)
	public void update(QuestionDTO questionDTO, Long questionId)
	{
		questionDTO.setId(questionId);
		update(questionDTO);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Question findQuestionById(Long questionId)
	{
		return questionDAO.find(questionId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = false)
	public boolean deleteQuestionById(Long questionId)
	{
		return questionDAO.deleteById(questionId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<QuestionDTO> getQuestionsByCategoryName(String categoryName,
			ListProp listProp)
	{
		if (!StringUtils.isBlank(categoryName))
		{
			List<Question> questionList = questionDAO
					.getQuestionsByCategoryName(categoryName, listProp);
			if (!CoreUtil.isEmpty(questionList))
			{
				List<QuestionDTO> questionDtos = new ArrayList<QuestionDTO>();
				for (Question question : questionList)
				{
					QuestionDTO questionDTO = copyEntitytoDTO(question);
					questionDtos.add(questionDTO);

				}
				return questionDtos;
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public QuestionDTO getQuestionDTOById(Long questionId)
	{
		Question question = new Question();

		if (questionId != null)
		{
			question = findQuestionById(questionId);
			QuestionDTO questionDTO = new QuestionDTO();
			if (question != null)
			{
				questionDTO = copyEntitytoDTO(question);
				return questionDTO;
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<QuestionDTO> getQuestionSearchResult(String[] searchKey,
			String categoryName, ListProp listProp)
	{

		List<Question> questionList = questionDAO.searchQuestions(searchKey,
				categoryName, listProp);

		List<QuestionDTO> questionDTOs = new ArrayList<QuestionDTO>();
		for (Question question : questionList)
		{
			questionDTOs.add(copyEntitytoDTO(question));
		}

		return questionDTOs;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = false)
	public void uploadquestionExcel(MultipartFile multipartFile)
	{

		try
		{
			InputStream input = multipartFile.getInputStream();
			HSSFWorkbook wb = new HSSFWorkbook(input);
			HSSFSheet sheet = wb.getSheetAt(0);

			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++)
			{
				boolean saveNewRecord = false;
				boolean isValidRecord = true;

				int excelRowNum = (i + 1);

				QuestionDTO questionDTO = new QuestionDTO();

				HSSFRow dataRow = sheet.getRow(i);

				// Question Id value
				HSSFCell questionIdValue = dataRow.getCell(QUESTIONID_CELLNUM);
				if (questionIdValue != null)
				{
					if (questionIdValue.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					{
						Long id = (long) questionIdValue.getNumericCellValue();
						QuestionDTO questionById = getQuestionDTOById(id);
						if (questionById != null)
						{
							questionDTO.setId(id);
						}
						else
						{
							isValidRecord = false;
							LOG.error(" No Record Exists With Question id '"
									+ id + "' at row :" + excelRowNum);
						}

					}
					else
					{
						isValidRecord = false;
						LOG.error("Invalid Id format at row : " + excelRowNum);
					}

				}
				else
				{
					saveNewRecord = true;
					LOG.info("Empty Id ,create a record at row : "
							+ excelRowNum);
				}

				// Question Value
				HSSFCell questionValue = dataRow.getCell(QUESTION_CELLNUM);
				if (questionValue != null
						&& questionValue.getCellType() == HSSFCell.CELL_TYPE_STRING)
				{
					String question = questionValue.getStringCellValue();
					if (!StringUtils.isEmpty(question.trim()))
					{
						questionDTO.setQuestion(question);
					}
					else
					{
						isValidRecord = false;
						LOG.error("Question Cannot be empty, please enter a question at row : "
								+ excelRowNum);
					}
				}
				else
				{
					isValidRecord = false;
					LOG.error("Null or Invalid Question format at row : "
							+ excelRowNum);
				}

				// Question Value
				HSSFCell answerValue = dataRow.getCell(ANSWER_CELLNUM);
				if (answerValue != null
						&& answerValue.getCellType() == HSSFCell.CELL_TYPE_STRING)
				{
					String answer = answerValue.getStringCellValue();
					if (!StringUtils.isEmpty(answer.trim()))
					{
						questionDTO.setAnswer(answer);
					}
					else
					{
						isValidRecord = false;
						LOG.error("Answer Cannot be empty, please enter an answer at row : "
								+ excelRowNum);
					}
				}
				else
				{
					isValidRecord = false;
					LOG.error("Null or Invalid Answer format at row : "
							+ excelRowNum);
				}

				// Categories Value
				HSSFCell catogriesValue = dataRow.getCell(CATEGORIES_CELLNUM);
				if (catogriesValue != null
						&& catogriesValue.getCellType() == HSSFCell.CELL_TYPE_STRING)
				{
					String categories = catogriesValue.getStringCellValue();
					List<String> categoryNameList = parseList(categories, ",");
					List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();
					for (String categoryName : categoryNameList)
					{
						CategoryDTO categoryDTO = categoryDAO
								.getCategoryDTOByName(categoryName);
						if (categoryDTO != null)
						{
							categoryDTOs.add(categoryDTO);
						}
						else
						{
							LOG.error(" No Category with name :  '"
									+ categoryName + "' exists at row : "
									+ excelRowNum);
							isValidRecord = false;
						}
					}
					questionDTO.setCategoryDTOs(categoryDTOs);

				}
				else
				{
					isValidRecord = false;
					LOG.error("Null or Invalid Categories format at row : "
							+ excelRowNum);
				}
				if (isValidRecord)
				{
					if (saveNewRecord)
					{
						create(questionDTO);
					}
					else
					{
						update(questionDTO);
					}
				}
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * To Convert from DTO list to Entity list
	 * 
	 * @param categoryDTOs
	 * @return category list
	 */
	private List<Category> processCategoryDto(
			final List<CategoryDTO> categoryDTOs)
	{
		if (!CoreUtil.isEmpty(categoryDTOs))
		{
			List<Category> categories = new ArrayList<Category>();
			Map<String, String> catMap = new HashMap<String, String>();
			for (CategoryDTO categoryDto : categoryDTOs)
			{
				if (!catMap.containsKey(categoryDto.getId().toString()))
				{
					Category category = categoryDAO.find(categoryDto.getId());
					categories.add(category);

					catMap.put(category.getId().toString(),
							category.getCategoryName());

					// Checks for Sub Category
					if (category.getParentCategory() != null)
					{
						if (!catMap.containsKey(category.getParentCategory()
								.getId().toString()))
						{
							categories.add(category.getParentCategory());
							catMap.put(category.getParentCategory().getId()
									.toString(), category.getParentCategory()
									.getCategoryName());
						}
					}
				}
			}
			return categories;
		}
		return null;
	}

	/**
	 * copies Question DTO data into Question entity
	 * 
	 * @param questionDTO
	 *            Question DTO. cannot be empty
	 * @param question
	 *            Question entity. cannot be empty
	 */
	private Question copyDTOtoEntity(final QuestionDTO questionDTO,
			final Question question)
	{
		if (questionDTO == null)
		{
			return null;
		}

		Question localQuestion = question;
		if (localQuestion == null)
		{
			localQuestion = new Question();
		}

		localQuestion.setQuestion(questionDTO.getQuestion().trim());

		localQuestion.setCategories(processCategoryDto(questionDTO
				.getCategoryDTOs()));

		localQuestion.setAnswer(questionDTO.getAnswer());

		return localQuestion;
	}

	/**
	 * copies Question entity data into Question dto
	 * 
	 * @param questionDTO
	 *            Question DTO. cannot be empty
	 * @param question
	 *            Question entity. cannot be empty
	 */
	private QuestionDTO copyEntitytoDTO(final Question question)
	{
		if (question == null)
		{
			return null;
		}

		QuestionDTO questionDTO = new QuestionDTO();

		questionDTO.setQuestion(question.getQuestion().trim());

		questionDTO.setCategoryDTOs(processCategoryEntity(question
				.getCategories()));
		questionDTO.setAnswer(question.getAnswer());
		questionDTO.setId(question.getId());

		return questionDTO;

	}

	/**
	 * To Convert from Entity list to DTO list
	 * 
	 * @param category
	 * @return categoryDTOs
	 */
	private List<CategoryDTO> processCategoryEntity(
			final List<Category> categories)
	{
		if (!CoreUtil.isEmpty(categories))
		{
			List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();
			for (Category category : categories)
			{
				CategoryDTO categoryDto = new CategoryDTO();
				categoryDto.setId(category.getId());
				categoryDto.setCategoryName(category.getCategoryName());
				categoryDto.setCategoryDisplayName(category
						.getCategoryDisplayName());

				categoryDTOs.add(categoryDto);
			}
			return categoryDTOs;
		}
		return null;
	}

	/**
	 * @param list
	 * @param delim
	 * @return
	 */
	private List<String> parseList(String list, String delim)
	{
		List<String> result = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(list, delim);
		while (tokenizer.hasMoreTokens())
		{
			result.add(tokenizer.nextToken());
		}
		return result;
	}
}
