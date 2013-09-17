/**
 * 
 */
package com.nathanclaire.alantra.businessobject.service.process;

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectData;

/**
 * An interface for business object creation services.
 * {@link BusinessObjectData} creation is basically persisting
 * the {@link BusinessObjectData} to some datastore.
 * 
 * @author Edward Banfa
 * 
 */
public interface BusinessObjectCreationService {

	/**
	 * Creates an instance of the {@link BusinessObjectData}
	 * in the underlying storage system.
	 * 
	 * @param businessObjectData the business object we are creating
	 * @return a copy of the persisted business object
	 * @throws ApplicationException if an exception occurred
	 */
	public BaseEntity create(BusinessObjectData businessObjectData) throws ApplicationException;

}
