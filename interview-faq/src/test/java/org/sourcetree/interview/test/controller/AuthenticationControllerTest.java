/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * AuthenticationControllerTest.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * 04-Nov-2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.test.controller;

import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.Test;
import org.sourcetree.interview.dto.LoginDTO;
import org.sourcetree.interview.test.BaseMvcTestCase;
import org.springframework.test.web.server.MvcResult;
import org.springframework.test.web.server.ResultActions;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Venkaiah Chowdary Koneru
 * 
 */
public class AuthenticationControllerTest extends BaseMvcTestCase
{

	/**
	 * Test method for
	 * {@link org.sourcetree.interview.controller.AuthenticationController#processLogin(org.sourcetree.interview.dto.LoginDTO, org.springframework.ui.Model)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public void testProcessLogin_1() throws Exception
	{
		ResultActions resultActions = mockMvc.perform(post("/admin/login"));

		MvcResult result = resultActions.andReturn();
		ModelAndView modelAndView = result.getModelAndView();

		resultActions.andExpect(status().isOk())
				.andExpect(model().attributeExists("login"))
				.andExpect(model().attribute("login", hasProperty("errors")));

		Map<String, Object> modelMap = modelAndView.getModelMap();
		LoginDTO loginDTO = (LoginDTO) modelMap.get("login");

		assertEquals("login", modelAndView.getViewName());
		assertNotNull(loginDTO);
		assertNotNull(loginDTO.getErrors());
		assertNotNull(loginDTO.getErrors().get("email"));
		assertNotNull(loginDTO.getErrors().get("password"));
		assertEquals("Email Required!", loginDTO.getErrors().get("email"));
	}

	/**
	 * Test method for
	 * {@link org.sourcetree.interview.controller.AuthenticationController#processLogin(org.sourcetree.interview.dto.LoginDTO, org.springframework.ui.Model)}
	 * . testing successful login strategy.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testProcessLogin_2() throws Exception
	{
		ResultActions resultActions = mockMvc.perform(post("/admin/login")
				.param("email", "admin@faqmasters.com").param("password",
						"password"));

		resultActions.andExpect(status().isOk()).andExpect(
				forwardedUrl("/WEB-INF/page/admin/console.jsp"));
	}

	/**
	 * Test method for
	 * {@link org.sourcetree.interview.controller.AuthenticationController#loginForm()}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLoginForm_1() throws Exception
	{
		mockMvc.perform(get("/admin/login")).andExpect(status().isOk())
				.andExpect(forwardedUrl("/WEB-INF/page/login.jsp"));
	}
}
