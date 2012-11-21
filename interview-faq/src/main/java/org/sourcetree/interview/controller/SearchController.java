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

import org.apache.commons.lang.StringUtils;
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
import org.springframework.web.bind.annotation.ResponseBody;

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
	@RequestMapping(value = "/searchResult", method = RequestMethod.GET)
	public String searchPage(Model model,
			@RequestParam("searchValue") String searchValue,
			@RequestParam("categoryId") Long categoryId)
	{
		if (StringUtils.isNotEmpty(searchValue))
		{
			// Initialize ListProp with first page
			ListProp listProp = WebUtil.initListProp("1", getDefaultPageSize(),
					null, null);
			String[] searchKey = searchValue.split(" ");
			model.addAttribute("questions",
					getQuestionList(searchKey, categoryId, listProp));
			model.addAttribute("searchValue", searchValue);
			model.addAttribute("categoryId", categoryId);

		}

		return "search/searchResult";
	}

	/**
	 * Question Listing for ajax call.
	 * 
	 * @param page
	 *            - page number
	 * @param sortProperty
	 *            - sort property if any (can be null or empty)
	 * @param sortOrder
	 *            - sort order (ASC or DESC)
	 * @return category list page
	 */
	@RequestMapping(value = "/searchResult/{page}", method = RequestMethod.GET)
	@ResponseBody
	public QuestionListDTO questionListAjax(Model model, @PathVariable(
			value = "page") String page, @RequestParam(value = "sortProperty",
			required = false) String sortProperty, @RequestParam(
			value = "sortOrder", required = false) String sortOrder,
			@RequestParam("searchValue") String searchValue,
			@RequestParam("categoryId") Long categoryId)
	{
		// Initialize ListProp with requested page
		ListProp listProp = WebUtil.initListProp(page, getDefaultPageSize(),
				null, null);
		String[] searchKey = searchValue.split(" ");

		return getQuestionList(searchKey, categoryId, listProp);
	}

	/**
	 * 
	 * @param listProp
	 * @return
	 */
	private QuestionListDTO getQuestionList(String[] searchKey,
			Long categoryId, ListProp listProp)
	{
		// Retrieve questions
		List<QuestionDTO> questionDTOs = questionService
				.getQuestionSearchResult(searchKey, categoryId, listProp);
		return new QuestionListDTO(questionDTOs, listProp);
	}
}
