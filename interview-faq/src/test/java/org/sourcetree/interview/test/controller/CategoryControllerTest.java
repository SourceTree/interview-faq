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
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Test;
import org.sourcetree.interview.AppConstants;
import org.sourcetree.interview.dto.ResponseDTO;
import org.sourcetree.interview.enums.OutcomeStatus;
import org.sourcetree.interview.enums.UserRoleEnum;
import org.sourcetree.interview.support.SessionAttributes;
import org.sourcetree.interview.test.BaseMvcTestCase;
import org.springframework.http.MediaType;
import org.springframework.test.web.server.ResultActions;

/**
 * @author Venkaiah Chowdary Koneru
 * 
 */
public class CategoryControllerTest extends BaseMvcTestCase
{

	/**
	 * Test method for
	 * {@link org.sourcetree.interview.controller.CategoryController#categoryForm()}
	 * .
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
	 * {@link org.sourcetree.interview.controller.CategoryController#categoryForm()}
	 * .
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

	/**
	 * Test method for
	 * {@link org.sourcetree.interview.controller.CategoryController#processCategory()}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFaqForm_3() throws Exception
	{
		ResultActions ra = mockMvc.perform(post("/category/new")).andExpect(
				status().isUnauthorized());
	}

	/**
	 * Test method for
	 * {@link org.sourcetree.interview.controller.CategoryController#processCategory()}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFaqForm_4() throws Exception
	{
		ResultActions ra = mockMvc.perform(post("/category/new")
				.sessionAttr(
						AppConstants.SESS_VARS,
						new SessionAttributes("", 0L,
								UserRoleEnum.SECONDARY_ADMIN))
				.param("categoryName", "").accept(MediaType.APPLICATION_JSON));
		ra.andExpect(status().isUnauthorized());

	}

	/**
	 * Test method for
	 * {@link org.sourcetree.interview.controller.CategoryController#processCategory()}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFaqForm_5() throws Exception
	{
		ResultActions ra = mockMvc.perform(post("/category/new")
				.sessionAttr(AppConstants.SESS_VARS,
						new SessionAttributes("", 0L, UserRoleEnum.ADMIN))
				.param("categoryName", "").accept(MediaType.APPLICATION_JSON));
		ra.andExpect(status().isOk()).andExpect(
				content().mimeType("application/json;charset=UTF-8"));
		ResponseDTO res = jaxbJacksonObjectMapper.readValue(ra.andReturn()
				.getResponse().getContentAsByteArray(), ResponseDTO.class);

		Assert.assertNotNull(res.getErrors());
		Assert.assertEquals(1, res.getErrors().size());

	}

	/**
	 * Test method for
	 * {@link org.sourcetree.interview.controller.CategoryController#processCategory()}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFaqForm_6() throws Exception
	{
		ResultActions ra = mockMvc.perform(post("/category/new")
				.sessionAttr(AppConstants.SESS_VARS,
						new SessionAttributes("", 0L, UserRoleEnum.ADMIN))
				.param("categoryName", "JAVA")
				.accept(MediaType.APPLICATION_JSON));
		ra.andExpect(status().isOk()).andExpect(
				content().mimeType("application/json;charset=UTF-8"));
		ResponseDTO res = jaxbJacksonObjectMapper.readValue(ra.andReturn()
				.getResponse().getContentAsByteArray(), ResponseDTO.class);

		Assert.assertNull(res.getErrors());
		Assert.assertEquals(OutcomeStatus.SUCCESS, res.getStatus());

	}

}
