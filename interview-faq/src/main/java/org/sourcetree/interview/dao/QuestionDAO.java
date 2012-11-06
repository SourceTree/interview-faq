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
}
