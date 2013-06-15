/**
 * 
 */
package com.nathanclaire.alantra.base.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * @author Edward Banfa 
 *
 */
public class PropertyUtils {
	
	public static void initializeBaseFields(BaseRequest baseRequest)
	{
		baseRequest.setCreatedByUsr("SYSTEM");
		baseRequest.setCreatedDt(new Date());
		baseRequest.setEffectiveDt(new Date());
		baseRequest.setRecSt('A');
		
	}
	
	/**
	 * @param fromObj
	 * @param toObj
	 * @param excludedFields
	 */
	public static void copyProperties(Object fromObj, Object toObj, List<ApplicationEntityField> includedFields) {
	    Class<? extends Object> fromClass = fromObj.getClass();
	    Class<? extends Object> toClass = toObj.getClass();
	    try {
	        BeanInfo fromBean = Introspector.getBeanInfo(fromClass);
	        BeanInfo toBean = Introspector.getBeanInfo(toClass);

	        PropertyDescriptor[] toPd = toBean.getPropertyDescriptors();
	        List<PropertyDescriptor> fromPd = Arrays.asList(fromBean
	                .getPropertyDescriptors());
	        
	        for (PropertyDescriptor toPropertyDescriptor : toPd) 
	        {
		        for (PropertyDescriptor fromPropertyDescriptor : fromPd) 
		        {
		            if (fromPropertyDescriptor.getName().equals(toPropertyDescriptor.getName()) && 
		            		!fromPropertyDescriptor.getName().equals("class")) 
		            {
		        	copyProperty(toObj, fromObj, toPropertyDescriptor, fromPropertyDescriptor, includedFields);
		            }
		        }
	        }
	    } catch (IntrospectionException e) {
	        e.printStackTrace();
	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * @param toObject
	 * @param fromObject
	 * @param toPropertyDescriptor
	 * @param fromPropertyDescriptor
	 * @param includedFields
	 */
	private static void copyProperty(Object toObject, Object fromObject, 
			PropertyDescriptor toPropertyDescriptor, PropertyDescriptor fromPropertyDescriptor, List<ApplicationEntityField> includedFields)
	{
             try 
             {
				if(toPropertyDescriptor.getWriteMethod() != null) 
				{  
					boolean excludeField = true;
					for(ApplicationEntityField includedField: includedFields)
						if (fromPropertyDescriptor.getName().equals(includedField.getName()))
							excludeField = false;
					// Copy only if field is on include list
					if(!excludeField)
				        toPropertyDescriptor.getWriteMethod().invoke(toObject, 
				        		fromPropertyDescriptor.getReadMethod().invoke(fromObject, null));
				 }
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
	}

}
