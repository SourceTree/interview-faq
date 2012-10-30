/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * InjectSessionAttributes.java
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

/**
 * @author Venkaiah Chowdary Koneru
 * 
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface InjectSessionAttributes
{

}
