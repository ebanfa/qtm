/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.loggers;

import javax.ejb.Stateless;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.DataInputLogger;
import com.nathanclaire.alantra.datasource.etl.TableData;
import com.nathanclaire.alantra.datasource.model.DataInputJob;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class DataInputLoggerImpl implements DataInputLogger {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.DataInputLogger#logDataInput(com.nathanclaire.alantra.datasource.model.DataInputJob, com.nathanclaire.alantra.datasource.etl.TableData)
	 */
	@Override
	public void logDataInput(DataInputJob inputJob, TableData tableData) throws ApplicationException {
		// TODO Auto-generated method stub

	}

}
