/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * HomeController.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * 03-Nov-2012			venky						Created
 * *************************************************************
 */
package org.sourcetree.interview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author venky
 * 
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController extends BaseController
{
	String viewHome()
	{
		return "home";
	}
}
