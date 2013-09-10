/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.process;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.extraction.DataExtractor;
import com.nathanclaire.alantra.datasource.etl.loading.DataLoader;
import com.nathanclaire.alantra.datasource.etl.transformation.DataProcessor;
import com.nathanclaire.alantra.datasource.etl.transformation.DataTransformer;
import com.nathanclaire.alantra.datasource.model.Data;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.model.DataFieldType;
import com.nathanclaire.alantra.datasource.model.DataInput;
import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.datasource.model.DataStructure;
import com.nathanclaire.alantra.datasource.model.DataType;

/**
 * @author Edward Banfa
 * 
 */
public interface DataService {


	public Data getData(String dataCode) throws ApplicationException;

	public Data getData(DataInputJob inputJob) throws ApplicationException;

	public DataType getDataType(String typeCode) throws ApplicationException;

	public DataField getDataField(String fieldCode) throws ApplicationException;

	public DataInput getDataInput(DataInputJob inputJob) throws ApplicationException;

	public DataChannel getDataChannel(DataInputJob inputJob) throws ApplicationException;
	
	public boolean skipFirstRow(DataStructure dataSourceStructure) throws ApplicationException;
	
	public boolean isStrict(DataStructure dataSourceStructure)throws ApplicationException;

	public DataStructure getDataStructure(String structureCode)	throws ApplicationException;

	public DataStructure getDataStructure(DataInputJob inputJob) throws ApplicationException;

	public DataFieldType getDataFieldType(String typeCode) throws ApplicationException;

	public Data createData(DataType dataType, DataStructure structure, String name, String code) throws ApplicationException;

	public String getPrimaryEntityCode(Data data) throws ApplicationException;
	
	public String getSecondaryEntityCode(Data data) throws ApplicationException;

}
