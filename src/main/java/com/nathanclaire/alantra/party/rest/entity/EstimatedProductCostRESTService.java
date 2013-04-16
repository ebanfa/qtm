/**
 * 
 */
package com.nathanclaire.alantra.party.rest.entity;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.party.model.EstimatedProductCost;
import com.nathanclaire.alantra.party.request.EstimatedProductCostRequest;
import com.nathanclaire.alantra.party.service.entity.EstimatedProductCostService;

/**
 * @author administrator
 *
 */
@Path("/estimatedproductcost")
@Stateless
public class EstimatedProductCostRESTService extends BaseEntityRESTService<EstimatedProductCost, EstimatedProductCostRequest> 
{
	@Inject
	EstimatedProductCostService estimatedProductCostService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<EstimatedProductCost> getAll(MultivaluedMap<String, String> queryParameters) {
		return estimatedProductCostService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected EstimatedProductCost getSingleInstance(Integer id) {
		return estimatedProductCostService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return estimatedProductCostService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(EstimatedProductCostRequest request) {
		estimatedProductCostService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(EstimatedProductCostRequest request) {
		estimatedProductCostService.update(request);
		return null;
	}
	
}