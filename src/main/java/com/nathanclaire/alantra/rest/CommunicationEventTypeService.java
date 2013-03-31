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

import com.nathanclaire.alantra.model.CommunicationEventType;
import com.nathanclaire.alantra.rest.request.CommunicationEventTypeRequest;

/**
 * @author administrator
 *
 */
@Path("/communicationeventtype")
@Stateless
public class CommunicationEventTypeService extends BaseEntityService<CommunicationEventType> 
{
	/**
	 * @param entityClass
	 */
	public CommunicationEventTypeService() {
		super(CommunicationEventType.class);
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
            CriteriaBuilder criteriaBuilder, Root<CommunicationEventType> root) 
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
     *   Create a CommunicationEventType. Data is contained in the CommunicationEventTypeRequest object
     * </p>
     * @param CommunicationEventTypeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCommunicationEventType(CommunicationEventTypeRequest request) {
        try 
        {
        	CommunicationEventType communicationEventType = this.loadModelFromRequest(request);
        	entityManager.persist(communicationEventType);
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
    public Response editCommunicationEventType(CommunicationEventTypeRequest request) 
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
    private CommunicationEventType loadModelFromRequest(CommunicationEventTypeRequest request) 
    {
		CommunicationEventType communicationEventType = new CommunicationEventType();
    	Integer communicationEventTypeId = request.getId();
    	// Are we editing a CommunicationEventType
    	if(communicationEventTypeId != null) 
    	{
    		communicationEventType = getEntityManager().find(CommunicationEventType.class, request.getId());
    		communicationEventType.setLastModifiedDt(request.getLastModifiedDt());
        	communicationEventType.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	communicationEventType.setCreatedDt(getCurrentDate());
        	communicationEventType.setCreatedByUsr(getCurrentUserName(request));
    	}
    	communicationEventType.setCode(request.getCode());
    	communicationEventType.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	communicationEventType.setCode(request.getCode()); 
    	communicationEventType.setName(request.getName()); 
    	communicationEventType.setDescription(request.getDescription()); 
    	communicationEventType.setEffectiveDt(request.getEffectiveDt()); 
    	communicationEventType.setRecSt(request.getRecSt()); 
		return communicationEventType;
	}
}
