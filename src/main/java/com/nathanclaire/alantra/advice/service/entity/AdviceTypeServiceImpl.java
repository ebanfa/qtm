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

import com.nathanclaire.alantra.advice.model.AdviceType;
import com.nathanclaire.alantra.advice.request.AdviceTypeRequest;
import com.nathanclaire.alantra.advice.response.AdviceTypeResponse;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionType;
import com.nathanclaire.alantra.transaction.service.entity.ServiceTransactionTypeService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class AdviceTypeServiceImpl 
	extends BaseEntityServiceImpl<AdviceType, AdviceTypeResponse, AdviceTypeRequest> 
	implements AdviceTypeService
{
	private static final String LIST_ITEM_SERVICETRANSACTIONTYPE = "serviceTransactionType";
	private static final String ENTITY_NAME = "AdviceType";
	private static final String LIST_ACTIVITY_CODE = "LIST_ADVICE_ADVICETYPE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_ADVICE_ADVICETYPE";
	
	private Logger logger = LoggerFactory.getLogger(AdviceTypeServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	ServiceTransactionTypeService  serviceTransactionTypeService;
	
	/**
	 * @param entityClass
	 */
	public AdviceTypeServiceImpl() {
		super(AdviceType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceType#findById(java.lang.Integer)
	 */
	@Override
	public AdviceType findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceType#findByCode(java.lang.String)
	 */
	@Override
	public AdviceType findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceType#findByName(java.lang.String)
	 */
	@Override
	public AdviceType findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceType#findAll(java.util.Map)
	 */
	@Override
	public List<AdviceType> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceType#createAdviceType(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public AdviceType create(AdviceTypeRequest adviceTypeRequest) throws ApplicationException {
		return createInstance(adviceTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceType#deleteAdviceType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceType#updateAdviceType(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public AdviceType update(AdviceTypeRequest adviceTypeRequest) throws ApplicationException {
		return updateInstance(adviceTypeRequest);
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
		List<ListItemResponse> serviceTransactionTypes = serviceTransactionTypeService.asListItem();
    	
		listItems.put(LIST_ITEM_SERVICETRANSACTIONTYPE, serviceTransactionTypes); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(AdviceType advicetype: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(advicetype.getId(), advicetype.getCode(), advicetype.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public AdviceType convertRequestToModel(AdviceTypeRequest adviceTypeRequest) 
     throws ApplicationException {
		AdviceType adviceType = new AdviceType();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(adviceTypeRequest, adviceType, allowedEntityFields);
    	//Process many to one relationships
    	if (adviceTypeRequest.getServiceTransactionTypeId() != null)
    	{
    		ServiceTransactionType serviceTransactionType = getEntityManager().find(ServiceTransactionType.class, adviceTypeRequest.getServiceTransactionTypeId());
    		adviceType.setServiceTransactionType(serviceTransactionType);
    	}
		return adviceType;
	}
	
	@Override
	public AdviceTypeResponse convertModelToResponse(AdviceType model) throws ApplicationException {
		if (model == null) return null;
		AdviceTypeResponse adviceTypeResponse = new AdviceTypeResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, adviceTypeResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getServiceTransactionType() != null)
			adviceTypeResponse.setServiceTransactionTypeId(model.getServiceTransactionType().getId());
			adviceTypeResponse.setServiceTransactionTypeText(model.getServiceTransactionType().getName());
		return adviceTypeResponse;
	}
}
