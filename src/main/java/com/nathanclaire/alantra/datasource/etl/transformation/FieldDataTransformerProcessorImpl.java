/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.transformation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.datasource.annotation.etl.FieldDataTransformerProcessor;
import com.nathanclaire.alantra.datasource.etl.util.CellData;
import com.nathanclaire.alantra.datasource.etl.util.RowData;
import com.nathanclaire.alantra.datasource.etl.util.TableData;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.model.DataFieldMap;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
@FieldDataTransformerProcessor
public class FieldDataTransformerProcessorImpl extends BaseDataProcessor  implements DataProcessor{

	@Inject DataTransformerProducer transformerProducer;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.processors.BaseDataProcessor#processTableDataRow(com.nathanclaire.alantra.datasource.etl.RowDataLite, com.nathanclaire.alantra.datasource.etl.TableDataLite, java.util.Set)
	 */
	@Override
	protected RowData processTableDataRow(RowData currentRow, TableData data, Set<DataField> fields) throws ApplicationException {
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{currentRow, data, fields}, 
				"FieldDataTransformerProcessorImpl.processTableDataRow");
		RowData processedRow = new RowData();
		List<CellData> processedCells = new ArrayList<CellData>();
		for (CellData cellData: currentRow.getColumns()){
			if(!cellData.isErrors())
				processedCells.addAll(processCellData(currentRow, fields, cellData));
		}
		for (CellData cellData: virtualFieldsToCellData(currentRow, fields)){
			if(!cellData.isErrors())
				processedCells.addAll(processCellData(currentRow, fields, cellData));
		}
		processedRow.setColumns(processedCells);
		return processedRow;
	}

	/**
	 * A single {@link CellData} can be processed into
	 * multiple resulting {@link CellData}s.
	 * 
	 * @param currentRow
	 * @param fields
	 * @param cellData
	 * @return
	 * @throws ApplicationException
	 */
	private List<CellData> processCellData(RowData currentRow, Set<DataField> fields, CellData cellData)
			throws ApplicationException 
	{
		// The data field the points to the current cell data
		DataField dataField = null;
		// The results of the transformation process
		List<CellData> transformationResults = new ArrayList<CellData>();
		// Get the field that references this cell data
		for(DataField field : fields)
			if(field.getCode().equals(cellData.getName()))
				dataField = field;
		if(dataField == null)
			throw new ApplicationException(ErrorCodes.DP_FIELD_TRANSFORMATION_ERROR_CD, "Dat field not found");
		
		// Get the field transformer mappings
		Set<DataFieldMap> dataFieldMappings = dataField.getDataFieldMaps();
		// For each mapping get the transformer, and
		// call the transformer to transform the cell data
		for(DataFieldMap fieldMap : dataFieldMappings) {
			String transformerCode = fieldMap.getDataTransformer().getCode();
			DataTransformer transformer = transformerProducer.getDataTransformer(transformerCode);
			if(transformer == null) 
				throw new ApplicationException(ErrorCodes.DP_DATA_TRANSFORMER_NOT_FOUND_ERROR_CD);
			try 
			{
				CellData transformationOutput = transformer.transform(cellData, fieldMap);
				transformationOutput.setBusinessObjectFieldCd(fieldMap.getBusinessObjectFieldCd());
				transformationOutput.setDataFieldId(dataField.getId());
				transformationResults.add(transformationOutput);
				if(cellData.isErrors())	currentRow.setErrors(true);
			} catch (ApplicationException e) {
				ExceptionUtil.processException(e, ErrorCodes.DP_FIELD_TRANSFORMATION_ERROR_CD);
			}
		}
		return transformationResults;
	}

	private List<CellData> virtualFieldsToCellData(RowData currentRow, Set<DataField> fields) throws ApplicationException{
		List<CellData> processedVirtualFields = new ArrayList<CellData>();
		for(DataField field : fields){
			if(StringUtil.flagToBoolean(field.getVirtualField())){
				CellData cellData = new CellData();
				cellData.setData(field.getDefaultValue());
				cellData.setName(field.getCode());
				cellData.setDataType(field.getDataFieldType().getCode());
				processedVirtualFields.add(cellData);
			}
		}
		return processedVirtualFields;
	}
}
