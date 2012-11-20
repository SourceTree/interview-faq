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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.sourcetree.interview.AppConstants;
import org.sourcetree.interview.dto.ListProp;
import org.sourcetree.interview.dto.QuestionDTO;
import org.sourcetree.interview.entity.Question;
import org.sourcetree.interview.support.HibernateUtil;
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

	private static final Map<String, String> QUESTION_DTO = new HashMap<String, String>();
	static
	{
		QUESTION_DTO.put("question", "question.question");
		QUESTION_DTO.put("id", "question.id");
		QUESTION_DTO.put("answer", "question.answer");
	}

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

	@Override
	public QuestionDTO getQuestionDTOById(Long Id)
	{
		StringBuilder queryStr = new StringBuilder("select ")
				.append(HibernateUtil.generateSelect(QUESTION_DTO));
		queryStr.append(AppConstants.FROM);
		queryStr.append(getEntityClass().getName()).append(" as question");
		queryStr.append(" where ");
		queryStr.append("question.id").append("=").append(":ID");
		queryStr.append(" and question.deleted=").append(":DELETED");

		Query query = getSessionFactory().getCurrentSession().createQuery(
				queryStr.toString());

		query.setResultTransformer(Transformers.aliasToBean(QuestionDTO.class));

		query.setParameter("ID", Id);
		query.setParameter("DELETED", Boolean.FALSE);

		return (QuestionDTO) query.uniqueResult();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings(AppConstants.SUPPRESS_WARNINGS_UNCHECKED)
	@Override
	public List<Question> getQuestionsByCategoryName(String categoryName,
			ListProp listProp)
	{
		StringBuilder queryStr = new StringBuilder(AppConstants.FROM);
		queryStr.append(getEntityClass().getName()).append(" as question");
		queryStr.append(" join question.categories as categories");
		queryStr.append(" where ");
		queryStr.append(getDialect().getLowercaseFunction()).append("(");
		queryStr.append("categories.categoryName)=").append(":NAME");
		queryStr.append(" and question.deleted=").append(":DELETED");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("NAME", categoryName.toLowerCase());
		params.put("DELETED", Boolean.FALSE);

		return (List<Question>) HibernateUtil.list(getSessionFactory(),
				"select count(question.id)", null, queryStr.toString(), params,
				listProp, null);
	}

	@SuppressWarnings(AppConstants.SUPPRESS_WARNINGS_UNCHECKED)
	@Override
	public List<Question> searchQuestions(String[] searchKey, Long categoryId)
	{
		StringBuilder queryStr = new StringBuilder("from ");
		queryStr.append(Question.class.getName()).append(" as _etc_");
		queryStr.append(" where ");

		if (searchKey != null && searchKey.length > 0 && categoryId != null)
		{
			int i = 0;

			for (String search : searchKey)
			{
				if (i == 0)
				{
					queryStr.append(getDialect().getLowercaseFunction())
							.append("(_etc_.").append("question)")
							.append(" like :VAL1" + i).append(" or ")
							.append(getDialect().getLowercaseFunction())
							.append("(_etc_.").append("answer)")
							.append(" like :VAL2" + i);

				}
				else
				{
					queryStr.append(" or ")
							.append(getDialect().getLowercaseFunction())
							.append("(_etc_.").append("question)")
							.append(" like :VAL1" + i).append(" or ")
							.append(getDialect().getLowercaseFunction())
							.append("(_etc_.").append("answer)")
							.append(" like :VAL2" + i);
				}
				i++;
			}
			queryStr.append(" and _etc_.").append("category_id")
					.append(" = :CATEGORY_ID");

		}
		else if (searchKey != null && searchKey.length > 0)
		{
			int i = 0;
			for (String search : searchKey)
			{
				if (i == 0)
				{
					queryStr.append(getDialect().getLowercaseFunction())
							.append("(_etc_.").append("question)")
							.append(" like :VAL1" + i).append(" or ")
							.append(getDialect().getLowercaseFunction())
							.append("(_etc_.").append("answer)")
							.append(" like :VAL2" + i);

				}
				else
				{
					queryStr.append(" or ")
							.append(getDialect().getLowercaseFunction())
							.append("(_etc_.").append("question)")
							.append(" like :VAL1" + i).append(" or ")
							.append(getDialect().getLowercaseFunction())
							.append("(_etc_.").append("answer)")
							.append(" like :VAL2" + i);
				}
				i++;
			}
		}

		queryStr.append(" and _etc_.deleted= :DELETED");

		Query query = getSessionFactory().getCurrentSession().createQuery(
				queryStr.toString());

		if (searchKey != null && searchKey.length > 0 && categoryId != null)
		{
			int i = 0;
			for (String search : searchKey)
			{
				query.setParameter("VAL1" + i, search.toLowerCase());
				query.setParameter("VAL2" + i, search.toLowerCase());
				i++;
			}
			query.setParameter("CATEGORY_ID", categoryId);
		}
		else if (searchKey != null && searchKey.length > 0)
		{
			int i = 0;
			for (String search : searchKey)
			{
				query.setParameter("VAL1" + i, search.toLowerCase());
				query.setParameter("VAL2" + i, search.toLowerCase());
				i++;
			}
		}

		return query.list();
	}
}
