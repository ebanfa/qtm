/**
 * 
 */
package com.nathanclaire.alantra.product.rest.entity;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.product.model.CostComponentType;
import com.nathanclaire.alantra.product.request.CostComponentTypeRequest;
import com.nathanclaire.alantra.product.service.entity.CostComponentTypeService;

/**
 * @author administrator
 *
 */
@Path("/costcomponenttype")
@Stateless
public class CostComponentTypeRESTService extends BaseEntityRESTService<CostComponentType, CostComponentTypeRequest> 
{
	@Inject
	CostComponentTypeService costComponentTypeService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<CostComponentType> getAll(MultivaluedMap<String, String> queryParameters) {
		return costComponentTypeService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected CostComponentType getSingleInstance(Integer id) {
		return costComponentTypeService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return costComponentTypeService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(CostComponentTypeRequest request) {
		costComponentTypeService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(CostComponentTypeRequest request) {
		costComponentTypeService.update(request);
		return null;
	}
	
}
