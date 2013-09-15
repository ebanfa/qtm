/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.process;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.base.util.PropertyUtil;
import com.nathanclaire.alantra.datasource.etl.extraction.DataExtractor;
import com.nathanclaire.alantra.datasource.etl.extraction.DataExtractorProducer;
import com.nathanclaire.alantra.datasource.etl.loading.DataLoader;
import com.nathanclaire.alantra.datasource.etl.loading.DataLoaderProducer;
import com.nathanclaire.alantra.datasource.etl.transformation.DataProcessor;
import com.nathanclaire.alantra.datasource.etl.transformation.DataProcessorProducer;
import com.nathanclaire.alantra.datasource.etl.transformation.DataTransformer;
import com.nathanclaire.alantra.datasource.model.Data;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.model.DataFieldType;
import com.nathanclaire.alantra.datasource.model.DataInput;
import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.datasource.model.DataStructure;
import com.nathanclaire.alantra.datasource.model.DataType;
import com.nathanclaire.alantra.datasource.request.DataRequest;
import com.nathanclaire.alantra.datasource.service.entity.DataFieldService;
import com.nathanclaire.alantra.datasource.service.entity.DataFieldTypeService;
import com.nathanclaire.alantra.datasource.service.entity.DataStructureService;
import com.nathanclaire.alantra.datasource.service.entity.DataTransformerService;
import com.nathanclaire.alantra.datasource.service.entity.DataTypeService;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataServiceImpl extends BaseProcessService implements DataService {

	@Inject DataTypeService dataTypeService;
	@Inject DataFieldService dataFieldService;
	@Inject DataStructureService dataStructureService;
	@Inject DataFieldTypeService dataFieldTypeService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Inject com.nathanclaire.alantra.datasource.service.entity.DataEntityService dataEntityService;
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataService#getData(java.lang.String)
	 */
	@Override
	public Data getData(String dataCode) throws ApplicationException {
		return (Data) EntityUtil.returnOrThrowIfNull(
				dataEntityService.findByCode(dataCode), ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "Data");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataService#getDataType(java.lang.String)
	 */
	@Override
	public DataType getDataType(String typeCode) throws ApplicationException {
		return (DataType) EntityUtil.returnOrThrowIfNull(
				dataTypeService.findByCode(typeCode), ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "DataType");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataService#getDataField(java.lang.String)
	 */
	@Override
	public DataField getDataField(String fieldCode) throws ApplicationException {
		return (DataField) EntityUtil.returnOrThrowIfNull(
				dataFieldService.findByCode(fieldCode), ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "DataField");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataService#getDataStructure(java.lang.String)
	 */
	@Override
	public DataStructure getDataStructure(String structureCode)	throws ApplicationException {
		return (DataStructure) EntityUtil.returnOrThrowIfNull(
				dataStructureService.findByCode(structureCode), ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "DataStructure");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataService#getDataFieldType(java.lang.String)
	 */
	@Override
	public DataFieldType getDataFieldType(String typeCode) throws ApplicationException {
		return (DataFieldType) EntityUtil.returnOrThrowIfNull(
				dataFieldTypeService.findByCode(typeCode), ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "DataFieldType");
	}


	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataService#createData(com.nathanclaire.alantra.datasource.model.DataType, com.nathanclaire.alantra.datasource.model.DataStructure, java.lang.String, java.lang.String)
	 */
	@Override
	public Data createData(DataType dataType, DataStructure structure, String name, String code) throws ApplicationException {
		// Debug and validate the provided parameters
		logger.debug("Creating data config: Type: {}, structure: {}, name: {}, code", dataType, structure, name, code);
		EntityUtil.returnOrThrowIfParamsArrayContainsNull( new Object[] {dataType, structure, name, code});
		try {
			DataRequest dataRequest = new DataRequest(dataType.getId(), structure.getId(), name, code);
			PropertyUtil.initializeBaseFields(dataRequest);
			return dataEntityService.create(dataRequest);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.BPS_ENTITY_CREATION_ERROR_CD);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataService#getPrimaryEntityCode(com.nathanclaire.alantra.datasource.model.Data)
	 */
	@Override
	public String getBusinessObjectCode(Data data) throws ApplicationException {
		EntityUtil.returnOrThrowIfParamsArrayContainsNull( new Object[] {data, ((data != null) ? data.getDataStructure():null)});
		return data.getDataStructure().getBusinessObjectCd();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataService#getDataStructure(com.nathanclaire.alantra.datasource.model.DataInputJob)
	 */
	@Override
	public DataStructure getDataStructure(DataInputJob inputJob) throws ApplicationException {
		return getData(inputJob).getDataStructure();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataService#getData(com.nathanclaire.alantra.datasource.model.DataInputJob)
	 */
	@Override
	public Data getData(DataInputJob inputJob) throws ApplicationException {
		return getDataInput(inputJob).getData();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataService#getDataInput(com.nathanclaire.alantra.datasource.model.DataInputJob)
	 */
	@Override
	public DataInput getDataInput(DataInputJob inputJob) throws ApplicationException {
		EntityUtil.returnOrThrowIfParamsArrayContainsNull( new Object[] {inputJob});
		return inputJob.getDataInput();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataService#getDataChannel(com.nathanclaire.alantra.datasource.model.DataInputJob)
	 */
	@Override
	public DataChannel getDataChannel(DataInputJob inputJob) throws ApplicationException {
		return getDataInput(inputJob).getDataChannel();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataService#skipFirstRow(com.nathanclaire.alantra.datasource.model.DataStructure)
	 */
	@Override
	public boolean skipFirstRow(DataStructure dataSourceStructure) throws ApplicationException 
	{
		Character skipFirstRowFG = dataSourceStructure.getSkipFirstFg();
		if(skipFirstRowFG == null)	return false;
		if(skipFirstRowFG.equals(BaseEntityService.ENTITY_FLAG_YES)) return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataService#isStrict(com.nathanclaire.alantra.datasource.model.DataStructure)
	 */
	@Override
	public boolean isStrict(DataStructure dataSourceStructure) throws ApplicationException 
	{
		Character strictFG = dataSourceStructure.getStrictFg();
		if(strictFG == null) return false;
		if(strictFG.equals(BaseEntityService.ENTITY_FLAG_YES)) return true;
		return false;
	}
}
