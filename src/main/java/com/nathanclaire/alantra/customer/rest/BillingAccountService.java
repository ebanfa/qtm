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
import com.nathanclaire.alantra.base.rest.request.BillingAccountRequest;
import com.nathanclaire.alantra.customer.model.BillingAccount;

/**
 * @author administrator
 *
 */
@Path("/billingaccount")
@Stateless
public class BillingAccountService extends BaseEntityService<BillingAccount> 
{
	/**
	 * @param entityClass
	 */
	public BillingAccountService() {
		super(BillingAccount.class);
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
            CriteriaBuilder criteriaBuilder, Root<BillingAccount> root) 
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
     *   Create a BillingAccount. Data is contained in the BillingAccountRequest object
     * </p>
     * @param BillingAccountRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBillingAccount(BillingAccountRequest request) {
        try 
        {
        	BillingAccount billingAccount = this.loadModelFromRequest(request);
        	entityManager.persist(billingAccount);
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
    public Response editBillingAccount(BillingAccountRequest request) 
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
    private BillingAccount loadModelFromRequest(BillingAccountRequest request) 
    {
		BillingAccount billingAccount = new BillingAccount();
    	Integer billingAccountId = request.getId();
    	// Are we editing a BillingAccount
    	if(billingAccountId != null) 
    	{
    		billingAccount = getEntityManager().find(BillingAccount.class, request.getId());
    		billingAccount.setLastModifiedDt(request.getLastModifiedDt());
        	billingAccount.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	billingAccount.setCreatedDt(getCurrentDate());
        	billingAccount.setCreatedByUsr(getCurrentUserName(request));
    	}
    	billingAccount.setCode(request.getCode());
    	billingAccount.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	billingAccount.setContMechId(request.getContMechId()); 
    	billingAccount.setCode(request.getCode()); 
    	billingAccount.setName(request.getName()); 
    	billingAccount.setDescription(request.getDescription()); 
    	billingAccount.setFromDt(request.getFromDt()); 
    	billingAccount.setThruDt(request.getThruDt()); 
    	billingAccount.setEffectiveDt(request.getEffectiveDt()); 
    	billingAccount.setRecSt(request.getRecSt()); 
		return billingAccount;
	}
}
