/**
 * 
 */
package com.nathanclaire.alantra.messaging.rest;

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

import com.nathanclaire.alantra.party.model.ContactMechanismType;
import com.nathanclaire.alantra.party.model.PartyRelationship;
import com.nathanclaire.alantra.base.rest.BaseEntityService;
import com.nathanclaire.alantra.base.rest.request.CommunicationEventPurposeRequest;
import com.nathanclaire.alantra.base.rest.request.CommunicationEventRequest;
import com.nathanclaire.alantra.base.rest.request.CommunicationEventStatusTypeRequest;
import com.nathanclaire.alantra.base.rest.request.CommunicationEventTypeRequest;
import com.nathanclaire.alantra.base.rest.request.ContactMechanismTypeRequest;
import com.nathanclaire.alantra.base.rest.request.PartyRelationshipRequest;
import com.nathanclaire.alantra.messaging.model.CommunicationEvent;
import com.nathanclaire.alantra.messaging.model.CommunicationEventPurpose;
import com.nathanclaire.alantra.messaging.model.CommunicationEventStatusType;
import com.nathanclaire.alantra.messaging.model.CommunicationEventType;

/**
 * @author administrator
 *
 */
@Path("/communicationevent")
@Stateless
public class CommunicationEventService extends BaseEntityService<CommunicationEvent> 
{
	/**
	 * @param entityClass
	 */
	public CommunicationEventService() {
		super(CommunicationEvent.class);
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
            CriteriaBuilder criteriaBuilder, Root<CommunicationEvent> root) 
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
     *   Create a CommunicationEvent. Data is contained in the CommunicationEventRequest object
     * </p>
     * @param CommunicationEventRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCommunicationEvent(CommunicationEventRequest request) {
        try 
        {
        	CommunicationEvent communicationEvent = this.loadModelFromRequest(request);
        	entityManager.persist(communicationEvent);
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
    public Response editCommunicationEvent(CommunicationEventRequest request) 
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
    private CommunicationEvent loadModelFromRequest(CommunicationEventRequest request) 
    {
		CommunicationEvent communicationEvent = new CommunicationEvent();
    	Integer communicationEventId = request.getId();
    	// Are we editing a CommunicationEvent
    	if(communicationEventId != null) 
    	{
    		communicationEvent = getEntityManager().find(CommunicationEvent.class, request.getId());
    		communicationEvent.setLastModifiedDt(request.getLastModifiedDt());
        	communicationEvent.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	communicationEvent.setCreatedDt(getCurrentDate());
        	communicationEvent.setCreatedByUsr(getCurrentUserName(request));
    	}
    	communicationEvent.setCode(request.getCode());
    	communicationEvent.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getCommunicationEventType() != null)
    	{
    		CommunicationEventType communicationEventType = entityManager.find(CommunicationEventType.class, request.getCommunicationEventType());
    		communicationEvent.setCommunicationEventType(communicationEventType);
    	}
    	if (request.getCommunicationEventPurpose() != null)
    	{
    		CommunicationEventPurpose communicationEventPurpose = entityManager.find(CommunicationEventPurpose.class, request.getCommunicationEventPurpose());
    		communicationEvent.setCommunicationEventPurpose(communicationEventPurpose);
    	}
    	if (request.getContactMechanismType() != null)
    	{
    		ContactMechanismType contactMechanismType = entityManager.find(ContactMechanismType.class, request.getContactMechanismType());
    		communicationEvent.setContactMechanismType(contactMechanismType);
    	}
    	if (request.getPartyRelationship() != null)
    	{
    		PartyRelationship partyRelationship = entityManager.find(PartyRelationship.class, request.getPartyRelationship());
    		communicationEvent.setPartyRelationship(partyRelationship);
    	}
    	if (request.getCommunicationEventStatusType() != null)
    	{
    		CommunicationEventStatusType communicationEventStatusType = entityManager.find(CommunicationEventStatusType.class, request.getCommunicationEventStatusType());
    		communicationEvent.setCommunicationEventStatusType(communicationEventStatusType);
    	}
    	communicationEvent.setCode(request.getCode()); 
    	communicationEvent.setDescription(request.getDescription()); 
    	communicationEvent.setEffectiveDt(request.getEffectiveDt()); 
    	communicationEvent.setRecSt(request.getRecSt()); 
		return communicationEvent;
	}
}
