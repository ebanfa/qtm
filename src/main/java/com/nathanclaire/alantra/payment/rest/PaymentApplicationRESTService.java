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
import com.nathanclaire.alantra.payment.model.PaymentApplication;
import com.nathanclaire.alantra.payment.rest.request.PaymentApplicationRequest;
import com.nathanclaire.alantra.payment.service.entity.PaymentApplicationService;

/**
 * @author administrator
 *
 */
@Path("/paymentapplication")
@Stateless
public class PaymentApplicationRESTService extends BaseEntityRESTService<PaymentApplication, PaymentApplicationRequest> 
{
	@Inject
	PaymentApplicationService paymentApplicationService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<PaymentApplication> getAll(MultivaluedMap<String, String> queryParameters) {
		return paymentApplicationService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected PaymentApplication getSingleInstance(Integer id) {
		return paymentApplicationService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return paymentApplicationService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(PaymentApplicationRequest request) {
		paymentApplicationService.createInstance(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(PaymentApplicationRequest request) {
		paymentApplicationService.updateInstance(request);
		return null;
	}
	
}
