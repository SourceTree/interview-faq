/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * QuestionService.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Nov 06, 2012		Chalam Pavuluri				Created
 * *************************************************************
 */
package org.sourcetree.interview.service;

import java.util.List;

import org.sourcetree.interview.dto.ListProp;
import org.sourcetree.interview.dto.QuestionDTO;
import org.sourcetree.interview.entity.Question;

/**
 * Question related services reside here
 * 
 * @author Chalam Pavuluri
 * 
 */
public interface QuestionService
{
	/**
	 * create insert new question object.
	 * 
	 * @param questionDTO
	 */
	void create(QuestionDTO questionDTO);

	/**
	 * Update Question. if a null value is found for id of Question DTO, it
	 * thorws invalid argument exception
	 * 
	 * @param questionDTO
	 */
	void update(QuestionDTO questionDTO);

	/**
	 * Update Question
	 * 
	 * @param questionDTO
	 * @param questionId
	 */
	void update(QuestionDTO questionDTO, Long questionId);

	/**
	 * Find Question by id
	 * 
	 * @param questionId
	 * @return Question
	 */
	Question findQuestionById(Long questionId);

	/**
	 * Delete question by id
	 * 
	 * @param questionId
	 * @return Boolean
	 */
	boolean deleteQuestionById(Long questionId);

	/**
	 * get question as a DTO object based on Id
	 * 
	 * @param questionId
	 *            Id of the question
	 * @return question as DTO object
	 */
	QuestionDTO getQuestionDTOById(Long questionId);

	/**
	 * Get Questions by category name
	 * 
	 * @param categoryName
	 * @param listProp
	 * @return
	 */
	List<QuestionDTO> getQuestionsByCategoryName(String categoryName,
			ListProp listProp);

	/**
	 * Get Questions by searchKey and categoryId
	 * 
	 * @param searchKey
	 * @param categoryName
	 * @param listProp
	 * @return Lit of Question
	 */
	List<QuestionDTO> getQuestionSearchResult(String[] searchKey,
			String categoryName, ListProp listProp);
}
