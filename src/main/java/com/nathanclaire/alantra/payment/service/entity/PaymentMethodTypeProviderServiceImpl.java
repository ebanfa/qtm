/**
 * 
 */
package com.nathanclaire.alantra.payment.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.payment.model.PaymentMethodTypeProvider;
import com.nathanclaire.alantra.payment.request.PaymentMethodTypeProviderRequest;

import com.nathanclaire.alantra.payment.model.PaymentMethodType;
import com.nathanclaire.alantra.party.model.Party;

/**
 * @author administrator
 *
 */
@Stateless
public class PaymentMethodTypeProviderServiceImpl extends BaseEntityServiceImpl<PaymentMethodTypeProvider, PaymentMethodTypeProviderRequest> implements PaymentMethodTypeProviderService
{
	/**
	 * @param entityClass
	 */
	public PaymentMethodTypeProviderServiceImpl() {
		super(PaymentMethodTypeProvider.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentMethodTypeProvider#findById(java.lang.Integer)
	 */
	@Override
	public PaymentMethodTypeProvider findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentMethodTypeProvider#findByCode(java.lang.String)
	 */
	@Override
	public PaymentMethodTypeProvider findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentMethodTypeProvider#findByName(java.lang.String)
	 */
	@Override
	public PaymentMethodTypeProvider findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentMethodTypeProvider#findAll(java.util.Map)
	 */
	@Override
	public List<PaymentMethodTypeProvider> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentMethodTypeProvider#createPaymentMethodTypeProvider(com.nathanclaire.alantra.payment.rest.request.ServiceRequest)
	 */
	@Override
	public PaymentMethodTypeProvider create(PaymentMethodTypeProviderRequest paymentMethodTypeProviderRequest) {
		return createInstance(paymentMethodTypeProviderRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentMethodTypeProvider#deletePaymentMethodTypeProvider(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentMethodTypeProvider#updatePaymentMethodTypeProvider(com.nathanclaire.alantra.payment.rest.request.ServiceRequest)
	 */
	@Override
	public PaymentMethodTypeProvider update(PaymentMethodTypeProviderRequest paymentMethodTypeProviderRequest) {
		return updateInstance(paymentMethodTypeProviderRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected PaymentMethodTypeProvider loadModelFromRequest(PaymentMethodTypeProviderRequest paymentMethodTypeProviderRequest) 
    {
		PaymentMethodTypeProvider paymentMethodTypeProvider = new PaymentMethodTypeProvider();
    	Integer paymentMethodTypeProviderId = paymentMethodTypeProviderRequest.getId();
    	// Are we editing a PaymentMethodTypeProvider
    	if(paymentMethodTypeProviderId != null) 
    	{
    		paymentMethodTypeProvider = getEntityManager().find(PaymentMethodTypeProvider.class, paymentMethodTypeProviderRequest.getId());
    		paymentMethodTypeProvider.setLastModifiedDt(paymentMethodTypeProviderRequest.getLastModifiedDt());
        	paymentMethodTypeProvider.setLastModifiedUsr(getCurrentUserName(paymentMethodTypeProviderRequest));
    	}
    	else
    	{
        	paymentMethodTypeProvider.setCreatedDt(getCurrentSystemDate());
        	paymentMethodTypeProvider.setCreatedByUsr(getCurrentUserName(paymentMethodTypeProviderRequest));
    	}
    	paymentMethodTypeProvider.setCode(paymentMethodTypeProviderRequest.getCode());
    	paymentMethodTypeProvider.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (paymentMethodTypeProviderRequest.getPaymentMethodType() != null)
    	{
    		PaymentMethodType paymentMethodType = getEntityManager().find(PaymentMethodType.class, paymentMethodTypeProviderRequest.getPaymentMethodType());
    		paymentMethodTypeProvider.setPaymentMethodType(paymentMethodType);
    	}
    	if (paymentMethodTypeProviderRequest.getParty() != null)
    	{
    		Party party = getEntityManager().find(Party.class, paymentMethodTypeProviderRequest.getParty());
    		paymentMethodTypeProvider.setParty(party);
    	}
    	paymentMethodTypeProvider.setName(paymentMethodTypeProviderRequest.getName()); 
    	paymentMethodTypeProvider.setDescription(paymentMethodTypeProviderRequest.getDescription()); 
    	paymentMethodTypeProvider.setFromDt(paymentMethodTypeProviderRequest.getFromDt()); 
    	paymentMethodTypeProvider.setThruDt(paymentMethodTypeProviderRequest.getThruDt()); 
    	paymentMethodTypeProvider.setCode(paymentMethodTypeProviderRequest.getCode()); 
    	paymentMethodTypeProvider.setEffectiveDt(paymentMethodTypeProviderRequest.getEffectiveDt()); 
    	paymentMethodTypeProvider.setRecSt(paymentMethodTypeProviderRequest.getRecSt()); 
		return paymentMethodTypeProvider;
	}
}
