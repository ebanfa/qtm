/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.transformers;

import javax.ejb.Stateless;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.CellData;
import com.nathanclaire.alantra.datasource.model.DataField;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class RelatedEntityCodeToIDTransformerImpl extends BaseDataTransformer
		implements RelatedEntityCodeToIDTransformer {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.DataTransformer#transform(com.nathanclaire.alantra.datasource.etl.CellData, com.nathanclaire.alantra.datasource.model.DataField)
	 */
	@Override
	public CellData transform(CellData cellData, DataField field) throws ApplicationException {
		return cellData;
	}

}
