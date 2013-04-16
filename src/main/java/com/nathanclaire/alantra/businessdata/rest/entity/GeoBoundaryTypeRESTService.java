/**
 * 
 */
package com.nathanclaire.alantra.businessdata.rest.entity;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.businessdata.model.GeoBoundaryType;
import com.nathanclaire.alantra.businessdata.request.GeoBoundaryTypeRequest;
import com.nathanclaire.alantra.businessdata.service.entity.GeoBoundaryTypeService;

/**
 * @author administrator
 *
 */
@Path("/geoboundarytype")
@Stateless
public class GeoBoundaryTypeRESTService extends BaseEntityRESTService<GeoBoundaryType, GeoBoundaryTypeRequest> 
{
	@Inject
	GeoBoundaryTypeService geoBoundaryTypeService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<GeoBoundaryType> getAll(MultivaluedMap<String, String> queryParameters) {
		return geoBoundaryTypeService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected GeoBoundaryType getSingleInstance(Integer id) {
		return geoBoundaryTypeService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return geoBoundaryTypeService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(GeoBoundaryTypeRequest request) {
		geoBoundaryTypeService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(GeoBoundaryTypeRequest request) {
		geoBoundaryTypeService.update(request);
		return null;
	}
	
}
