/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * ResponseDTO.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Oct 30, 2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.dto;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import org.sourcetree.interview.enums.OutcomeStatus;

/**
 * Response DTO
 * 
 * @author Venkaiah Chowdary Koneru
 */
@XmlRootElement(name = "response")
public class ResponseDTO implements Serializable
{

	private static final long serialVersionUID = 1L;

	private OutcomeStatus status = OutcomeStatus.SUCCESS;
	private Map<String, String> errors;

	/**
	 * returns the status
	 * 
	 * @return <code>status</code>
	 */
	public OutcomeStatus getStatus()
	{
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(OutcomeStatus status)
	{
		this.status = status;
	}

	/**
	 * returns the errors
	 * 
	 * @return error Map
	 */
	public Map<String, String> getErrors()
	{
		return errors;
	}

	/**
	 * @param errors
	 *            the errors to set
	 */
	public void setErrors(Map<String, String> errors)
	{
		this.errors = errors;
	}

}
