/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.producers;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.DataTransformer;
import com.nathanclaire.alantra.datasource.etl.DataTransformerProducer;
import com.nathanclaire.alantra.datasource.etl.transformers.NoOperationDataTransformer;
import com.nathanclaire.alantra.datasource.etl.transformers.RelatedEntityCodeToIDTransformer;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.service.entity.DataTransformerService;


/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class DataTransformerProducerImpl implements DataTransformerProducer {

	@Inject NoOperationDataTransformer noOperationDataTransformer;
	@Inject RelatedEntityCodeToIDTransformer relatedEntityCodeToIDTransformer;
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.producers.DataTransformerProducer#getDataTransformer(com.nathanclaire.alantra.datasource.model.DataField)
	 */
	@Override
	public DataTransformer getDataTransformer(DataField field)	throws ApplicationException {
		com.nathanclaire.alantra.datasource.model.DataTransformer transformer = 
				field.getDataTransformer();
		if(transformer.getCode().equals(DataTransformerService.NO_OPERATION_DATA_TRANSFORMER))
			return noOperationDataTransformer;
		if(transformer.getCode().equals(DataTransformerService.REL_ENTITY_CODE_TO_ID_DATA_TRANSFORMER))
			return relatedEntityCodeToIDTransformer;
		return null;
	}
	

}
