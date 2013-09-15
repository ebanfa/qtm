/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.transformation;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.annotation.etl.MapBasedTransformer;
import com.nathanclaire.alantra.datasource.annotation.etl.NoOperationDataTransformer;
import com.nathanclaire.alantra.datasource.annotation.etl.RelatedEntityCodeToIDTransformer;
import com.nathanclaire.alantra.datasource.service.entity.DataTransformerService;


/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class DataTransformerProducerImpl implements DataTransformerProducer {

	@Inject @MapBasedTransformer DataTransformer mapBasedTransformer;
	@Inject @NoOperationDataTransformer DataTransformer noOperationDataTransformer;
	@Inject @RelatedEntityCodeToIDTransformer DataTransformer relatedEntityCodeToIDTransformer;
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.producers.DataTransformerProducer#getDataTransformer(com.nathanclaire.alantra.datasource.model.DataField)
	 */
	@Override
	public DataTransformer getDataTransformer(String transformerCode)	throws ApplicationException 
	{
		if(transformerCode.equals(DataTransformerService.MAP_BASED_DATA_TRANSFORMER))
			return mapBasedTransformer;
		if(transformerCode.equals(DataTransformerService.NO_OPERATION_DATA_TRANSFORMER))
			return noOperationDataTransformer;
		if(transformerCode.equals(DataTransformerService.REL_ENTITY_CODE_TO_ID_DATA_TRANSFORMER))
			return relatedEntityCodeToIDTransformer;
		return null;
	}
	

}
