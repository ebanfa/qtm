/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.transformation;

import javax.ejb.Stateless;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.annotation.etl.RelatedEntityCodeToIDTransformer;
import com.nathanclaire.alantra.datasource.etl.util.CellData;
import com.nathanclaire.alantra.datasource.model.DataFieldMap;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
@RelatedEntityCodeToIDTransformer
public class RelatedEntityCodeToIDTransformerImpl extends AbstractDataTransformer
		implements DataTransformer {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.transformation.AbstractDataTransformer#transformStringField(com.nathanclaire.alantra.datasource.etl.util.CellData, com.nathanclaire.alantra.datasource.model.DataFieldMap)
	 */
	@Override
	protected CellData transformStringField(CellData cellData,
			DataFieldMap fieldMap) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.transformation.AbstractDataTransformer#transformCharacterField(com.nathanclaire.alantra.datasource.etl.util.CellData, com.nathanclaire.alantra.datasource.model.DataFieldMap)
	 */
	@Override
	protected CellData transformCharacterField(CellData cellData,
			DataFieldMap fieldMap) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.transformation.AbstractDataTransformer#transformRelationshipField(com.nathanclaire.alantra.datasource.etl.util.CellData, com.nathanclaire.alantra.datasource.model.DataFieldMap)
	 */
	@Override
	protected CellData transformRelationshipField(CellData cellData,
			DataFieldMap fieldMap) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.transformation.AbstractDataTransformer#transformDateField(com.nathanclaire.alantra.datasource.etl.util.CellData, com.nathanclaire.alantra.datasource.model.DataFieldMap)
	 */
	@Override
	protected CellData transformDateField(CellData cellData,
			DataFieldMap fieldMap) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.transformation.AbstractDataTransformer#transformDecimalField(com.nathanclaire.alantra.datasource.etl.util.CellData, com.nathanclaire.alantra.datasource.model.DataFieldMap)
	 */
	@Override
	protected CellData transformDecimalField(CellData cellData,
			DataFieldMap fieldMap) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.transformation.AbstractDataTransformer#transformIntegerField(com.nathanclaire.alantra.datasource.etl.util.CellData, com.nathanclaire.alantra.datasource.model.DataFieldMap)
	 */
	@Override
	protected CellData transformIntegerField(CellData cellData,
			DataFieldMap fieldMap) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}


}
