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

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.sourcetree.interview.dao.CategoryDAO;
import org.sourcetree.interview.dto.CategoryDTO;
import org.sourcetree.interview.dto.ListProp;
import org.sourcetree.interview.entity.Category;
import org.sourcetree.interview.enums.QueryCriteriaTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = false)
	public void create(CategoryDTO catergoryDTO)
	{
		Category category = new Category();
		category.setCategoryName(catergoryDTO.getCategoryName().trim());
		category.setCategoryDescription(catergoryDTO.getCategoryDescription());
		category.setCreatedDate(new Date());
		categoryDAO.save(category);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = false)
	public void update(CategoryDTO catergoryDTO, Long categoryId)
	{
		Category category = findCategoryById(categoryId);
		if (category != null)
		{
			// TODO: do we need to update name as well ? -- Venky
			category.setCategoryName(catergoryDTO.getCategoryName().trim());
			category.setCategoryDescription(catergoryDTO
					.getCategoryDescription());
			categoryDAO.update(category);

		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
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
	public List<CategoryDTO> findAllCategories()
	{

		return findAllCategories(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CategoryDTO> findAllCategories(ListProp listProp)
	{
		return categoryDAO.getAllCategoryDTOs(listProp);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CategoryDTO getCategoryDTOByName(String name)
	{
		if (!StringUtils.isBlank(name))
		{
			return categoryDAO.getCategoryDTOByName(name.trim());
		}
		return null;
	}

}
