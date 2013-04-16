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
import com.nathanclaire.alantra.messaging.model.CommunicationEventPurposeType;
import com.nathanclaire.alantra.messaging.request.CommunicationEventPurposeTypeRequest;
import com.nathanclaire.alantra.messaging.service.entity.CommunicationEventPurposeTypeService;

/**
 * @author administrator
 *
 */
@Path("/communicationeventpurposetype")
@Stateless
public class CommunicationEventPurposeTypeRESTService extends BaseEntityRESTService<CommunicationEventPurposeType, CommunicationEventPurposeTypeRequest> 
{
	@Inject
	CommunicationEventPurposeTypeService communicationEventPurposeTypeService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<CommunicationEventPurposeType> getAll(MultivaluedMap<String, String> queryParameters) {
		return communicationEventPurposeTypeService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected CommunicationEventPurposeType getSingleInstance(Integer id) {
		return communicationEventPurposeTypeService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return communicationEventPurposeTypeService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(CommunicationEventPurposeTypeRequest request) {
		communicationEventPurposeTypeService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(CommunicationEventPurposeTypeRequest request) {
		communicationEventPurposeTypeService.update(request);
		return null;
	}
	
}
