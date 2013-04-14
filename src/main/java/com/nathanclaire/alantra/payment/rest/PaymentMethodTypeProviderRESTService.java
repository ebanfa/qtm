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
import com.nathanclaire.alantra.payment.model.PaymentMethodTypeProvider;
import com.nathanclaire.alantra.payment.rest.request.PaymentMethodTypeProviderRequest;
import com.nathanclaire.alantra.payment.service.entity.PaymentMethodTypeProviderService;

/**
 * @author administrator
 *
 */
@Path("/paymentmethodtypeprovider")
@Stateless
public class PaymentMethodTypeProviderRESTService extends BaseEntityRESTService<PaymentMethodTypeProvider, PaymentMethodTypeProviderRequest> 
{
	@Inject
	PaymentMethodTypeProviderService paymentMethodTypeProviderService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<PaymentMethodTypeProvider> getAll(MultivaluedMap<String, String> queryParameters) {
		return paymentMethodTypeProviderService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected PaymentMethodTypeProvider getSingleInstance(Integer id) {
		return paymentMethodTypeProviderService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return paymentMethodTypeProviderService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(PaymentMethodTypeProviderRequest request) {
		paymentMethodTypeProviderService.createInstance(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(PaymentMethodTypeProviderRequest request) {
		paymentMethodTypeProviderService.updateInstance(request);
		return null;
	}
	
}
