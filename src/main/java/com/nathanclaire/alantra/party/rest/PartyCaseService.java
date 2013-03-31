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

import com.nathanclaire.alantra.party.model.CaseRole;
import com.nathanclaire.alantra.party.model.CaseStatusType;
import com.nathanclaire.alantra.party.model.PartyCase;
import com.nathanclaire.alantra.base.rest.BaseEntityService;
import com.nathanclaire.alantra.base.rest.request.CaseRoleRequest;
import com.nathanclaire.alantra.base.rest.request.CaseStatusTypeRequest;
import com.nathanclaire.alantra.base.rest.request.CommunicationEventRequest;
import com.nathanclaire.alantra.base.rest.request.PartyCaseRequest;
import com.nathanclaire.alantra.messaging.model.CommunicationEvent;

/**
 * @author administrator
 *
 */
@Path("/partycase")
@Stateless
public class PartyCaseService extends BaseEntityService<PartyCase> 
{
	/**
	 * @param entityClass
	 */
	public PartyCaseService() {
		super(PartyCase.class);
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
            CriteriaBuilder criteriaBuilder, Root<PartyCase> root) 
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
     *   Create a PartyCase. Data is contained in the PartyCaseRequest object
     * </p>
     * @param PartyCaseRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPartyCase(PartyCaseRequest request) {
        try 
        {
        	PartyCase partyCase = this.loadModelFromRequest(request);
        	entityManager.persist(partyCase);
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
    public Response editPartyCase(PartyCaseRequest request) 
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
    private PartyCase loadModelFromRequest(PartyCaseRequest request) 
    {
		PartyCase partyCase = new PartyCase();
    	Integer partyCaseId = request.getId();
    	// Are we editing a PartyCase
    	if(partyCaseId != null) 
    	{
    		partyCase = getEntityManager().find(PartyCase.class, request.getId());
    		partyCase.setLastModifiedDt(request.getLastModifiedDt());
        	partyCase.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	partyCase.setCreatedDt(getCurrentDate());
        	partyCase.setCreatedByUsr(getCurrentUserName(request));
    	}
    	partyCase.setCode(request.getCode());
    	partyCase.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getCaseRole() != null)
    	{
    		CaseRole caseRole = entityManager.find(CaseRole.class, request.getCaseRole());
    		partyCase.setCaseRole(caseRole);
    	}
    	if (request.getCommunicationEvent() != null)
    	{
    		CommunicationEvent communicationEvent = entityManager.find(CommunicationEvent.class, request.getCommunicationEvent());
    		partyCase.setCommunicationEvent(communicationEvent);
    	}
    	if (request.getCaseStatusType() != null)
    	{
    		CaseStatusType caseStatusType = entityManager.find(CaseStatusType.class, request.getCaseStatusType());
    		partyCase.setCaseStatusType(caseStatusType);
    	}
    	partyCase.setCode(request.getCode()); 
    	partyCase.setName(request.getName()); 
    	partyCase.setDescription(request.getDescription()); 
    	partyCase.setEffectiveDt(request.getEffectiveDt()); 
    	partyCase.setRecSt(request.getRecSt()); 
		return partyCase;
	}
}
