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
import com.nathanclaire.alantra.messaging.model.CommunicationEventStatusType;
import com.nathanclaire.alantra.messaging.rest.request.CommunicationEventStatusTypeRequest;
import com.nathanclaire.alantra.messaging.service.entity.CommunicationEventStatusTypeService;

/**
 * @author administrator
 *
 */
@Path("/communicationeventstatustype")
@Stateless
public class CommunicationEventStatusTypeRESTService extends BaseEntityRESTService<CommunicationEventStatusType, CommunicationEventStatusTypeRequest> 
{
	@Inject
	CommunicationEventStatusTypeService communicationEventStatusTypeService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<CommunicationEventStatusType> getAll(MultivaluedMap<String, String> queryParameters) {
		return communicationEventStatusTypeService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected CommunicationEventStatusType getSingleInstance(Integer id) {
		return communicationEventStatusTypeService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return communicationEventStatusTypeService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(CommunicationEventStatusTypeRequest request) {
		communicationEventStatusTypeService.createInstance(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(CommunicationEventStatusTypeRequest request) {
		communicationEventStatusTypeService.updateInstance(request);
		return null;
	}
	
}
