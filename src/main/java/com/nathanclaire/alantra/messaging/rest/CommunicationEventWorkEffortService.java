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

import com.nathanclaire.alantra.base.rest.BaseEntityService;
import com.nathanclaire.alantra.base.rest.request.CommunicationEventRequest;
import com.nathanclaire.alantra.base.rest.request.CommunicationEventWorkEffortRequest;
import com.nathanclaire.alantra.base.rest.request.WorkEffortRequest;
import com.nathanclaire.alantra.messaging.model.CommunicationEvent;
import com.nathanclaire.alantra.messaging.model.CommunicationEventWorkEffort;
import com.nathanclaire.alantra.workeffort.model.WorkEffort;

/**
 * @author administrator
 *
 */
@Path("/communicationeventworkeffort")
@Stateless
public class CommunicationEventWorkEffortService extends BaseEntityService<CommunicationEventWorkEffort> 
{
	/**
	 * @param entityClass
	 */
	public CommunicationEventWorkEffortService() {
		super(CommunicationEventWorkEffort.class);
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
            CriteriaBuilder criteriaBuilder, Root<CommunicationEventWorkEffort> root) 
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
     *   Create a CommunicationEventWorkEffort. Data is contained in the CommunicationEventWorkEffortRequest object
     * </p>
     * @param CommunicationEventWorkEffortRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCommunicationEventWorkEffort(CommunicationEventWorkEffortRequest request) {
        try 
        {
        	CommunicationEventWorkEffort communicationEventWorkEffort = this.loadModelFromRequest(request);
        	entityManager.persist(communicationEventWorkEffort);
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
    public Response editCommunicationEventWorkEffort(CommunicationEventWorkEffortRequest request) 
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
    private CommunicationEventWorkEffort loadModelFromRequest(CommunicationEventWorkEffortRequest request) 
    {
		CommunicationEventWorkEffort communicationEventWorkEffort = new CommunicationEventWorkEffort();
    	Integer communicationEventWorkEffortId = request.getId();
    	// Are we editing a CommunicationEventWorkEffort
    	if(communicationEventWorkEffortId != null) 
    	{
    		communicationEventWorkEffort = getEntityManager().find(CommunicationEventWorkEffort.class, request.getId());
    		communicationEventWorkEffort.setLastModifiedDt(request.getLastModifiedDt());
        	communicationEventWorkEffort.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	communicationEventWorkEffort.setCreatedDt(getCurrentDate());
        	communicationEventWorkEffort.setCreatedByUsr(getCurrentUserName(request));
    	}
    	communicationEventWorkEffort.setCode(request.getCode());
    	communicationEventWorkEffort.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getWorkEffort() != null)
    	{
    		WorkEffort workEffort = entityManager.find(WorkEffort.class, request.getWorkEffort());
    		communicationEventWorkEffort.setWorkEffort(workEffort);
    	}
    	if (request.getCommunicationEvent() != null)
    	{
    		CommunicationEvent communicationEvent = entityManager.find(CommunicationEvent.class, request.getCommunicationEvent());
    		communicationEventWorkEffort.setCommunicationEvent(communicationEvent);
    	}
    	communicationEventWorkEffort.setCode(request.getCode()); 
    	communicationEventWorkEffort.setDescription(request.getDescription()); 
    	communicationEventWorkEffort.setEffectiveDt(request.getEffectiveDt()); 
    	communicationEventWorkEffort.setRecSt(request.getRecSt()); 
		return communicationEventWorkEffort;
	}
}