/**
 * 
 */
package com.nathanclaire.alantra.channel.rest.entity;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.channel.model.ServiceTransactionType;
import com.nathanclaire.alantra.channel.request.ServiceTransactionTypeRequest;
import com.nathanclaire.alantra.channel.service.entity.ServiceTransactionTypeService;

/**
 * @author administrator
 *
 */
@Path("/servicetransactiontype")
@Stateless
public class ServiceTransactionTypeRESTService extends BaseEntityRESTService<ServiceTransactionType, ServiceTransactionTypeRequest> 
{
	@Inject
	ServiceTransactionTypeService serviceTransactionTypeService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<ServiceTransactionType> getAll(MultivaluedMap<String, String> queryParameters) {
		return serviceTransactionTypeService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected ServiceTransactionType getSingleInstance(Integer id) {
		return serviceTransactionTypeService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return serviceTransactionTypeService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(ServiceTransactionTypeRequest request) {
		serviceTransactionTypeService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(ServiceTransactionTypeRequest request) {
		serviceTransactionTypeService.update(request);
		return null;
	}
	
}
