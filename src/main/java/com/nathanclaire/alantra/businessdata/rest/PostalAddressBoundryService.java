/**
 * 
 */
package com.nathanclaire.alantra.businessdata.rest;

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

import com.nathanclaire.alantra.party.model.PostalAddress;
import com.nathanclaire.alantra.party.rest.request.PostalAddressRequest;
import com.nathanclaire.alantra.base.rest.BaseEntityService;
import com.nathanclaire.alantra.businessdata.model.GeoBoundry;
import com.nathanclaire.alantra.businessdata.model.PostalAddressBoundry;
import com.nathanclaire.alantra.businessdata.rest.request.GeoBoundryRequest;
import com.nathanclaire.alantra.businessdata.rest.request.PostalAddressBoundryRequest;

/**
 * @author administrator
 *
 */
@Path("/postaladdressboundry")
@Stateless
public class PostalAddressBoundryService extends BaseEntityService<PostalAddressBoundry> 
{
	/**
	 * @param entityClass
	 */
	public PostalAddressBoundryService() {
		super(PostalAddressBoundry.class);
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
            CriteriaBuilder criteriaBuilder, Root<PostalAddressBoundry> root) 
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
     *   Create a PostalAddressBoundry. Data is contained in the PostalAddressBoundryRequest object
     * </p>
     * @param PostalAddressBoundryRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPostalAddressBoundry(PostalAddressBoundryRequest request) {
        try 
        {
        	PostalAddressBoundry postalAddressBoundry = this.loadModelFromRequest(request);
        	entityManager.persist(postalAddressBoundry);
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
    public Response editPostalAddressBoundry(PostalAddressBoundryRequest request) 
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
    private PostalAddressBoundry loadModelFromRequest(PostalAddressBoundryRequest request) 
    {
		PostalAddressBoundry postalAddressBoundry = new PostalAddressBoundry();
    	Integer postalAddressBoundryId = request.getId();
    	// Are we editing a PostalAddressBoundry
    	if(postalAddressBoundryId != null) 
    	{
    		postalAddressBoundry = getEntityManager().find(PostalAddressBoundry.class, request.getId());
    		postalAddressBoundry.setLastModifiedDt(request.getLastModifiedDt());
        	postalAddressBoundry.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	postalAddressBoundry.setCreatedDt(getCurrentDate());
        	postalAddressBoundry.setCreatedByUsr(getCurrentUserName(request));
    	}
    	postalAddressBoundry.setCode(request.getCode());
    	postalAddressBoundry.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getPostalAddress() != null)
    	{
    		PostalAddress postalAddress = entityManager.find(PostalAddress.class, request.getPostalAddress());
    		postalAddressBoundry.setPostalAddress(postalAddress);
    	}
    	if (request.getGeoBoundry() != null)
    	{
    		GeoBoundry geoBoundry = entityManager.find(GeoBoundry.class, request.getGeoBoundry());
    		postalAddressBoundry.setGeoBoundry(geoBoundry);
    	}
    	postalAddressBoundry.setCode(request.getCode()); 
    	postalAddressBoundry.setDescription(request.getDescription()); 
    	postalAddressBoundry.setEffectiveDt(request.getEffectiveDt()); 
    	postalAddressBoundry.setRecSt(request.getRecSt()); 
		return postalAddressBoundry;
	}
}
