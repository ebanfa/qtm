/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.process;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.CellData;
import com.nathanclaire.alantra.datasource.model.RowData;
import com.nathanclaire.alantra.datasource.model.TableData;
import com.nathanclaire.alantra.datasource.request.CellDataRequest;
import com.nathanclaire.alantra.datasource.request.RowDataRequest;
import com.nathanclaire.alantra.datasource.request.TableDataRequest;
import com.nathanclaire.alantra.datasource.service.entity.CellDataService;
import com.nathanclaire.alantra.datasource.service.entity.RowDataService;
import com.nathanclaire.alantra.datasource.service.entity.TableDataService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class DataSourceModuleServiceImpl extends BaseProcessService implements
		DataSourceModuleService {

	@Inject RowDataService rowDataService;
	@Inject CellDataService cellDataService;
	@Inject TableDataService tableDataService;
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataSourceModuleService#createTableData(com.nathanclaire.alantra.datasource.request.TableDataRequest)
	 */
	@Override
	public TableData createTableData(TableDataRequest tableDataRequest)
			throws ApplicationException {
		return tableDataService.create(tableDataRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataSourceModuleService#createRowData(com.nathanclaire.alantra.datasource.request.RowDataRequest)
	 */
	@Override
	public RowData createRowData(RowDataRequest rowDataRequest)
			throws ApplicationException {
		return rowDataService.create(rowDataRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataSourceModuleService#createCellData(com.nathanclaire.alantra.datasource.request.RowDataRequest)
	 */
	@Override
	public CellData createCellData(CellDataRequest cellDataRequest)
			throws ApplicationException {
		return cellDataService.create(cellDataRequest);
	}


}
