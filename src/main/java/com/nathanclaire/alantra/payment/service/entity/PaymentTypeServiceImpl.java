/**
 * 
 */
package com.nathanclaire.alantra.payment.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.payment.model.PaymentType;
import com.nathanclaire.alantra.payment.rest.request.PaymentTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class PaymentTypeServiceImpl extends BaseEntityServiceImpl<PaymentType, PaymentTypeRequest> implements PaymentTypeService
{
	/**
	 * @param entityClass
	 */
	public PaymentTypeServiceImpl() {
		super(PaymentType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentType#findById(java.lang.Integer)
	 */
	@Override
	public PaymentType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentType#findByCode(java.lang.String)
	 */
	@Override
	public PaymentType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentType#findByName(java.lang.String)
	 */
	@Override
	public PaymentType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentType#findAll(java.util.Map)
	 */
	@Override
	public List<PaymentType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentType#createPaymentType(com.nathanclaire.alantra.payment.rest.request.ServiceRequest)
	 */
	@Override
	public PaymentType createInstance(PaymentTypeRequest paymentTypeRequest) {
		return createInsance(paymentTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentType#deletePaymentType(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentType#updatePaymentType(com.nathanclaire.alantra.payment.rest.request.ServiceRequest)
	 */
	@Override
	public PaymentType updateInstance(PaymentTypeRequest paymentTypeRequest) {
		return updateInstance(paymentTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected PaymentType loadModelFromRequest(PaymentTypeRequest paymentTypeRequest) 
    {
		PaymentType paymentType = new PaymentType();
    	Integer paymentTypeId = paymentTypeRequest.getId();
    	// Are we editing a PaymentType
    	if(paymentTypeId != null) 
    	{
    		paymentType = getEntityManager().find(PaymentType.class, paymentTypeRequest.getId());
    		paymentType.setLastModifiedDt(paymentTypeRequest.getLastModifiedDt());
        	paymentType.setLastModifiedUsr(getCurrentUserName(paymentTypeRequest));
    	}
    	else
    	{
        	paymentType.setCreatedDt(getCurrentSystemDate());
        	paymentType.setCreatedByUsr(getCurrentUserName(paymentTypeRequest));
    	}
    	paymentType.setCode(paymentTypeRequest.getCode());
    	paymentType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	paymentType.setName(paymentTypeRequest.getName()); 
    	paymentType.setDescription(paymentTypeRequest.getDescription()); 
    	paymentType.setCode(paymentTypeRequest.getCode()); 
    	paymentType.setEffectiveDt(paymentTypeRequest.getEffectiveDt()); 
    	paymentType.setRecSt(paymentTypeRequest.getRecSt()); 
		return paymentType;
	}
}
