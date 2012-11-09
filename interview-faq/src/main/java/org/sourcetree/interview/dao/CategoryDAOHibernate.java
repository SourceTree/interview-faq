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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sourcetree.interview.AppConstants;
import org.sourcetree.interview.dto.CategoryDTO;
import org.sourcetree.interview.entity.Category;
import org.sourcetree.interview.support.HibernateUtil;
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
	private static final Map<String, String> CATEGORY_ALL = new HashMap<String, String>();
	static
	{
		CATEGORY_ALL.put("name", "category.name");
		CATEGORY_ALL.put("id", "category.id");
	}

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

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings(AppConstants.SUPPRESS_WARNINGS_UNCHECKED)
	@Override
	public List<CategoryDTO> getAllCategoryDTOs()
	{
		StringBuilder queryStr = new StringBuilder(" from ");
		queryStr.append(getEntityClass().getName()).append(" as category");
		queryStr.append(" where category.deleted=").append(Boolean.FALSE);

		return (List<CategoryDTO>) HibernateUtil.list(getSessionFactory(),
				null, CATEGORY_ALL, queryStr.toString(), null, null,
				CategoryDTO.class);
	}

}
