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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.sourcetree.interview.dto.CategoryDTO;
import org.sourcetree.interview.dto.QuestionDTO;
import org.sourcetree.interview.dto.ResponseDTO;
import org.sourcetree.interview.enums.OutcomeStatus;
import org.sourcetree.interview.enums.UserRoleEnum;
import org.sourcetree.interview.service.CategoryService;
import org.sourcetree.interview.service.QuestionService;
import org.sourcetree.interview.support.annotation.Restricted;
import org.sourcetree.interview.support.validation.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	@Restricted(rolesAllowed = { UserRoleEnum.ADMIN },
			setSessionAttributes = false)
	String questionForm(Model model)
	{
		model.addAttribute("categories", categoryService.findAllCategories());
		return "question/questionForm";
	}

	/**
	 * to handle new partner request. this method will be invoked in the event
	 * of form submissions from the client which will contains the
	 * <b>application/x-www-form-urlencoded</b> as the content-type header
	 * 
	 * @param questionDTO
	 *            question DTO object
	 * @return <code>{@linkplain userDTO}</code> - response will be Serialised
	 *         to either XML/JSON/text base on the request headers
	 */
	@RequestMapping(value = "/addNew", method = RequestMethod.POST)
	@Restricted(rolesAllowed = { UserRoleEnum.ADMIN },
			setSessionAttributes = false)
	@ResponseBody
	public ResponseDTO processQuestionAddNew(
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

	/**
	 * to navigate to question edit form.
	 * 
	 * @param questionId
	 * @param model
	 * @return question edit page
	 */
	@RequestMapping(value = "/edit/{questionId}", method = RequestMethod.GET)
	@Restricted(rolesAllowed = { UserRoleEnum.ADMIN },
			setSessionAttributes = false)
	public String questionEditForm(@PathVariable Long questionId, Model model)
	{
		model.addAttribute("question",
				questionService.getQuestionDTOById(questionId));
		model.addAttribute("categories", categoryService.findAllCategories());

		return "question/questionEdit";
	}

	/**
	 * to handle question edit form request.
	 * 
	 * @param questionDTO
	 *            Question DTO object
	 * @return <code>{@linkplain QuestionDTO}</code> - response will be
	 *         Serialised to either XML/JSON/text base on the request headers
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Restricted(rolesAllowed = { UserRoleEnum.ADMIN },
			setSessionAttributes = false)
	@ResponseBody
	public ResponseDTO updateQuestion(@ModelAttribute QuestionDTO questionDTO)
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
			questionService.update(questionDTO);
		}

		return response;
	}

	/**
	 * To view the question details.
	 * 
	 * @param questionId
	 * @param model
	 * @return question details page
	 */
	@RequestMapping(value = "/view/{questionId}", method = RequestMethod.GET)
	public String questionDetails(@PathVariable Long questionId, Model model)
	{
		model.addAttribute("question",
				questionService.getQuestionDTOById(questionId));

		return "question/questionDetails";
	}

	/**
	 * To export the questions to excel.
	 * 
	 * @param categoryName
	 * @param model
	 * @return excel with all the questions
	 */
	@RequestMapping(value = "/exportToExcel/{categoryName}",
			method = RequestMethod.GET)
	public ModelAndView exportQuestionsToExcel(
			@PathVariable String categoryName, Model model)
	{

		List<QuestionDTO> questions = new ArrayList<QuestionDTO>();
		questions = questionService.getQuestionsByCategoryName(categoryName);
		return new ModelAndView("qestionsExcel", "questions", questions);
	}

	/**
	 * Custom property editor for the Category DTO
	 * 
	 * @param request
	 *            http servlet request
	 * @param binder
	 *            spring's servlet request binder
	 */
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder)
	{
		binder.registerCustomEditor(List.class, "categoryDTOs",
				new CustomCollectionEditor(List.class)
				{
					@Override
					protected Object convertElement(Object element)
					{
						if (element != null)
						{
							CategoryDTO categoryDTO = new CategoryDTO();
							categoryDTO.setId(Long.valueOf((String) element));
							return categoryDTO;
						}
						return null;
					}
				});
	}
}
