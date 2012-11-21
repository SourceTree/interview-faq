/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * QuestionDAO.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Nov 06, 2012		Chalam Pavuluri				Created
 * *************************************************************
 */
package org.sourcetree.interview.dao;

import java.util.List;

import org.sourcetree.interview.dto.ListProp;
import org.sourcetree.interview.dto.QuestionDTO;
import org.sourcetree.interview.entity.Question;

/**
 * Trace Partner related DAO
 * 
 * @author Chalam Pavuluri
 * 
 */
public interface QuestionDAO extends GenericDAO<Question, Long>
{
	/**
	 * Retrieves questions for the passed in category id
	 * 
	 * @param categoryId
	 * @return
	 */
	List<Question> getQuestionsByCategoryId(Long categoryId);

	/**
	 * Retrieves the question as DTO object with ignore case.
	 * 
	 * @param id
	 *            question id to fetch
	 * @return Question DTO
	 */
	QuestionDTO getQuestionDTOById(Long id);

	/**
	 * 
	 * @param categoryName
	 * @param listProp
	 * @return
	 */
	List<Question> getQuestionsByCategoryName(String categoryName,
			ListProp listProp);

	/**
	 * Get Questions by searchKey and categoryId
	 * 
	 * @param searchKey
	 * @param categoryId
	 * @return List QuestionDTO
	 */
	List<QuestionDTO> searchQuestions(String[] searchKey, Long categoryId,
			ListProp listProp);
}
