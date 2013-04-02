/**
 * 
 */
package com.nathanclaire.alantra.customer.rest;

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
import com.nathanclaire.alantra.customer.model.CustomerBlacklist;
import com.nathanclaire.alantra.customer.rest.request.CustomerBlacklistRequest;
import com.nathanclaire.alantra.party.model.Party;

/**
 * @author administrator
 *
 */
@Path("/customerblacklist")
@Stateless
public class CustomerBlacklistService extends BaseEntityService<CustomerBlacklist> 
{
	/**
	 * @param entityClass
	 */
	public CustomerBlacklistService() {
		super(CustomerBlacklist.class);
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
            CriteriaBuilder criteriaBuilder, Root<CustomerBlacklist> root) 
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
     *   Create a CustomerBlacklist. Data is contained in the CustomerBlacklistRequest object
     * </p>
     * @param CustomerBlacklistRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCustomerBlacklist(CustomerBlacklistRequest request) {
        try 
        {
        	CustomerBlacklist customerBlacklist = this.loadModelFromRequest(request);
        	entityManager.persist(customerBlacklist);
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
    public Response editCustomerBlacklist(CustomerBlacklistRequest request) 
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
    private CustomerBlacklist loadModelFromRequest(CustomerBlacklistRequest request) 
    {
		CustomerBlacklist customerBlacklist = new CustomerBlacklist();
    	Integer customerBlacklistId = request.getId();
    	// Are we editing a CustomerBlacklist
    	if(customerBlacklistId != null) 
    	{
    		customerBlacklist = getEntityManager().find(CustomerBlacklist.class, request.getId());
    		customerBlacklist.setLastModifiedDt(request.getLastModifiedDt());
        	customerBlacklist.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	customerBlacklist.setCreatedDt(getCurrentDate());
        	customerBlacklist.setCreatedByUsr(getCurrentUserName(request));
    	}
    	customerBlacklist.setCode(request.getCode());
    	customerBlacklist.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getParty() != null)
    	{
    		Party party = entityManager.find(Party.class, request.getParty());
    		customerBlacklist.setParty(party);
    	}
    	customerBlacklist.setCode(request.getCode()); 
    	customerBlacklist.setDescription(request.getDescription()); 
    	customerBlacklist.setEffectiveDt(request.getEffectiveDt()); 
    	customerBlacklist.setRecSt(request.getRecSt()); 
		return customerBlacklist;
	}
}
