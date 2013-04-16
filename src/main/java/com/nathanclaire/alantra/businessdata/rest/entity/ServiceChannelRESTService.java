/**
 * 
 */
package com.nathanclaire.alantra.businessdata.rest.entity;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.businessdata.model.ServiceChannel;
import com.nathanclaire.alantra.businessdata.request.ServiceChannelRequest;
import com.nathanclaire.alantra.businessdata.service.entity.ServiceChannelService;

/**
 * @author administrator
 *
 */
@Path("/servicechannel")
@Stateless
public class ServiceChannelRESTService extends BaseEntityRESTService<ServiceChannel, ServiceChannelRequest> 
{
	@Inject
	ServiceChannelService serviceChannelService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<ServiceChannel> getAll(MultivaluedMap<String, String> queryParameters) {
		return serviceChannelService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected ServiceChannel getSingleInstance(Integer id) {
		return serviceChannelService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return serviceChannelService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(ServiceChannelRequest request) {
		serviceChannelService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(ServiceChannelRequest request) {
		serviceChannelService.update(request);
		return null;
	}
	
}
