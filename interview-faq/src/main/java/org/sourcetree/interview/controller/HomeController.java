/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * HomeController.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * 03-Nov-2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Venkaiah Chowdary Koneru
 */
@Controller
public class HomeController extends BaseController
{
	@RequestMapping(value = "/home")
	String viewHome()
	{
		return "home";
	}
}
