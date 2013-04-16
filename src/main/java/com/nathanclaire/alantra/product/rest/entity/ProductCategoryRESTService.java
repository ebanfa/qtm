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
import com.nathanclaire.alantra.product.model.ProductCategory;
import com.nathanclaire.alantra.product.request.ProductCategoryRequest;
import com.nathanclaire.alantra.product.service.entity.ProductCategoryService;

/**
 * @author administrator
 *
 */
@Path("/productcategory")
@Stateless
public class ProductCategoryRESTService extends BaseEntityRESTService<ProductCategory, ProductCategoryRequest> 
{
	@Inject
	ProductCategoryService productCategoryService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<ProductCategory> getAll(MultivaluedMap<String, String> queryParameters) {
		return productCategoryService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected ProductCategory getSingleInstance(Integer id) {
		return productCategoryService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return productCategoryService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(ProductCategoryRequest request) {
		productCategoryService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(ProductCategoryRequest request) {
		productCategoryService.update(request);
		return null;
	}
	
}
