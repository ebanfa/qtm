/**
 * 
 */
package com.nathanclaire.alantra.product.rest;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.product.model.ProductFeatureApplicability;
import com.nathanclaire.alantra.product.rest.request.ProductFeatureApplicabilityRequest;
import com.nathanclaire.alantra.product.service.entity.ProductFeatureApplicabilityService;

/**
 * @author administrator
 *
 */
@Path("/productfeatureapplicability")
@Stateless
public class ProductFeatureApplicabilityRESTService extends BaseEntityRESTService<ProductFeatureApplicability, ProductFeatureApplicabilityRequest> 
{
	@Inject
	ProductFeatureApplicabilityService productFeatureApplicabilityService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<ProductFeatureApplicability> getAll(MultivaluedMap<String, String> queryParameters) {
		return productFeatureApplicabilityService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected ProductFeatureApplicability getSingleInstance(Integer id) {
		return productFeatureApplicabilityService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return productFeatureApplicabilityService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(ProductFeatureApplicabilityRequest request) {
		productFeatureApplicabilityService.createInstance(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(ProductFeatureApplicabilityRequest request) {
		productFeatureApplicabilityService.updateInstance(request);
		return null;
	}
	
}
