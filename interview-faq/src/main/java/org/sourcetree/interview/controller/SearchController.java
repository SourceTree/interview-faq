/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * SearchController.java
 * Modification History
 * *************************************************************
 * Date					Author						Comment
 * Nov 18, 2012			SRINU SOMINENI						Created
 * *************************************************************
 */
package org.sourcetree.interview.controller;

import org.apache.commons.lang.StringUtils;
import org.sourcetree.interview.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author SRINU SOMINENI
 * 
 */
@Controller
@RequestMapping(value = "/search")
public class SearchController extends BaseController
{
	@Autowired
	private QuestionService questionService;

	/**
	 * @param model
	 * @param searchValue
	 * @param categoryId
	 * @return search result page
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String searchPage(Model model,
			@RequestParam("searchValue") String searchValue,
			@RequestParam("categoryId") Long categoryId)
	{
		if (StringUtils.isNotEmpty(searchValue))
		{
			String[] searchKey = searchValue.split(" ");
			model.addAttribute("questions", questionService
					.getQuestionSearchResult(searchKey, categoryId));

		}

		return "search/searchResult";
	}
}
