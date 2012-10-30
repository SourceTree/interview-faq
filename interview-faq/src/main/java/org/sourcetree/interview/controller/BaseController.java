/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * BaseController.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Oct 30, 2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.sourcetree.interview.support.AjaxUtils;
import org.sourcetree.interview.support.NoSuchDataException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * All the common tasks related to Controller classes should reside inside this
 * base class.
 * 
 * @author Venkaiah Chowdary Koneru
 * 
 */
public abstract class BaseController
{
	protected static final Logger LOG = Logger.getLogger(BaseController.class);

	protected static final String LOGIN_FORM_PAGE = "loginForm";
	protected static final String TRACE_PAGE = "advancedTrace";

	@Value("#{appProps['pagination.size']}")
	private Integer defaultPageSize;

	/**
	 * Exception handler for Invalid data requests
	 * 
	 * @param ex
	 *            instance of Runtime Exception
	 * @param request
	 *            HTTP request object
	 * @param response
	 *            HTTP response object
	 * @param webRequest
	 *            Spring's web request object
	 * 
	 * @return error page view name
	 */
	@ExceptionHandler(RuntimeException.class)
	public String handleNoDataException(RuntimeException ex,
			HttpServletRequest request, HttpServletResponse response,
			WebRequest webRequest)
	{
		if (!AjaxUtils.isAjaxRequest(webRequest))
		{
			request.setAttribute("exception", ex);
			if (ex instanceof NoSuchDataException)
			{
				NoSuchDataException nsex = (NoSuchDataException) ex;
				request.setAttribute("data", nsex.getData());
				request.setAttribute("nodatatype",
						nsex.isUserRelated() ? "nodata.post" : "nodata.user");

				if (nsex.isUserRelated())
				{
					response.setStatus(HttpStatus.UNAUTHORIZED.value());
					return "error/unauthorized";
				}
				else
				{
					response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
					return "error/exception";
				}
			}
			else
			{
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return "error/exception";
			}
		}

		if (ex instanceof NoSuchDataException)
		{
			NoSuchDataException nsex = (NoSuchDataException) ex;
			if (nsex.isUserRelated())
			{
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
			}
			else
			{
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			}
		}
		else
		{
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}

		LOG.error("[Exception]", ex);
		return null;
	}

	/**
	 * @return the defaultPageSize
	 */
	public Integer getDefaultPageSize()
	{
		return defaultPageSize;
	}
}
