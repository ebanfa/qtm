/**
 * 
 */
package com.nathanclaire.alantra.base.service.process;

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.request.BaseRequest;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.TableDataLite;

/**
 * @author Edward Banfa 
 *
 */
public interface EntityDataInputService {
	
	public BaseEntity processDataInput(BaseRequest primaryEntityRequest, BaseRequest secEntityRequest, TableDataLite tableDataLite) throws ApplicationException;
	
}

