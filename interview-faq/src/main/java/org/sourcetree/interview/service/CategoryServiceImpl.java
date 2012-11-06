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

import java.util.List;

import org.sourcetree.interview.dao.CategoryDAO;
import org.sourcetree.interview.dto.CategoryDTO;
import org.sourcetree.interview.entity.Category;
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
		category.setCategoryName(catergoryDTO.getCategoryName());
		category.setCategoryDescription(catergoryDTO.getCategoryDescription());
		category.setQuestions(catergoryDTO.getQuestions());
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
			category.setCategoryName(catergoryDTO.getCategoryName());
			category.setCategoryDescription(catergoryDTO
					.getCategoryDescription());
			category.setQuestions(catergoryDTO.getQuestions());
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
}
