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

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.sourcetree.interview.dto.ListProp;
import org.sourcetree.interview.dto.QuestionDTO;
import org.sourcetree.interview.dto.QuestionListDTO;
import org.sourcetree.interview.service.QuestionService;
import org.sourcetree.interview.support.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	 * @param page
	 * @param model
	 * @param searchValue
	 * @return return search result page
	 */
	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	public String searchPage(
			@PathVariable(value = "page") String page,
			@RequestParam(value = "searchValue", required = true) String searchValue,
			Model model)
	{
		if (!StringUtils.isBlank(searchValue))
		{
			// Initialize ListProp with first page
			ListProp listProp = WebUtil.initListProp(page,
					getDefaultPageSize(), null, null);

			String[] searchKey = searchValue.split(" ");

			model.addAttribute("questions",
					getQuestionList(searchKey, null, listProp));

			model.addAttribute("searchValue", searchValue);
		}

		return "search/searchResult";
	}

	/**
	 * common method to fetch questions list.
	 * 
	 * @param listProp
	 * @param categoryName
	 * @return question list dto with list of questions and pagination
	 *         information.
	 */
	private QuestionListDTO getQuestionList(String[] searchKey,
			String categoryName, ListProp listProp)
	{
		// Retrieve questions
		List<QuestionDTO> questionDTOs = questionService
				.getQuestionSearchResult(searchKey, categoryName, listProp);

		return new QuestionListDTO(questionDTOs, listProp);
	}
}
