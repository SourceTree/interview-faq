/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * BaseMvcTestCase.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Oct 31, 2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.test;

import org.apache.commons.dbcp.BasicDataSource;
import org.aspectj.weaver.ast.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.ContextMockMvcBuilder;
import org.springframework.test.web.server.setup.MockMvcBuilders;

/**
 * Base class for unit testing spring MVC annotated controllers with spring
 * context.
 * 
 * @author Venkaiah Chowdary Koneru
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml",
		"classpath:dispatcher-test-servlet.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BaseMvcTestCase
{
	protected MockMvc mockMvc;

	@Autowired
	protected Jaxb2Marshaller jaxb2Marshaller;

	// @Autowired
	// protected RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private BasicDataSource datasource;

	private ContextMockMvcBuilder ctx;

	/**
	 * Initial setup of the test case. initialises all the beans defined in
	 * application context and dispatcher-servlet context.
	 * 
	 * @throws Exception
	 *             in case of errors
	 */
	@Before
	public void setupContext() throws Exception
	{
		ctx = MockMvcBuilders.xmlConfigSetup(
				"classpath:applicationContext-test.xml",
				"classpath:dispatcher-test-servlet.xml");

		mockMvc = ctx.build();

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
		UtilTestCase.afterTestMethod(datasource, null);
	}
}
