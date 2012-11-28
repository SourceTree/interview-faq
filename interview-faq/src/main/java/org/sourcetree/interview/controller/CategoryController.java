package org.sourcetree.interview.controller;

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

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.sourcetree.interview.dto.CategoryDTO;
import org.sourcetree.interview.dto.CategoryListDTO;
import org.sourcetree.interview.dto.ListProp;
import org.sourcetree.interview.dto.QuestionDTO;
import org.sourcetree.interview.dto.QuestionListDTO;
import org.sourcetree.interview.dto.ResponseDTO;
import org.sourcetree.interview.enums.OutcomeStatus;
import org.sourcetree.interview.enums.UserRoleEnum;
import org.sourcetree.interview.service.CategoryService;
import org.sourcetree.interview.service.QuestionService;
import org.sourcetree.interview.support.WebUtil;
import org.sourcetree.interview.support.annotation.Restricted;
import org.sourcetree.interview.support.validation.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Category Controller. contains entry point for add, update, view categories.
 * 
 * @author Venkaiah Chowdary Koneru
 */
@Controller
@RequestMapping(value = "/category")
public class CategoryController extends BaseController
{
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private QuestionService questionService;

	/**
	 * Category Form
	 * 
	 * @param model
	 * 
	 * @return Category Form
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	@Restricted(rolesAllowed = { UserRoleEnum.ADMIN },
			setSessionAttributes = false)
	public String categoryForm(Model model)
	{
		model.addAttribute("parentCategories",
				categoryService.findAllParentCategories());

		return "category/categoryForm";
	}

	/**
	 * to handle new category request. this method will be invoked in the event
	 * of form submissions from the client which will contains the
	 * <b>application/x-www-form-urlencoded</b> as the content-type header
	 * 
	 * @param categoryDTO
	 *            category DTO object
	 * @return <code>{@linkplain categoryDTO}</code> - response will be
	 *         Serialised to either XML/JSON/text base on the request headers
	 */
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@Restricted(rolesAllowed = { UserRoleEnum.ADMIN },
			setSessionAttributes = false)
	@ResponseBody
	public ResponseDTO processCategory(@ModelAttribute CategoryDTO categoryDTO)
	{
		ResponseDTO response = new ResponseDTO();

		Map<String, String> errors = ValidationUtil.validate(categoryDTO,
				validator, messageSource);

		if ((!errors.isEmpty() && !errors.containsKey("categoryName") || errors
				.isEmpty())
				&& categoryService.isCategoryExists(categoryDTO
						.getCategoryName().trim()))
		{
			errors.put("categoryName",
					messageSource.getMessage("categoryName.exists", null, null));
		}

		if (!errors.isEmpty())
		{
			response.setErrors(errors);
			response.setStatus(OutcomeStatus.FAILURE);
		}
		else
		{
			categoryService.create(categoryDTO);
		}

		return response;
	}

	/**
	 * @param model
	 * @param categoryId
	 * @return category edit page
	 */
	@RequestMapping(value = "/edit/{categoryId}", method = RequestMethod.GET)
	@Restricted(rolesAllowed = { UserRoleEnum.ADMIN },
			setSessionAttributes = false)
	public String editPage(Model model,
			@PathVariable(value = "categoryId") Long categoryId)
	{
		model.addAttribute("category",
				categoryService.getCategoryDTOById(categoryId));

		model.addAttribute("parentCategories",
				categoryService.findAllParentCategories());

		return "category/categoryEdit";
	}

	/**
	 * to handle edit category form update request.
	 * 
	 * @param categoryDTO
	 *            category DTO object
	 * @return <code>{@linkplain categoryDTO}</code> - response will be
	 *         Serialised to either XML/JSON/text base on the request headers
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Restricted(rolesAllowed = { UserRoleEnum.ADMIN },
			setSessionAttributes = false)
	@ResponseBody
	public ResponseDTO updateCategory(@ModelAttribute CategoryDTO categoryDTO)
	{
		ResponseDTO response = new ResponseDTO();

		Map<String, String> errors = ValidationUtil.validate(categoryDTO,
				validator, messageSource);
		if (!errors.isEmpty())
		{
			response.setErrors(errors);
			response.setStatus(OutcomeStatus.FAILURE);
		}
		else
		{
			categoryService.update(categoryDTO, categoryDTO.getId());
		}

		return response;
	}

	/**
	 * To navigate to category details page. the resulting view contains the
	 * information about sub-categories (if any) of requested category and list
	 * of questions.
	 * 
	 * @param categoryName
	 * @param model
	 * @return category page
	 */
	@RequestMapping(value = "/{categoryName}", method = RequestMethod.GET)
	public String categoryDetailsForm(
			@PathVariable(value = "categoryName") String categoryName,
			Model model)
	{
		// Initialize ListProp with first page
		ListProp listProp = WebUtil.initListProp("1", getDefaultPageSize(),
				null, null);

		setCategoryListAttributes(model, null, listProp, categoryName);

		return "category/categoryDetails";

	}

