/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * User.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Oct 31, 2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.sourcetree.interview.enums.UserRoleEnum;

/**
 * User Entity
 * 
 * @author Venkaiah Chowdary Koneru
 */
@Entity
@Table(name = "user")
@GenericGenerator(strategy = "native", name = "UserSeq")
public class User extends AbstractEntity
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UserSeq")
	@Column(name = "id")
	private Long id;

	@Column(name = "name", length = 80)
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "password", length = 80)
	private String password;

	@Column(name = "role", length = 30)
	@Enumerated(EnumType.STRING)
	private UserRoleEnum role;

	/**
	 * @return the id
	 */
	public Long getId()
	{
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id)
	{
		this.id = id;
	}

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
