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
import com.nathanclaire.alantra.party.model.ContactMechanismType;
import com.nathanclaire.alantra.party.rest.request.ContactMechanismTypeRequest;
import com.nathanclaire.alantra.party.service.entity.ContactMechanismTypeService;

/**
 * @author administrator
 *
 */
@Path("/contactmechanismtype")
@Stateless
public class ContactMechanismTypeRESTService extends BaseEntityRESTService<ContactMechanismType, ContactMechanismTypeRequest> 
{
	@Inject
	ContactMechanismTypeService contactMechanismTypeService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<ContactMechanismType> getAll(MultivaluedMap<String, String> queryParameters) {
		return contactMechanismTypeService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected ContactMechanismType getSingleInstance(Integer id) {
		return contactMechanismTypeService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return contactMechanismTypeService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(ContactMechanismTypeRequest request) {
		contactMechanismTypeService.createInstance(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(ContactMechanismTypeRequest request) {
		contactMechanismTypeService.updateInstance(request);
		return null;
	}
	
}
