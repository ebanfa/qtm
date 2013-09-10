/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.service;

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.request.BaseRequest;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.util.TableData;

/**
 * @author Edward Banfa
 *
 */
public interface EntityDataInputService {
	
	public BaseEntity processDataInput(BaseRequest primaryEntity, 
			BaseRequest secondaryEntity, TableData tableData) throws ApplicationException;

}
