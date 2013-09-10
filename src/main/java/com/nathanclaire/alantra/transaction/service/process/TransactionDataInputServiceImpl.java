/**
 * 
 */
package com.nathanclaire.alantra.transaction.service.process;

import javax.ejb.Stateless;

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.request.BaseRequest;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.service.BaseEntityDataInputService;
import com.nathanclaire.alantra.datasource.etl.service.EntityDataInputService;
import com.nathanclaire.alantra.datasource.etl.util.TableData;
import com.nathanclaire.alantra.datasource.model.DataChannel;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class TransactionDataInputServiceImpl extends BaseEntityDataInputService implements EntityDataInputService {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.service.BaseEntityDataInputService#doDataInput(com.nathanclaire.alantra.datasource.model.DataChannel, com.nathanclaire.alantra.base.request.BaseRequest, com.nathanclaire.alantra.base.request.BaseRequest, com.nathanclaire.alantra.datasource.etl.TableData)
	 */
	@Override
	public BaseEntity doDataInput(DataChannel channel,
			BaseRequest primaryEntity, BaseRequest secondaryEntity,
			TableData tableData) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

}
