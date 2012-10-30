/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * AbstractEntity.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Oct 30, 2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Super class for all the Entity Objects
 * 
 * @author Venkaiah Chowdary Koneru
 * 
 */
public abstract class AbstractEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long id;

	private Boolean deleted = Boolean.FALSE;

	private Date createdDate;
	private Date modifiedDate;

	private Integer version;

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
	 * @return the createdDate
	 */
	public Date getCreatedDate()
	{
		return (createdDate != null) ? new Date(createdDate.getTime()) : null;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = (createdDate != null) ? new Date(
				createdDate.getTime()) : null;
	}

	/**
	 * @return the modifiedDate
	 */
	public Date getModifiedDate()
	{
		return (modifiedDate != null) ? new Date(modifiedDate.getTime()) : null;
	}

	/**
	 * @param modifiedDate
	 *            the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate)
	{
		this.modifiedDate = (modifiedDate != null) ? new Date(
				modifiedDate.getTime()) : null;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion()
	{
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(Integer version)
	{
		this.version = version;
	}

	/**
	 * @return the deleted
	 */
	public Boolean getDeleted()
	{
		return deleted;
	}

	/**
	 * @param deleted
	 *            the deleted to set
	 */
	public void setDeleted(Boolean deleted)
	{
		this.deleted = deleted;
	}
}
