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

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.sourcetree.interview.AppConstants;
import org.sourcetree.interview.dto.CategoryDTO;
import org.sourcetree.interview.dto.ListProp;
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
	private static final Map<String, String> CATEGORY_DTO_ALL = new HashMap<String, String>();
	static
	{
		CATEGORY_DTO_ALL.put("categoryName", "category.categoryName");
		CATEGORY_DTO_ALL.put("categoryDescription",
				"category.categoryDescription");
		CATEGORY_DTO_ALL.put("id", "category.id");
	}

	private static final Map<String, String> CATEGORY_DTO = new HashMap<String, String>();
	static
	{
		CATEGORY_DTO.put("categoryName", "category.categoryName");
		CATEGORY_DTO.put("id", "category.id");
		CATEGORY_DTO.put("categoryDescription", "category.categoryDescription");
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
		// TODO: CHALAM
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings(AppConstants.SUPPRESS_WARNINGS_UNCHECKED)
	@Override
	public List<CategoryDTO> getAllCategoryDTOs(ListProp listProp)
	{
		StringBuilder queryStr = new StringBuilder(" from ");
		queryStr.append(getEntityClass().getName()).append(" as category");
		queryStr.append(" where category.deleted=").append(Boolean.FALSE);

		return (List<CategoryDTO>) HibernateUtil.list(getSessionFactory(),
				listProp != null ? "select count(*)" : null, CATEGORY_DTO_ALL,
				queryStr.toString(), null, listProp, CategoryDTO.class);
	}

	@Override
	public CategoryDTO getCategoryDTOByName(String name)
	{
		StringBuilder queryStr = new StringBuilder("select ")
				.append(HibernateUtil.generateSelect(CATEGORY_DTO));
		queryStr.append(AppConstants.FROM);
		queryStr.append(getEntityClass().getName()).append(" as category");
		queryStr.append(" where ");
		queryStr.append(getDialect().getLowercaseFunction()).append("(")
				.append("category.categoryName").append(")=").append(":NAME");
		queryStr.append(" and category.deleted=").append(":DELETED");

		Query query = getSessionFactory().getCurrentSession().createQuery(
				queryStr.toString());

		query.setResultTransformer(Transformers.aliasToBean(CategoryDTO.class));

		query.setParameter("NAME", name.toLowerCase());
		query.setParameter("DELETED", Boolean.FALSE);

		return (CategoryDTO) query.uniqueResult();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings(AppConstants.SUPPRESS_WARNINGS_UNCHECKED)
	@Override
	public List<CategoryDTO> getAllParentCategorDTOs(ListProp listProp)
	{
		StringBuilder queryStr = new StringBuilder(AppConstants.FROM);
		queryStr.append(getEntityClass().getName()).append(" as category");
		queryStr.append(" where category.deleted=").append(Boolean.FALSE);
		queryStr.append(" and category.parentCategory is null");

		return (List<CategoryDTO>) HibernateUtil.list(getSessionFactory(),
				listProp != null ? "select count(*)" : null, CATEGORY_DTO_ALL,
				queryStr.toString(), null, listProp, CategoryDTO.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings(AppConstants.SUPPRESS_WARNINGS_UNCHECKED)
	public List<CategoryDTO> getChildCategorDTOsByParentName(ListProp listProp,
			String parentCategoryName)
	{
		StringBuilder queryStr = new StringBuilder(AppConstants.FROM);
		queryStr.append(getEntityClass().getName()).append(" as category");
		queryStr.append(" where category.deleted=").append(":DELETED");
		queryStr.append(" and ");
		queryStr.append(getDialect().getLowercaseFunction()).append("(")
				.append("category.parentCategory.categoryName").append(")=")
				.append(":NAME");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("DELETED", Boolean.FALSE);
		params.put("NAME", parentCategoryName.toLowerCase());

		return (List<CategoryDTO>) HibernateUtil.list(getSessionFactory(),
				listProp != null ? "select count(*)" : null, CATEGORY_DTO_ALL,
				queryStr.toString(), params, listProp, CategoryDTO.class);
	}
}
