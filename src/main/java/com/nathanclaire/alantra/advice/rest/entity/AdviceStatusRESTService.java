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
import com.nathanclaire.alantra.advice.model.AdviceStatus;
import com.nathanclaire.alantra.advice.request.AdviceStatusRequest;
import com.nathanclaire.alantra.advice.service.entity.AdviceStatusService;

/**
 * @author administrator
 *
 */
@Path("/advicestatus")
@Stateless
public class AdviceStatusRESTService extends BaseEntityRESTService<AdviceStatus, AdviceStatusRequest> 
{
	@Inject
	AdviceStatusService adviceStatusService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<AdviceStatus> getAll(MultivaluedMap<String, String> queryParameters) {
		return adviceStatusService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected AdviceStatus getSingleInstance(Integer id) {
		return adviceStatusService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return adviceStatusService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(AdviceStatusRequest request) {
		adviceStatusService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(AdviceStatusRequest request) {
		adviceStatusService.update(request);
		return null;
	}
	
}
