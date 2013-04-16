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
import com.nathanclaire.alantra.product.model.ProductFeatureCategory;
import com.nathanclaire.alantra.product.request.ProductFeatureCategoryRequest;
import com.nathanclaire.alantra.product.service.entity.ProductFeatureCategoryService;

/**
 * @author administrator
 *
 */
@Path("/productfeaturecategory")
@Stateless
public class ProductFeatureCategoryRESTService extends BaseEntityRESTService<ProductFeatureCategory, ProductFeatureCategoryRequest> 
{
	@Inject
	ProductFeatureCategoryService productFeatureCategoryService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<ProductFeatureCategory> getAll(MultivaluedMap<String, String> queryParameters) {
		return productFeatureCategoryService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected ProductFeatureCategory getSingleInstance(Integer id) {
		return productFeatureCategoryService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return productFeatureCategoryService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(ProductFeatureCategoryRequest request) {
		productFeatureCategoryService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(ProductFeatureCategoryRequest request) {
		productFeatureCategoryService.update(request);
		return null;
	}
	
}
