/**
 * 
 */
package com.nathanclaire.alantra.customer.rest.entity;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.customer.model.BillingAccount;
import com.nathanclaire.alantra.customer.request.BillingAccountRequest;
import com.nathanclaire.alantra.customer.service.entity.BillingAccountService;

/**
 * @author administrator
 *
 */
@Path("/billingaccount")
@Stateless
public class BillingAccountRESTService extends BaseEntityRESTService<BillingAccount, BillingAccountRequest> 
{
	@Inject
	BillingAccountService billingAccountService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<BillingAccount> getAll(MultivaluedMap<String, String> queryParameters) {
		return billingAccountService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected BillingAccount getSingleInstance(Integer id) {
		return billingAccountService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return billingAccountService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(BillingAccountRequest request) {
		billingAccountService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(BillingAccountRequest request) {
		billingAccountService.update(request);
		return null;
	}
	
}
