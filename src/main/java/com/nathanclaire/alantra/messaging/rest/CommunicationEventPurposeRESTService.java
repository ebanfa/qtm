/**
 * 
 */
package com.nathanclaire.alantra.messaging.rest;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.messaging.model.CommunicationEventPurpose;
import com.nathanclaire.alantra.messaging.rest.request.CommunicationEventPurposeRequest;
import com.nathanclaire.alantra.messaging.service.entity.CommunicationEventPurposeService;

/**
 * @author administrator
 *
 */
@Path("/communicationeventpurpose")
@Stateless
public class CommunicationEventPurposeRESTService extends BaseEntityRESTService<CommunicationEventPurpose, CommunicationEventPurposeRequest> 
{
	@Inject
	CommunicationEventPurposeService communicationEventPurposeService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<CommunicationEventPurpose> getAll(MultivaluedMap<String, String> queryParameters) {
		return communicationEventPurposeService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected CommunicationEventPurpose getSingleInstance(Integer id) {
		return communicationEventPurposeService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return communicationEventPurposeService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(CommunicationEventPurposeRequest request) {
		communicationEventPurposeService.createInstance(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(CommunicationEventPurposeRequest request) {
		communicationEventPurposeService.updateInstance(request);
		return null;
	}
	
}
