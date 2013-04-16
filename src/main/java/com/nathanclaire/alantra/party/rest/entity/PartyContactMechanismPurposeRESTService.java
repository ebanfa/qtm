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
import com.nathanclaire.alantra.party.model.PartyContactMechanismPurpose;
import com.nathanclaire.alantra.party.request.PartyContactMechanismPurposeRequest;
import com.nathanclaire.alantra.party.service.entity.PartyContactMechanismPurposeService;

/**
 * @author administrator
 *
 */
@Path("/partycontactmechanismpurpose")
@Stateless
public class PartyContactMechanismPurposeRESTService extends BaseEntityRESTService<PartyContactMechanismPurpose, PartyContactMechanismPurposeRequest> 
{
	@Inject
	PartyContactMechanismPurposeService partyContactMechanismPurposeService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<PartyContactMechanismPurpose> getAll(MultivaluedMap<String, String> queryParameters) {
		return partyContactMechanismPurposeService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected PartyContactMechanismPurpose getSingleInstance(Integer id) {
		return partyContactMechanismPurposeService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return partyContactMechanismPurposeService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(PartyContactMechanismPurposeRequest request) {
		partyContactMechanismPurposeService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(PartyContactMechanismPurposeRequest request) {
		partyContactMechanismPurposeService.update(request);
		return null;
	}
	
}
