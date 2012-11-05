/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * RegistrationController.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Nov 5, 2012			Venkaiah Chowdary Koneru						Created
 * *************************************************************
 */
package org.sourcetree.interview.controller;

import java.util.Map;

import org.sourcetree.interview.dto.ResponseDTO;
import org.sourcetree.interview.dto.UserDTO;
import org.sourcetree.interview.enums.OutcomeStatus;
import org.sourcetree.interview.service.UserService;
import org.sourcetree.interview.support.validation.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Registration Controller
 * 
 * @author Venkaiah Chowdary Koneru
 */
@Controller
@RequestMapping("/register")
public class RegistrationController extends BaseController
{
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	String loginForm()
	{
		return "regForm";
	}

	/**
	 * to handle new partner request. this method will be invoked in the event
	 * of form submissions from the client which will contains the
	 * <b>application/x-www-form-urlencoded</b> as the content-type header
	 * 
	 * @param userDTO
	 *            user DTO object
	 * @return <code>{@linkplain userDTO}</code> - response will be Serialised
	 *         to either XML/JSON/text base on the request headers
	 */
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO processRegistration(@ModelAttribute UserDTO userDTO)
	{
		ResponseDTO response = new ResponseDTO();

		Map<String, String> errors = ValidationUtil.validate(userDTO,
				validator, messageSource);

		if ((!errors.isEmpty() && !errors.containsKey("email") || errors
				.isEmpty())
				&& userService.usernameExists(userDTO.getEmail().trim()))
		{
			errors.put("email",
					messageSource.getMessage("email.exists", null, null));
		}

		if (!errors.isEmpty())
		{
			response.setErrors(errors);
			response.setStatus(OutcomeStatus.FAILURE);
		}
		else
		{
			userService.create(userDTO);
		}

		return response;
	}
}
