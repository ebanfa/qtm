/**
 * 
 */
package com.nathanclaire.alantra.rest;

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

import com.nathanclaire.alantra.rest.request.PaymentTypeRequest;
import com.nathanclaire.alantra.rest.request.PartyRequest;
import com.nathanclaire.alantra.rest.request.PartyRequest;
import com.nathanclaire.alantra.rest.request.PaymentMethodTypeProviderRequest;
import com.nathanclaire.alantra.model.party.Party;
import com.nathanclaire.alantra.model.payments.Payment;
import com.nathanclaire.alantra.model.payments.PaymentMethodType;
import com.nathanclaire.alantra.model.payments.PaymentMethodTypeProvider;
import com.nathanclaire.alantra.model.payments.PaymentType;
import com.nathanclaire.alantra.rest.request.PaymentMethodTypeRequest;
import com.nathanclaire.alantra.rest.request.PaymentRequest;

/**
 * @author administrator
 *
 */
@Path("/payment")
@Stateless
public class PaymentService extends BaseEntityService<Payment> 
{
	/**
	 * @param entityClass
	 */
	public PaymentService() {
		super(Payment.class);
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
            CriteriaBuilder criteriaBuilder, Root<Payment> root) 
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
     *   Create a Payment. Data is contained in the PaymentRequest object
     * </p>
     * @param PaymentRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPayment(PaymentRequest request) {
        try 
        {
        	Payment payment = this.loadModelFromRequest(request);
        	entityManager.persist(payment);
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
    public Response editPayment(PaymentRequest request) 
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
    private Payment loadModelFromRequest(PaymentRequest request) 
    {
		Payment payment = new Payment();
    	Integer paymentId = request.getId();
    	// Are we editing a Payment
    	if(paymentId != null) 
    	{
    		payment = getEntityManager().find(Payment.class, request.getId());
    		payment.setLastModifiedDt(request.getLastModifiedDt());
        	payment.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	payment.setCreatedDt(getCurrentDate());
        	payment.setCreatedByUsr(getCurrentUserName(request));
    	}
    	payment.setCode(request.getCode());
    	payment.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getPaymentType() != null)
    	{
    		PaymentType paymentType = entityManager.find(PaymentType.class, request.getPaymentType());
    		payment.setPaymentType(paymentType);
    	}
    	if (request.getPartyByToPartyId() != null)
    	{
    		Party partyByToPartyId = entityManager.find(Party.class, request.getPartyByToPartyId());
    		payment.setPartyByToPartyId(partyByToPartyId);
    	}
    	if (request.getPartyByFromPartyId() != null)
    	{
    		Party partyByFromPartyId = entityManager.find(Party.class, request.getPartyByFromPartyId());
    		payment.setPartyByFromPartyId(partyByFromPartyId);
    	}
    	if (request.getPaymentMethodTypeProvider() != null)
    	{
    		PaymentMethodTypeProvider paymentMethodTypeProvider = entityManager.find(PaymentMethodTypeProvider.class, request.getPaymentMethodTypeProvider());
    		payment.setPaymentMethodTypeProvider(paymentMethodTypeProvider);
    	}
    	if (request.getPaymentMethodType() != null)
    	{
    		PaymentMethodType paymentMethodType = entityManager.find(PaymentMethodType.class, request.getPaymentMethodType());
    		payment.setPaymentMethodType(paymentMethodType);
    	}
    	payment.setCode(request.getCode()); 
    	payment.setName(request.getName()); 
    	payment.setDescription(request.getDescription()); 
    	payment.setComment(request.getComment()); 
    	payment.setAmount(request.getAmount()); 
    	payment.setEffectiveDt(request.getEffectiveDt()); 
    	payment.setRecSt(request.getRecSt()); 
		return payment;
	}
}
