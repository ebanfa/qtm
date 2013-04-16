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
import com.nathanclaire.alantra.product.model.ProductCategoryType;
import com.nathanclaire.alantra.product.request.ProductCategoryTypeRequest;
import com.nathanclaire.alantra.product.service.entity.ProductCategoryTypeService;

/**
 * @author administrator
 *
 */
@Path("/productcategorytype")
@Stateless
public class ProductCategoryTypeRESTService extends BaseEntityRESTService<ProductCategoryType, ProductCategoryTypeRequest> 
{
	@Inject
	ProductCategoryTypeService productCategoryTypeService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<ProductCategoryType> getAll(MultivaluedMap<String, String> queryParameters) {
		return productCategoryTypeService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected ProductCategoryType getSingleInstance(Integer id) {
		return productCategoryTypeService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return productCategoryTypeService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(ProductCategoryTypeRequest request) {
		productCategoryTypeService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(ProductCategoryTypeRequest request) {
		productCategoryTypeService.update(request);
		return null;
	}
	
}
