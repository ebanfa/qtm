/**
 * 
 */
package com.nathanclaire.alantra.customer.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.rest.request.CustomerRequest;

import com.nathanclaire.alantra.party.model.Party;
import com.nathanclaire.alantra.party.rest.request.PartyRequest;

/**
 * @author administrator
 *
 */
@Stateless
public class CustomerServiceImpl extends BaseEntityServiceImpl<Customer> implements CustomerService
{
	/**
	 * @param entityClass
	 */
	public CustomerServiceImpl() {
		super(Customer.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Customer#findById(java.lang.Integer)
	 */
	@Override
	public Customer findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Customer#findByCode(java.lang.String)
	 */
	@Override
	public Customer findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Customer#findByName(java.lang.String)
	 */
	@Override
	public Customer findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Customer#findAll(java.util.Map)
	 */
	@Override
	public List<Customer> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Customer#createCustomer(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public Customer createInstance(BaseRequest customerRequest) {
		return createInsance(customerRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Customer#deleteCustomer(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Customer#updateCustomer(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public Customer updateInstance(BaseRequest customerRequest) {
		return updateInstance(customerRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected Customer loadModelFromRequest(BaseRequest request) 
    {
    	CustomerRequest customerRequest = (CustomerRequest) request;
		Customer customer = new Customer();
    	Integer customerId = customerRequest.getId();
    	// Are we editing a Customer
    	if(customerId != null) 
    	{
    		customer = getEntityManager().find(Customer.class, customerRequest.getId());
    		customer.setLastModifiedDt(customerRequest.getLastModifiedDt());
        	customer.setLastModifiedUsr(getCurrentUserName(customerRequest));
    	}
    	else
    	{
        	customer.setCreatedDt(getCurrentSystemDate());
        	customer.setCreatedByUsr(getCurrentUserName(customerRequest));
    	}
    	customer.setCode(customerRequest.getCode());
    	customer.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (customerRequest.getParty() != null)
    	{
    		Party party = getEntityManager().find(Party.class, customerRequest.getParty());
    		customer.setParty(party);
    	}
    	customer.setCode(customerRequest.getCode()); 
    	customer.setEffectiveDt(customerRequest.getEffectiveDt()); 
    	customer.setRecSt(customerRequest.getRecSt()); 
		return customer;
	}
}
