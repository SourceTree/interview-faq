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

import javax.servlet.http.HttpSession;

import org.sourcetree.interview.dto.LoginDTO;
import org.sourcetree.interview.entity.User;
import org.sourcetree.interview.enums.OutcomeStatus;
import org.sourcetree.interview.service.UserService;
import org.sourcetree.interview.support.AuthorizationUtil;
import org.sourcetree.interview.support.CoreUtil;
import org.sourcetree.interview.support.validation.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Admin Login Controller
 * 
 * @author Venkaiah Chowdary Koneru
 */
@Controller
@RequestMapping(value = "/admin")
public class AuthenticationController extends BaseController
{
	@Value("#{appProps['inactive.timeout.minutes']}")
	private Integer inactiveTimeOutInMinutes;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	String loginForm()
	{
		return LOGIN_FORM_PAGE;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	String processLogin(LoginDTO loginDTO, Model model, HttpSession session)
	{

		Map<String, String> errors = ValidationUtil.validate(loginDTO,
				validator, messageSource);

		if (!CoreUtil.isEmpty(errors))
		{
			loginDTO.setErrors(errors);
			loginDTO.setStatus(OutcomeStatus.FAILURE);

			model.addAttribute("login", loginDTO);

			if (LOG.isDebugEnabled())
			{
				LOG.debug("Form Validation Failed: Redirecting to Login Page with Error messages");
			}

			return LOGIN_FORM_PAGE;
		}

		User user = userService.getUser(loginDTO.getEmail().toLowerCase());

		if (user != null
				&& user.getEmail().equalsIgnoreCase(loginDTO.getEmail())
				&& userService.isValidPassword(user.getEmail(),
						user.getPassword(), loginDTO.getPassword()))
		{
			AuthorizationUtil.createNewAuthorizedSession(user, session,
					inactiveTimeOutInMinutes);
		}
		else
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("Invalid Login: Redirecting to Login Page with Error message");
			}

			model.addAttribute("login", loginDTO);
			model.addAttribute("errorMsg",
					"The Username or password you have entered is incorrect.");

			return LOGIN_FORM_PAGE;
		}

		return "admin/console";
	}

	/**
	 * performs logout and redirect to home page.
	 * 
	 * @param httpSession
	 * @return home page
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpSession httpSession)
	{
		AuthorizationUtil.removeAuthorizedSession(httpSession);

		return "redirect:/";
	}
}
