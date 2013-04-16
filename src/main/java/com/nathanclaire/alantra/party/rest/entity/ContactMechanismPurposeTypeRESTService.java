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
import com.nathanclaire.alantra.party.model.ContactMechanismPurposeType;
import com.nathanclaire.alantra.party.request.ContactMechanismPurposeTypeRequest;
import com.nathanclaire.alantra.party.service.entity.ContactMechanismPurposeTypeService;

/**
 * @author administrator
 *
 */
@Path("/contactmechanismpurposetype")
@Stateless
public class ContactMechanismPurposeTypeRESTService extends BaseEntityRESTService<ContactMechanismPurposeType, ContactMechanismPurposeTypeRequest> 
{
	@Inject
	ContactMechanismPurposeTypeService contactMechanismPurposeTypeService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<ContactMechanismPurposeType> getAll(MultivaluedMap<String, String> queryParameters) {
		return contactMechanismPurposeTypeService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected ContactMechanismPurposeType getSingleInstance(Integer id) {
		return contactMechanismPurposeTypeService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return contactMechanismPurposeTypeService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(ContactMechanismPurposeTypeRequest request) {
		contactMechanismPurposeTypeService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(ContactMechanismPurposeTypeRequest request) {
		contactMechanismPurposeTypeService.update(request);
		return null;
	}
	
}
