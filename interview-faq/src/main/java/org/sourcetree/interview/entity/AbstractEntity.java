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

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Super class for all the Entity Objects
 * 
 * @author Venkaiah Chowdary Koneru
 * 
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Column(name = "deleted")
	private Boolean deleted = Boolean.FALSE;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_date")
	private Date modifiedDate;

	@Version
	@Column(name = "version")
	private Integer version;

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
