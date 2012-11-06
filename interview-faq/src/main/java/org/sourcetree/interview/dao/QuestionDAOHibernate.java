/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * QuestionDAOHibernate.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Nov 06, 2012		Chalam Pavuluri				Created
 * *************************************************************
 */
package org.sourcetree.interview.dao;

import java.util.List;

import org.sourcetree.interview.entity.Question;
import org.springframework.stereotype.Repository;

/**
 * QuestionDAO interface implementation
 * 
 * @author Chalam Pavuluri
 * 
 */
@Repository
public class QuestionDAOHibernate extends GenericDAOImpl<Question, Long>
		implements QuestionDAO
{
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Class<Question> getEntityClass()
	{
		return Question.class;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Question> getQuestionsByCategoryId(Long categoryId)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
