/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * CategoryDAOHibernate.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Nov 06, 2012		Chalam Pavuluri				Created
 * *************************************************************
 */
package org.sourcetree.interview.dao;

import java.util.List;

import org.sourcetree.interview.entity.Category;
import org.springframework.stereotype.Repository;

/**
 * CategoryDAO interface implementation
 * 
 * @author Chalam Pavuluri
 * 
 */
@Repository
public class CategoryDAOHibernate extends GenericDAOImpl<Category, Long>
		implements CategoryDAO
{
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Class<Category> getEntityClass()
	{
		return Category.class;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Category> getCategoriesByQuestionId(Long questionId)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
