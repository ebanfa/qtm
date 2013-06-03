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

import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.businessdata.request.CurrencyRequest;
import com.nathanclaire.alantra.businessdata.response.CurrencyResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class CurrencyServiceImpl 
	extends BaseEntityServiceImpl<Currency, CurrencyResponse, CurrencyRequest> 
	implements CurrencyService
{
	private static final String ENTITY_NAME = "Currency";
	private static final String LIST_ACTIVITY_CODE = "LIST_BUSINESSDATA_CURRENCY";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_BUSINESSDATA_CURRENCY";
	
	private Logger logger = LoggerFactory.getLogger(CurrencyServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public CurrencyServiceImpl() {
		super(Currency.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.Currency#findById(java.lang.Integer)
	 */
	@Override
	public Currency findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.Currency#findByCode(java.lang.String)
	 */
	@Override
	public Currency findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.Currency#findByName(java.lang.String)
	 */
	@Override
	public Currency findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.Currency#findAll(java.util.Map)
	 */
	@Override
	public List<Currency> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.Currency#createCurrency(com.nathanclaire.alantra.businessdata.rest.request.ServiceRequest)
	 */
	@Override
	public Currency create(CurrencyRequest currencyRequest) throws ApplicationException {
		return createInstance(currencyRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.Currency#deleteCurrency(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.Currency#updateCurrency(com.nathanclaire.alantra.businessdata.rest.request.ServiceRequest)
	 */
	@Override
	public Currency update(CurrencyRequest currencyRequest) throws ApplicationException {
		return updateInstance(currencyRequest);
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
		for(Currency currency: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(currency.getId(), currency.getCode(), currency.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public Currency convertRequestToModel(CurrencyRequest currencyRequest) 
     throws ApplicationException {
		Currency currency = new Currency();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(currencyRequest, currency, allowedEntityFields);
    	//Process many to one relationships
		return currency;
	}
	
	@Override
	public CurrencyResponse convertModelToResponse(Currency model) throws ApplicationException {
		if (model == null) return null;
		CurrencyResponse currencyResponse = new CurrencyResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, currencyResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return currencyResponse;
	}
}
