/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * UserDAOHibernate.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Nov 05, 2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.dao;

import org.sourcetree.interview.entity.User;
import org.springframework.stereotype.Repository;

/**
 * UserDAO interface implementation
 * 
 * @author Venkaiah Chowdary Koneru
 * 
 */
@Repository
public class UserDAOHibernate extends GenericDAOImpl<User, Long> implements
		UserDAO
{
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Class<User> getEntityClass()
	{
		return User.class;
	}
}
