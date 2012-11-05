/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * CategoryController.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Nov 6, 2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.controller;

import org.sourcetree.interview.enums.UserRoleEnum;
import org.sourcetree.interview.support.annotation.Restricted;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Venkaiah Chowdary Koneru
 * 
 */
@Controller
@RequestMapping(value = "/category")
public class CategoryController extends BaseController
{

	/**
	 * Sample controller to demo restricted annotation
	 * 
	 * @return
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	@Restricted(rolesAllowed = { UserRoleEnum.ADMIN },
			setSessionAttributes = false)
	public String faqForm()
	{
		return "faqForm";
	}
}
