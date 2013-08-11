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

import com.nathanclaire.alantra.datasource.model.CellData;
import com.nathanclaire.alantra.datasource.model.RowData;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.request.CellDataRequest;
import com.nathanclaire.alantra.datasource.response.CellDataResponse;
import com.nathanclaire.alantra.datasource.service.entity.RowDataService;
import com.nathanclaire.alantra.datasource.service.entity.DataFieldService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class CellDataServiceImpl 
	extends BaseEntityServiceImpl<CellData, CellDataResponse, CellDataRequest> 
	implements CellDataService
{
	private static final String LIST_ITEM_ROWDATA = "rowData";
	private static final String LIST_ITEM_DATAFIELD = "dataField";
	private static final String ENTITY_NAME = "CellData";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_CELLDATA";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_CELLDATA";
	
	private Logger logger = LoggerFactory.getLogger(CellDataServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	RowDataService  rowDataService;
	@Inject
	DataFieldService  dataFieldService;
	
	/**
	 * @param entityClass
	 */
	public CellDataServiceImpl() {
		super(CellData.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.CellData#findById(java.lang.Integer)
	 */
	@Override
	public CellData findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.CellData#findByCode(java.lang.String)
	 */
	@Override
	public CellData findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.CellData#findByName(java.lang.String)
	 */
	@Override
	public CellData findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.CellData#findAll(java.util.Map)
	 */
	@Override
	public List<CellData> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.CellData#createCellData(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public CellData create(CellDataRequest cellDataRequest) throws ApplicationException {
		return createInstance(cellDataRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.CellData#deleteCellData(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.CellData#updateCellData(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public CellData update(CellDataRequest cellDataRequest) throws ApplicationException {
		return updateInstance(cellDataRequest);
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
		List<ListItemResponse> rowDatas = rowDataService.asListItem();
		List<ListItemResponse> dataFields = dataFieldService.asListItem();
    	
		listItems.put(LIST_ITEM_ROWDATA, rowDatas); 
		listItems.put(LIST_ITEM_DATAFIELD, dataFields); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(CellData celldata: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(celldata.getId(), celldata.getCode(), celldata.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public CellData convertRequestToModel(CellDataRequest cellDataRequest) 
     throws ApplicationException {
		CellData cellData = new CellData();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(cellDataRequest, cellData, allowedEntityFields);
    	//Process many to one relationships
    	if (cellDataRequest.getRowDataId() != null)
    	{
    		RowData rowData = getEntityManager().find(RowData.class, cellDataRequest.getRowDataId());
    		cellData.setRowData(rowData);
    	}
    	if (cellDataRequest.getDataFieldId() != null)
    	{
    		DataField dataField = getEntityManager().find(DataField.class, cellDataRequest.getDataFieldId());
    		cellData.setDataField(dataField);
    	}
		return cellData;
	}
	
	@Override
	public CellDataResponse convertModelToResponse(CellData model) throws ApplicationException {
		if (model == null) return null;
		CellDataResponse cellDataResponse = new CellDataResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, cellDataResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getRowData() != null)
			cellDataResponse.setRowDataId(model.getRowData().getId());
			cellDataResponse.setRowDataText(model.getRowData().getName());
		if(model.getDataField() != null)
			cellDataResponse.setDataFieldId(model.getDataField().getId());
			cellDataResponse.setDataFieldText(model.getDataField().getName());
		return cellDataResponse;
	}
}
