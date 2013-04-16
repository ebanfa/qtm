/**
 * 
 */
package com.nathanclaire.alantra.customer.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.customer.model.BillingAccount;
import com.nathanclaire.alantra.customer.request.BillingAccountRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class BillingAccountServiceImpl extends BaseEntityServiceImpl<BillingAccount, BillingAccountRequest> implements BillingAccountService
{
	/**
	 * @param entityClass
	 */
	public BillingAccountServiceImpl() {
		super(BillingAccount.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.BillingAccount#findById(java.lang.Integer)
	 */
	@Override
	public BillingAccount findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.BillingAccount#findByCode(java.lang.String)
	 */
	@Override
	public BillingAccount findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.BillingAccount#findByName(java.lang.String)
	 */
	@Override
	public BillingAccount findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.BillingAccount#findAll(java.util.Map)
	 */
	@Override
	public List<BillingAccount> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.BillingAccount#createBillingAccount(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public BillingAccount create(BillingAccountRequest billingAccountRequest) {
		return createInstance(billingAccountRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.BillingAccount#deleteBillingAccount(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.BillingAccount#updateBillingAccount(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public BillingAccount update(BillingAccountRequest billingAccountRequest) {
		return updateInstance(billingAccountRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected BillingAccount loadModelFromRequest(BillingAccountRequest billingAccountRequest) 
    {
		BillingAccount billingAccount = new BillingAccount();
    	Integer billingAccountId = billingAccountRequest.getId();
    	// Are we editing a BillingAccount
    	if(billingAccountId != null) 
    	{
    		billingAccount = getEntityManager().find(BillingAccount.class, billingAccountRequest.getId());
    		billingAccount.setLastModifiedDt(billingAccountRequest.getLastModifiedDt());
        	billingAccount.setLastModifiedUsr(getCurrentUserName(billingAccountRequest));
    	}
    	else
    	{
        	billingAccount.setCreatedDt(getCurrentSystemDate());
        	billingAccount.setCreatedByUsr(getCurrentUserName(billingAccountRequest));
    	}
    	billingAccount.setCode(billingAccountRequest.getCode());
    	billingAccount.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	billingAccount.setContMechId(billingAccountRequest.getContMechId()); 
    	billingAccount.setName(billingAccountRequest.getName()); 
    	billingAccount.setDescription(billingAccountRequest.getDescription()); 
    	billingAccount.setFromDt(billingAccountRequest.getFromDt()); 
    	billingAccount.setThruDt(billingAccountRequest.getThruDt()); 
    	billingAccount.setCode(billingAccountRequest.getCode()); 
    	billingAccount.setEffectiveDt(billingAccountRequest.getEffectiveDt()); 
    	billingAccount.setRecSt(billingAccountRequest.getRecSt()); 
		return billingAccount;
	}
}
