/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.CellData;
import com.nathanclaire.alantra.datasource.model.RowData;
import com.nathanclaire.alantra.datasource.model.TableData;
import com.nathanclaire.alantra.datasource.request.CellDataRequest;
import com.nathanclaire.alantra.datasource.request.RowDataRequest;
import com.nathanclaire.alantra.datasource.request.TableDataRequest;

/**
 * @author Edward Banfa 
 *
 */
public interface DataSourceModuleService {

	public TableData  createTableData(TableDataRequest tableDataRequest) throws ApplicationException;

	public RowData createRowData(RowDataRequest rowDataRequest) throws ApplicationException;

	public CellData createCellData(CellDataRequest rowDataRequest) throws ApplicationException;

}
