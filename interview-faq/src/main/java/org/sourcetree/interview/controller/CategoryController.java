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

import org.sourcetree.interview.dto.CategoryDTO;
import org.sourcetree.interview.dto.CategoryListDTO;
import org.sourcetree.interview.dto.ListProp;
import org.sourcetree.interview.dto.ResponseDTO;
import org.sourcetree.interview.enums.OutcomeStatus;
import org.sourcetree.interview.enums.UserRoleEnum;
import org.sourcetree.interview.service.CategoryService;
import org.sourcetree.interview.support.SessionAttributes;
import org.sourcetree.interview.support.WebUtil;
import org.sourcetree.interview.support.annotation.InjectSessionAttributes;
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
	 * Category Listing page. for first initial page, fetch the first page (10
	 * records) to display. this will reduce the server round trip.
	 * 
	 * @param model
	 * @return category list page
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String categoryList(Model model)
	{
		// TODO: remove - venky
		// Initialize ListProp with first page
		ListProp listProp = WebUtil.initListProp("1", getDefaultPageSize(),
				null, null);

		model.addAttribute("categories", getCategoryList(listProp));

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
	@ResponseBody
	public CategoryListDTO categoryListAjax(
			@PathVariable(value = "page") String page,
			@RequestParam(value = "sortProperty", required = false) String sortProperty,
			@RequestParam(value = "sortOrder", required = false) String sortOrder)
	{
		// Initialize ListProp with requested page
		ListProp listProp = WebUtil.initListProp(page, getDefaultPageSize(),
				null, null);

		return getCategoryList(listProp);
	}

	/**
	 * @param sessionAttributes
	 * @param name
	 * @param model
	 * @return category page
	 */
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	@InjectSessionAttributes
	public String categoryDetailsForm(SessionAttributes sessionAttributes,
			@PathVariable String name, Model model)
	{
		model.addAttribute("category",
				categoryService.getCategoryDTOByName(name));

		if (sessionAttributes.getRole() == null
				|| sessionAttributes.getRole() != UserRoleEnum.ADMIN)
		{
			return "category/categoryDetails";
		}

		model.addAttribute("parentCategories",
				categoryService.findAllParentCategories());
		return "category/categoryEdit";
	}

	/**
	 * to handle new partner request. this method will be invoked in the event
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
	 * to handle new partner request. this method will be invoked in the event
	 * of form submissions from the client which will contains the
	 * <b>application/x-www-form-urlencoded</b> as the content-type header
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

		categoryService.update(categoryDTO, categoryDTO.getId());

		return response;
	}

	/**
	 * 
	 * @param listProp
	 * @return
	 */
	private CategoryListDTO getCategoryList(ListProp listProp)
	{
		// Retrieve categories
		List<CategoryDTO> categoryDTOs = categoryService
				.findAllParentCategories(listProp);
		return new CategoryListDTO(categoryDTOs, listProp);
	}
}
