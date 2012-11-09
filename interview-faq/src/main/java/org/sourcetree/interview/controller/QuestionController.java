/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * QuestionController.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Nov 7, 2012	    Chalam Pavuluri				Created
 * *************************************************************
 */
package org.sourcetree.interview.controller;

import java.util.Map;

import org.sourcetree.interview.dto.QuestionDTO;
import org.sourcetree.interview.dto.ResponseDTO;
import org.sourcetree.interview.enums.OutcomeStatus;
import org.sourcetree.interview.service.CategoryService;
import org.sourcetree.interview.service.QuestionService;
import org.sourcetree.interview.support.validation.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Question Controller
 * 
 * @author Chalam Pavuluri
 */
@Controller
@RequestMapping("/question")
public class QuestionController extends BaseController
{
	@Autowired
	private QuestionService questionService;

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/addNew", method = RequestMethod.GET)
	String questionForm(Model model)
	{
		// List<CategoryDTO> categoryDtos = categoryService.findAllCategories();
		// model.addAttribute("categories", categoryDtos);

		return "question/questionForm";
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
	@RequestMapping(value = "/questionForm", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO processRegistration(
			@ModelAttribute QuestionDTO questionDTO)
	{
		ResponseDTO response = new ResponseDTO();

		Map<String, String> errors = ValidationUtil.validate(questionDTO,
				validator, messageSource);

		if (!errors.isEmpty())
		{
			response.setErrors(errors);
			response.setStatus(OutcomeStatus.FAILURE);
		}
		else
		{
			questionService.create(questionDTO);
		}

		return response;
	}
}
