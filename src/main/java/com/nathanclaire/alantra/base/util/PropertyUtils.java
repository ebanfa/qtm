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
import java.util.List;

import com.nathanclaire.alantra.application.model.ApplicationEntityField;

/**
 * @author Edward Banfa 
 *
 */
public class PropertyUtils {
	
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
		        	copyProperty(toObj, fromObj, toPropertyDescriptor, fromPropertyDescriptor, includedFields);
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
		String fromPropertyDescriptorName = fromPropertyDescriptor.getName();
        if (fromPropertyDescriptorName.equals(toPropertyDescriptor.getName()) && !fromPropertyDescriptorName.equals("class")) 
        {
             try 
             {
				if(toPropertyDescriptor.getWriteMethod() != null) 
				{  

					boolean excludeField = true;
					for(ApplicationEntityField includedField: includedFields)
					{
						if (fromPropertyDescriptorName.equals(includedField.getName()))
							excludeField = false;
					}
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

}
