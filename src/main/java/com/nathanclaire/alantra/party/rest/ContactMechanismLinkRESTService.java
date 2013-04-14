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
import com.nathanclaire.alantra.party.model.ContactMechanismLink;
import com.nathanclaire.alantra.party.rest.request.ContactMechanismLinkRequest;
import com.nathanclaire.alantra.party.service.entity.ContactMechanismLinkService;

/**
 * @author administrator
 *
 */
@Path("/contactmechanismlink")
@Stateless
public class ContactMechanismLinkRESTService extends BaseEntityRESTService<ContactMechanismLink, ContactMechanismLinkRequest> 
{
	@Inject
	ContactMechanismLinkService contactMechanismLinkService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<ContactMechanismLink> getAll(MultivaluedMap<String, String> queryParameters) {
		return contactMechanismLinkService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected ContactMechanismLink getSingleInstance(Integer id) {
		return contactMechanismLinkService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return contactMechanismLinkService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(ContactMechanismLinkRequest request) {
		contactMechanismLinkService.createInstance(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(ContactMechanismLinkRequest request) {
		contactMechanismLinkService.updateInstance(request);
		return null;
	}
	
}
