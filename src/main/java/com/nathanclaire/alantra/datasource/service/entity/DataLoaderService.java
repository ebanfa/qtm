/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.datasource.model.DataLoader;
import com.nathanclaire.alantra.datasource.request.DataLoaderRequest;
import com.nathanclaire.alantra.datasource.response.DataLoaderResponse;

/**
 * @author Edward Banfa
 *
 */
public interface DataLoaderService extends BaseEntityService<DataLoader, DataLoaderResponse, DataLoaderRequest>
{

	public static final String TRANSACTION_DATA_LOADER = "TRANSACTION_DATA_LOADER";
	
}
