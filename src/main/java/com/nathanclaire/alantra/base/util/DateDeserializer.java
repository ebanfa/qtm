package com.nathanclaire.alantra.base.util;
import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Customer Deserializer for all date properties
 * @author Edward Banfa 
 *
 */
public class DateDeserializer extends JsonDeserializer<Date> 
{
	/**
	 * Acceptable date format. Should externalize this and make it configurable by the user
	 */
	private static String DATE_FORMAT = "dd/MM/yyyy";
    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    	String dateString = jp.getText();
    	if ((dateString != null) && (!dateString.isEmpty()))
    	{
        	// The ISO standard format for date is 'yyyy-MM-dd'
    	    DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_FORMAT);
    		DateTime dateTime = DateTime.parse(jp.getText(), formatter);
    		return dateTime.toDate();
    	}
    	return null;
    }
}