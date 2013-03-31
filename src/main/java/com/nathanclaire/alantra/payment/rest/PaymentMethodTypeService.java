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

import com.nathanclaire.alantra.base.rest.BaseEntityService;
import com.nathanclaire.alantra.payment.model.PaymentMethodType;
import com.nathanclaire.alantra.payment.rest.request.PaymentMethodTypeRequest;

/**
 * @author administrator
 *
 */
@Path("/paymentmethodtype")
@Stateless
public class PaymentMethodTypeService extends BaseEntityService<PaymentMethodType> 
{
	/**
	 * @param entityClass
	 */
	public PaymentMethodTypeService() {
		super(PaymentMethodType.class);
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
            CriteriaBuilder criteriaBuilder, Root<PaymentMethodType> root) 
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
     *   Create a PaymentMethodType. Data is contained in the PaymentMethodTypeRequest object
     * </p>
     * @param PaymentMethodTypeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPaymentMethodType(PaymentMethodTypeRequest request) {
        try 
        {
        	PaymentMethodType paymentMethodType = this.loadModelFromRequest(request);
        	entityManager.persist(paymentMethodType);
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
    public Response editPaymentMethodType(PaymentMethodTypeRequest request) 
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
    private PaymentMethodType loadModelFromRequest(PaymentMethodTypeRequest request) 
    {
		PaymentMethodType paymentMethodType = new PaymentMethodType();
    	Integer paymentMethodTypeId = request.getId();
    	// Are we editing a PaymentMethodType
    	if(paymentMethodTypeId != null) 
    	{
    		paymentMethodType = getEntityManager().find(PaymentMethodType.class, request.getId());
    		paymentMethodType.setLastModifiedDt(request.getLastModifiedDt());
        	paymentMethodType.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	paymentMethodType.setCreatedDt(getCurrentDate());
        	paymentMethodType.setCreatedByUsr(getCurrentUserName(request));
    	}
    	paymentMethodType.setCode(request.getCode());
    	paymentMethodType.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	paymentMethodType.setCode(request.getCode()); 
    	paymentMethodType.setName(request.getName()); 
    	paymentMethodType.setDescription(request.getDescription()); 
    	paymentMethodType.setEffectiveDt(request.getEffectiveDt()); 
    	paymentMethodType.setRecSt(request.getRecSt()); 
		return paymentMethodType;
	}
}
