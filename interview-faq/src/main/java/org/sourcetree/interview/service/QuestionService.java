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
	 * Find Questions by category id
	 * 
	 * @param categoryId
	 * @return List of Questions
	 */
	List<Question> getQuestionsByCategoryId(Long categoryId);

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
	 * @param Id
	 *            Id of the question
	 * @return question as DTO object
	 */
	QuestionDTO getQuestionDTOById(Long Id);

	/**
	 * Get Questions by category name
	 * 
	 * @param categoryName
	 * @return
	 */
	List<QuestionDTO> getQuestionsByCategoryName(String categoryName);

}
