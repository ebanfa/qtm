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

import com.nathanclaire.alantra.model.PartyRole;
import com.nathanclaire.alantra.model.PartyRoleType;
import com.nathanclaire.alantra.rest.request.PartyRoleTypeRequest;
import com.nathanclaire.alantra.model.Party;
import com.nathanclaire.alantra.rest.request.PartyRequest;
import com.nathanclaire.alantra.rest.request.PartyRoleRequest;

/**
 * @author administrator
 *
 */
@Path("/partyrole")
@Stateless
public class PartyRoleService extends BaseEntityService<PartyRole> 
{
	/**
	 * @param entityClass
	 */
	public PartyRoleService() {
		super(PartyRole.class);
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
            CriteriaBuilder criteriaBuilder, Root<PartyRole> root) 
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
     *   Create a PartyRole. Data is contained in the PartyRoleRequest object
     * </p>
     * @param PartyRoleRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPartyRole(PartyRoleRequest request) {
        try 
        {
        	PartyRole partyRole = this.loadModelFromRequest(request);
        	entityManager.persist(partyRole);
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
    public Response editPartyRole(PartyRoleRequest request) 
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
    private PartyRole loadModelFromRequest(PartyRoleRequest request) 
    {
		PartyRole partyRole = new PartyRole();
    	Integer partyRoleId = request.getId();
    	// Are we editing a PartyRole
    	if(partyRoleId != null) 
    	{
    		partyRole = getEntityManager().find(PartyRole.class, request.getId());
    		partyRole.setLastModifiedDt(request.getLastModifiedDt());
        	partyRole.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	partyRole.setCreatedDt(getCurrentDate());
        	partyRole.setCreatedByUsr(getCurrentUserName(request));
    	}
    	partyRole.setCode(request.getCode());
    	partyRole.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getPartyRoleType() != null)
    	{
    		PartyRoleType partyRoleType = entityManager.find(PartyRoleType.class, request.getPartyRoleType());
    		partyRole.setPartyRoleType(partyRoleType);
    	}
    	if (request.getParty() != null)
    	{
    		Party party = entityManager.find(Party.class, request.getParty());
    		partyRole.setParty(party);
    	}
    	partyRole.setCode(request.getCode()); 
    	partyRole.setName(request.getName()); 
    	partyRole.setDescription(request.getDescription()); 
    	partyRole.setFromDt(request.getFromDt()); 
    	partyRole.setThruDt(request.getThruDt()); 
    	partyRole.setEffectiveDt(request.getEffectiveDt()); 
    	partyRole.setRecSt(request.getRecSt()); 
		return partyRole;
	}
}
