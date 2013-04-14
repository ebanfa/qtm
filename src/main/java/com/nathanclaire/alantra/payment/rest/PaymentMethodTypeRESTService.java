/**
 * 
 */
package com.nathanclaire.alantra.payment.rest;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.payment.model.PaymentMethodType;
import com.nathanclaire.alantra.payment.rest.request.PaymentMethodTypeRequest;
import com.nathanclaire.alantra.payment.service.entity.PaymentMethodTypeService;

/**
 * @author administrator
 *
 */
@Path("/paymentmethodtype")
@Stateless
public class PaymentMethodTypeRESTService extends BaseEntityRESTService<PaymentMethodType, PaymentMethodTypeRequest> 
{
	@Inject
	PaymentMethodTypeService paymentMethodTypeService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<PaymentMethodType> getAll(MultivaluedMap<String, String> queryParameters) {
		return paymentMethodTypeService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected PaymentMethodType getSingleInstance(Integer id) {
		return paymentMethodTypeService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return paymentMethodTypeService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(PaymentMethodTypeRequest request) {
		paymentMethodTypeService.createInstance(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(PaymentMethodTypeRequest request) {
		paymentMethodTypeService.updateInstance(request);
		return null;
	}
	
}
