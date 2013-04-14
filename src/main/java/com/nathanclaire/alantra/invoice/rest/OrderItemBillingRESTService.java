/**
 * 
 */
package com.nathanclaire.alantra.invoice.rest;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.invoice.model.OrderItemBilling;
import com.nathanclaire.alantra.invoice.rest.request.OrderItemBillingRequest;
import com.nathanclaire.alantra.invoice.service.entity.OrderItemBillingService;

/**
 * @author administrator
 *
 */
@Path("/orderitembilling")
@Stateless
public class OrderItemBillingRESTService extends BaseEntityRESTService<OrderItemBilling, OrderItemBillingRequest> 
{
	@Inject
	OrderItemBillingService orderItemBillingService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<OrderItemBilling> getAll(MultivaluedMap<String, String> queryParameters) {
		return orderItemBillingService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected OrderItemBilling getSingleInstance(Integer id) {
		return orderItemBillingService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return orderItemBillingService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(OrderItemBillingRequest request) {
		orderItemBillingService.createInstance(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(OrderItemBillingRequest request) {
		orderItemBillingService.updateInstance(request);
		return null;
	}
	
}
