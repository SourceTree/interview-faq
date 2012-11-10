/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * CategoryDAO.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Nov 06, 2012		Chalam Pavuluri				Created
 * *************************************************************
 */
package org.sourcetree.interview.dao;

import java.util.List;

import org.sourcetree.interview.dto.CategoryDTO;
import org.sourcetree.interview.dto.ListProp;
import org.sourcetree.interview.entity.Category;

/**
 * Trace Partner related DAO
 * 
 * @author Chalam Pavuluri
 * 
 */
public interface CategoryDAO extends GenericDAO<Category, Long>
{

	/**
	 * Retrieves all the categories associated with the Question
	 * 
	 * @param questionId
	 * @return
	 */
	List<Category> getCategoriesByQuestionId(Long questionId);

	/**
	 * Retrieve all Non- Deleted categories
	 * 
	 * @param listProp
	 *            Listing property object. can be null.
	 * 
	 * @return Category DTO List
	 */
	List<CategoryDTO> getAllCategoryDTOs(ListProp listProp);

	/**
	 * Retrieves the category as DTO object with ignore case.
	 * 
	 * @param param
	 *            category name to fetch
	 * @return Category DTO
	 */
	CategoryDTO getCategoryDTOByName(String param);
}
