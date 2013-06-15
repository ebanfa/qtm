/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.transformers;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.Stateless;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.DateUtil;
import com.nathanclaire.alantra.datasource.etl.CellData;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.service.entity.DataFieldTypeService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class NoOperationDataTransformerImpl extends BaseDataTransformer
		implements NoOperationDataTransformer {


	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.DataTransformer#transform(com.nathanclaire.alantra.datasource.etl.CellData, com.nathanclaire.alantra.datasource.model.DataField)
	 */
	@Override
	public CellData transform(CellData cellData, DataField field) throws ApplicationException {
		if(field.getDataFieldType().getCode().equals(DataFieldTypeService.STRING))
			return cellData;
		else if(field.getDataFieldType().getCode().equals(DataFieldTypeService.INTEGER))
		{
			String stringValue = (String) cellData.getData();
			Integer integerValue = Integer.valueOf(stringValue);
			cellData.setData(integerValue);
			return cellData;
		}
		else if(field.getDataFieldType().getCode().equals(DataFieldTypeService.DECIMAL))
		{
			String stringValue = (String) cellData.getData();
			BigDecimal decimalValue = new BigDecimal(stringValue);
			cellData.setData(decimalValue);
			return cellData;
		}
		else if(field.getDataFieldType().getCode().equals(DataFieldTypeService.DATE))
		{
			String stringValue = (String) cellData.getData();
			Date date = DateUtil.convertStringToJavaDate(stringValue);
			cellData.setData(date);
			return cellData;
		}
		else if(field.getDataFieldType().getCode().equals(DataFieldTypeService.RELATIONSHIP))
		{
			String stringValue = (String) cellData.getData();
			Integer integerValue = this.getRelatedIDFromRelatedCode(field, stringValue);
			cellData.setData(integerValue);
			return cellData;
		}
		else
			return cellData;
	}
	

}
