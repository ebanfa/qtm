/**
 * 
 */
package com.nathanclaire.alantra.payment.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.payment.model.PaymentApplication;
import com.nathanclaire.alantra.payment.rest.request.PaymentApplicationRequest;

import com.nathanclaire.alantra.payment.model.Payment;
import com.nathanclaire.alantra.payment.rest.request.PaymentRequest;
import com.nathanclaire.alantra.customer.model.BillingAccount;
import com.nathanclaire.alantra.customer.rest.request.BillingAccountRequest;
import com.nathanclaire.alantra.invoice.model.InvoiceItem;
import com.nathanclaire.alantra.invoice.rest.request.InvoiceItemRequest;

/**
 * @author administrator
 *
 */
@Stateless
public class PaymentApplicationServiceImpl extends BaseEntityServiceImpl<PaymentApplication> implements PaymentApplicationService
{
	/**
	 * @param entityClass
	 */
	public PaymentApplicationServiceImpl() {
		super(PaymentApplication.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentApplication#findById(java.lang.Integer)
	 */
	@Override
	public PaymentApplication findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentApplication#findByCode(java.lang.String)
	 */
	@Override
	public PaymentApplication findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentApplication#findByName(java.lang.String)
	 */
	@Override
	public PaymentApplication findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentApplication#findAll(java.util.Map)
	 */
	@Override
	public List<PaymentApplication> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentApplication#createPaymentApplication(com.nathanclaire.alantra.payment.rest.request.ServiceRequest)
	 */
	@Override
	public PaymentApplication createInstance(BaseRequest paymentApplicationRequest) {
		return createInsance(paymentApplicationRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentApplication#deletePaymentApplication(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.payment.service.PaymentApplication#updatePaymentApplication(com.nathanclaire.alantra.payment.rest.request.ServiceRequest)
	 */
	@Override
	public PaymentApplication updateInstance(BaseRequest paymentApplicationRequest) {
		return updateInstance(paymentApplicationRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected PaymentApplication loadModelFromRequest(BaseRequest request) 
    {
    	PaymentApplicationRequest paymentApplicationRequest = (PaymentApplicationRequest) request;
		PaymentApplication paymentApplication = new PaymentApplication();
    	Integer paymentApplicationId = paymentApplicationRequest.getId();
    	// Are we editing a PaymentApplication
    	if(paymentApplicationId != null) 
    	{
    		paymentApplication = getEntityManager().find(PaymentApplication.class, paymentApplicationRequest.getId());
    		paymentApplication.setLastModifiedDt(paymentApplicationRequest.getLastModifiedDt());
        	paymentApplication.setLastModifiedUsr(getCurrentUserName(paymentApplicationRequest));
    	}
    	else
    	{
        	paymentApplication.setCreatedDt(getCurrentSystemDate());
        	paymentApplication.setCreatedByUsr(getCurrentUserName(paymentApplicationRequest));
    	}
    	paymentApplication.setCode(paymentApplicationRequest.getCode());
    	paymentApplication.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (paymentApplicationRequest.getPayment() != null)
    	{
    		Payment payment = getEntityManager().find(Payment.class, paymentApplicationRequest.getPayment());
    		paymentApplication.setPayment(payment);
    	}
    	if (paymentApplicationRequest.getBillingAccount() != null)
    	{
    		BillingAccount billingAccount = getEntityManager().find(BillingAccount.class, paymentApplicationRequest.getBillingAccount());
    		paymentApplication.setBillingAccount(billingAccount);
    	}
    	if (paymentApplicationRequest.getInvoiceItem() != null)
    	{
    		InvoiceItem invoiceItem = getEntityManager().find(InvoiceItem.class, paymentApplicationRequest.getInvoiceItem());
    		paymentApplication.setInvoiceItem(invoiceItem);
    	}
    	paymentApplication.setDescription(paymentApplicationRequest.getDescription()); 
    	paymentApplication.setAmountAppl(paymentApplicationRequest.getAmountAppl()); 
    	paymentApplication.setCode(paymentApplicationRequest.getCode()); 
    	paymentApplication.setEffectiveDt(paymentApplicationRequest.getEffectiveDt()); 
    	paymentApplication.setRecSt(paymentApplicationRequest.getRecSt()); 
		return paymentApplication;
	}
}
