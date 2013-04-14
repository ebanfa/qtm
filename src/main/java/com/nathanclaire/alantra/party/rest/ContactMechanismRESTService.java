/**
 * 
 */
package com.nathanclaire.alantra.party.rest;

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

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.party.model.ContactMechanism;
import com.nathanclaire.alantra.party.model.ContactMechanismType;
import com.nathanclaire.alantra.party.rest.request.ContactMechanismTypeRequest;
import com.nathanclaire.alantra.party.rest.request.ContactMechanismRequest;

/**
 * @author administrator
 *
 */
@Path("/contactmechanism")
@Stateless
public class ContactMechanismRESTService extends BaseEntityRESTService<ContactMechanism> 
{
	/**
	 * @param entityClass
	 */
	public ContactMechanismRESTService() {
		super(ContactMechanism.class);
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
            CriteriaBuilder criteriaBuilder, Root<ContactMechanism> root) 
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
     *   Create a ContactMechanism. Data is contained in the ContactMechanismRequest object
     * </p>
     * @param ContactMechanismRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createContactMechanism(ContactMechanismRequest request) {
        try 
        {
        	ContactMechanism contactMechanism = this.loadModelFromRequest(request);
        	entityManager.persist(contactMechanism);
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
    public Response editContactMechanism(ContactMechanismRequest request) 
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
    private ContactMechanism loadModelFromRequest(ContactMechanismRequest request) 
    {
		ContactMechanism contactMechanism = new ContactMechanism();
    	Integer contactMechanismId = request.getId();
    	// Are we editing a ContactMechanism
    	if(contactMechanismId != null) 
    	{
    		contactMechanism = getEntityManager().find(ContactMechanism.class, request.getId());
    		contactMechanism.setLastModifiedDt(request.getLastModifiedDt());
        	contactMechanism.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	contactMechanism.setCreatedDt(getCurrentDate());
        	contactMechanism.setCreatedByUsr(getCurrentUserName(request));
    	}
    	contactMechanism.setCode(request.getCode());
    	contactMechanism.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getContactMechanismType() != null)
    	{
    		ContactMechanismType contactMechanismType = entityManager.find(ContactMechanismType.class, request.getContactMechanismType());
    		contactMechanism.setContactMechanismType(contactMechanismType);
    	}
    	contactMechanism.setName(request.getName()); 
    	contactMechanism.setDescription(request.getDescription()); 
    	contactMechanism.setCode(request.getCode()); 
    	contactMechanism.setEffectiveDt(request.getEffectiveDt()); 
    	contactMechanism.setRecSt(request.getRecSt()); 
		return contactMechanism;
	}
}
