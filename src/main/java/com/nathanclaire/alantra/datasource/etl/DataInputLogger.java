/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataInputJob;

/**
 * @author Edward Banfa 
 *
 */
public interface DataInputLogger {

	public void logDataInput(DataInputJob inputJob, TableData tableData) throws ApplicationException;
}
