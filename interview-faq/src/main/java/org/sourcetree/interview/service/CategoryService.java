/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * CategoryService.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Nov 06, 2012		Chalam Pavuluri				Created
 * *************************************************************
 */
package org.sourcetree.interview.service;

import java.util.List;

import org.sourcetree.interview.dto.CategoryDTO;
import org.sourcetree.interview.entity.Category;

/**
 * Category related services reside here
 * 
 * @author Chalam Pavuluri
 * 
 */
public interface CategoryService
{
	/**
	 * create insert new category object.
	 * 
	 * @param categoryDTO
	 *            category DTO
	 */
	void create(CategoryDTO catergoryDTO);

	/**
	 * update existing category object.
	 * 
	 * @param catergoryDTO
	 * @param categoryId
	 */
	void update(CategoryDTO catergoryDTO, Long categoryId);

	/**
	 * Find category by id
	 * 
	 * @param categoryId
	 * @return Category
	 */
	public Category findCategoryById(Long categoryId);

	/**
	 * Find Categories by question id
	 * 
	 * @param questionId
	 * @return List of Categories
	 */
	public List<Category> getCategoriesByQuestionId(Long questionId);

	/**
	 * Delete Category record
	 * 
	 * @param categoryId
	 * @return
	 */
	public boolean deleteCategoryById(Long categoryId);

}
