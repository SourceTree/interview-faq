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

import java.util.ArrayList;
import java.util.List;

import org.sourcetree.interview.dao.CategoryDAO;
import org.sourcetree.interview.dao.QuestionDAO;
import org.sourcetree.interview.dto.CategoryDTO;
import org.sourcetree.interview.dto.QuestionDTO;
import org.sourcetree.interview.entity.Category;
import org.sourcetree.interview.entity.Question;
import org.sourcetree.interview.support.CoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Question service implementation
 * 
 * @author Chalam Pavuluri
 */
@Service
@Transactional(readOnly = true)
public class QuestionServiceImpl implements QuestionService
{
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
		Question question = new Question();

		copyDTOtoEntity(questionDTO, question);

		questionDAO.save(question);

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
				copyDTOtoEntity(questionDTO, question);
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
	public List<Question> getQuestionsByCategoryId(Long categoryId)
	{
		return questionDAO.getQuestionsByCategoryId(categoryId);
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
	 * To Convert from DTO list to Entity list
	 * 
	 * @param categoryDTOs
	 * @return category list
	 */
	private List<Category> processCategoryDto(
			final List<CategoryDTO> categoryDTOs)
	{
		if (CoreUtil.isEmpty(categoryDTOs))
		{
			List<Category> categories = new ArrayList<Category>();
			for (CategoryDTO categoryDto : categoryDTOs)
			{
				Category category = categoryDAO.find(categoryDto.getId());
				categories.add(category);
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
	private void copyDTOtoEntity(final QuestionDTO questionDTO,
			final Question question)
	{
		if (question != null && questionDTO != null)
		{
			question.setQuestion(questionDTO.getQuestion());

			question.setCategories(processCategoryDto(questionDTO
					.getCategoryDTOs()));
			question.setAnswer(questionDTO.getAnswer());
		}
	}
}
