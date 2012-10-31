/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * User.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Feb 29, 2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.entity;

import javax.persistence.Entity;

import org.sourcetree.interview.enums.UserRoleEnum;

/**
 * User Entity
 * 
 * @author Venkaiah Chowdary Koneru
 */
@Entity
public class User extends AbstractEntity
{
	private static final long serialVersionUID = 1L;

	private String name;
	private String email;
	private String password;
	private String identification;

	private UserRoleEnum role;

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @return the identification
	 */
	public String getIdentification()
	{
		return identification;
	}

	/**
	 * @param identification
	 *            the identification to set
	 */
	public void setIdentification(String identification)
	{
		this.identification = identification;
	}

	/**
	 * @return the role
	 */
	public UserRoleEnum getRole()
	{
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(UserRoleEnum role)
	{
		this.role = role;
	}

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}
}
