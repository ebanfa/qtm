/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;

import com.nathanclaire.alantra.datasource.model.DataRow;
import com.nathanclaire.alantra.datasource.model.DataTable;
import com.nathanclaire.alantra.datasource.request.RowDataRequest;
import com.nathanclaire.alantra.datasource.response.RowDataResponse;
import com.nathanclaire.alantra.datasource.service.entity.TableDataService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class RowDataServiceImpl 
	extends BaseEntityServiceImpl<DataRow, RowDataResponse, RowDataRequest> 
	implements RowDataService
{
	private static final String LIST_ITEM_TABLEDATA = "tableData";
	private static final String ENTITY_NAME = "RowData";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_ROWDATA";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_ROWDATA";
	
	private Logger logger = LoggerFactory.getLogger(RowDataServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	TableDataService  tableDataService;
	
	/**
	 * @param entityClass
	 */
	public RowDataServiceImpl() {
		super(DataRow.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.RowData#findById(java.lang.Integer)
	 */
	@Override
	public DataRow findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.RowData#findByCode(java.lang.String)
	 */
	@Override
	public DataRow findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.RowData#findByName(java.lang.String)
	 */
	@Override
	public DataRow findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.RowData#findAll(java.util.Map)
	 */
	@Override
	public List<DataRow> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.RowData#createRowData(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataRow create(RowDataRequest rowDataRequest) throws ApplicationException {
		return createInstance(rowDataRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.RowData#deleteRowData(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.RowData#updateRowData(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataRow update(RowDataRequest rowDataRequest) throws ApplicationException {
		return updateInstance(rowDataRequest);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getListActivityCode()
	 */
	@Override
	public String getListActivityCode() throws ApplicationException {
		return LIST_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEditActivityCode()
	 */
	@Override
	public String getEditActivityCode() throws ApplicationException {
		return EDIT_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityName()
	 */
	@Override
	public String getEntityName() throws ApplicationException {
		return ENTITY_NAME;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityFields()
	 */
	@Override
	public List<ApplicationEntityField> getEntityFields() throws ApplicationException {
		return applicationEntityService.getFieldsForEntity(ENTITY_NAME);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#relatedEntitesToListItems()
	 */
	@Override
	public Map<String, List<ListItemResponse>> relatedEntitesToListItems() 
	 throws ApplicationException {
		Map<String, List<ListItemResponse>> listItems = new HashMap<String, List<ListItemResponse>>(); 
		List<ListItemResponse> tableDatas = tableDataService.asListItem();
    	
		listItems.put(LIST_ITEM_TABLEDATA, tableDatas); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(DataRow rowdata: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(rowdata.getId(), rowdata.getCode(), rowdata.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataRow convertRequestToModel(RowDataRequest rowDataRequest) 
     throws ApplicationException {
		DataRow dataRow = new DataRow();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(rowDataRequest, dataRow, allowedEntityFields);
    	//Process many to one relationships
    	if (rowDataRequest.getTableDataId() != null)
    	{
    		DataTable dataTable = getEntityManager().find(DataTable.class, rowDataRequest.getTableDataId());
    		dataRow.setDataTable(dataTable);
    	}
		return dataRow;
	}
	
	@Override
	public RowDataResponse convertModelToResponse(DataRow model) throws ApplicationException {
		if (model == null) return null;
		RowDataResponse rowDataResponse = new RowDataResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, rowDataResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getDataTable() != null)
			rowDataResponse.setTableDataId(model.getDataTable().getId());
			rowDataResponse.setTableDataText(model.getDataTable().getName());
		return rowDataResponse;
	}
}
