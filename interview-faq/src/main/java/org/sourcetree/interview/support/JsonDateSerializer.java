/*
 * Copyright © 2012, Source Tree, All Rights Reserved
 * 
 * JsonDateSerializer.java
 * Modification History
 * *************************************************************
 * Date				Author						Comment
 * Oct 30, 2012		Venkaiah Chowdary Koneru	Created
 * *************************************************************
 */
package org.sourcetree.interview.support;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.stereotype.Component;

/**
 * Used to serialize Java.util.Date, which is not a common JSON type, so we have
 * to create a custom serialize method.
 * 
 * @author Venkaiah Chowdary Koneru
 * @see JsonSerializer
 */
@Component
public class JsonDateSerializer extends JsonSerializer<Date>
{
	private final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"MM-dd-yyyy hh:mm:ss a");

	/**
	 * converts the date object to string format
	 * 
	 * @param date
	 * @param gen
	 * @param provider
	 * 
	 * @throws IOException
	 *             in case of errors
	 */
	@Override
	public void serialize(Date date, JsonGenerator gen,
			SerializerProvider provider) throws IOException
	{
		String formattedDate = dateFormat.format(date);

		gen.writeString(formattedDate);
	}
}
