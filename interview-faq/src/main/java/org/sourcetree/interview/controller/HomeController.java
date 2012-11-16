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

import org.sourcetree.interview.dto.CategoryListDTO;
import org.sourcetree.interview.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Venkaiah Chowdary Koneru
 */
@Controller
public class HomeController extends BaseController
{

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/home")
	String viewHome(Model model)
	{

		model.addAttribute("parentCategories", getCategoryList());

		return "home";

	}

	/**
	 * 
	 * @param listProp
	 * @return
	 */
	private CategoryListDTO getCategoryList()
	{
		return new CategoryListDTO(categoryService.findAllParentCategories(),
				null);
	}
}
