/**
 * 
 */
package com.nathanclaire.alantra.customer.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.customer.model.CustomerBlacklist;
import com.nathanclaire.alantra.customer.rest.request.CustomerBlacklistRequest;

import com.nathanclaire.alantra.party.model.Party;
import com.nathanclaire.alantra.party.rest.request.PartyRequest;

/**
 * @author administrator
 *
 */
@Stateless
public class CustomerBlacklistServiceImpl extends BaseEntityServiceImpl<CustomerBlacklist> implements CustomerBlacklistService
{
	/**
	 * @param entityClass
	 */
	public CustomerBlacklistServiceImpl() {
		super(CustomerBlacklist.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerBlacklist#findById(java.lang.Integer)
	 */
	@Override
	public CustomerBlacklist findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerBlacklist#findByCode(java.lang.String)
	 */
	@Override
	public CustomerBlacklist findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerBlacklist#findByName(java.lang.String)
	 */
	@Override
	public CustomerBlacklist findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerBlacklist#findAll(java.util.Map)
	 */
	@Override
	public List<CustomerBlacklist> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerBlacklist#createCustomerBlacklist(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerBlacklist createInstance(BaseRequest customerBlacklistRequest) {
		return createInsance(customerBlacklistRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerBlacklist#deleteCustomerBlacklist(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerBlacklist#updateCustomerBlacklist(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerBlacklist updateInstance(BaseRequest customerBlacklistRequest) {
		return updateInstance(customerBlacklistRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected CustomerBlacklist loadModelFromRequest(BaseRequest request) 
    {
    	CustomerBlacklistRequest customerBlacklistRequest = (CustomerBlacklistRequest) request;
		CustomerBlacklist customerBlacklist = new CustomerBlacklist();
    	Integer customerBlacklistId = customerBlacklistRequest.getId();
    	// Are we editing a CustomerBlacklist
    	if(customerBlacklistId != null) 
    	{
    		customerBlacklist = getEntityManager().find(CustomerBlacklist.class, customerBlacklistRequest.getId());
    		customerBlacklist.setLastModifiedDt(customerBlacklistRequest.getLastModifiedDt());
        	customerBlacklist.setLastModifiedUsr(getCurrentUserName(customerBlacklistRequest));
    	}
    	else
    	{
        	customerBlacklist.setCreatedDt(getCurrentSystemDate());
        	customerBlacklist.setCreatedByUsr(getCurrentUserName(customerBlacklistRequest));
    	}
    	customerBlacklist.setCode(customerBlacklistRequest.getCode());
    	customerBlacklist.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (customerBlacklistRequest.getParty() != null)
    	{
    		Party party = getEntityManager().find(Party.class, customerBlacklistRequest.getParty());
    		customerBlacklist.setParty(party);
    	}
    	customerBlacklist.setDescription(customerBlacklistRequest.getDescription()); 
    	customerBlacklist.setCode(customerBlacklistRequest.getCode()); 
    	customerBlacklist.setEffectiveDt(customerBlacklistRequest.getEffectiveDt()); 
    	customerBlacklist.setRecSt(customerBlacklistRequest.getRecSt()); 
		return customerBlacklist;
	}
}
