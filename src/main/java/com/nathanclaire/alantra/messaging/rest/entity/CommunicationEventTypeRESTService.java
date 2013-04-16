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
import com.nathanclaire.alantra.messaging.model.CommunicationEventType;
import com.nathanclaire.alantra.messaging.request.CommunicationEventTypeRequest;
import com.nathanclaire.alantra.messaging.service.entity.CommunicationEventTypeService;

/**
 * @author administrator
 *
 */
@Path("/communicationeventtype")
@Stateless
public class CommunicationEventTypeRESTService extends BaseEntityRESTService<CommunicationEventType, CommunicationEventTypeRequest> 
{
	@Inject
	CommunicationEventTypeService communicationEventTypeService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<CommunicationEventType> getAll(MultivaluedMap<String, String> queryParameters) {
		return communicationEventTypeService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected CommunicationEventType getSingleInstance(Integer id) {
		return communicationEventTypeService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return communicationEventTypeService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(CommunicationEventTypeRequest request) {
		communicationEventTypeService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(CommunicationEventTypeRequest request) {
		communicationEventTypeService.update(request);
		return null;
	}
	
}
