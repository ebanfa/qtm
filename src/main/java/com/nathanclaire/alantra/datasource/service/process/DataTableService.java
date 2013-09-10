/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataCell;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.datasource.model.DataRow;
import com.nathanclaire.alantra.datasource.model.DataTable;

/**
 * @author Edward Banfa
 * 
 */
public interface DataTableService {

	public DataTable getDataTable(String tableCode) throws ApplicationException;

	public DataTable createDataTable(DataInputJob inputJob,
			String primEntityNm, Integer primEntityRd, Integer primEntityCreated, 
			Integer primEntityRejected, Integer totalEntityRd, Integer totalEntityCreated,
			Integer totalEntityRejected, Character importStatusFg) throws ApplicationException;

	public DataRow createDataRow(DataTable dataTable, String name, String code,
			Character isHeaderFg, Character importStatusFg)	throws ApplicationException;

	public DataCell createDataCell(DataRow dataRow, DataField dataField, String name, 
			String code, Character importStatusFg, String data) throws ApplicationException;
}
