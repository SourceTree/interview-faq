/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * GenericDAOImpl.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Oct 30, 2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.dao;

import static org.sourcetree.interview.AppConstants.SUPPRESS_WARNINGS_UNCHECKED;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Implementation of <code>GenericDAO</code> using Hibernate. The SessionFactory
 * property is annotated for automatic injection.
 * 
 * @param <T>
 *            The type of the entity object for which this instance is to be
 *            used.
 * @param <ID>
 *            The type of the id of the entity object for which this instance is
 *            to be used.
 * 
 * @author Venkaiah Chowdary Koneru
 */
public abstract class GenericDAOImpl<T, ID extends Serializable> implements
		GenericDAO<T, ID>
{
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings(SUPPRESS_WARNINGS_UNCHECKED)
	@Override
	public ID save(T newInstance)
	{
		return (ID) sessionFactory.getCurrentSession().save(newInstance);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(T transientObject)
	{
		sessionFactory.getCurrentSession().update(transientObject);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(T persistentObject)
	{
		sessionFactory.getCurrentSession().delete(persistentObject);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings(SUPPRESS_WARNINGS_UNCHECKED)
	@Override
	public T find(ID id)
	{
		if (id != null)
		{
			return (T) sessionFactory.getCurrentSession().get(getEntityClass(),
					id);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T findByParameter(String key, Object value)
	{
		return findByParameter(key, value, false);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings(SUPPRESS_WARNINGS_UNCHECKED)
	@Override
	public T findByParameter(String key, Object value, boolean retreiveDeleted)
	{
		if (!StringUtils.isBlank(key) && value != null)
		{
			StringBuilder queryStr = new StringBuilder("from ");
			queryStr.append(getEntityClass().getName()).append(" as _etc_");
			queryStr.append(" where _etc_.").append(key).append("= :VAL");

			if (!retreiveDeleted)
			{
				queryStr.append(" and _etc_.deleted= :DELETED");
			}

			Query query = sessionFactory.getCurrentSession().createQuery(
					queryStr.toString());
			query.setParameter("VAL", value);

			if (!retreiveDeleted)
			{
				query.setParameter("DELETED", Boolean.FALSE);
			}

			return (T) query.uniqueResult();
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteById(ID id)
	{
		if (id != null)
		{
			T entity = find(id);
			if (entity != null)
			{
				sessionFactory.getCurrentSession().delete(entity);
				return true;
			}
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings(SUPPRESS_WARNINGS_UNCHECKED)
	@Override
	public List<T> findAll()
	{
		return sessionFactory.getCurrentSession()
				.createCriteria(getEntityClass())
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean exists(ID id)
	{
		if (id != null)
		{
			T entity = find(id);
			if (entity != null)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existsByParameter(String key, String value)
	{
		return existsByParameter(key, value, false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existsByParameter(String key, String value,
			boolean excludeDeleted)
	{
		if (!StringUtils.isBlank(key) && !StringUtils.isBlank(value))
		{
			T entity = findByParameter(key, value, !excludeDeleted);

			if (entity != null)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	/**
	 * Get the specified type from the DAO
	 * 
	 * @return type of the entity class
	 */
	protected abstract Class<T> getEntityClass();
}
