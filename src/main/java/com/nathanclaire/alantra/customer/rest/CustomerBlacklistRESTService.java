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
import com.nathanclaire.alantra.customer.model.CustomerBlacklist;
import com.nathanclaire.alantra.customer.rest.request.CustomerBlacklistRequest;
import com.nathanclaire.alantra.customer.service.entity.CustomerBlacklistService;

/**
 * @author administrator
 *
 */
@Path("/customerblacklist")
@Stateless
public class CustomerBlacklistRESTService extends BaseEntityRESTService<CustomerBlacklist, CustomerBlacklistRequest> 
{
	@Inject
	CustomerBlacklistService customerBlacklistService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<CustomerBlacklist> getAll(MultivaluedMap<String, String> queryParameters) {
		return customerBlacklistService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected CustomerBlacklist getSingleInstance(Integer id) {
		return customerBlacklistService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return customerBlacklistService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(CustomerBlacklistRequest request) {
		customerBlacklistService.createInstance(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(CustomerBlacklistRequest request) {
		customerBlacklistService.updateInstance(request);
		return null;
	}
	
}
