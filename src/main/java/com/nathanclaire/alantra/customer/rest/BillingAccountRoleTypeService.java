/**
 * 
 */
package com.nathanclaire.alantra.customer.rest;

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
import com.nathanclaire.alantra.customer.model.BillingAccountRoleType;
import com.nathanclaire.alantra.customer.rest.request.BillingAccountRoleTypeRequest;

/**
 * @author administrator
 *
 */
@Path("/billingaccountroletype")
@Stateless
public class BillingAccountRoleTypeService extends BaseEntityService<BillingAccountRoleType> 
{
	/**
	 * @param entityClass
	 */
	public BillingAccountRoleTypeService() {
		super(BillingAccountRoleType.class);
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
            CriteriaBuilder criteriaBuilder, Root<BillingAccountRoleType> root) 
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
     *   Create a BillingAccountRoleType. Data is contained in the BillingAccountRoleTypeRequest object
     * </p>
     * @param BillingAccountRoleTypeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBillingAccountRoleType(BillingAccountRoleTypeRequest request) {
        try 
        {
        	BillingAccountRoleType billingAccountRoleType = this.loadModelFromRequest(request);
        	entityManager.persist(billingAccountRoleType);
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
    public Response editBillingAccountRoleType(BillingAccountRoleTypeRequest request) 
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
    private BillingAccountRoleType loadModelFromRequest(BillingAccountRoleTypeRequest request) 
    {
		BillingAccountRoleType billingAccountRoleType = new BillingAccountRoleType();
    	Integer billingAccountRoleTypeId = request.getId();
    	// Are we editing a BillingAccountRoleType
    	if(billingAccountRoleTypeId != null) 
    	{
    		billingAccountRoleType = getEntityManager().find(BillingAccountRoleType.class, request.getId());
    		billingAccountRoleType.setLastModifiedDt(request.getLastModifiedDt());
        	billingAccountRoleType.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	billingAccountRoleType.setCreatedDt(getCurrentDate());
        	billingAccountRoleType.setCreatedByUsr(getCurrentUserName(request));
    	}
    	billingAccountRoleType.setCode(request.getCode());
    	billingAccountRoleType.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	billingAccountRoleType.setCode(request.getCode()); 
    	billingAccountRoleType.setName(request.getName()); 
    	billingAccountRoleType.setDescription(request.getDescription()); 
    	billingAccountRoleType.setEffectiveDt(request.getEffectiveDt()); 
    	billingAccountRoleType.setRecSt(request.getRecSt()); 
		return billingAccountRoleType;
	}
}
