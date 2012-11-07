/*
 * Copyright © 2012, Sourece Tree, All Rights Reserved
 * 
 * UserService.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Nov 05, 2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.service;

import org.sourcetree.interview.dto.UserDTO;
import org.sourcetree.interview.entity.User;

/**
 * User related services reside here
 * 
 * @author Venkaiah Chowdary Koneru
 * 
 */
public interface UserService
{
	/**
	 * create insert new user object.
	 * <p>
	 * converts the email(username) to lowercase letters.
	 * </p>
	 * 
	 * @param userDTO
	 *            user DTO
	 */
	void create(UserDTO userDTO);

	/**
	 * @param email
	 *            email to check
	 * @return true if a record with same email exists
	 */
	boolean usernameExists(String email);

	/**
	 * retrieves user information by email (username)
	 * 
	 * @param email
	 *            email
	 * @return <code>{@linkplain User}
	 */
	User getUser(String email);

	/**
	 * checks whether the passed in email and password combination is valid or
	 * not.
	 * 
	 * @param email
	 *            retrieved from entity
	 * @param password
	 *            hashed password retrieved from entity
	 * @param requestPassword
	 *            raw (un-hashed or un-encrypted) password received with the
	 *            request
	 * @return true or false
	 */
	boolean isValidPassword(String email, String password,
			String requestPassword);

	/**
	 * use this to get the hash string for the passed in token.
	 * 
	 * @param salt
	 *            salt for hashing
	 * @param token
	 *            token which is to be hashed
	 * @return hashed string for the passed in token
	 */
	String hashPassword(String salt, String token);
}
