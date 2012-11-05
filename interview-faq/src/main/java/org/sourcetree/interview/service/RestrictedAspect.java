/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * RestrictedAspect.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Nov 06, 2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.sourcetree.interview.support.AuthorizationUtil;
import org.sourcetree.interview.support.CoreUtil;
import org.sourcetree.interview.support.SessionAttributes;
import org.sourcetree.interview.support.annotation.Restricted;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * This holds the Authorization checking logic which is triggered by
 * <code>Restricted</code> annotation.
 * 
 * @author Venkaiah Chowdary Koneru
 */
@Service
@Aspect
public class RestrictedAspect
{
	private static final Log LOG = LogFactory.getLog(RestrictedAspect.class);

	/**
	 * Pointcut for <code>Restricted</code> annotation
	 */
	@Pointcut("@annotation(org.sourcetree.interview.support.annotation.Restricted)")
	public void restrictedPointcut()
	{

	}

	/**
	 * pointcut for <code>InjectSessionAttributes</code> annotation
	 */
	@Pointcut("@annotation(org.sourcetree.interview.support.annotation.InjectSessionAttributes)")
	public void injectSessionAttributesPointcut()
	{

	}

	/**
	 * advise for the <code>Restricted</code> annotation's pointcut
	 * 
	 * @param joinPoint
	 * @param restrictedAnnotation
	 * @return joinpoint with args
	 * @throws Throwable
	 */
	@Around("restrictedPointcut()")
	public Object processRequest(ProceedingJoinPoint joinPoint)
			throws Throwable
	{
		LOG.trace("Restricted Annotation is activated");

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();

		final SessionAttributes sessionAttributes = AuthorizationUtil
				.retrieveSessionAttributes(request);

		Restricted methodAnnotation = ((MethodSignature) joinPoint
				.getSignature()).getMethod().getAnnotation(Restricted.class);
		if (!AuthorizationUtil.isUserInRole(sessionAttributes,
				methodAnnotation.rolesAllowed()))
		{
			AuthorizationUtil.throwUnauthorizedException(true);
		}

		if (methodAnnotation.setSessionAttributes())
		{
			return joinPoint.proceed(setSessionAttributes(joinPoint.getArgs(),
					sessionAttributes));
		}

		LOG.trace("Restricted Annotation completed");
		return joinPoint.proceed();
	}

	/**
	 * advise for the <code>InjectSessionAttributes</code> annotation's pointcut
	 * 
	 * @param joinPoint
	 * @return joinpoint with new arguments
	 * @throws Throwable
	 */
	@Around("injectSessionAttributesPointcut()")
	public Object processInjectRequest(ProceedingJoinPoint joinPoint)
			throws Throwable
	{
		LOG.trace("InjectSessionAttributes annotation is activated");

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();

		final SessionAttributes sessionAttributes = AuthorizationUtil
				.retrieveSessionAttributes(request);

		if (sessionAttributes != null)
		{
			LOG.trace("InjectSessionAttributes annotation completed");
			return joinPoint.proceed(setSessionAttributes(joinPoint.getArgs(),
					sessionAttributes));
		}

		LOG.trace("InjectSessionAttributes annotation is completed");
		return joinPoint.proceed();
	}

	/**
	 * To set the session attributes for the request
	 * 
	 * @param arguments
	 * @param sessionAttributes
	 * @return array of objects containing method arguments
	 */
	private Object[] setSessionAttributes(final Object[] arguments,
			final SessionAttributes sessionAttributes)
	{
		if (!CoreUtil.isEmpty(arguments))
		{
			for (final Object argument : arguments)
			{
				if (argument instanceof SessionAttributes)
				{
					LOG.trace("Found the required session variables arguments");

					if (sessionAttributes != null)
					{
						SessionAttributes methodSessionArgument = (SessionAttributes) argument;
						methodSessionArgument.setEmail(sessionAttributes
								.getEmail());
						methodSessionArgument.setRole(sessionAttributes
								.getRole());
						methodSessionArgument.setUserId(sessionAttributes
								.getUserId());
					}

					LOG.debug("exiting the loop after setting the session variables");

					break;
				}
			}
		}

		return arguments;
	}
}
