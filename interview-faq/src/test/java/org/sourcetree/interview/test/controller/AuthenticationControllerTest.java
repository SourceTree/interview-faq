/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * AuthenticationControllerTest.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * 04-Nov-2012			Venkaiah Chowdary Koneru						Created
 * *************************************************************
 */
package org.sourcetree.interview.test.controller;

import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
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
	public void testProcessLogin() throws Exception
	{
		ResultActions resultActions = mockMvc.perform(post("/auth/login"));

		MvcResult result = resultActions.andReturn();
		ModelAndView modelAndView = result.getModelAndView();

		resultActions.andExpect(status().isOk())
				.andExpect(model().attributeExists("login"))
				.andExpect(model().attribute("login", hasProperty("errors")));

		Map<String, Object> modelMap = modelAndView.getModelMap();
		LoginDTO loginDTO = (LoginDTO) modelMap.get("login");

		assertNotNull(loginDTO);
		assertNotNull(loginDTO.getErrors());
		assertNotNull(loginDTO.getErrors().get("username"));
		assertNotNull(loginDTO.getErrors().get("password"));
		assertEquals("", loginDTO.getErrors().get("username"));
	}

}
