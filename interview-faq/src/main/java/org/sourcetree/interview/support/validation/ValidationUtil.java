/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * ValidationUtil.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * 04-Nov-2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.support.validation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.springframework.context.MessageSource;

/**
 * @author Venkaiah Chowdary Koneru
 * 
 */
public final class ValidationUtil
{
	/**
	 * Private constructor to restrict instantiability.
	 */
	private ValidationUtil()
	{
	}

	/**
	 * validates an object against number of validation groups.
	 * 
	 * This helper method is intended for grabbing all the messages across all
	 * the groups instead of one group single time.
	 * 
	 * @param dto
	 *            the object that need to be validated
	 * @param validator
	 *            validator instance
	 * @param messageSource
	 *            messageSource instance to retrieve error messages
	 * @return map of error codes as key and error message as value
	 */
	public static Map<String, String> validate(final Object dto,
			final Validator validator, final MessageSource messageSource)
	{
		Map<String, String> errors = new HashMap<String, String>();

		Set<ConstraintViolation<Object>> voilations = validator.validate(dto,
				Default.class);
		putErrors(voilations, messageSource, errors);

		voilations = validator.validate(dto, SecondGroup.class);
		putErrors(voilations, messageSource, errors);

		voilations = validator.validate(dto, ThirdGroup.class);
		putErrors(voilations, messageSource, errors);

		return errors;
	}

	/**
	 * helper method to push error messages into map
	 * 
	 * @param voilations
	 * @param messageSource
	 * @param errors
	 */
	private static void putErrors(Set<ConstraintViolation<Object>> voilations,
			MessageSource messageSource, Map<String, String> errors)
	{
		for (ConstraintViolation<Object> voilation : voilations)
		{
			if (!errors.containsKey(voilation.getPropertyPath().toString()))
			{
				errors.put(voilation.getPropertyPath().toString(),
						messageSource.getMessage(voilation.getMessage(), null,
								null));
			}
		}
	}
}
