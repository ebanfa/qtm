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
import com.nathanclaire.alantra.base.rest.request.PartyRelationshipTypeRequest;
import com.nathanclaire.alantra.base.rest.request.PartyRoleTypeRequest;
import com.nathanclaire.alantra.party.model.PartyRelationshipType;
import com.nathanclaire.alantra.party.model.PartyRoleType;

/**
 * @author administrator
 *
 */
@Path("/partyrelationshiptype")
@Stateless
public class PartyRelationshipTypeService extends BaseEntityService<PartyRelationshipType> 
{
	/**
	 * @param entityClass
	 */
	public PartyRelationshipTypeService() {
		super(PartyRelationshipType.class);
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
            CriteriaBuilder criteriaBuilder, Root<PartyRelationshipType> root) 
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
     *   Create a PartyRelationshipType. Data is contained in the PartyRelationshipTypeRequest object
     * </p>
     * @param PartyRelationshipTypeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPartyRelationshipType(PartyRelationshipTypeRequest request) {
        try 
        {
        	PartyRelationshipType partyRelationshipType = this.loadModelFromRequest(request);
        	entityManager.persist(partyRelationshipType);
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
    public Response editPartyRelationshipType(PartyRelationshipTypeRequest request) 
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
    private PartyRelationshipType loadModelFromRequest(PartyRelationshipTypeRequest request) 
    {
		PartyRelationshipType partyRelationshipType = new PartyRelationshipType();
    	Integer partyRelationshipTypeId = request.getId();
    	// Are we editing a PartyRelationshipType
    	if(partyRelationshipTypeId != null) 
    	{
    		partyRelationshipType = getEntityManager().find(PartyRelationshipType.class, request.getId());
    		partyRelationshipType.setLastModifiedDt(request.getLastModifiedDt());
        	partyRelationshipType.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	partyRelationshipType.setCreatedDt(getCurrentDate());
        	partyRelationshipType.setCreatedByUsr(getCurrentUserName(request));
    	}
    	partyRelationshipType.setCode(request.getCode());
    	partyRelationshipType.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getPartyRoleTypeByFromRoleTyId() != null)
    	{
    		PartyRoleType partyRoleTypeByFromRoleTyId = entityManager.find(PartyRoleType.class, request.getPartyRoleTypeByFromRoleTyId());
    		partyRelationshipType.setPartyRoleTypeByFromRoleTyId(partyRoleTypeByFromRoleTyId);
    	}
    	if (request.getPartyRoleTypeByToRoleTyId() != null)
    	{
    		PartyRoleType partyRoleTypeByToRoleTyId = entityManager.find(PartyRoleType.class, request.getPartyRoleTypeByToRoleTyId());
    		partyRelationshipType.setPartyRoleTypeByToRoleTyId(partyRoleTypeByToRoleTyId);
    	}
    	partyRelationshipType.setCode(request.getCode()); 
    	partyRelationshipType.setName(request.getName()); 
    	partyRelationshipType.setDescription(request.getDescription()); 
    	partyRelationshipType.setEffectiveDt(request.getEffectiveDt()); 
    	partyRelationshipType.setRecSt(request.getRecSt()); 
		return partyRelationshipType;
	}
}
