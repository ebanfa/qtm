/**
 * 
 */
package com.nathanclaire.alantra.messaging.rest.entity;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.messaging.model.CommunicationEvent;
import com.nathanclaire.alantra.messaging.request.CommunicationEventRequest;
import com.nathanclaire.alantra.messaging.service.entity.CommunicationEventService;

/**
 * @author administrator
 *
 */
@Path("/communicationevent")
@Stateless
public class CommunicationEventRESTService extends BaseEntityRESTService<CommunicationEvent, CommunicationEventRequest> 
{
	@Inject
	CommunicationEventService communicationEventService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<CommunicationEvent> getAll(MultivaluedMap<String, String> queryParameters) {
		return communicationEventService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected CommunicationEvent getSingleInstance(Integer id) {
		return communicationEventService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return communicationEventService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(CommunicationEventRequest request) {
		communicationEventService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(CommunicationEventRequest request) {
		communicationEventService.update(request);
		return null;
	}
	
}
