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
import com.nathanclaire.alantra.businessdata.model.GeoBoundry;
import com.nathanclaire.alantra.businessdata.request.GeoBoundryRequest;
import com.nathanclaire.alantra.businessdata.service.entity.GeoBoundryService;

/**
 * @author administrator
 *
 */
@Path("/geoboundry")
@Stateless
public class GeoBoundryRESTService extends BaseEntityRESTService<GeoBoundry, GeoBoundryRequest> 
{
	@Inject
	GeoBoundryService geoBoundryService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<GeoBoundry> getAll(MultivaluedMap<String, String> queryParameters) {
		return geoBoundryService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected GeoBoundry getSingleInstance(Integer id) {
		return geoBoundryService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return geoBoundryService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(GeoBoundryRequest request) {
		geoBoundryService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(GeoBoundryRequest request) {
		geoBoundryService.update(request);
		return null;
	}
	
}
