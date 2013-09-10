/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.util.TableData;
import com.nathanclaire.alantra.datasource.model.DataInputJob;

/**
 * @author Edward Banfa
 *
 */
public interface ETLService {
	
	public void extractData(DataInputJob inputJob) throws ApplicationException;
	
	public void transformData(DataInputJob inputJob, TableData tableData) throws ApplicationException;
	
	public void loadData(DataInputJob inputJob, TableData tableData) throws ApplicationException;

}
