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
	void create(CategoryDTO categoryDTO);

	/**
	 * update existing category object.
	 * 
	 * @param categoryDTO
	 * @param categoryId
	 */
	void update(CategoryDTO categoryDTO, Long categoryId);

	/**
	 * Find category by id
	 * 
	 * @param categoryId
	 * @return Category
	 */
	Category findCategoryById(Long categoryId);

	/**
	 * Find Categories by question id
	 * 
	 * @param questionId
	 * @return List of Categories
	 */
	List<Category> getCategoriesByQuestionId(Long questionId);

	/**
	 * Delete Category record
	 * 
	 * @param categoryId
	 * @return
	 */
	boolean deleteCategoryById(Long categoryId);

	/**
	 * @param categoryName
	 *            categoryName to check
	 * @return true if a record with same email exists
	 */
	boolean isCategoryExists(String categoryName);

	/**
	 * 
	 * @return List of category DTO
	 */
	List<CategoryDTO> findAllCategories();

	/**
	 * get category based given param
	 * 
	 * @param param
	 * @return
	 */
	CategoryDTO findCategoryByParam(String param);

}
