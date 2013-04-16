/**
 * 
 */
package com.nathanclaire.alantra.businessdata.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.businessdata.request.CurrencyRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class CurrencyServiceImpl extends BaseEntityServiceImpl<Currency, CurrencyRequest> implements CurrencyService
{
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
	public Currency findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.Currency#findByCode(java.lang.String)
	 */
	@Override
	public Currency findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.Currency#findByName(java.lang.String)
	 */
	@Override
	public Currency findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.Currency#findAll(java.util.Map)
	 */
	@Override
	public List<Currency> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.Currency#createCurrency(com.nathanclaire.alantra.businessdata.rest.request.ServiceRequest)
	 */
	@Override
	public Currency create(CurrencyRequest currencyRequest) {
		return createInstance(currencyRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.Currency#deleteCurrency(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.Currency#updateCurrency(com.nathanclaire.alantra.businessdata.rest.request.ServiceRequest)
	 */
	@Override
	public Currency update(CurrencyRequest currencyRequest) {
		return updateInstance(currencyRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected Currency loadModelFromRequest(CurrencyRequest currencyRequest) 
    {
		Currency currency = new Currency();
    	Integer currencyId = currencyRequest.getId();
    	// Are we editing a Currency
    	if(currencyId != null) 
    	{
    		currency = getEntityManager().find(Currency.class, currencyRequest.getId());
    		currency.setLastModifiedDt(currencyRequest.getLastModifiedDt());
        	currency.setLastModifiedUsr(getCurrentUserName(currencyRequest));
    	}
    	else
    	{
        	currency.setCreatedDt(getCurrentSystemDate());
        	currency.setCreatedByUsr(getCurrentUserName(currencyRequest));
    	}
    	currency.setCode(currencyRequest.getCode());
    	currency.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	currency.setCrncyNm(currencyRequest.getCrncyNm()); 
    	currency.setCrncySym(currencyRequest.getCrncySym()); 
    	currency.setRemarks(currencyRequest.getRemarks()); 
    	currency.setCode(currencyRequest.getCode()); 
    	currency.setEffectiveDt(currencyRequest.getEffectiveDt()); 
    	currency.setRecSt(currencyRequest.getRecSt()); 
		return currency;
	}
}
