/**
 * 
 */
package com.nathanclaire.alantra.channel.rest;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.channel.model.ServiceProtocolAdapter;
import com.nathanclaire.alantra.channel.rest.request.ServiceProtocolAdapterRequest;
import com.nathanclaire.alantra.channel.service.entity.ServiceProtocolAdapterService;

/**
 * @author administrator
 *
 */
@Path("/serviceprotocoladapter")
@Stateless
public class ServiceProtocolAdapterRESTService extends BaseEntityRESTService<ServiceProtocolAdapter, ServiceProtocolAdapterRequest> 
{
	@Inject
	ServiceProtocolAdapterService serviceProtocolAdapterService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<ServiceProtocolAdapter> getAll(MultivaluedMap<String, String> queryParameters) {
		return serviceProtocolAdapterService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected ServiceProtocolAdapter getSingleInstance(Integer id) {
		return serviceProtocolAdapterService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return serviceProtocolAdapterService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(ServiceProtocolAdapterRequest request) {
		serviceProtocolAdapterService.createInstance(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(ServiceProtocolAdapterRequest request) {
		serviceProtocolAdapterService.updateInstance(request);
		return null;
	}
	
}
