/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataField;

/**
 * @author Edward Banfa 
 *
 */
public interface DataTransformer 
{
	public CellData transform(CellData cellData, DataField field) throws ApplicationException;
}
