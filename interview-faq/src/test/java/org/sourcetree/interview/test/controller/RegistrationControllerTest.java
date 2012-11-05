/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * RegistrationControllerTest.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Nov 5, 2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.test.controller;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;
import org.sourcetree.interview.dto.ResponseDTO;
import org.sourcetree.interview.dto.UserDTO;
import org.sourcetree.interview.enums.OutcomeStatus;
import org.sourcetree.interview.test.BaseMvcTestCase;
import org.springframework.http.MediaType;
import org.springframework.test.web.server.ResultActions;

/**
 * @author Venkaiah Chowdary Koneru
 * 
 */
public class RegistrationControllerTest extends BaseMvcTestCase
{
	/**
	 * Test method for
	 * {@link org.sourcetree.interview.controller.RegistrationController#processRegistration(UserDTO userDTO)}
	 * .
	 * 
	 * @throws Exception
	 *             in case of errors
	 */
	@Test
	public void testXMLProcessNewPartnerFormRequest_1() throws Exception
	{
		// check for mandatory
		ResultActions ra = mockMvc
				.perform(post("/register/new").param("name", "")
						.param("email", "").param("password", "")
						.param("confirmPassword", "")
						.accept(MediaType.APPLICATION_XML));

		ra.andExpect(status().isOk()).andExpect(
				content().mimeType(MediaType.APPLICATION_XML));

		StringReader reader = new StringReader(ra.andReturn().getResponse()
				.getContentAsString());

		ResponseDTO res = (ResponseDTO) jaxb2Marshaller
				.unmarshal(new javax.xml.transform.stream.StreamSource(reader));

		Assert.assertNotNull(res.getErrors());
		Assert.assertEquals(4, res.getErrors().size());
	}

	/**
	 * Test method for
	 * {@link org.sourcetree.interview.controller.RegistrationController#processRegistration(UserDTO userDTO)}
	 * .
	 * 
	 * @throws Exception
	 *             in case of errors
	 */
	@Test
	public void testXMLProcessNewPartnerFormRequest_2() throws Exception
	{
		// check for mandatory
		ResultActions ra = mockMvc.perform(post("/register/new")
				.param("name", "Venky").param("email", "venky@gmail.com")
				.param("password", "password")
				.param("confirmPassword", "password")
				.accept(MediaType.APPLICATION_XML));

		ra.andExpect(status().isOk()).andExpect(
				content().mimeType(MediaType.APPLICATION_XML));

		StringReader reader = new StringReader(ra.andReturn().getResponse()
				.getContentAsString());

		ResponseDTO res = (ResponseDTO) jaxb2Marshaller
				.unmarshal(new javax.xml.transform.stream.StreamSource(reader));

		Assert.assertNull(res.getErrors());
		Assert.assertEquals(OutcomeStatus.SUCCESS, res.getStatus());
	}

	/**
	 * Test method for
	 * {@link org.sourcetree.interview.controller.RegistrationController#processRegistration(UserDTO userDTO)}
	 * .
	 * 
	 * @throws Exception
	 *             in case of errors
	 */
	@Test
	public void testJSONProcessNewPartnerFormRequest_1() throws Exception
	{
		// check for mandatory
		ResultActions ra = mockMvc.perform(post("/register/new")
				.param("name", "").param("email", "").param("password", "")
				.param("confirmPassword", "")
				.accept(MediaType.APPLICATION_JSON));

		ra.andExpect(status().isOk()).andExpect(
				content().mimeType("application/json;charset=UTF-8"));

		ResponseDTO res = jaxbJacksonObjectMapper.readValue(ra.andReturn()
				.getResponse().getContentAsByteArray(), ResponseDTO.class);

		Assert.assertNotNull(res.getErrors());
		Assert.assertEquals(4, res.getErrors().size());
	}

	/**
	 * Test method for
	 * {@link org.sourcetree.interview.controller.RegistrationController#processRegistration(UserDTO userDTO)}
	 * .
	 * 
	 * @throws Exception
	 *             in case of errors
	 */
	@Test
	public void testJSONProcessNewPartnerFormRequest_2() throws Exception
	{
		// check for mandatory
		ResultActions ra = mockMvc.perform(post("/register/new")
				.param("name", "Venky").param("email", "venky@gmail.com")
				.param("password", "password")
				.param("confirmPassword", "password")
				.accept(MediaType.APPLICATION_JSON));

		ra.andExpect(status().isOk()).andExpect(
				content().mimeType("application/json;charset=UTF-8"));

		ResponseDTO res = jaxbJacksonObjectMapper.readValue(ra.andReturn()
				.getResponse().getContentAsByteArray(), ResponseDTO.class);

		Assert.assertNull(res.getErrors());
		Assert.assertEquals(OutcomeStatus.SUCCESS, res.getStatus());
	}

	/**
	 * Test method for
	 * {@link org.sourcetree.interview.controller.RegistrationFormController#regForm()}
	 * .
	 * 
	 * @throws Exception
	 *             in case of errors
	 */
	@Test
	public void testRegisterForm_1() throws Exception
	{
		mockMvc.perform(get("/register/new")).andExpect(status().isOk())
				.andExpect(forwardedUrl("/WEB-INF/page/regForm.jsp"));
	}
}
