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
import com.nathanclaire.alantra.customer.model.BillingAccountRoleType;
import com.nathanclaire.alantra.customer.request.BillingAccountRoleTypeRequest;
import com.nathanclaire.alantra.customer.service.entity.BillingAccountRoleTypeService;

/**
 * @author administrator
 *
 */
@Path("/billingaccountroletype")
@Stateless
public class BillingAccountRoleTypeRESTService extends BaseEntityRESTService<BillingAccountRoleType, BillingAccountRoleTypeRequest> 
{
	@Inject
	BillingAccountRoleTypeService billingAccountRoleTypeService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<BillingAccountRoleType> getAll(MultivaluedMap<String, String> queryParameters) {
		return billingAccountRoleTypeService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected BillingAccountRoleType getSingleInstance(Integer id) {
		return billingAccountRoleTypeService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return billingAccountRoleTypeService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(BillingAccountRoleTypeRequest request) {
		billingAccountRoleTypeService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(BillingAccountRoleTypeRequest request) {
		billingAccountRoleTypeService.update(request);
		return null;
	}
	
}
