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
import com.nathanclaire.alantra.product.model.ProductFeatureApplicabilityType;
import com.nathanclaire.alantra.product.request.ProductFeatureApplicabilityTypeRequest;
import com.nathanclaire.alantra.product.service.entity.ProductFeatureApplicabilityTypeService;

/**
 * @author administrator
 *
 */
@Path("/productfeatureapplicabilitytype")
@Stateless
public class ProductFeatureApplicabilityTypeRESTService extends BaseEntityRESTService<ProductFeatureApplicabilityType, ProductFeatureApplicabilityTypeRequest> 
{
	@Inject
	ProductFeatureApplicabilityTypeService productFeatureApplicabilityTypeService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<ProductFeatureApplicabilityType> getAll(MultivaluedMap<String, String> queryParameters) {
		return productFeatureApplicabilityTypeService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected ProductFeatureApplicabilityType getSingleInstance(Integer id) {
		return productFeatureApplicabilityTypeService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return productFeatureApplicabilityTypeService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(ProductFeatureApplicabilityTypeRequest request) {
		productFeatureApplicabilityTypeService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(ProductFeatureApplicabilityTypeRequest request) {
		productFeatureApplicabilityTypeService.update(request);
		return null;
	}
	
}
