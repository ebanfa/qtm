/**
 * 
 */
package com.nathanclaire.alantra.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.nathanclaire.alantra.model.BaseEntity;

/**
 * @author Edward Banfa 
 *
 */
public class ManyToOneFieldSerializer extends JsonSerializer<BaseEntity> {

	@Override
	public void serialize(BaseEntity entity, JsonGenerator jgen, SerializerProvider provider) 
			throws IOException, 	JsonProcessingException 
	{
		jgen.writeString(entity.getId().toString());
	}

}
