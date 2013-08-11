/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.transformers;

import javax.ejb.Stateless;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.CellDataLite;
import com.nathanclaire.alantra.datasource.model.DataField;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class RelatedEntityCodeToIDTransformerImpl extends BaseDataTransformer
		implements RelatedEntityCodeToIDTransformer {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.DataTransformer#transform(com.nathanclaire.alantra.datasource.etl.CellDataLite, com.nathanclaire.alantra.datasource.model.DataField)
	 */
	@Override
	public CellDataLite transform(CellDataLite cellDataLite, DataField field) throws ApplicationException {
		return cellDataLite;
	}

}
