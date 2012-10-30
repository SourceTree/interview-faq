/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * InvalidFormatException.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Oct 30, 2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.support;

/**
 * This exception is thrown to indicate problems while processing invalid format
 * events data
 * 
 * @author Venkaiah Chowdary Koneru
 * 
 */
public class InvalidFormatException extends Exception
{
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public InvalidFormatException()
	{
		super();
	}

	/**
	 * Constructor to pass in exception message
	 * 
	 * @param message
	 */
	public InvalidFormatException(String message)
	{
		super(message);
	}

	/**
	 * constructor to pass in exception message and throwable
	 * 
	 * @param message
	 * @param cause
	 */
	public InvalidFormatException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
