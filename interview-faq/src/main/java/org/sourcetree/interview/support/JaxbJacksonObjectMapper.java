/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * JaxbJacksonObjectMapper.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Oct 30, 2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.support;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;

/**
 * Annotation introspector to use for serialization process is configured
 * separately for serialization and deserialization purposes
 * 
 * @author Venkaiah Chowdary Koneru
 */
public class JaxbJacksonObjectMapper extends ObjectMapper
{
	/**
	 * no arg constructor
	 */
	public JaxbJacksonObjectMapper()
	{
		final AnnotationIntrospector introspector = new JacksonAnnotationIntrospector();
		super.getDeserializationConfig().withAnnotationIntrospector(
				introspector);
		super.getSerializationConfig().withAnnotationIntrospector(introspector);
	}
}
