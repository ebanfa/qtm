/**
 * 
 */
package com.nathanclaire.alantra.rule.event;

import com.nathanclaire.alantra.rule.engine.BusinessObjectData;

/**
 * A {@link BusinessObjectData} related event occurred,
 * examples of events are validation, creation and routing
 * events. 
 * 
 * @author Edward Banfa
 *
 */
public class BusinessObjectEvent {

	/**
	 * @param businessObject
	 */
	public BusinessObjectEvent(BusinessObjectData businessObject) {
		this.businessObject = businessObject;
	}

	private BusinessObjectData businessObject;

	/**
	 * @return the businessObject
	 */
	public BusinessObjectData getBusinessObject() {
		return businessObject;
	}

	/**
	 * @param businessObject the businessObject to set
	 */
	public void setBusinessObject(BusinessObjectData businessObject) {
		this.businessObject = businessObject;
	}
}
