/**
 * 
 */
package com.nathanclaire.alantra.businessdata.rest;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.businessdata.model.GeoBoundryAssociation;
import com.nathanclaire.alantra.businessdata.rest.request.GeoBoundryAssociationRequest;
import com.nathanclaire.alantra.businessdata.service.entity.GeoBoundryAssociationService;

/**
 * @author administrator
 *
 */
@Path("/geoboundryassociation")
@Stateless
public class GeoBoundryAssociationRESTService extends BaseEntityRESTService<GeoBoundryAssociation, GeoBoundryAssociationRequest> 
{
	@Inject
	GeoBoundryAssociationService geoBoundryAssociationService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<GeoBoundryAssociation> getAll(MultivaluedMap<String, String> queryParameters) {
		return geoBoundryAssociationService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected GeoBoundryAssociation getSingleInstance(Integer id) {
		return geoBoundryAssociationService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return geoBoundryAssociationService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(GeoBoundryAssociationRequest request) {
		geoBoundryAssociationService.createInstance(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(GeoBoundryAssociationRequest request) {
		geoBoundryAssociationService.updateInstance(request);
		return null;
	}
	
}
