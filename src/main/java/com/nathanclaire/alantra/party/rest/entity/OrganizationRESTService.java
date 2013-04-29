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
import javax.ws.rs.core.Response.Status;

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.party.model.Organization;
import com.nathanclaire.alantra.party.request.OrganizationRequest;
import com.nathanclaire.alantra.party.service.entity.OrganizationService;

/**
 * @author administrator
 *
 */
@Path("/organization")
@Stateless
public class OrganizationRESTService  extends BaseEntityRESTService<Organization, OrganizationRequest> 
{
	@Inject
	OrganizationService organizationService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<Organization> getAll(MultivaluedMap<String, String> queryParameters) {
		return organizationService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected Organization getSingleInstance(Integer id) {
		Organization organization = organizationService.findById(id);
		organization.setName(organization.getParty().getName());
		return organization;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return organizationService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(OrganizationRequest request) {
		organizationService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(OrganizationRequest request) {
		Organization organization;
		try {
			organization = organizationService.update(request);
			//return Response.status(200).entity(organization).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(Status.BAD_REQUEST).entity("Some shit").build();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#deleteInstance(java.lang.Integer)
	 */
	@Override
	protected Response deleteInstance(Integer id) {
		//organizationService.delete(id);
		return Response.status(Status.BAD_REQUEST).entity("Some shit").build();
	}
	
	
}
