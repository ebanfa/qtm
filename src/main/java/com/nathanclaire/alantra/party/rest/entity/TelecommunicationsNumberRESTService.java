/**
 * 
 */
package com.nathanclaire.alantra.party.rest.entity;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.party.model.TelecommunicationsNumber;
import com.nathanclaire.alantra.party.request.TelecommunicationsNumberRequest;
import com.nathanclaire.alantra.party.service.entity.TelecommunicationsNumberService;

/**
 * @author administrator
 *
 */
@Path("/telecommunicationsnumber")
@Stateless
public class TelecommunicationsNumberRESTService extends BaseEntityRESTService<TelecommunicationsNumber, TelecommunicationsNumberRequest> 
{
	@Inject
	TelecommunicationsNumberService telecommunicationsNumberService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<TelecommunicationsNumber> getAll(MultivaluedMap<String, String> queryParameters) {
		return telecommunicationsNumberService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected TelecommunicationsNumber getSingleInstance(Integer id) {
		return telecommunicationsNumberService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return telecommunicationsNumberService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(TelecommunicationsNumberRequest request) {
		telecommunicationsNumberService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(TelecommunicationsNumberRequest request) {
		telecommunicationsNumberService.update(request);
		return null;
	}
	
}
