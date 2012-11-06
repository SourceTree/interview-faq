/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * QuestionServiceImpl.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Nov 06, 2012		Chalam Pavuluri				Created
 * *************************************************************
 */
package org.sourcetree.interview.service;

import java.util.List;

import org.sourcetree.interview.dao.QuestionDAO;
import org.sourcetree.interview.dto.QuestionDTO;
import org.sourcetree.interview.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Question service implementation
 * 
 * @author Chalam Pavuluri
 */
@Service
@Transactional(readOnly = true)
public class QuestionServiceImpl implements QuestionService
{
	@Autowired
	private QuestionDAO questionDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = false)
	public void create(QuestionDTO questionDTO)
	{
		Question question = new Question();
		question.setQuestion(questionDTO.getQuestion());
		question.setCategories(questionDTO.getCategories());

		questionDAO.save(question);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = false)
	public void update(QuestionDTO questionDTO, Long questionId)
	{
		Question question = findQuestionById(questionId);
		if (question != null)
		{
			question.setQuestion(questionDTO.getQuestion());
			question.setCategories(questionDTO.getCategories());

			questionDAO.update(question);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Question findQuestionById(Long questionId)
	{
		return questionDAO.find(questionId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Question> getQuestionsByCategoryId(Long categoryId)
	{
		return questionDAO.getQuestionsByCategoryId(categoryId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = false)
	public boolean deleteQuestionById(Long questionId)
	{
		return questionDAO.deleteById(questionId);
	}

}
