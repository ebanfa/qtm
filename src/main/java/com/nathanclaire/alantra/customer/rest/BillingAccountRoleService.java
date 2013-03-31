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

import com.nathanclaire.alantra.party.model.Party;
import com.nathanclaire.alantra.party.rest.request.PartyRequest;
import com.nathanclaire.alantra.base.rest.BaseEntityService;
import com.nathanclaire.alantra.customer.model.BillingAccount;
import com.nathanclaire.alantra.customer.model.BillingAccountRole;
import com.nathanclaire.alantra.customer.model.BillingAccountRoleType;
import com.nathanclaire.alantra.customer.rest.request.BillingAccountRequest;
import com.nathanclaire.alantra.customer.rest.request.BillingAccountRoleRequest;
import com.nathanclaire.alantra.customer.rest.request.BillingAccountRoleTypeRequest;

/**
 * @author administrator
 *
 */
@Path("/billingaccountrole")
@Stateless
public class BillingAccountRoleService extends BaseEntityService<BillingAccountRole> 
{
	/**
	 * @param entityClass
	 */
	public BillingAccountRoleService() {
		super(BillingAccountRole.class);
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
            CriteriaBuilder criteriaBuilder, Root<BillingAccountRole> root) 
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
     *   Create a BillingAccountRole. Data is contained in the BillingAccountRoleRequest object
     * </p>
     * @param BillingAccountRoleRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBillingAccountRole(BillingAccountRoleRequest request) {
        try 
        {
        	BillingAccountRole billingAccountRole = this.loadModelFromRequest(request);
        	entityManager.persist(billingAccountRole);
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
    public Response editBillingAccountRole(BillingAccountRoleRequest request) 
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
    private BillingAccountRole loadModelFromRequest(BillingAccountRoleRequest request) 
    {
		BillingAccountRole billingAccountRole = new BillingAccountRole();
    	Integer billingAccountRoleId = request.getId();
    	// Are we editing a BillingAccountRole
    	if(billingAccountRoleId != null) 
    	{
    		billingAccountRole = getEntityManager().find(BillingAccountRole.class, request.getId());
    		billingAccountRole.setLastModifiedDt(request.getLastModifiedDt());
        	billingAccountRole.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	billingAccountRole.setCreatedDt(getCurrentDate());
        	billingAccountRole.setCreatedByUsr(getCurrentUserName(request));
    	}
    	billingAccountRole.setCode(request.getCode());
    	billingAccountRole.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getBillingAccountRoleType() != null)
    	{
    		BillingAccountRoleType billingAccountRoleType = entityManager.find(BillingAccountRoleType.class, request.getBillingAccountRoleType());
    		billingAccountRole.setBillingAccountRoleType(billingAccountRoleType);
    	}
    	if (request.getParty() != null)
    	{
    		Party party = entityManager.find(Party.class, request.getParty());
    		billingAccountRole.setParty(party);
    	}
    	if (request.getBillingAccount() != null)
    	{
    		BillingAccount billingAccount = entityManager.find(BillingAccount.class, request.getBillingAccount());
    		billingAccountRole.setBillingAccount(billingAccount);
    	}
    	billingAccountRole.setCode(request.getCode()); 
    	billingAccountRole.setName(request.getName()); 
    	billingAccountRole.setDescription(request.getDescription()); 
    	billingAccountRole.setFromDt(request.getFromDt()); 
    	billingAccountRole.setThruDt(request.getThruDt()); 
    	billingAccountRole.setEffectiveDt(request.getEffectiveDt()); 
    	billingAccountRole.setRecSt(request.getRecSt()); 
		return billingAccountRole;
	}
}
