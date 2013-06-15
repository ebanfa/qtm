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

import com.nathanclaire.alantra.datasource.model.DataInputJobSummaryStatus;
import com.nathanclaire.alantra.datasource.request.DataInputJobSummaryStatusRequest;
import com.nathanclaire.alantra.datasource.response.DataInputJobSummaryStatusResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataInputJobSummaryStatusServiceImpl 
	extends BaseEntityServiceImpl<DataInputJobSummaryStatus, DataInputJobSummaryStatusResponse, DataInputJobSummaryStatusRequest> 
	implements DataInputJobSummaryStatusService
{
	private static final String ENTITY_NAME = "DataInputJobSummaryStatus";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATAINPUTJOBSUMMARYSTATUS";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATAINPUTJOBSUMMARYSTATUS";
	
	private Logger logger = LoggerFactory.getLogger(DataInputJobSummaryStatusServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public DataInputJobSummaryStatusServiceImpl() {
		super(DataInputJobSummaryStatus.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobSummaryStatus#findById(java.lang.Integer)
	 */
	@Override
	public DataInputJobSummaryStatus findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobSummaryStatus#findByCode(java.lang.String)
	 */
	@Override
	public DataInputJobSummaryStatus findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobSummaryStatus#findByName(java.lang.String)
	 */
	@Override
	public DataInputJobSummaryStatus findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobSummaryStatus#findAll(java.util.Map)
	 */
	@Override
	public List<DataInputJobSummaryStatus> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobSummaryStatus#createDataInputJobSummaryStatus(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataInputJobSummaryStatus create(DataInputJobSummaryStatusRequest dataInputJobSummaryStatusRequest) throws ApplicationException {
		return createInstance(dataInputJobSummaryStatusRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobSummaryStatus#deleteDataInputJobSummaryStatus(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobSummaryStatus#updateDataInputJobSummaryStatus(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataInputJobSummaryStatus update(DataInputJobSummaryStatusRequest dataInputJobSummaryStatusRequest) throws ApplicationException {
		return updateInstance(dataInputJobSummaryStatusRequest);
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
		for(DataInputJobSummaryStatus datainputjobsummarystatus: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(datainputjobsummarystatus.getId(), datainputjobsummarystatus.getCode(), datainputjobsummarystatus.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataInputJobSummaryStatus convertRequestToModel(DataInputJobSummaryStatusRequest dataInputJobSummaryStatusRequest) 
     throws ApplicationException {
		DataInputJobSummaryStatus dataInputJobSummaryStatus = new DataInputJobSummaryStatus();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(dataInputJobSummaryStatusRequest, dataInputJobSummaryStatus, allowedEntityFields);
    	//Process many to one relationships
		return dataInputJobSummaryStatus;
	}
	
	@Override
	public DataInputJobSummaryStatusResponse convertModelToResponse(DataInputJobSummaryStatus model) throws ApplicationException {
		if (model == null) return null;
		DataInputJobSummaryStatusResponse dataInputJobSummaryStatusResponse = new DataInputJobSummaryStatusResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, dataInputJobSummaryStatusResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return dataInputJobSummaryStatusResponse;
	}
}
