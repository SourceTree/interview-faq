/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * AuthenticationController.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * 04-Nov-2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.controller;

import java.util.Map;

import org.sourcetree.interview.dto.LoginDTO;
import org.sourcetree.interview.enums.OutcomeStatus;
import org.sourcetree.interview.support.CoreUtil;
import org.sourcetree.interview.support.validation.ValidationUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Venkaiah Chowdary Koneru
 * 
 */
@Controller
@RequestMapping(value = "/admin")
public class AuthenticationController extends BaseController
{
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	String processLogin(LoginDTO loginDTO, Model model)
	{

		Map<String, String> errors = ValidationUtil.validate(loginDTO,
				validator, messageSource);

		if (!CoreUtil.isEmpty(errors))
		{
			loginDTO.setErrors(errors);
			loginDTO.setStatus(OutcomeStatus.FAILURE);

			model.addAttribute("login", loginDTO);

			return "login";
		}

		// TODO: validate login credentials with database

		return "adminHome";
	}
}
