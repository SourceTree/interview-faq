/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * UserServiceTest.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Nov 5, 2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.test.service;

import org.junit.Assert;
import org.junit.Test;
import org.sourcetree.interview.dto.UserDTO;
import org.sourcetree.interview.entity.User;
import org.sourcetree.interview.enums.UserRoleEnum;
import org.sourcetree.interview.service.UserService;
import org.sourcetree.interview.test.BaseTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 * @author Venkaiah Chowdary Koneru
 * 
 */
public class UserServiceTest extends BaseTestCase
{

	@Autowired
	private UserService userService;

	@Autowired
	ShaPasswordEncoder shaPasswordEncoder;

	/**
	 * Test method for
	 * {@link org.sourcetree.interview.service.UserServiceImpl#create(org.sourcetree.interview.dto.UserDTO)}
	 * .
	 */
	@Test
	public void testCreate_1()
	{
		UserDTO dto = new UserDTO();
		dto.setEmail("venky1@gmail.com");
		dto.setPassword("password");
		dto.setName("Venky");

		userService.create(dto);

		Assert.assertTrue(userService.usernameExists("venky1@gmail.com"));
	}

	/**
	 * Test method for
	 * {@link org.sourcetree.interview.service.UserServiceImpl#usernameExists(java.lang.String)}
	 * .
	 */
	@Test
	public void testUsernameExists_1()
	{
		String email = "admin@faqmasters.com";

		Assert.assertTrue(userService.usernameExists(email));
	}

	/**
	 * Test method for
	 * {@link org.sourcetree.interview.service.UserServiceImpl#usernameExists(java.lang.String)}
	 * .
	 */
	@Test
	public void testUsernameExists_2()
	{
		String email = "xyz@faqmasters.com";

		Assert.assertFalse(userService.usernameExists(email));
	}

	/**
	 * Test method for
	 * {@link org.sourcetree.interview.service.UserServiceImpl#getUser(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetUser_1()
	{
		String email = "admin@faqmasters.com";

		User user = userService.getUser(email);

		Assert.assertNotNull(user);
		Assert.assertEquals(email, user.getEmail());
		Assert.assertEquals(UserRoleEnum.ADMIN, user.getRole());
	}
}
