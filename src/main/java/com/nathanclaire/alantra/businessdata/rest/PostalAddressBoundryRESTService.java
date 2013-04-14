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
import com.nathanclaire.alantra.businessdata.model.PostalAddressBoundry;
import com.nathanclaire.alantra.businessdata.rest.request.PostalAddressBoundryRequest;
import com.nathanclaire.alantra.businessdata.service.entity.PostalAddressBoundryService;

/**
 * @author administrator
 *
 */
@Path("/postaladdressboundry")
@Stateless
public class PostalAddressBoundryRESTService extends BaseEntityRESTService<PostalAddressBoundry, PostalAddressBoundryRequest> 
{
	@Inject
	PostalAddressBoundryService postalAddressBoundryService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<PostalAddressBoundry> getAll(MultivaluedMap<String, String> queryParameters) {
		return postalAddressBoundryService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected PostalAddressBoundry getSingleInstance(Integer id) {
		return postalAddressBoundryService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return postalAddressBoundryService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(PostalAddressBoundryRequest request) {
		postalAddressBoundryService.createInstance(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(PostalAddressBoundryRequest request) {
		postalAddressBoundryService.updateInstance(request);
		return null;
	}
	
}
