/**
 * 
 */
package com.nathanclaire.alantra.party.rest;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.party.model.PartyRelationship;
import com.nathanclaire.alantra.party.rest.request.PartyRelationshipRequest;
import com.nathanclaire.alantra.party.service.entity.PartyRelationshipService;

/**
 * @author administrator
 *
 */
@Path("/partyrelationship")
@Stateless
public class PartyRelationshipRESTService extends BaseEntityRESTService<PartyRelationship, PartyRelationshipRequest> 
{
	@Inject
	PartyRelationshipService partyRelationshipService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<PartyRelationship> getAll(MultivaluedMap<String, String> queryParameters) {
		return partyRelationshipService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected PartyRelationship getSingleInstance(Integer id) {
		return partyRelationshipService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return partyRelationshipService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(PartyRelationshipRequest request) {
		partyRelationshipService.createInstance(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(PartyRelationshipRequest request) {
		partyRelationshipService.updateInstance(request);
		return null;
	}
	
}
