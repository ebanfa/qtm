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
import com.nathanclaire.alantra.businessdata.model.UomConversion;
import com.nathanclaire.alantra.businessdata.rest.request.UomConversionRequest;
import com.nathanclaire.alantra.businessdata.service.entity.UomConversionService;

/**
 * @author administrator
 *
 */
@Path("/uomconversion")
@Stateless
public class UomConversionRESTService extends BaseEntityRESTService<UomConversion, UomConversionRequest> 
{
	@Inject
	UomConversionService uomConversionService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<UomConversion> getAll(MultivaluedMap<String, String> queryParameters) {
		return uomConversionService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected UomConversion getSingleInstance(Integer id) {
		return uomConversionService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return uomConversionService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(UomConversionRequest request) {
		uomConversionService.createInstance(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(UomConversionRequest request) {
		uomConversionService.updateInstance(request);
		return null;
	}
	
}
