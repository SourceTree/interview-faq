/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * CategoryServiceImpl.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Nov 06, 2012		Chalam Pavuluri				Created
 * *************************************************************
 */
package org.sourcetree.interview.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.sourcetree.interview.dao.CategoryDAO;
import org.sourcetree.interview.dto.CategoryDTO;
import org.sourcetree.interview.dto.ListProp;
import org.sourcetree.interview.dto.QuestionDTO;
import org.sourcetree.interview.entity.Category;
import org.sourcetree.interview.entity.Question;
import org.sourcetree.interview.enums.QueryCriteriaTypeEnum;
import org.sourcetree.interview.support.CoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Category service implementation
 * 
 * @author Chalam Pavuluri
 */
@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService
{
	@Autowired
	private CategoryDAO categoryDAO;

	private static final String CACHE_ALL_KEY = "-2";

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = false)
	@CacheEvict(value = { "allParentCategories", "allCategories",
			"categoryDTOByName", "categoryDTOById", "categoryById" },
			allEntries = true, beforeInvocation = false)
	public void create(CategoryDTO catergoryDTO)
	{
		Category category = new Category();
		category.setCategoryName(catergoryDTO.getCategoryName().trim());
		category.setCategoryDescription(catergoryDTO.getCategoryDescription());
		category.setCategoryDisplayName(catergoryDTO.getCategoryDisplayName());
		category.setCreatedDate(new Date());

		if (catergoryDTO.getParentCategoryDTO() != null
				&& catergoryDTO.getParentCategoryDTO().getId() != null)
		{
			category.setParentCategory(findCategoryById(catergoryDTO
					.getParentCategoryDTO().getId()));
		}

		categoryDAO.save(category);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = false)
	@CacheEvict(value = { "allParentCategories", "allCategories",
			"categoryDTOByName", "categoryDTOById", "categoryById" },
			allEntries = true, beforeInvocation = false)
	public void update(CategoryDTO catergoryDTO, Long categoryId)
	{
		Category category = findCategoryById(categoryId);
		if (category != null)
		{
			category.setCategoryDescription(catergoryDTO
					.getCategoryDescription());
			category.setCategoryDisplayName(catergoryDTO
					.getCategoryDisplayName());

			// Logic for updating parent category
			if (catergoryDTO.getParentCategoryDTO() != null)
			{
				if ((category.getParentCategory() != null && catergoryDTO
						.getParentCategoryDTO().getId()
						.compareTo(category.getParentCategory().getId()) != 0)
						|| category.getParentCategory() == null)
				{
					category.setParentCategory(findCategoryById(catergoryDTO
							.getParentCategoryDTO().getId()));
				}
			}
			else
			{
				category.setParentCategory(null);
			}

			category.setModifiedDate(new Date());
			categoryDAO.update(category);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Cacheable(value = "categoryById", key = "#categoryId")
	public Category findCategoryById(Long categoryId)
	{
		return categoryDAO.find(categoryId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Category> getCategoriesByQuestionId(Long questionId)
	{
		return categoryDAO.getCategoriesByQuestionId(questionId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = false)
	@CacheEvict(value = { "allParentCategories", "allCategories",
			"categoryDTOByName", "categoryDTOById", "categoryById" },
			allEntries = true)
	public boolean deleteCategoryById(Long categoryId)
	{
		return categoryDAO.deleteById(categoryId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCategoryExists(String categoryName)
	{
		if (!StringUtils.isBlank(categoryName))
		{
			return categoryDAO.existsByParameter("categoryName",
					categoryName.trim(), QueryCriteriaTypeEnum.IGNORE_CASE);
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Cacheable(value = "allCategories", key = CACHE_ALL_KEY)
	public List<CategoryDTO> findAllCategories()
	{

		return findAllCategories(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Cacheable(value = "allCategories", key = "#listProp.page")
	public List<CategoryDTO> findAllCategories(ListProp listProp)
	{
		return categoryDAO.getAllCategoryDTOs(listProp);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Cacheable(value = "categoryDTOByName", key = "#name")
	public CategoryDTO getCategoryDTOByName(String name)
	{
		if (!StringUtils.isBlank(name))
		{
			Category category = categoryDAO.findByParameter("categoryName",
					name.trim(), false, true);
			if (category != null)
			{
				return copyEntityToDTO(category);
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Cacheable(value = "categoryDTOById", key = "#categoryId")
	public CategoryDTO getCategoryDTOById(Long categoryId)
	{

		Category category = categoryDAO.find(categoryId);

		if (category != null)
		{
			return copyEntityToDTO(category);
		}

		return null;
	}

	@Override
	@Cacheable(value = "allParentCategories", key = CACHE_ALL_KEY)
	public List<CategoryDTO> findAllParentCategories()
	{
		return findAllParentCategories(null);
	}

	@Override
	@Cacheable(value = "allParentCategories", key = "#listProp.page")
	public List<CategoryDTO> findAllParentCategories(ListProp listProp)
	{
		return categoryDAO.getAllParentCategorDTOs(listProp);
	}

	@Override
	// @Cacheable(value = "allChildCategoriesByParentName", key =
	// "#listProp.page")
	public List<CategoryDTO> findAllChildCategorDTOsByParentName(
			ListProp listProp, String parentCategoryName)
	{
		return categoryDAO.getChildCategorDTOsByParentName(listProp,
				parentCategoryName);
	}

	/**
	 * 
	 * @param category
	 * @return
	 */
	private CategoryDTO copyEntityToDTO(final Category category)
	{
		if (category == null)
		{
			return null;
		}

		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(category.getId());
		categoryDTO.setCategoryName(category.getCategoryName());
		categoryDTO.setCategoryDescription(category.getCategoryDescription());
		categoryDTO.setCategoryDisplayName(category.getCategoryDisplayName());

		if (category.getParentCategory() != null)
		{
			categoryDTO.setParentCategoryDTO(copyEntityToDTO(category
					.getParentCategory()));
		}
		categoryDTO.setQuestionDtos(processQuestionEntity(category
				.getQuestions()));

		return categoryDTO;
	}

	/**
	 * To Convert from Entity list to DTO list
	 * 
	 * @param questions
	 * @return List Question DTOs
	 */
	private List<QuestionDTO> processQuestionEntity(
			final List<Question> questions)
	{
		if (!CoreUtil.isEmpty(questions))
		{
			List<QuestionDTO> questionDTOs = new ArrayList<QuestionDTO>();
			for (Question question : questions)
			{
				QuestionDTO questionDTO = new QuestionDTO();
				questionDTO.setId(question.getId());
				questionDTO.setQuestion(question.getQuestion());
				questionDTO.setAnswer(question.getAnswer());
				questionDTOs.add(questionDTO);
			}
			return questionDTOs;
		}
		return null;
	}
}
