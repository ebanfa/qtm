/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.transformation;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.Stateless;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.DateUtil;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.datasource.annotation.etl.MapBasedTransformer;
import com.nathanclaire.alantra.datasource.etl.util.CellData;
import com.nathanclaire.alantra.datasource.model.DataFieldMap;
import com.nathanclaire.alantra.datasource.model.TransformationMap;
import com.nathanclaire.alantra.datasource.model.TransformationMapItem;

/**
 * This class uses a {@link TransformationMap} to transform
 * the value of a {@link CellData}. The {@link TransformationMap} 
 * contains a source value mapped to a destination value.
 * 
 * The {@link DataFieldMap} will contain instructions on how to 
 * compare the source value with the value in the {@link CellData}.
 * Example are EQUALS, LIKE. So in the case of equals, we would check if the
 * value in the {@link CellData} is equal to the source value, if so then
 * we replace the value in the {@link CellData} with the destination
 * value.
 * 
 * @author Edward Banfa
 *
 */
@Stateless
@MapBasedTransformer
public class MapBasedTransformerImpl extends AbstractDataTransformer {

	private static final String LIKE = "LIKE";
	private static final String EQUALS = "EQUALS";
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.transformation.AbstractDataTransformer#transformStringField(com.nathanclaire.alantra.datasource.etl.util.CellData, com.nathanclaire.alantra.datasource.model.DataFieldMap)
	 */
	@Override
	protected CellData transformStringField(CellData cellData, DataFieldMap fieldMap) 
			throws ApplicationException 
	{		
		TransformationMapItem mapItem = getMapping(cellData, fieldMap);
		// If we don't have a mapping, we return the cell
		// data unchanged
		if(mapItem == null) return cellData;
		logTransformatio(cellData, mapItem);
		try {
			String dataString = cellData.getData().toString();
			// Test for equals
			if(fieldMap.getOpCd().equals(EQUALS))
				if(dataString.equals(mapItem.getSrc()))
					cellData.setData(mapItem.getDst());
			// Test for like
			else if(fieldMap.getOpCd().equals(LIKE)) 
				if(dataString.startsWith(mapItem.getSrc()))
					cellData.setData(mapItem.getDst());
		} catch (Exception e) {
			ExceptionUtil.logException(e);
			setCellDataError(cellData, ENTITY_FIELD_ERROR, e.getMessage());
		}
		return cellData;
	}
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.transformation.AbstractDataTransformer#transformCharacterField(com.nathanclaire.alantra.datasource.etl.util.CellData, com.nathanclaire.alantra.datasource.model.DataFieldMap)
	 */
	@Override
	protected CellData transformCharacterField(CellData cellData, DataFieldMap fieldMap) throws ApplicationException {
		TransformationMapItem mapItem = getMapping(cellData, fieldMap);
		// If we don't have a mapping, we return the cell
		// data unchanged
		if(mapItem == null) return cellData;
		logTransformatio(cellData, mapItem);
		try {
			String dataString = cellData.getData().toString();
			Character dataChar = dataString.charAt(0);
			Character sourceChar = mapItem.getSrc().charAt(0);
			Character destinationChar = mapItem.getDst().charAt(0);
			// Test for equals
			if(fieldMap.getOpCd().equals(EQUALS))
				if(dataChar.equals(sourceChar))
					cellData.setData(destinationChar);
		} catch (Exception e) {
			ExceptionUtil.logException(e);
			setCellDataError(cellData, ENTITY_FIELD_ERROR, e.getMessage());
		}
		return cellData;
	}
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.transformation.AbstractDataTransformer#transformRelationshipField(com.nathanclaire.alantra.datasource.etl.util.CellData, com.nathanclaire.alantra.datasource.model.DataFieldMap)
	 */
	@Override
	protected CellData transformRelationshipField(CellData cellData,
			DataFieldMap fieldMap) throws ApplicationException 
	{
		TransformationMapItem mapItem = getMapping(cellData, fieldMap);
		// If we don't have a mapping, we return the cell
		// data unchanged
		if(mapItem == null) return cellData;
		logTransformatio(cellData, mapItem);
		try {
			String dataString = cellData.getData().toString();
			// Test for equals
			if(fieldMap.getOpCd().equals(EQUALS))
				if(dataString.equals(mapItem.getSrc()))
					cellData.setData(
							getRelatedObjectFromRelatedCode(fieldMap.getDataField(), mapItem.getDst()));			
			// Test for like
			else if(fieldMap.getOpCd().equals(LIKE)) 
					// Since we are dealing with a relationship field
					// we have to now load the related field's data
					cellData.setData(
							getRelatedObjectFromRelatedCode(fieldMap.getDataField(), mapItem.getDst()));
		} catch (Exception e) {
			ExceptionUtil.logException(e);
			setCellDataError(cellData, ENTITY_FIELD_ERROR, e.getMessage());
		}
		return cellData;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.transformation.AbstractDataTransformer#transformDateField(com.nathanclaire.alantra.datasource.etl.util.CellData, com.nathanclaire.alantra.datasource.model.DataFieldMap)
	 */
	@Override
	protected CellData transformDateField(CellData cellData, DataFieldMap fieldMap) 
			throws ApplicationException {

		TransformationMapItem mapItem = getMapping(cellData, fieldMap);
		// If we don't have a mapping, we return the cell
		// data unchanged
		if(mapItem == null) return cellData;
		logTransformatio(cellData, mapItem);

		String dataString = cellData.getData().toString();
		// If the value in the cell data is a 
		// a supported type
		if(cellData.getData() instanceof Date | 
				cellData.getData() instanceof String |
				cellData.getData() instanceof DateTime)
		{
			try {

				Date dataDate = DateUtil.convertStringToJavaDate(
						dataString, fieldMap.getDataField().getFieldFormat());
				Date sourceDate = 
						DateUtil.convertStringToJavaDate(dataString, mapItem.getSrc());
				Date destinationDat = 
						DateUtil.convertStringToJavaDate(dataString, mapItem.getDst());
				// Test for equals
				if(fieldMap.getOpCd().equals(EQUALS))
					if( dataDate.equals(sourceDate)) 
						cellData.setData(destinationDat);
				else
					setCellDataError(cellData, ENTITY_FIELD_ERROR, ErrorCodes.MBT_UNSUPPORTED_DATE_OPCODE_ERROR_MSG);
			} catch (Exception e) {
				ExceptionUtil.logException(e);
				setCellDataError(cellData, ENTITY_FIELD_ERROR, e.getMessage());
			}
		}
		// Else we flag cell with the error
		else {
			setCellDataTypeError(cellData);
		}
		return cellData;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.transformation.AbstractDataTransformer#transformDecimalField(com.nathanclaire.alantra.datasource.etl.util.CellData, com.nathanclaire.alantra.datasource.model.DataFieldMap)
	 */
	@Override
	protected CellData transformDecimalField(CellData cellData,
			DataFieldMap fieldMap) throws ApplicationException 
	{
		TransformationMapItem mapItem = getMapping(cellData, fieldMap);
		// If we don't have a mapping, we return the cell
		// data unchanged
		if(mapItem == null) return cellData;
		logTransformatio(cellData, mapItem);

		String dataString = cellData.getData().toString();
		// If the value in the cell data is a 
		// a supported type
		if(cellData.getData() instanceof Integer | 
				cellData.getData() instanceof Long | 
				cellData.getData() instanceof Float | 
				cellData.getData() instanceof Double |
				cellData.getData() instanceof BigDecimal)
		{
			try {
				BigDecimal dataBigDecimal = new BigDecimal(dataString);
				BigDecimal sourceBigDecimal = new BigDecimal(mapItem.getSrc());
				BigDecimal destinationBigDecimal = new BigDecimal(mapItem.getDst());
				// Test for equals
				if(fieldMap.getOpCd().equals(EQUALS))
					if( dataBigDecimal.equals(sourceBigDecimal)) 
						cellData.setData(destinationBigDecimal);
				else
					setCellDataError(cellData, ENTITY_FIELD_ERROR, ErrorCodes.MBT_UNSUPPORTED_BIGDECIMAL_OPCODE_ERROR_MSG);
			} catch (Exception e) {
				ExceptionUtil.logException(e);
				setCellDataError(cellData, ENTITY_FIELD_ERROR, e.getMessage());
			}
		}
		// Else we flag cell with the error
		else {
			setCellDataTypeError(cellData);
		}
		return cellData;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.transformation.AbstractDataTransformer#transformIntegerField(com.nathanclaire.alantra.datasource.etl.util.CellData, com.nathanclaire.alantra.datasource.model.DataFieldMap)
	 */
	@Override
	protected CellData transformIntegerField(CellData cellData,
			DataFieldMap fieldMap) throws ApplicationException 
	{
		TransformationMapItem mapItem = getMapping(cellData, fieldMap);
		// If we don't have a mapping, we return the cell
		// data unchanged
		if(mapItem == null)	return cellData;
		logTransformatio(cellData, mapItem);

		String dataString = cellData.getData().toString();
		// If the value in the cell data is a 
		// a supported type
		if(cellData.getData() instanceof Integer | 
				cellData.getData() instanceof Long |  
				cellData.getData() instanceof Float |
				cellData.getData() instanceof Double |
				cellData.getData() instanceof BigDecimal)
		{
			try {
				Integer dataInteger = Integer.valueOf(dataString);
				Integer sourceInteger = Integer.valueOf(mapItem.getSrc());
				Integer destinationInteger = Integer.valueOf(mapItem.getDst());
				// Test for equals
				if(fieldMap.getOpCd().equals(EQUALS))
					if(dataInteger.equals(sourceInteger)) 
						cellData.setData(destinationInteger);
				else
					setCellDataError(cellData, ENTITY_FIELD_ERROR, ErrorCodes.MBT_UNSUPPORTED_INT_OPCODE_ERROR_MSG);
			} catch (Exception e) {
				ExceptionUtil.logException(e);
				setCellDataError(cellData, ENTITY_FIELD_ERROR, e.getMessage());
			}
		}
		// Else we flag cell with the error
		else {
			setCellDataTypeError(cellData);
		}
		return cellData;
	}

	private TransformationMapItem getMapping(CellData cellData,	DataFieldMap fieldMap) {
		// Get the mapping
		TransformationMapItem mapping = null;
		TransformationMap transformationMap = fieldMap.getTransformationMap();
		for(TransformationMapItem mapItem : transformationMap.getTransformationMapItems())
			if(mapItem.getSrc().equals(cellData.getData().toString()))
				mapping = mapItem;
		return mapping;
	}



	private void logTransformatio(CellData cellData,
			TransformationMapItem mapItem) {
		logger.debug("Transforming {} with mapItem {}. Source Data: {} " +
				"and Destination Data: {}", cellData, mapItem.getCode(), mapItem.getSrc(), mapItem.getDst());
	}


}
