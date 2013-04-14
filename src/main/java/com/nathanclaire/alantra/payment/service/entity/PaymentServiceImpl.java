/**
 * 
 */
package com.nathanclaire.alantra.payment.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.payment.model.Payment;
import com.nathanclaire.alantra.payment.rest.request.PaymentRequest;

import com.nathanclaire.alantra.payment.model.PaymentType;
import com.nathanclaire.alantra.payment.rest.request.PaymentTypeRequest;
import com.nathanclaire.alantra.party.model.Party;
import com.nathanclaire.alantra.party.rest.request.PartyRequest;
import com.nathanclaire.alantra.party.model.Party;
import com.nathanclaire.alantra.party.rest.request.PartyRequest;
import com.nathanclaire.alantra.payment.model.PaymentMethodTypeProvider;
import com.nathanclaire.alantra.payment.rest.request.PaymentMethodTypeProviderRequest;
import com.nathanclaire.alantra.payment.model.PaymentMethodType;
import com.nathanclaire.alantra.payment.rest.request.PaymentMethodTypeRequest;

/**
 * @author administrator
 *
 */
@Stateless
public class PaymentServiceImpl extends BaseEntityServiceImpl<Payment> implements PaymentService
{
	/**
	 * @param entityClass
	 */
	public PaymentServiceImpl() {
		super(Payment.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.Payment#findById(java.lang.Integer)
	 */
	@Override
	public Payment findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.Payment#findByCode(java.lang.String)
	 */
	@Override
	public Payment findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.Payment#findByName(java.lang.String)
	 */
	@Override
	public Payment findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.Payment#findAll(java.util.Map)
	 */
	@Override
	public List<Payment> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.Payment#createPayment(com.nathanclaire.alantra.payment.rest.request.ServiceRequest)
	 */
	@Override
	public Payment createInstance(BaseRequest paymentRequest) {
		return createInsance(paymentRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.Payment#deletePayment(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.Payment#updatePayment(com.nathanclaire.alantra.payment.rest.request.ServiceRequest)
	 */
	@Override
	public Payment updateInstance(BaseRequest paymentRequest) {
		return updateInstance(paymentRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected Payment loadModelFromRequest(BaseRequest request) 
    {
    	PaymentRequest paymentRequest = (PaymentRequest) request;
		Payment payment = new Payment();
    	Integer paymentId = paymentRequest.getId();
    	// Are we editing a Payment
    	if(paymentId != null) 
    	{
    		payment = getEntityManager().find(Payment.class, paymentRequest.getId());
    		payment.setLastModifiedDt(paymentRequest.getLastModifiedDt());
        	payment.setLastModifiedUsr(getCurrentUserName(paymentRequest));
    	}
    	else
    	{
        	payment.setCreatedDt(getCurrentSystemDate());
        	payment.setCreatedByUsr(getCurrentUserName(paymentRequest));
    	}
    	payment.setCode(paymentRequest.getCode());
    	payment.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (paymentRequest.getPaymentType() != null)
    	{
    		PaymentType paymentType = getEntityManager().find(PaymentType.class, paymentRequest.getPaymentType());
    		payment.setPaymentType(paymentType);
    	}
    	if (paymentRequest.getPartyByToPartyId() != null)
    	{
    		Party partyByToPartyId = getEntityManager().find(Party.class, paymentRequest.getPartyByToPartyId());
    		payment.setPartyByToPartyId(partyByToPartyId);
    	}
    	if (paymentRequest.getPartyByFromPartyId() != null)
    	{
    		Party partyByFromPartyId = getEntityManager().find(Party.class, paymentRequest.getPartyByFromPartyId());
    		payment.setPartyByFromPartyId(partyByFromPartyId);
    	}
    	if (paymentRequest.getPaymentMethodTypeProvider() != null)
    	{
    		PaymentMethodTypeProvider paymentMethodTypeProvider = getEntityManager().find(PaymentMethodTypeProvider.class, paymentRequest.getPaymentMethodTypeProvider());
    		payment.setPaymentMethodTypeProvider(paymentMethodTypeProvider);
    	}
    	if (paymentRequest.getPaymentMethodType() != null)
    	{
    		PaymentMethodType paymentMethodType = getEntityManager().find(PaymentMethodType.class, paymentRequest.getPaymentMethodType());
    		payment.setPaymentMethodType(paymentMethodType);
    	}
    	payment.setName(paymentRequest.getName()); 
    	payment.setDescription(paymentRequest.getDescription()); 
    	payment.setComment(paymentRequest.getComment()); 
    	payment.setAmount(paymentRequest.getAmount()); 
    	payment.setCode(paymentRequest.getCode()); 
    	payment.setEffectiveDt(paymentRequest.getEffectiveDt()); 
    	payment.setRecSt(paymentRequest.getRecSt()); 
		return payment;
	}
}
