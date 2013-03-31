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

import com.nathanclaire.alantra.model.PartyRoleType;
import com.nathanclaire.alantra.model.RoleType;
import com.nathanclaire.alantra.rest.request.RoleTypeRequest;
import com.nathanclaire.alantra.model.PartyRoleType;
import com.nathanclaire.alantra.rest.request.PartyRoleTypeRequest;
import com.nathanclaire.alantra.rest.request.PartyRoleTypeRequest;

/**
 * @author administrator
 *
 */
@Path("/partyroletype")
@Stateless
public class PartyRoleTypeService extends BaseEntityService<PartyRoleType> 
{
	/**
	 * @param entityClass
	 */
	public PartyRoleTypeService() {
		super(PartyRoleType.class);
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
            CriteriaBuilder criteriaBuilder, Root<PartyRoleType> root) 
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
     *   Create a PartyRoleType. Data is contained in the PartyRoleTypeRequest object
     * </p>
     * @param PartyRoleTypeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPartyRoleType(PartyRoleTypeRequest request) {
        try 
        {
        	PartyRoleType partyRoleType = this.loadModelFromRequest(request);
        	entityManager.persist(partyRoleType);
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
    public Response editPartyRoleType(PartyRoleTypeRequest request) 
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
    private PartyRoleType loadModelFromRequest(PartyRoleTypeRequest request) 
    {
		PartyRoleType partyRoleType = new PartyRoleType();
    	Integer partyRoleTypeId = request.getId();
    	// Are we editing a PartyRoleType
    	if(partyRoleTypeId != null) 
    	{
    		partyRoleType = getEntityManager().find(PartyRoleType.class, request.getId());
    		partyRoleType.setLastModifiedDt(request.getLastModifiedDt());
        	partyRoleType.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	partyRoleType.setCreatedDt(getCurrentDate());
        	partyRoleType.setCreatedByUsr(getCurrentUserName(request));
    	}
    	partyRoleType.setCode(request.getCode());
    	partyRoleType.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getRoleType() != null)
    	{
    		RoleType roleType = entityManager.find(RoleType.class, request.getRoleType());
    		partyRoleType.setRoleType(roleType);
    	}
    	if (request.getPartyRoleType() != null)
    	{
    		PartyRoleType parentPartyRoleType = entityManager.find(PartyRoleType.class, request.getPartyRoleType());
    		partyRoleType.setPartyRoleType(parentPartyRoleType);
    	}
    	partyRoleType.setCode(request.getCode()); 
    	partyRoleType.setName(request.getName()); 
    	partyRoleType.setDescription(request.getDescription()); 
    	partyRoleType.setEffectiveDt(request.getEffectiveDt()); 
    	partyRoleType.setRecSt(request.getRecSt()); 
		return partyRoleType;
	}
}
