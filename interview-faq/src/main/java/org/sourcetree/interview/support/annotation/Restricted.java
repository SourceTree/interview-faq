/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * Restricted.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Oct 30, 2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.support.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.sourcetree.interview.enums.UserRoleEnum;

/**
 * Use it to check the current user role and authorization.
 * 
 * @author Venkaiah Chowdary Koneru
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Restricted
{
	/**
	 * Allowed Roles
	 * 
	 * @return array of UserRoleEnum values
	 */
	UserRoleEnum[] rolesAllowed();

	/**
	 * set whether to inject current session values into sessionAttributes
	 * argument
	 * 
	 * @return true or false
	 */
	boolean setSessionAttributes() default true;
}
