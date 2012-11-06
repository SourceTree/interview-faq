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
	public Question findQuestionById(Long questionId);

	/**
	 * Find Questions by category id
	 * 
	 * @param categoryId
	 * @return List of Questions
	 */
	public List<Question> getQuestionsByCategoryId(Long categoryId);

	/**
	 * Delete question by id
	 * 
	 * @param questionId
	 * @return Boolean
	 */
	public boolean deleteQuestionById(Long questionId);

}
