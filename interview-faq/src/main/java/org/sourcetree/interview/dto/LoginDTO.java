/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * LoginDTO.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * 04-Nov-2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.sourcetree.interview.support.validation.SecondGroup;

import com.sun.xml.txw2.annotation.XmlElement;

/**
 * @author Venkaiah Chowdary Koneru
 * 
 */
@XmlElement(value = "login")
public class LoginDTO extends BaseDTO
{
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "login.email.null")
	@Size(min = 3, message = "login.email.invalid", groups = SecondGroup.class)
	private String email;

	@NotEmpty(message = "login.password.null")
	@Size(min = 3, message = "login.password.invalid",
			groups = SecondGroup.class)
	private String password;

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

}
