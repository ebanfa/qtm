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

import com.nathanclaire.alantra.model.ContactMechanismLink;
import com.nathanclaire.alantra.model.ContactMechanism;
import com.nathanclaire.alantra.rest.request.ContactMechanismRequest;
import com.nathanclaire.alantra.model.ContactMechanism;
import com.nathanclaire.alantra.rest.request.ContactMechanismRequest;
import com.nathanclaire.alantra.rest.request.ContactMechanismLinkRequest;

/**
 * @author administrator
 *
 */
@Path("/contactmechanismlink")
@Stateless
public class ContactMechanismLinkService extends BaseEntityService<ContactMechanismLink> 
{
	/**
	 * @param entityClass
	 */
	public ContactMechanismLinkService() {
		super(ContactMechanismLink.class);
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
            CriteriaBuilder criteriaBuilder, Root<ContactMechanismLink> root) 
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
     *   Create a ContactMechanismLink. Data is contained in the ContactMechanismLinkRequest object
     * </p>
     * @param ContactMechanismLinkRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createContactMechanismLink(ContactMechanismLinkRequest request) {
        try 
        {
        	ContactMechanismLink contactMechanismLink = this.loadModelFromRequest(request);
        	entityManager.persist(contactMechanismLink);
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
    public Response editContactMechanismLink(ContactMechanismLinkRequest request) 
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
    private ContactMechanismLink loadModelFromRequest(ContactMechanismLinkRequest request) 
    {
		ContactMechanismLink contactMechanismLink = new ContactMechanismLink();
    	Integer contactMechanismLinkId = request.getId();
    	// Are we editing a ContactMechanismLink
    	if(contactMechanismLinkId != null) 
    	{
    		contactMechanismLink = getEntityManager().find(ContactMechanismLink.class, request.getId());
    		contactMechanismLink.setLastModifiedDt(request.getLastModifiedDt());
        	contactMechanismLink.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	contactMechanismLink.setCreatedDt(getCurrentDate());
        	contactMechanismLink.setCreatedByUsr(getCurrentUserName(request));
    	}
    	contactMechanismLink.setCode(request.getCode());
    	contactMechanismLink.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getContactMechanismByToId() != null)
    	{
    		ContactMechanism contactMechanismByToId = entityManager.find(ContactMechanism.class, request.getContactMechanismByToId());
    		contactMechanismLink.setContactMechanismByToId(contactMechanismByToId);
    	}
    	if (request.getContactMechanismByFromId() != null)
    	{
    		ContactMechanism contactMechanismByFromId = entityManager.find(ContactMechanism.class, request.getContactMechanismByFromId());
    		contactMechanismLink.setContactMechanismByFromId(contactMechanismByFromId);
    	}
    	contactMechanismLink.setCode(request.getCode()); 
    	contactMechanismLink.setDescription(request.getDescription()); 
    	contactMechanismLink.setEffectiveDt(request.getEffectiveDt()); 
    	contactMechanismLink.setRecSt(request.getRecSt()); 
		return contactMechanismLink;
	}
}
