/**
 * 
 */
package com.nathanclaire.alantra.customer.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.customer.model.BillingAccountRole;
import com.nathanclaire.alantra.customer.rest.request.BillingAccountRoleRequest;

import com.nathanclaire.alantra.customer.model.BillingAccountRoleType;
import com.nathanclaire.alantra.customer.rest.request.BillingAccountRoleTypeRequest;
import com.nathanclaire.alantra.party.model.Party;
import com.nathanclaire.alantra.party.rest.request.PartyRequest;
import com.nathanclaire.alantra.customer.model.BillingAccount;
import com.nathanclaire.alantra.customer.rest.request.BillingAccountRequest;

/**
 * @author administrator
 *
 */
@Stateless
public class BillingAccountRoleServiceImpl extends BaseEntityServiceImpl<BillingAccountRole> implements BillingAccountRoleService
{
	/**
	 * @param entityClass
	 */
	public BillingAccountRoleServiceImpl() {
		super(BillingAccountRole.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.BillingAccountRole#findById(java.lang.Integer)
	 */
	@Override
	public BillingAccountRole findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.BillingAccountRole#findByCode(java.lang.String)
	 */
	@Override
	public BillingAccountRole findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.BillingAccountRole#findByName(java.lang.String)
	 */
	@Override
	public BillingAccountRole findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.BillingAccountRole#findAll(java.util.Map)
	 */
	@Override
	public List<BillingAccountRole> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.BillingAccountRole#createBillingAccountRole(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public BillingAccountRole createInstance(BaseRequest billingAccountRoleRequest) {
		return createInsance(billingAccountRoleRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.BillingAccountRole#deleteBillingAccountRole(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.BillingAccountRole#updateBillingAccountRole(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public BillingAccountRole updateInstance(BaseRequest billingAccountRoleRequest) {
		return updateInstance(billingAccountRoleRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected BillingAccountRole loadModelFromRequest(BaseRequest request) 
    {
    	BillingAccountRoleRequest billingAccountRoleRequest = (BillingAccountRoleRequest) request;
		BillingAccountRole billingAccountRole = new BillingAccountRole();
    	Integer billingAccountRoleId = billingAccountRoleRequest.getId();
    	// Are we editing a BillingAccountRole
    	if(billingAccountRoleId != null) 
    	{
    		billingAccountRole = getEntityManager().find(BillingAccountRole.class, billingAccountRoleRequest.getId());
    		billingAccountRole.setLastModifiedDt(billingAccountRoleRequest.getLastModifiedDt());
        	billingAccountRole.setLastModifiedUsr(getCurrentUserName(billingAccountRoleRequest));
    	}
    	else
    	{
        	billingAccountRole.setCreatedDt(getCurrentSystemDate());
        	billingAccountRole.setCreatedByUsr(getCurrentUserName(billingAccountRoleRequest));
    	}
    	billingAccountRole.setCode(billingAccountRoleRequest.getCode());
    	billingAccountRole.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (billingAccountRoleRequest.getBillingAccountRoleType() != null)
    	{
    		BillingAccountRoleType billingAccountRoleType = getEntityManager().find(BillingAccountRoleType.class, billingAccountRoleRequest.getBillingAccountRoleType());
    		billingAccountRole.setBillingAccountRoleType(billingAccountRoleType);
    	}
    	if (billingAccountRoleRequest.getParty() != null)
    	{
    		Party party = getEntityManager().find(Party.class, billingAccountRoleRequest.getParty());
    		billingAccountRole.setParty(party);
    	}
    	if (billingAccountRoleRequest.getBillingAccount() != null)
    	{
    		BillingAccount billingAccount = getEntityManager().find(BillingAccount.class, billingAccountRoleRequest.getBillingAccount());
    		billingAccountRole.setBillingAccount(billingAccount);
    	}
    	billingAccountRole.setName(billingAccountRoleRequest.getName()); 
    	billingAccountRole.setDescription(billingAccountRoleRequest.getDescription()); 
    	billingAccountRole.setFromDt(billingAccountRoleRequest.getFromDt()); 
    	billingAccountRole.setThruDt(billingAccountRoleRequest.getThruDt()); 
    	billingAccountRole.setCode(billingAccountRoleRequest.getCode()); 
    	billingAccountRole.setEffectiveDt(billingAccountRoleRequest.getEffectiveDt()); 
    	billingAccountRole.setRecSt(billingAccountRoleRequest.getRecSt()); 
		return billingAccountRole;
	}
}
