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

import com.nathanclaire.alantra.businessdata.model.Country;
import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.businessdata.request.CountryRequest;
import com.nathanclaire.alantra.businessdata.response.CountryResponse;
import com.nathanclaire.alantra.businessdata.service.entity.CurrencyService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class CountryServiceImpl 
	extends BaseEntityServiceImpl<Country, CountryResponse, CountryRequest> 
	implements CountryService
{
	private static final String LIST_ITEM_CURRENCY = "currency";
	private static final String ENTITY_NAME = "Country";
	private static final String LIST_ACTIVITY_CODE = "LIST_BUSINESSDATA_COUNTRY";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_BUSINESSDATA_COUNTRY";
	
	private Logger logger = LoggerFactory.getLogger(CountryServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	CurrencyService  currencyService;
	
	/**
	 * @param entityClass
	 */
	public CountryServiceImpl() {
		super(Country.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.Country#findById(java.lang.Integer)
	 */
	@Override
	public Country findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.Country#findByCode(java.lang.String)
	 */
	@Override
	public Country findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.Country#findByName(java.lang.String)
	 */
	@Override
	public Country findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.Country#findAll(java.util.Map)
	 */
	@Override
	public List<Country> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.Country#createCountry(com.nathanclaire.alantra.businessdata.rest.request.ServiceRequest)
	 */
	@Override
	public Country create(CountryRequest countryRequest) throws ApplicationException {
		return createInstance(countryRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.Country#deleteCountry(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.Country#updateCountry(com.nathanclaire.alantra.businessdata.rest.request.ServiceRequest)
	 */
	@Override
	public Country update(CountryRequest countryRequest) throws ApplicationException {
		return updateInstance(countryRequest);
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
		List<ListItemResponse> currencys = currencyService.asListItem();
    	
		listItems.put(LIST_ITEM_CURRENCY, currencys); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(Country country: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(country.getId(), country.getCode(), country.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public Country convertRequestToModel(CountryRequest countryRequest) 
     throws ApplicationException {
		Country country = new Country();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(countryRequest, country, allowedEntityFields);
    	//Process many to one relationships
    	if (countryRequest.getCurrencyId() != null)
    	{
    		Currency currency = getEntityManager().find(Currency.class, countryRequest.getCurrencyId());
    		country.setCurrency(currency);
    	}
		return country;
	}
	
	@Override
	public CountryResponse convertModelToResponse(Country model) throws ApplicationException {
		if (model == null) return null;
		CountryResponse countryResponse = new CountryResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, countryResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getCurrency() != null)
			countryResponse.setCurrencyId(model.getCurrency().getId());
			countryResponse.setCurrencyText(model.getCurrency().getName());
		return countryResponse;
	}
}
