/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * CategoryServiceTest.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Nov 6, 2012			soomineni						Created
 * *************************************************************
 */
package org.sourcetree.interview.test.service;

import org.junit.Assert;
import org.junit.Test;
import org.sourcetree.interview.dto.CategoryDTO;
import org.sourcetree.interview.entity.Category;
import org.sourcetree.interview.service.CategoryService;
import org.sourcetree.interview.test.BaseTestCase;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Srinu Somineni
 * 
 */
public class CategoryServiceTest extends BaseTestCase
{
	@Autowired
	private CategoryService categoryService;

	/**
	 * Test method for
	 * {@link org.sourcetree.interview.service.CategoryServiceImpl#create(org.sourcetree.interview.dto.CategoryDTO)}
	 * .
	 */
	@Test
	public void testCreate_1()
	{
		CategoryDTO catDTO = new CategoryDTO();
		catDTO.setCategoryName("ASP.NET");
		catDTO.setCategoryDescription("");

		categoryService.create(catDTO);

		Assert.assertTrue(categoryService.isCategoryExists("ASP.NET"));
	}

	/**
	 * Test method for
	 * {@link org.sourcetree.interview.service.CategoryServiceImpl#isCategoryExists(java.lang.String)}
	 * .
	 */
	@Test
	public void testCategoryExists_1()
	{
		String name = "java";

		Assert.assertTrue(categoryService.isCategoryExists(name));
	}

	/**
	 * Test method for
	 * {@link org.sourcetree.interview.service.CategoryServiceImpl#isCategoryExists(java.lang.String)}
	 * .
	 */
	@Test
	public void testCategoryExists_2()
	{
		String name = "AS400";

		Assert.assertFalse(categoryService.isCategoryExists(name));
	}

	/**
	 * Test method for
	 * {@link org.sourcetree.interview.service.CategoryServiceImpl#findCategoryById(Long)}
	 * .
	 */
	@Test
	public void testfindCategory_1()
	{
		long id = 1;
		String name = "Java";

		Category cat = categoryService.findCategoryById(id);

		Assert.assertNotNull(cat);
		Assert.assertEquals(name, cat.getCategoryName());
	}

	/**
	 * Test method for
	 * {@link org.sourcetree.interview.service.CategoryServiceImpl#getCategoryDTOByName(String)}
	 * .
	 */
	@Test
	public void getCategoryDTOByName_1()
	{
		Assert.assertNull(categoryService.getCategoryDTOByName(null));
		Assert.assertNull(categoryService.getCategoryDTOByName(""));
		Assert.assertNull(categoryService.getCategoryDTOByName(" "));
	}

	/**
	 * Test method for
	 * {@link org.sourcetree.interview.service.CategoryServiceImpl#getCategoryDTOByName(String)}
	 * .
	 */
	@Test
	public void getCategoryDTOByName_2()
	{
		String name = "Java";
		CategoryDTO categoryDTO = categoryService.getCategoryDTOByName(name);

		Assert.assertNotNull(categoryDTO);
		Assert.assertEquals(name, categoryDTO.getCategoryName());
		Assert.assertNotNull(categoryDTO.getId());
		Assert.assertNotNull(categoryDTO.getCategoryDescription());

		name = "java";
		categoryDTO = categoryService.getCategoryDTOByName(name);

		Assert.assertNotNull(categoryDTO);
		Assert.assertEquals("Java", categoryDTO.getCategoryName());
		Assert.assertNotNull(categoryDTO.getId());
		Assert.assertNotNull(categoryDTO.getCategoryDescription());
	}
}
