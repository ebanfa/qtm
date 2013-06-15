/**
 * 
 */
package com.nathanclaire.alantra.businessdata.service.entity;

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

import com.nathanclaire.alantra.businessdata.model.BusinessUnit;
import com.nathanclaire.alantra.businessdata.model.Country;
import com.nathanclaire.alantra.businessdata.request.BusinessUnitRequest;
import com.nathanclaire.alantra.businessdata.response.BusinessUnitResponse;
import com.nathanclaire.alantra.businessdata.service.entity.CountryService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class BusinessUnitServiceImpl 
	extends BaseEntityServiceImpl<BusinessUnit, BusinessUnitResponse, BusinessUnitRequest> 
	implements BusinessUnitService
{
	private static final String LIST_ITEM_COUNTRY = "country";
	private static final String ENTITY_NAME = "BusinessUnit";
	private static final String LIST_ACTIVITY_CODE = "LIST_BUSINESSDATA_BUSINESSUNIT";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_BUSINESSDATA_BUSINESSUNIT";
	
	private Logger logger = LoggerFactory.getLogger(BusinessUnitServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	CountryService  countryService;
	
	/**
	 * @param entityClass
	 */
	public BusinessUnitServiceImpl() {
		super(BusinessUnit.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.BusinessUnit#findById(java.lang.Integer)
	 */
	@Override
	public BusinessUnit findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.BusinessUnit#findByCode(java.lang.String)
	 */
	@Override
	public BusinessUnit findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.BusinessUnit#findByName(java.lang.String)
	 */
	@Override
	public BusinessUnit findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.BusinessUnit#findAll(java.util.Map)
	 */
	@Override
	public List<BusinessUnit> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.BusinessUnit#createBusinessUnit(com.nathanclaire.alantra.businessdata.rest.request.ServiceRequest)
	 */
	@Override
	public BusinessUnit create(BusinessUnitRequest businessUnitRequest) throws ApplicationException {
		return createInstance(businessUnitRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.BusinessUnit#deleteBusinessUnit(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.BusinessUnit#updateBusinessUnit(com.nathanclaire.alantra.businessdata.rest.request.ServiceRequest)
	 */
	@Override
	public BusinessUnit update(BusinessUnitRequest businessUnitRequest) throws ApplicationException {
		return updateInstance(businessUnitRequest);
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
		List<ListItemResponse> countrys = countryService.asListItem();
    	
		listItems.put(LIST_ITEM_COUNTRY, countrys); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(BusinessUnit businessunit: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(businessunit.getId(), businessunit.getCode(), businessunit.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public BusinessUnit convertRequestToModel(BusinessUnitRequest businessUnitRequest) 
     throws ApplicationException {
		BusinessUnit businessUnit = new BusinessUnit();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(businessUnitRequest, businessUnit, allowedEntityFields);
    	//Process many to one relationships
    	if (businessUnitRequest.getCountryId() != null)
    	{
    		Country country = getEntityManager().find(Country.class, businessUnitRequest.getCountryId());
    		businessUnit.setCountry(country);
    	}
		return businessUnit;
	}
	
	@Override
	public BusinessUnitResponse convertModelToResponse(BusinessUnit model) throws ApplicationException {
		if (model == null) return null;
		BusinessUnitResponse businessUnitResponse = new BusinessUnitResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, businessUnitResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getCountry() != null)
			businessUnitResponse.setCountryId(model.getCountry().getId());
			businessUnitResponse.setCountryText(model.getCountry().getName());
		return businessUnitResponse;
	}
}
