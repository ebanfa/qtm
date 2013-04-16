/**
 * 
 */
package com.nathanclaire.alantra.advice.rest.entity;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.advice.request.AdviceRequest;
import com.nathanclaire.alantra.advice.service.entity.AdviceService;

/**
 * @author administrator
 *
 */
@Path("/advice")
@Stateless
public class AdviceRESTService extends BaseEntityRESTService<Advice, AdviceRequest> 
{
	@Inject
	AdviceService adviceService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<Advice> getAll(MultivaluedMap<String, String> queryParameters) {
		return adviceService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected Advice getSingleInstance(Integer id) {
		return adviceService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return adviceService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(AdviceRequest request) {
		adviceService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(AdviceRequest request) {
		adviceService.update(request);
		return null;
	}
	
}
