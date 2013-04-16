/**
 * 
 */
package com.nathanclaire.alantra.payment.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.payment.model.PaymentMethodType;
import com.nathanclaire.alantra.payment.request.PaymentMethodTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class PaymentMethodTypeServiceImpl extends BaseEntityServiceImpl<PaymentMethodType, PaymentMethodTypeRequest> implements PaymentMethodTypeService
{
	/**
	 * @param entityClass
	 */
	public PaymentMethodTypeServiceImpl() {
		super(PaymentMethodType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentMethodType#findById(java.lang.Integer)
	 */
	@Override
	public PaymentMethodType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentMethodType#findByCode(java.lang.String)
	 */
	@Override
	public PaymentMethodType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentMethodType#findByName(java.lang.String)
	 */
	@Override
	public PaymentMethodType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentMethodType#findAll(java.util.Map)
	 */
	@Override
	public List<PaymentMethodType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentMethodType#createPaymentMethodType(com.nathanclaire.alantra.payment.rest.request.ServiceRequest)
	 */
	@Override
	public PaymentMethodType create(PaymentMethodTypeRequest paymentMethodTypeRequest) {
		return createInstance(paymentMethodTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentMethodType#deletePaymentMethodType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentMethodType#updatePaymentMethodType(com.nathanclaire.alantra.payment.rest.request.ServiceRequest)
	 */
	@Override
	public PaymentMethodType update(PaymentMethodTypeRequest paymentMethodTypeRequest) {
		return updateInstance(paymentMethodTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected PaymentMethodType loadModelFromRequest(PaymentMethodTypeRequest paymentMethodTypeRequest) 
    {
		PaymentMethodType paymentMethodType = new PaymentMethodType();
    	Integer paymentMethodTypeId = paymentMethodTypeRequest.getId();
    	// Are we editing a PaymentMethodType
    	if(paymentMethodTypeId != null) 
    	{
    		paymentMethodType = getEntityManager().find(PaymentMethodType.class, paymentMethodTypeRequest.getId());
    		paymentMethodType.setLastModifiedDt(paymentMethodTypeRequest.getLastModifiedDt());
        	paymentMethodType.setLastModifiedUsr(getCurrentUserName(paymentMethodTypeRequest));
    	}
    	else
    	{
        	paymentMethodType.setCreatedDt(getCurrentSystemDate());
        	paymentMethodType.setCreatedByUsr(getCurrentUserName(paymentMethodTypeRequest));
    	}
    	paymentMethodType.setCode(paymentMethodTypeRequest.getCode());
    	paymentMethodType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	paymentMethodType.setName(paymentMethodTypeRequest.getName()); 
    	paymentMethodType.setDescription(paymentMethodTypeRequest.getDescription()); 
    	paymentMethodType.setCode(paymentMethodTypeRequest.getCode()); 
    	paymentMethodType.setEffectiveDt(paymentMethodTypeRequest.getEffectiveDt()); 
    	paymentMethodType.setRecSt(paymentMethodTypeRequest.getRecSt()); 
		return paymentMethodType;
	}
}
