/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * CategoryControllerTest.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Nov 6, 2012			Venkaiah Chowdary Koneru						Created
 * *************************************************************
 */
package org.sourcetree.interview.test.controller;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.sourcetree.interview.AppConstants;
import org.sourcetree.interview.enums.UserRoleEnum;
import org.sourcetree.interview.support.SessionAttributes;
import org.sourcetree.interview.test.BaseMvcTestCase;

/**
 * @author Venkaiah Chowdary Koneru
 * 
 */
public class CategoryControllerTest extends BaseMvcTestCase
{

	/**
	 * Test method for
	 * {@link org.sourcetree.interview.controller.CategoryController#faqForm()}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFaqForm_1() throws Exception
	{
		mockMvc.perform(get("/category/new")).andExpect(
				status().isUnauthorized());
	}

	/**
	 * Test method for
	 * {@link org.sourcetree.interview.controller.CategoryController#faqForm()}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFaqForm_2() throws Exception
	{
		mockMvc.perform(
				get("/category/new").sessionAttr(AppConstants.SESS_VARS,
						new SessionAttributes("", 0L, UserRoleEnum.ADMIN)))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/WEB-INF/page/faqForm.jsp"));
	}
}
