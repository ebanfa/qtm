/**
 * 
 */
package com.nathanclaire.alantra.customer.service.entity;

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

import com.nathanclaire.alantra.customer.model.LimitType;
import com.nathanclaire.alantra.customer.request.LimitTypeRequest;
import com.nathanclaire.alantra.customer.response.LimitTypeResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class LimitTypeServiceImpl 
	extends BaseEntityServiceImpl<LimitType, LimitTypeResponse, LimitTypeRequest> 
	implements LimitTypeService
{
	private static final String ENTITY_NAME = "LimitType";
	private static final String LIST_ACTIVITY_CODE = "LIST_CUSTOMER_LIMITTYPE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CUSTOMER_LIMITTYPE";
	
	private Logger logger = LoggerFactory.getLogger(LimitTypeServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public LimitTypeServiceImpl() {
		super(LimitType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.LimitType#findById(java.lang.Integer)
	 */
	@Override
	public LimitType findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.LimitType#findByCode(java.lang.String)
	 */
	@Override
	public LimitType findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.LimitType#findByName(java.lang.String)
	 */
	@Override
	public LimitType findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.LimitType#findAll(java.util.Map)
	 */
	@Override
	public List<LimitType> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.LimitType#createLimitType(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public LimitType create(LimitTypeRequest limitTypeRequest) throws ApplicationException {
		return createInstance(limitTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.LimitType#deleteLimitType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.LimitType#updateLimitType(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public LimitType update(LimitTypeRequest limitTypeRequest) throws ApplicationException {
		return updateInstance(limitTypeRequest);
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
		for(LimitType limittype: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(limittype.getId(), limittype.getCode(), limittype.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public LimitType convertRequestToModel(LimitTypeRequest limitTypeRequest) 
     throws ApplicationException {
		LimitType limitType = new LimitType();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(limitTypeRequest, limitType, allowedEntityFields);
    	//Process many to one relationships
		return limitType;
	}
	
	@Override
	public LimitTypeResponse convertModelToResponse(LimitType model) throws ApplicationException {
		if (model == null) return null;
		LimitTypeResponse limitTypeResponse = new LimitTypeResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, limitTypeResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return limitTypeResponse;
	}
}
