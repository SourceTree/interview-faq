/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * UserServiceImpl.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Nov 05, 2012		Venkaiah Chowdary Koneru	created
 * *************************************************************
 */
package org.sourcetree.interview.service;

import java.util.Date;

import org.sourcetree.interview.dao.UserDAO;
import org.sourcetree.interview.dto.UserDTO;
import org.sourcetree.interview.entity.User;
import org.sourcetree.interview.enums.UserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User service implementation
 * 
 * @author Venkaiah Chowdary Koneru
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserDAO userDAO;

	@Autowired
	private ShaPasswordEncoder shaPasswordEncoder;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = false)
	public void create(UserDTO userDTO)
	{
		User user = new User();

		user.setEmail(userDTO.getEmail().trim().toLowerCase());
		user.setPassword(shaPasswordEncoder.encodePassword(
				userDTO.getPassword(), user.getEmail()));
		user.setRole(UserRoleEnum.ADMIN);
		user.setCreatedDate(new Date());
		user.setName(userDTO.getName());

		userDAO.save(user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean usernameExists(String username)
	{
		return userDAO.existsByParameter("email", username);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User getUser(String userName)
	{
		return userDAO.findByParameter("email", userName);
	}
}
