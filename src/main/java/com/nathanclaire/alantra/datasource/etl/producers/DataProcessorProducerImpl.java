/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.producers;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.DataProcessor;
import com.nathanclaire.alantra.datasource.model.DataInput;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class DataProcessorProducerImpl implements DataProcessorProducer {

	@Inject DataProcessor dataProcessor;
	@Override
	public DataProcessor getDataProcessor(DataInput dataInput) throws ApplicationException {
		return dataProcessor;
		/*List<DataProcessor> processors = new ArrayList<DataProcessor>();
		Map<String, DataProcessor> dataProcessors = this.getDataProcessorsImpl();
		// 1. Get the data processor instances
		Set<DataInputProcessors> processorInstances = dataInput
				.getDataInputProcessorses();
		
		for (DataInputProcessors processorInstance : processorInstances) 
		{
			com.nathanclaire.alantra.datasource.model.DataProcessor processor = 
					processorInstance.getDataProcessor();
			
			if(dataProcessors.containsKey(processor.getCode()))
				processors.add(dataProcessors.get(processor.getCode()));
		}
		return processors;*/
	}
	
	/**
	 * @return
	 */
	private Map<String, DataProcessor> getDataProcessorsImpl()
	{
		Map<String, DataProcessor> dataProcessors = new HashMap<String, DataProcessor>();
		return dataProcessors;
	}

}
