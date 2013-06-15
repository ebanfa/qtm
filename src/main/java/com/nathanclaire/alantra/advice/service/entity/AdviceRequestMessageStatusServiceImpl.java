/**
 * 
 */
package com.nathanclaire.alantra.advice.service.entity;

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

import com.nathanclaire.alantra.advice.model.AdviceRequestMessageStatus;
import com.nathanclaire.alantra.advice.request.AdviceRequestMessageStatusRequest;
import com.nathanclaire.alantra.advice.response.AdviceRequestMessageStatusResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class AdviceRequestMessageStatusServiceImpl 
	extends BaseEntityServiceImpl<AdviceRequestMessageStatus, AdviceRequestMessageStatusResponse, AdviceRequestMessageStatusRequest> 
	implements AdviceRequestMessageStatusService
{
	private static final String ENTITY_NAME = "AdviceRequestMessageStatus";
	private static final String LIST_ACTIVITY_CODE = "LIST_ADVICE_ADVICEREQUESTMESSAGESTATUS";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_ADVICE_ADVICEREQUESTMESSAGESTATUS";
	
	private Logger logger = LoggerFactory.getLogger(AdviceRequestMessageStatusServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public AdviceRequestMessageStatusServiceImpl() {
		super(AdviceRequestMessageStatus.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceRequestMessageStatus#findById(java.lang.Integer)
	 */
	@Override
	public AdviceRequestMessageStatus findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceRequestMessageStatus#findByCode(java.lang.String)
	 */
	@Override
	public AdviceRequestMessageStatus findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceRequestMessageStatus#findByName(java.lang.String)
	 */
	@Override
	public AdviceRequestMessageStatus findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceRequestMessageStatus#findAll(java.util.Map)
	 */
	@Override
	public List<AdviceRequestMessageStatus> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceRequestMessageStatus#createAdviceRequestMessageStatus(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public AdviceRequestMessageStatus create(AdviceRequestMessageStatusRequest adviceRequestMessageStatusRequest) throws ApplicationException {
		return createInstance(adviceRequestMessageStatusRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceRequestMessageStatus#deleteAdviceRequestMessageStatus(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceRequestMessageStatus#updateAdviceRequestMessageStatus(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public AdviceRequestMessageStatus update(AdviceRequestMessageStatusRequest adviceRequestMessageStatusRequest) throws ApplicationException {
		return updateInstance(adviceRequestMessageStatusRequest);
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
		for(AdviceRequestMessageStatus advicerequestmessagestatus: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(advicerequestmessagestatus.getId(), advicerequestmessagestatus.getCode(), advicerequestmessagestatus.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public AdviceRequestMessageStatus convertRequestToModel(AdviceRequestMessageStatusRequest adviceRequestMessageStatusRequest) 
     throws ApplicationException {
		AdviceRequestMessageStatus adviceRequestMessageStatus = new AdviceRequestMessageStatus();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(adviceRequestMessageStatusRequest, adviceRequestMessageStatus, allowedEntityFields);
    	//Process many to one relationships
		return adviceRequestMessageStatus;
	}
	
	@Override
	public AdviceRequestMessageStatusResponse convertModelToResponse(AdviceRequestMessageStatus model) throws ApplicationException {
		if (model == null) return null;
		AdviceRequestMessageStatusResponse adviceRequestMessageStatusResponse = new AdviceRequestMessageStatusResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, adviceRequestMessageStatusResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return adviceRequestMessageStatusResponse;
	}
}
