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
import com.nathanclaire.alantra.party.model.PartyRelationship;
import com.nathanclaire.alantra.party.model.PartyRelationshipType;
import com.nathanclaire.alantra.party.model.PartyRole;
import com.nathanclaire.alantra.party.rest.request.PartyRelationshipRequest;
import com.nathanclaire.alantra.party.rest.request.PartyRelationshipTypeRequest;
import com.nathanclaire.alantra.party.rest.request.PartyRoleRequest;

/**
 * @author administrator
 *
 */
@Path("/partyrelationship")
@Stateless
public class PartyRelationshipService extends BaseEntityService<PartyRelationship> 
{
	/**
	 * @param entityClass
	 */
	public PartyRelationshipService() {
		super(PartyRelationship.class);
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
            CriteriaBuilder criteriaBuilder, Root<PartyRelationship> root) 
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
     *   Create a PartyRelationship. Data is contained in the PartyRelationshipRequest object
     * </p>
     * @param PartyRelationshipRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPartyRelationship(PartyRelationshipRequest request) {
        try 
        {
        	PartyRelationship partyRelationship = this.loadModelFromRequest(request);
        	entityManager.persist(partyRelationship);
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
    public Response editPartyRelationship(PartyRelationshipRequest request) 
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
    private PartyRelationship loadModelFromRequest(PartyRelationshipRequest request) 
    {
		PartyRelationship partyRelationship = new PartyRelationship();
    	Integer partyRelationshipId = request.getId();
    	// Are we editing a PartyRelationship
    	if(partyRelationshipId != null) 
    	{
    		partyRelationship = getEntityManager().find(PartyRelationship.class, request.getId());
    		partyRelationship.setLastModifiedDt(request.getLastModifiedDt());
        	partyRelationship.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	partyRelationship.setCreatedDt(getCurrentDate());
        	partyRelationship.setCreatedByUsr(getCurrentUserName(request));
    	}
    	partyRelationship.setCode(request.getCode());
    	partyRelationship.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getPartyRole() != null)
    	{
    		PartyRole partyRole = entityManager.find(PartyRole.class, request.getPartyRole());
    		partyRelationship.setFromPartyRole(partyRole);
    	}
    	if (request.getPartyRelationshipType() != null)
    	{
    		PartyRelationshipType partyRelationshipType = entityManager.find(PartyRelationshipType.class, request.getPartyRelationshipType());
    		partyRelationship.setPartyRelationshipType(partyRelationshipType);
    	}
    	//partyRelationship.setToPartyRole(request.getToRoleId()); 
    	partyRelationship.setCode(request.getCode()); 
    	partyRelationship.setName(request.getName()); 
    	partyRelationship.setDescription(request.getDescription()); 
    	partyRelationship.setComment(request.getComment()); 
    	partyRelationship.setFromDt(request.getFromDt()); 
    	partyRelationship.setThruDt(request.getThruDt()); 
    	partyRelationship.setEffectiveDt(request.getEffectiveDt()); 
    	partyRelationship.setRecSt(request.getRecSt()); 
		return partyRelationship;
	}
}
