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
import org.sourcetree.interview.dto.ListProp;
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
	 * Retrieves all categories irrespective of parent or sub category. Doesn't
	 * contain parent category reference information.
	 * 
	 * @return List of category DTO
	 */
	List<CategoryDTO> findAllCategories();

	/**
	 * Retrieves all categories irrespective of parent or sub category. Doesn't
	 * contain parent category reference information.
	 * 
	 * @param listProp
	 * @return List of category DTO
	 */
	List<CategoryDTO> findAllCategories(ListProp listProp);

	/**
	 * retrieves all categories which doesn't contain any parents.
	 * 
	 * @return List of category DTO
	 */
	List<CategoryDTO> findAllParentCategories();

	/**
	 * retrieves all categories which doesn't contain any parents.
	 * 
	 * @param listProp
	 * @return List of category DTO
	 */
	List<CategoryDTO> findAllParentCategories(ListProp listProp);

	/**
	 * Get category as a DTO object based on name. Retrieves the parent category
	 * reference information.
	 * 
	 * @param name
	 *            name of the category
	 * @return category as DTO object
	 */
	CategoryDTO getCategoryDTOByName(String name);

	/**
	 * Get category as a DTO object based on category ID. Retrieves the parent
	 * category reference information.
	 * 
	 * @param categoryId
	 *            category ID
	 * @return category as DTO object
	 */
	CategoryDTO getCategoryDTOById(Long categoryId);

	/**
	 * retrieves all child categories by parent category name.
	 * 
	 * @param parentCategoryName
	 * @return List of category DTO
	 */
	public List<CategoryDTO> findAllChildCategorDTOsByParentName(
			String parentCategoryName);

	/**
	 * retrieves all child categories by parent category name.
	 * 
	 * @param listProp
	 * @param parentCategoryName
	 * @return List of category DTO
	 */
	public List<CategoryDTO> findAllChildCategorDTOsByParentName(
			String parentCategoryName, ListProp listProp);

}
