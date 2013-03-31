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
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.rest.request.CustomerRequest;
import com.nathanclaire.alantra.party.model.Party;
import com.nathanclaire.alantra.party.rest.request.PartyRequest;

/**
 * @author administrator
 *
 */
@Path("/customer")
@Stateless
public class CustomerService extends BaseEntityService<Customer> 
{
	/**
	 * @param entityClass
	 */
	public CustomerService() {
		super(Customer.class);
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
            CriteriaBuilder criteriaBuilder, Root<Customer> root) 
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
     *   Create a Customer. Data is contained in the CustomerRequest object
     * </p>
     * @param CustomerRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCustomer(CustomerRequest request) {
        try 
        {
        	Customer customer = this.loadModelFromRequest(request);
        	entityManager.persist(customer);
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
    public Response editCustomer(CustomerRequest request) 
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
    private Customer loadModelFromRequest(CustomerRequest request) 
    {
		Customer customer = new Customer();
    	Integer customerId = request.getId();
    	// Are we editing a Customer
    	if(customerId != null) 
    	{
    		customer = getEntityManager().find(Customer.class, request.getId());
    		customer.setLastModifiedDt(request.getLastModifiedDt());
        	customer.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	customer.setCreatedDt(getCurrentDate());
        	customer.setCreatedByUsr(getCurrentUserName(request));
    	}
    	customer.setCode(request.getCode());
    	customer.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getParty() != null)
    	{
    		Party party = entityManager.find(Party.class, request.getParty());
    		customer.setParty(party);
    	}
    	customer.setCode(request.getCode()); 
    	customer.setEffectiveDt(request.getEffectiveDt()); 
    	customer.setRecSt(request.getRecSt()); 
		return customer;
	}
}
