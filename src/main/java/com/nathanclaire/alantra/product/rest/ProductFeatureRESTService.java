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
import com.nathanclaire.alantra.product.model.ProductFeature;
import com.nathanclaire.alantra.product.rest.request.ProductFeatureRequest;
import com.nathanclaire.alantra.product.service.entity.ProductFeatureService;

/**
 * @author administrator
 *
 */
@Path("/productfeature")
@Stateless
public class ProductFeatureRESTService extends BaseEntityRESTService<ProductFeature, ProductFeatureRequest> 
{
	@Inject
	ProductFeatureService productFeatureService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<ProductFeature> getAll(MultivaluedMap<String, String> queryParameters) {
		return productFeatureService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected ProductFeature getSingleInstance(Integer id) {
		return productFeatureService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return productFeatureService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(ProductFeatureRequest request) {
		productFeatureService.createInstance(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(ProductFeatureRequest request) {
		productFeatureService.updateInstance(request);
		return null;
	}
	
}
