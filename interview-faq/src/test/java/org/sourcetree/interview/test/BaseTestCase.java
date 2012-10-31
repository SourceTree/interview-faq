/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * BaseTestCase.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Oct 31, 2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.test;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Base class for unit tests with spring context.
 * <p>
 * Each method is run within a transaction which is rolled back when method is
 * over.
 * </p>
 * 
 * @author Venkaiah Chowdary Koneru
 * @see TransactionConfiguration
 * @see ContextConfiguration
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
@TransactionConfiguration(defaultRollback = true,
		transactionManager = "transactionManager")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class BaseTestCase
{
	@Autowired
	private BasicDataSource datasource;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * to be run before every {@linkplain Test} method.
	 * 
	 * @throws Exception
	 *             in case of errors
	 */
	@Before
	public void setupContext() throws Exception
	{
		UtilTestCase.setupContext(datasource);
	}

	/**
	 * to be run after every {@linkplain Test} method
	 * 
	 * @throws Exception
	 *             in case of errors
	 */
	@After
	public void afterTestMethod() throws Exception
	{
		UtilTestCase.afterTestMethod(datasource, redisTemplate);
	}
}
