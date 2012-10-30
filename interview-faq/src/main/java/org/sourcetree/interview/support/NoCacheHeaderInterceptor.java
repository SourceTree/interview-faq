/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * NoCacheHeaderInterceptor.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Oct 30, 2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Interceptor to handle HTML caching issues on client side. Setting no cache
 * headers in the response will ensure that the browser would request for a
 * fresh copy from the server every time even when using browser back button.
 * 
 * @author Venkaiah Chowdary Koneru
 */
public class NoCacheHeaderInterceptor extends HandlerInterceptorAdapter
{
	/**
	 * Intercept the execution of a handler. Called after handler execution,
	 * allow manipulate the ModelAndView object before render it to view page.
	 * 
	 * @param request
	 *            current HTTP request
	 * @param response
	 *            current HTTP response
	 * @param handler
	 *            Chosen handler to execute, for type and/or instance
	 *            examination
	 * @param modelAndView
	 *            the <code>ModelAndView</code> that the handler returned (can
	 *            also be null)
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView)
	{
		// HTTP 1.1
		response.setHeader("Cache-Control",
				"no-cache, no-store, must-revalidate");

		// HTTP 1.0
		response.setHeader("Pragma", "no-cache");

		// Proxies
		response.setHeader("Expires", "0");
	}
}
