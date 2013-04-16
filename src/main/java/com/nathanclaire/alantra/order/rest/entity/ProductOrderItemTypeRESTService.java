/**
 * 
 */
package com.nathanclaire.alantra.order.rest.entity;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.order.model.ProductOrderItemType;
import com.nathanclaire.alantra.order.request.ProductOrderItemTypeRequest;
import com.nathanclaire.alantra.order.service.entity.ProductOrderItemTypeService;

/**
 * @author administrator
 *
 */
@Path("/productorderitemtype")
@Stateless
public class ProductOrderItemTypeRESTService extends BaseEntityRESTService<ProductOrderItemType, ProductOrderItemTypeRequest> 
{
	@Inject
	ProductOrderItemTypeService productOrderItemTypeService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<ProductOrderItemType> getAll(MultivaluedMap<String, String> queryParameters) {
		return productOrderItemTypeService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected ProductOrderItemType getSingleInstance(Integer id) {
		return productOrderItemTypeService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return productOrderItemTypeService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(ProductOrderItemTypeRequest request) {
		productOrderItemTypeService.create(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(ProductOrderItemTypeRequest request) {
		productOrderItemTypeService.update(request);
		return null;
	}
	
}
