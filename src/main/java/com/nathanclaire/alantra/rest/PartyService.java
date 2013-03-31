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

import com.nathanclaire.alantra.model.Party;
import com.nathanclaire.alantra.model.PartyType;
import com.nathanclaire.alantra.rest.request.PartyTypeRequest;
import com.nathanclaire.alantra.rest.request.PartyRequest;

/**
 * @author administrator
 *
 */
@Path("/party")
@Stateless
public class PartyService extends BaseEntityService<Party> 
{
	/**
	 * @param entityClass
	 */
	public PartyService() {
		super(Party.class);
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
            CriteriaBuilder criteriaBuilder, Root<Party> root) 
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
     *   Create a Party. Data is contained in the PartyRequest object
     * </p>
     * @param PartyRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createParty(PartyRequest request) {
        try 
        {
        	Party party = this.loadModelFromRequest(request);
        	entityManager.persist(party);
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
    public Response editParty(PartyRequest request) 
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
    private Party loadModelFromRequest(PartyRequest request) 
    {
		Party party = new Party();
    	Integer partyId = request.getId();
    	// Are we editing a Party
    	if(partyId != null) 
    	{
    		party = getEntityManager().find(Party.class, request.getId());
    		party.setLastModifiedDt(request.getLastModifiedDt());
        	party.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	party.setCreatedDt(getCurrentDate());
        	party.setCreatedByUsr(getCurrentUserName(request));
    	}
    	party.setCode(request.getCode());
    	party.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getPartyType() != null)
    	{
    		PartyType partyType = entityManager.find(PartyType.class, request.getPartyType());
    		party.setPartyType(partyType);
    	}
    	party.setCode(request.getCode()); 
    	party.setName(request.getName()); 
    	party.setDescription(request.getDescription()); 
    	party.setEffectiveDt(request.getEffectiveDt()); 
    	party.setRecSt(request.getRecSt()); 
		return party;
	}
}
