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

import com.nathanclaire.alantra.datasource.model.DataTable;
import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.datasource.request.TableDataRequest;
import com.nathanclaire.alantra.datasource.response.TableDataResponse;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class TableDataServiceImpl 
	extends BaseEntityServiceImpl<DataTable, TableDataResponse, TableDataRequest> 
	implements TableDataService
{
	private static final String LIST_ITEM_DATAINPUTJOB = "dataInputJob";
	private static final String ENTITY_NAME = "TableData";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_TABLEDATA";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_TABLEDATA";
	
	private Logger logger = LoggerFactory.getLogger(TableDataServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	DataInputJobService  dataInputJobService;
	
	/**
	 * @param entityClass
	 */
	public TableDataServiceImpl() {
		super(DataTable.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.TableData#findById(java.lang.Integer)
	 */
	@Override
	public DataTable findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.TableData#findByCode(java.lang.String)
	 */
	@Override
	public DataTable findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.TableData#findByName(java.lang.String)
	 */
	@Override
	public DataTable findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.TableData#findAll(java.util.Map)
	 */
	@Override
	public List<DataTable> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.TableData#createTableData(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataTable create(TableDataRequest tableDataRequest) throws ApplicationException {
		return createInstance(tableDataRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.TableData#deleteTableData(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.TableData#updateTableData(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataTable update(TableDataRequest tableDataRequest) throws ApplicationException {
		return updateInstance(tableDataRequest);
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
		List<ListItemResponse> dataInputJobs = dataInputJobService.asListItem();
    	
		listItems.put(LIST_ITEM_DATAINPUTJOB, dataInputJobs); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(DataTable tabledata: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(tabledata.getId(), tabledata.getCode(), tabledata.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataTable convertRequestToModel(TableDataRequest tableDataRequest) 
     throws ApplicationException {
		DataTable dataTable = new DataTable();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(tableDataRequest, dataTable, allowedEntityFields);
    	//Process many to one relationships
    	if (tableDataRequest.getDataInputJobId() != null)
    	{
    		DataInputJob dataInputJob = getEntityManager().find(DataInputJob.class, tableDataRequest.getDataInputJobId());
    		dataTable.setDataInputJob(dataInputJob);
    	}
		return dataTable;
	}
	
	@Override
	public TableDataResponse convertModelToResponse(DataTable model) throws ApplicationException {
		if (model == null) return null;
		TableDataResponse tableDataResponse = new TableDataResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, tableDataResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getDataInputJob() != null)
			tableDataResponse.setDataInputJobId(model.getDataInputJob().getId());
			tableDataResponse.setDataInputJobText(model.getDataInputJob().getName());
		return tableDataResponse;
	}
}
