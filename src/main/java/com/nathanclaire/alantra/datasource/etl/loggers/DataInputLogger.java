/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.loggers;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.util.TableData;
import com.nathanclaire.alantra.datasource.model.DataInputJob;

/**
 * @author Edward Banfa 
 *
 */
public interface DataInputLogger {

	public TableData logDataInput(DataInputJob inputJob, TableData tableData) throws ApplicationException;
}
