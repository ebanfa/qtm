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
import com.nathanclaire.alantra.payment.model.PaymentType;
import com.nathanclaire.alantra.payment.rest.request.PaymentTypeRequest;
import com.nathanclaire.alantra.payment.service.entity.PaymentTypeService;

/**
 * @author administrator
 *
 */
@Path("/paymenttype")
@Stateless
public class PaymentTypeRESTService extends BaseEntityRESTService<PaymentType, PaymentTypeRequest> 
{
	@Inject
	PaymentTypeService paymentTypeService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<PaymentType> getAll(MultivaluedMap<String, String> queryParameters) {
		return paymentTypeService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected PaymentType getSingleInstance(Integer id) {
		return paymentTypeService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return paymentTypeService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(PaymentTypeRequest request) {
		paymentTypeService.createInstance(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(PaymentTypeRequest request) {
		paymentTypeService.updateInstance(request);
		return null;
	}
	
}
