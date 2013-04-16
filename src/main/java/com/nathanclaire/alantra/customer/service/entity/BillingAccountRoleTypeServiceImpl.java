/**
 * 
 */
package com.nathanclaire.alantra.customer.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.customer.model.BillingAccountRoleType;
import com.nathanclaire.alantra.customer.request.BillingAccountRoleTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class BillingAccountRoleTypeServiceImpl extends BaseEntityServiceImpl<BillingAccountRoleType, BillingAccountRoleTypeRequest> implements BillingAccountRoleTypeService
{
	/**
	 * @param entityClass
	 */
	public BillingAccountRoleTypeServiceImpl() {
		super(BillingAccountRoleType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.BillingAccountRoleType#findById(java.lang.Integer)
	 */
	@Override
	public BillingAccountRoleType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.BillingAccountRoleType#findByCode(java.lang.String)
	 */
	@Override
	public BillingAccountRoleType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.BillingAccountRoleType#findByName(java.lang.String)
	 */
	@Override
	public BillingAccountRoleType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.BillingAccountRoleType#findAll(java.util.Map)
	 */
	@Override
	public List<BillingAccountRoleType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.BillingAccountRoleType#createBillingAccountRoleType(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public BillingAccountRoleType create(BillingAccountRoleTypeRequest billingAccountRoleTypeRequest) {
		return createInstance(billingAccountRoleTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.BillingAccountRoleType#deleteBillingAccountRoleType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.BillingAccountRoleType#updateBillingAccountRoleType(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public BillingAccountRoleType update(BillingAccountRoleTypeRequest billingAccountRoleTypeRequest) {
		return updateInstance(billingAccountRoleTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected BillingAccountRoleType loadModelFromRequest(BillingAccountRoleTypeRequest billingAccountRoleTypeRequest) 
    {
		BillingAccountRoleType billingAccountRoleType = new BillingAccountRoleType();
    	Integer billingAccountRoleTypeId = billingAccountRoleTypeRequest.getId();
    	// Are we editing a BillingAccountRoleType
    	if(billingAccountRoleTypeId != null) 
    	{
    		billingAccountRoleType = getEntityManager().find(BillingAccountRoleType.class, billingAccountRoleTypeRequest.getId());
    		billingAccountRoleType.setLastModifiedDt(billingAccountRoleTypeRequest.getLastModifiedDt());
        	billingAccountRoleType.setLastModifiedUsr(getCurrentUserName(billingAccountRoleTypeRequest));
    	}
    	else
    	{
        	billingAccountRoleType.setCreatedDt(getCurrentSystemDate());
        	billingAccountRoleType.setCreatedByUsr(getCurrentUserName(billingAccountRoleTypeRequest));
    	}
    	billingAccountRoleType.setCode(billingAccountRoleTypeRequest.getCode());
    	billingAccountRoleType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	billingAccountRoleType.setName(billingAccountRoleTypeRequest.getName()); 
    	billingAccountRoleType.setDescription(billingAccountRoleTypeRequest.getDescription()); 
    	billingAccountRoleType.setCode(billingAccountRoleTypeRequest.getCode()); 
    	billingAccountRoleType.setEffectiveDt(billingAccountRoleTypeRequest.getEffectiveDt()); 
    	billingAccountRoleType.setRecSt(billingAccountRoleTypeRequest.getRecSt()); 
		return billingAccountRoleType;
	}
}
