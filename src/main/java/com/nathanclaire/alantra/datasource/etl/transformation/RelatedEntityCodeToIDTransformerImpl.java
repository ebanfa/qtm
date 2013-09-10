/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.transformation;

import javax.ejb.Stateless;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.annotation.etl.RelatedEntityCodeToIDTransformer;
import com.nathanclaire.alantra.datasource.etl.util.CellData;
import com.nathanclaire.alantra.datasource.model.DataField;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
@RelatedEntityCodeToIDTransformer
public class RelatedEntityCodeToIDTransformerImpl extends BaseDataTransformer
		implements DataTransformer {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.DataTransformer#transform(com.nathanclaire.alantra.datasource.etl.CellDataLite, com.nathanclaire.alantra.datasource.model.DataField)
	 */
	@Override
	public CellData transform(CellData cellData, DataField field) throws ApplicationException {
		return cellData;
	}

}
