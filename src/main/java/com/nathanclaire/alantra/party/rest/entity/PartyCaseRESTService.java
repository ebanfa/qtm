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
import com.nathanclaire.alantra.party.model.PartyCase;
import com.nathanclaire.alantra.party.request.PartyCaseRequest;
import com.nathanclaire.alantra.party.service.entity.PartyCaseService;

/**
 * @author administrator
 *
 */
@Path("/partycase")
@Stateless
public class PartyCaseRESTService extends BaseEntityRESTService<PartyCase, PartyCaseRequest> 
{
	@Inject
	PartyCaseService partyCaseService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<PartyCase> getAll(MultivaluedMap<String, String> queryParameters) {
		return partyCaseService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected PartyCase getSingleInstance(Integer id) {
		return partyCaseService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return partyCaseService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(PartyCaseRequest request) {
		partyCaseService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(PartyCaseRequest request) {
		partyCaseService.update(request);
		return null;
	}
	
}