	/**
	 * 
	 * @param categoryName
	 * @param page
	 * @param searchValue
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{categoryName}/{page}",
			method = RequestMethod.GET)
	public String subCategoryQuestionsList(
			@PathVariable(value = "categoryName") String categoryName,
			@PathVariable(value = "page") String page,
			@RequestParam(value = "searchValue", required = false) String searchValue,
			Model model)
	{
		// Initialize ListProp with first page
		ListProp listProp = WebUtil.initListProp(page, getDefaultPageSize(),
				null, null);

		setCategoryListAttributes(model, searchValue, listProp, categoryName);

		return "category/categoryDetails";
	}

	/**
	 * Category Listing page. for first initial page, fetch the first page (10
	 * records) to display. this will reduce the server round trip.
	 * 
	 * @param model
	 * @return category list page
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@Restricted(rolesAllowed = { UserRoleEnum.ADMIN },
			setSessionAttributes = false)
	public String categoryList(Model model)
	{
		// Initialize ListProp with first page
		ListProp listProp = WebUtil.initListProp("1", getDefaultPageSize(),
				null, null);

		model.addAttribute("categories", getAllCategoryList(listProp));

		return "category/categoryList";
	}

	/**
	 * Category Listing for ajax call.
	 * 
	 * @param page
	 *            - page number
	 * @param sortProperty
	 *            - sort property if any (can be null or empty)
	 * @param sortOrder
	 *            - sort order (ASC or DESC)
	 * @return category list page
	 */
	@RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
	@Restricted(rolesAllowed = { UserRoleEnum.ADMIN },
			setSessionAttributes = false)
	@ResponseBody
	public CategoryListDTO categoryListAjax(
			@PathVariable(value = "page") String page,
			@RequestParam(value = "sortProperty", required = false) String sortProperty,
			@RequestParam(value = "sortOrder", required = false) String sortOrder)
	{
		// Initialize ListProp with requested page
		ListProp listProp = WebUtil.initListProp(page, getDefaultPageSize(),
				null, null);

		return getAllCategoryList(listProp);
	}

	/**
	 * 
	 * @param model
	 * @param searchParam
	 *            can be null / empty
	 * @param listProp
	 * @param categories
	 */
	private void setCategoryListAttributes(Model model, String searchParam,
			ListProp listProp, String categoryName)
	{
		List<QuestionDTO> questionDTOs = null;
		List<CategoryDTO> childCategories = null;
		String[] searchQuery = null;

		CategoryDTO categoryDTO = categoryService
				.getCategoryDTOByName(categoryName);

		model.addAttribute("categoryDTO", categoryDTO);

		if (categoryDTO.getParentCategoryDTO() != null)
		{
			childCategories = categoryService
					.findAllChildCategorDTOsByParentName(categoryDTO
							.getParentCategoryDTO().getCategoryName());

			model.addAttribute("parentCategoryName", categoryDTO
					.getParentCategoryDTO().getCategoryName());
		}
		else
		{
			childCategories = categoryService
					.findAllChildCategorDTOsByParentName(categoryDTO
							.getCategoryName());

			model.addAttribute("parentCategoryName",
					categoryDTO.getCategoryName());
		}

		model.addAttribute("childCategories", childCategories);

		if (!StringUtils.isBlank(searchParam))
		{
			model.addAttribute("searchValue", searchParam);
			searchQuery = searchParam.split(" ");
		}

		questionDTOs = questionService.getQuestionSearchResult(searchQuery,
				categoryDTO.getCategoryName(), listProp);

		model.addAttribute("questionsList", new QuestionListDTO(questionDTOs,
				listProp));
	}

	/**
	 * 
	 * @param listProp
	 * @return
	 */
	private CategoryListDTO getAllCategoryList(ListProp listProp)
	{
		// Retrieve categories
		List<CategoryDTO> categoryDTOs = categoryService
				.findAllCategories(listProp);
		return new CategoryListDTO(categoryDTOs, listProp);
	}
}
