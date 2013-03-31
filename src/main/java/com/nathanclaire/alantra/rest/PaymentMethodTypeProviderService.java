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

import com.nathanclaire.alantra.model.PaymentMethodTypeProvider;
import com.nathanclaire.alantra.model.PaymentMethodType;
import com.nathanclaire.alantra.rest.request.PaymentMethodTypeRequest;
import com.nathanclaire.alantra.model.Party;
import com.nathanclaire.alantra.rest.request.PartyRequest;
import com.nathanclaire.alantra.rest.request.PaymentMethodTypeProviderRequest;

/**
 * @author administrator
 *
 */
@Path("/paymentmethodtypeprovider")
@Stateless
public class PaymentMethodTypeProviderService extends BaseEntityService<PaymentMethodTypeProvider> 
{
	/**
	 * @param entityClass
	 */
	public PaymentMethodTypeProviderService() {
		super(PaymentMethodTypeProvider.class);
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
            CriteriaBuilder criteriaBuilder, Root<PaymentMethodTypeProvider> root) 
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
     *   Create a PaymentMethodTypeProvider. Data is contained in the PaymentMethodTypeProviderRequest object
     * </p>
     * @param PaymentMethodTypeProviderRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPaymentMethodTypeProvider(PaymentMethodTypeProviderRequest request) {
        try 
        {
        	PaymentMethodTypeProvider paymentMethodTypeProvider = this.loadModelFromRequest(request);
        	entityManager.persist(paymentMethodTypeProvider);
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
    public Response editPaymentMethodTypeProvider(PaymentMethodTypeProviderRequest request) 
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
    private PaymentMethodTypeProvider loadModelFromRequest(PaymentMethodTypeProviderRequest request) 
    {
		PaymentMethodTypeProvider paymentMethodTypeProvider = new PaymentMethodTypeProvider();
    	Integer paymentMethodTypeProviderId = request.getId();
    	// Are we editing a PaymentMethodTypeProvider
    	if(paymentMethodTypeProviderId != null) 
    	{
    		paymentMethodTypeProvider = getEntityManager().find(PaymentMethodTypeProvider.class, request.getId());
    		paymentMethodTypeProvider.setLastModifiedDt(request.getLastModifiedDt());
        	paymentMethodTypeProvider.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	paymentMethodTypeProvider.setCreatedDt(getCurrentDate());
        	paymentMethodTypeProvider.setCreatedByUsr(getCurrentUserName(request));
    	}
    	paymentMethodTypeProvider.setCode(request.getCode());
    	paymentMethodTypeProvider.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getPaymentMethodType() != null)
    	{
    		PaymentMethodType paymentMethodType = entityManager.find(PaymentMethodType.class, request.getPaymentMethodType());
    		paymentMethodTypeProvider.setPaymentMethodType(paymentMethodType);
    	}
    	if (request.getParty() != null)
    	{
    		Party party = entityManager.find(Party.class, request.getParty());
    		paymentMethodTypeProvider.setParty(party);
    	}
    	paymentMethodTypeProvider.setCode(request.getCode()); 
    	paymentMethodTypeProvider.setName(request.getName()); 
    	paymentMethodTypeProvider.setDescription(request.getDescription()); 
    	paymentMethodTypeProvider.setFromDt(request.getFromDt()); 
    	paymentMethodTypeProvider.setThruDt(request.getThruDt()); 
    	paymentMethodTypeProvider.setEffectiveDt(request.getEffectiveDt()); 
    	paymentMethodTypeProvider.setRecSt(request.getRecSt()); 
		return paymentMethodTypeProvider;
	}
}
