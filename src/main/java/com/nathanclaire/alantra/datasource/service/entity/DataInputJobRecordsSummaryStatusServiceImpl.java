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

import com.nathanclaire.alantra.datasource.model.DataInputJobRecordsSummaryStatus;
import com.nathanclaire.alantra.datasource.request.DataInputJobRecordsSummaryStatusRequest;
import com.nathanclaire.alantra.datasource.response.DataInputJobRecordsSummaryStatusResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataInputJobRecordsSummaryStatusServiceImpl 
	extends BaseEntityServiceImpl<DataInputJobRecordsSummaryStatus, DataInputJobRecordsSummaryStatusResponse, DataInputJobRecordsSummaryStatusRequest> 
	implements DataInputJobRecordsSummaryStatusService
{
	private static final String ENTITY_NAME = "DataInputJobRecordsSummaryStatus";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATAINPUTJOBRECORDSSUMMARYSTATUS";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATAINPUTJOBRECORDSSUMMARYSTATUS";
	
	private Logger logger = LoggerFactory.getLogger(DataInputJobRecordsSummaryStatusServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public DataInputJobRecordsSummaryStatusServiceImpl() {
		super(DataInputJobRecordsSummaryStatus.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobRecordsSummaryStatus#findById(java.lang.Integer)
	 */
	@Override
	public DataInputJobRecordsSummaryStatus findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobRecordsSummaryStatus#findByCode(java.lang.String)
	 */
	@Override
	public DataInputJobRecordsSummaryStatus findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobRecordsSummaryStatus#findByName(java.lang.String)
	 */
	@Override
	public DataInputJobRecordsSummaryStatus findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobRecordsSummaryStatus#findAll(java.util.Map)
	 */
	@Override
	public List<DataInputJobRecordsSummaryStatus> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobRecordsSummaryStatus#createDataInputJobRecordsSummaryStatus(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataInputJobRecordsSummaryStatus create(DataInputJobRecordsSummaryStatusRequest dataInputJobRecordsSummaryStatusRequest) throws ApplicationException {
		return createInstance(dataInputJobRecordsSummaryStatusRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobRecordsSummaryStatus#deleteDataInputJobRecordsSummaryStatus(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobRecordsSummaryStatus#updateDataInputJobRecordsSummaryStatus(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataInputJobRecordsSummaryStatus update(DataInputJobRecordsSummaryStatusRequest dataInputJobRecordsSummaryStatusRequest) throws ApplicationException {
		return updateInstance(dataInputJobRecordsSummaryStatusRequest);
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
    	
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(DataInputJobRecordsSummaryStatus datainputjobrecordssummarystatus: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(datainputjobrecordssummarystatus.getId(), datainputjobrecordssummarystatus.getCode(), datainputjobrecordssummarystatus.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataInputJobRecordsSummaryStatus convertRequestToModel(DataInputJobRecordsSummaryStatusRequest dataInputJobRecordsSummaryStatusRequest) 
     throws ApplicationException {
		DataInputJobRecordsSummaryStatus dataInputJobRecordsSummaryStatus = new DataInputJobRecordsSummaryStatus();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(dataInputJobRecordsSummaryStatusRequest, dataInputJobRecordsSummaryStatus, allowedEntityFields);
    	//Process many to one relationships
		return dataInputJobRecordsSummaryStatus;
	}
	
	@Override
	public DataInputJobRecordsSummaryStatusResponse convertModelToResponse(DataInputJobRecordsSummaryStatus model) throws ApplicationException {
		if (model == null) return null;
		DataInputJobRecordsSummaryStatusResponse dataInputJobRecordsSummaryStatusResponse = new DataInputJobRecordsSummaryStatusResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, dataInputJobRecordsSummaryStatusResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return dataInputJobRecordsSummaryStatusResponse;
	}
}
