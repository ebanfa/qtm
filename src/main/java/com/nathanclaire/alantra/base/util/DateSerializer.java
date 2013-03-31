/**
 * 
 */
package com.nathanclaire.alantra.base.util;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * Customer Serializer for all date properties
 * @author Edward Banfa 
 *
 */
public class DateSerializer extends JsonSerializer<Date> {

	/**
	 * Date format we will be serializing. Should externalize this and make it configurable by the user
	 */
	private static final String DATE_FORMAT = ("dd/MM/yyyy");

	@Override
	public void serialize(Date date, JsonGenerator jgen, SerializerProvider provider) throws IOException,	JsonProcessingException 
	{
		DateTime dateTime = new DateTime(date);
		String formattedDate = DateTimeFormat.forPattern(DATE_FORMAT).print(dateTime);
		jgen.writeString(formattedDate);
	}

}
