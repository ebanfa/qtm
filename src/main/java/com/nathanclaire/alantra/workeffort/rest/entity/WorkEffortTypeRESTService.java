/**
 * 
 */
package com.nathanclaire.alantra.workeffort.rest.entity;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.workeffort.model.WorkEffortType;
import com.nathanclaire.alantra.workeffort.request.WorkEffortTypeRequest;
import com.nathanclaire.alantra.workeffort.service.entity.WorkEffortTypeService;

/**
 * @author administrator
 *
 */
@Path("/workefforttype")
@Stateless
public class WorkEffortTypeRESTService extends BaseEntityRESTService<WorkEffortType, WorkEffortTypeRequest> 
{
	@Inject
	WorkEffortTypeService workEffortTypeService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<WorkEffortType> getAll(MultivaluedMap<String, String> queryParameters) {
		return workEffortTypeService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected WorkEffortType getSingleInstance(Integer id) {
		return workEffortTypeService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return workEffortTypeService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(WorkEffortTypeRequest request) {
		workEffortTypeService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(WorkEffortTypeRequest request) {
		workEffortTypeService.update(request);
		return null;
	}
	
}
