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
import com.nathanclaire.alantra.base.rest.request.PaymentTypeRequest;
import com.nathanclaire.alantra.payments.model.PaymentType;

/**
 * @author administrator
 *
 */
@Path("/paymenttype")
@Stateless
public class PaymentTypeService extends BaseEntityService<PaymentType> 
{
	/**
	 * @param entityClass
	 */
	public PaymentTypeService() {
		super(PaymentType.class);
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
            CriteriaBuilder criteriaBuilder, Root<PaymentType> root) 
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
     *   Create a PaymentType. Data is contained in the PaymentTypeRequest object
     * </p>
     * @param PaymentTypeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPaymentType(PaymentTypeRequest request) {
        try 
        {
        	PaymentType paymentType = this.loadModelFromRequest(request);
        	entityManager.persist(paymentType);
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
    public Response editPaymentType(PaymentTypeRequest request) 
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
    private PaymentType loadModelFromRequest(PaymentTypeRequest request) 
    {
		PaymentType paymentType = new PaymentType();
    	Integer paymentTypeId = request.getId();
    	// Are we editing a PaymentType
    	if(paymentTypeId != null) 
    	{
    		paymentType = getEntityManager().find(PaymentType.class, request.getId());
    		paymentType.setLastModifiedDt(request.getLastModifiedDt());
        	paymentType.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	paymentType.setCreatedDt(getCurrentDate());
        	paymentType.setCreatedByUsr(getCurrentUserName(request));
    	}
    	paymentType.setCode(request.getCode());
    	paymentType.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	paymentType.setCode(request.getCode()); 
    	paymentType.setName(request.getName()); 
    	paymentType.setDescription(request.getDescription()); 
    	paymentType.setEffectiveDt(request.getEffectiveDt()); 
    	paymentType.setRecSt(request.getRecSt()); 
		return paymentType;
	}
}
