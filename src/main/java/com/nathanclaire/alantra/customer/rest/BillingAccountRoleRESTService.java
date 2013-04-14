/**
 * 
 */
package com.nathanclaire.alantra.customer.rest;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.customer.model.BillingAccountRole;
import com.nathanclaire.alantra.customer.rest.request.BillingAccountRoleRequest;
import com.nathanclaire.alantra.customer.service.entity.BillingAccountRoleService;

/**
 * @author administrator
 *
 */
@Path("/billingaccountrole")
@Stateless
public class BillingAccountRoleRESTService extends BaseEntityRESTService<BillingAccountRole, BillingAccountRoleRequest> 
{
	@Inject
	BillingAccountRoleService billingAccountRoleService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<BillingAccountRole> getAll(MultivaluedMap<String, String> queryParameters) {
		return billingAccountRoleService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected BillingAccountRole getSingleInstance(Integer id) {
		return billingAccountRoleService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return billingAccountRoleService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(BillingAccountRoleRequest request) {
		billingAccountRoleService.createInstance(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(BillingAccountRoleRequest request) {
		billingAccountRoleService.updateInstance(request);
		return null;
	}
	
}
