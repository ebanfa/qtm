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
import com.nathanclaire.alantra.party.model.PartyContactMechanism;
import com.nathanclaire.alantra.party.rest.request.PartyContactMechanismRequest;
import com.nathanclaire.alantra.party.service.entity.PartyContactMechanismService;

/**
 * @author administrator
 *
 */
@Path("/partycontactmechanism")
@Stateless
public class PartyContactMechanismRESTService extends BaseEntityRESTService<PartyContactMechanism, PartyContactMechanismRequest> 
{
	@Inject
	PartyContactMechanismService partyContactMechanismService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<PartyContactMechanism> getAll(MultivaluedMap<String, String> queryParameters) {
		return partyContactMechanismService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected PartyContactMechanism getSingleInstance(Integer id) {
		return partyContactMechanismService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return partyContactMechanismService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(PartyContactMechanismRequest request) {
		partyContactMechanismService.createInstance(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(PartyContactMechanismRequest request) {
		partyContactMechanismService.updateInstance(request);
		return null;
	}
	
}
