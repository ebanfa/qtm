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

import com.nathanclaire.alantra.base.rest.BaseEntityService;
import com.nathanclaire.alantra.base.rest.request.ContactMechanismRequest;
import com.nathanclaire.alantra.base.rest.request.PostalAddressRequest;
import com.nathanclaire.alantra.party.model.ContactMechanism;
import com.nathanclaire.alantra.party.model.PostalAddress;

/**
 * @author administrator
 *
 */
@Path("/postaladdress")
@Stateless
public class PostalAddressService extends BaseEntityService<PostalAddress> 
{
	/**
	 * @param entityClass
	 */
	public PostalAddressService() {
		super(PostalAddress.class);
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
            CriteriaBuilder criteriaBuilder, Root<PostalAddress> root) 
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
     *   Create a PostalAddress. Data is contained in the PostalAddressRequest object
     * </p>
     * @param PostalAddressRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPostalAddress(PostalAddressRequest request) {
        try 
        {
        	PostalAddress postalAddress = this.loadModelFromRequest(request);
        	entityManager.persist(postalAddress);
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
    public Response editPostalAddress(PostalAddressRequest request) 
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
    private PostalAddress loadModelFromRequest(PostalAddressRequest request) 
    {
		PostalAddress postalAddress = new PostalAddress();
    	Integer postalAddressId = request.getId();
    	// Are we editing a PostalAddress
    	if(postalAddressId != null) 
    	{
    		postalAddress = getEntityManager().find(PostalAddress.class, request.getId());
    		postalAddress.setLastModifiedDt(request.getLastModifiedDt());
        	postalAddress.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	postalAddress.setCreatedDt(getCurrentDate());
        	postalAddress.setCreatedByUsr(getCurrentUserName(request));
    	}
    	postalAddress.setCode(request.getCode());
    	postalAddress.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getContactMechanism() != null)
    	{
    		ContactMechanism contactMechanism = entityManager.find(ContactMechanism.class, request.getContactMechanism());
    		postalAddress.setContactMechanism(contactMechanism);
    	}
    	postalAddress.setCode(request.getCode()); 
    	postalAddress.setAddress1(request.getAddress1()); 
    	postalAddress.setAddress2(request.getAddress2()); 
    	postalAddress.setDirections(request.getDirections()); 
    	postalAddress.setDescription(request.getDescription()); 
    	postalAddress.setEffectiveDt(request.getEffectiveDt()); 
    	postalAddress.setRecSt(request.getRecSt()); 
		return postalAddress;
	}
}
