/**
 * 
 */
package com.nathanclaire.alantra.workeffort.rest;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.workeffort.model.WorkEffort;
import com.nathanclaire.alantra.workeffort.rest.request.WorkEffortRequest;
import com.nathanclaire.alantra.workeffort.service.entity.WorkEffortService;

/**
 * @author administrator
 *
 */
@Path("/workeffort")
@Stateless
public class WorkEffortRESTService extends BaseEntityRESTService<WorkEffort, WorkEffortRequest> 
{
	@Inject
	WorkEffortService workEffortService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<WorkEffort> getAll(MultivaluedMap<String, String> queryParameters) {
		return workEffortService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected WorkEffort getSingleInstance(Integer id) {
		return workEffortService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return workEffortService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(WorkEffortRequest request) {
		workEffortService.createInstance(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(WorkEffortRequest request) {
		workEffortService.updateInstance(request);
		return null;
	}
	
}
