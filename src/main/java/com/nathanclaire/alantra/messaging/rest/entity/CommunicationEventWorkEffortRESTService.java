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
import com.nathanclaire.alantra.messaging.model.CommunicationEventWorkEffort;
import com.nathanclaire.alantra.messaging.request.CommunicationEventWorkEffortRequest;
import com.nathanclaire.alantra.messaging.service.entity.CommunicationEventWorkEffortService;

/**
 * @author administrator
 *
 */
@Path("/communicationeventworkeffort")
@Stateless
public class CommunicationEventWorkEffortRESTService extends BaseEntityRESTService<CommunicationEventWorkEffort, CommunicationEventWorkEffortRequest> 
{
	@Inject
	CommunicationEventWorkEffortService communicationEventWorkEffortService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<CommunicationEventWorkEffort> getAll(MultivaluedMap<String, String> queryParameters) {
		return communicationEventWorkEffortService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected CommunicationEventWorkEffort getSingleInstance(Integer id) {
		return communicationEventWorkEffortService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return communicationEventWorkEffortService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(CommunicationEventWorkEffortRequest request) {
		communicationEventWorkEffortService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(CommunicationEventWorkEffortRequest request) {
		communicationEventWorkEffortService.update(request);
		return null;
	}
	
}
