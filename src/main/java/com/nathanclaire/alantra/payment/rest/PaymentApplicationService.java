/**
 * 
 */
package com.nathanclaire.alantra.payment.rest;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.nathanclaire.alantra.payments.model.Payment;
import com.nathanclaire.alantra.payments.model.PaymentApplication;
import com.nathanclaire.alantra.base.rest.BaseEntityService;
import com.nathanclaire.alantra.base.rest.request.BillingAccountRequest;
import com.nathanclaire.alantra.base.rest.request.InvoiceItemRequest;
import com.nathanclaire.alantra.base.rest.request.PaymentApplicationRequest;
import com.nathanclaire.alantra.base.rest.request.PaymentRequest;
import com.nathanclaire.alantra.customer.model.BillingAccount;
import com.nathanclaire.alantra.invoice.model.InvoiceItem;

/**
 * @author administrator
 *
 */
@Path("/paymentapplication")
@Stateless
public class PaymentApplicationService extends BaseEntityService<PaymentApplication> 
{
	/**
	 * @param entityClass
	 */
	public PaymentApplicationService() {
		super(PaymentApplication.class);
	}

    /**
     * <p>
     *     Subclasses may choose to expand the set of supported query parameters (for adding more filtering
     *     criteria) by overriding this method.
     * </p>
     * @param queryParameters - the HTTP query parameters received by the endpoint
     * @param criteriaBuilder - @{link CriteriaBuilder} used by the invoker
     * @param root  @{link Root} used by the invoker
     * @return a list of {@link Predicate}s that will added as query parameters
     */
    protected Predicate[] extractPredicates(MultivaluedMap<String, String> queryParameters,
            CriteriaBuilder criteriaBuilder, Root<PaymentApplication> root) 
    {
    	List<Predicate> predicates = new ArrayList<Predicate>() ;
        if (queryParameters.containsKey(CODE_CRITERIA)) {
             String code = queryParameters.getFirst(CODE_CRITERIA) + "%";
            //predicates.add(criteriaBuilder.equal(root.get(CODE_CRITERIA), code));
            predicates.add(criteriaBuilder.like(root.<String>get(CODE_CRITERIA), code));
        }
        return predicates.toArray(new Predicate[]{});
	}
    
    /**
     * <p>
     *   Create a PaymentApplication. Data is contained in the PaymentApplicationRequest object
     * </p>
     * @param PaymentApplicationRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPaymentApplication(PaymentApplicationRequest request) {
        try 
        {
        	PaymentApplication paymentApplication = this.loadModelFromRequest(request);
        	entityManager.persist(paymentApplication);
            return null;
        } 
        catch (ConstraintViolationException e) 
        {
            // A WebApplicationException can wrap a response
            // Throwing the exception causes an automatic rollback
            throw new WebApplicationException(e);
        } catch (Exception e) {
            // Finally, handle 
            throw new WebApplicationException(e);
        }
    }
    
    /**
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    @PUT 
    @Path("/{id:[0-9][0-9]*}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editPaymentApplication(PaymentApplicationRequest request) 
    {
    	try 
        {
    		this.loadModelFromRequest(request);
            return null;
        } 
        catch (ConstraintViolationException e) 
        {
            // A WebApplicationException can wrap a response
            // Throwing the exception causes an automatic rollback
            throw new WebApplicationException(e);
        } catch (Exception e) {
            // Finally, handle 
            throw new WebApplicationException(e);
        }
    }
    
    /**
     * @param request
     * @return
     */
    private PaymentApplication loadModelFromRequest(PaymentApplicationRequest request) 
    {
		PaymentApplication paymentApplication = new PaymentApplication();
    	Integer paymentApplicationId = request.getId();
    	// Are we editing a PaymentApplication
    	if(paymentApplicationId != null) 
    	{
    		paymentApplication = getEntityManager().find(PaymentApplication.class, request.getId());
    		paymentApplication.setLastModifiedDt(request.getLastModifiedDt());
        	paymentApplication.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	paymentApplication.setCreatedDt(getCurrentDate());
        	paymentApplication.setCreatedByUsr(getCurrentUserName(request));
    	}
    	paymentApplication.setCode(request.getCode());
    	paymentApplication.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getPayment() != null)
    	{
    		Payment payment = entityManager.find(Payment.class, request.getPayment());
    		paymentApplication.setPayment(payment);
    	}
    	if (request.getBillingAccount() != null)
    	{
    		BillingAccount billingAccount = entityManager.find(BillingAccount.class, request.getBillingAccount());
    		paymentApplication.setBillingAccount(billingAccount);
    	}
    	if (request.getInvoiceItem() != null)
    	{
    		InvoiceItem invoiceItem = entityManager.find(InvoiceItem.class, request.getInvoiceItem());
    		paymentApplication.setInvoiceItem(invoiceItem);
    	}
    	paymentApplication.setCode(request.getCode()); 
    	paymentApplication.setDescription(request.getDescription()); 
    	paymentApplication.setAmountAppl(request.getAmountAppl()); 
    	paymentApplication.setEffectiveDt(request.getEffectiveDt()); 
    	paymentApplication.setRecSt(request.getRecSt()); 
		return paymentApplication;
	}
}
