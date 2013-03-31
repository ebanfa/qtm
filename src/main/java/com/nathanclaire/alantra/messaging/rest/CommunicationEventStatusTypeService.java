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
import com.nathanclaire.alantra.base.rest.request.CommunicationEventStatusTypeRequest;
import com.nathanclaire.alantra.messaging.model.CommunicationEventStatusType;

/**
 * @author administrator
 *
 */
@Path("/communicationeventstatustype")
@Stateless
public class CommunicationEventStatusTypeService extends BaseEntityService<CommunicationEventStatusType> 
{
	/**
	 * @param entityClass
	 */
	public CommunicationEventStatusTypeService() {
		super(CommunicationEventStatusType.class);
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
            CriteriaBuilder criteriaBuilder, Root<CommunicationEventStatusType> root) 
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
     *   Create a CommunicationEventStatusType. Data is contained in the CommunicationEventStatusTypeRequest object
     * </p>
     * @param CommunicationEventStatusTypeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCommunicationEventStatusType(CommunicationEventStatusTypeRequest request) {
        try 
        {
        	CommunicationEventStatusType communicationEventStatusType = this.loadModelFromRequest(request);
        	entityManager.persist(communicationEventStatusType);
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
    public Response editCommunicationEventStatusType(CommunicationEventStatusTypeRequest request) 
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
    private CommunicationEventStatusType loadModelFromRequest(CommunicationEventStatusTypeRequest request) 
    {
		CommunicationEventStatusType communicationEventStatusType = new CommunicationEventStatusType();
    	Integer communicationEventStatusTypeId = request.getId();
    	// Are we editing a CommunicationEventStatusType
    	if(communicationEventStatusTypeId != null) 
    	{
    		communicationEventStatusType = getEntityManager().find(CommunicationEventStatusType.class, request.getId());
    		communicationEventStatusType.setLastModifiedDt(request.getLastModifiedDt());
        	communicationEventStatusType.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	communicationEventStatusType.setCreatedDt(getCurrentDate());
        	communicationEventStatusType.setCreatedByUsr(getCurrentUserName(request));
    	}
    	communicationEventStatusType.setCode(request.getCode());
    	communicationEventStatusType.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	communicationEventStatusType.setCode(request.getCode()); 
    	communicationEventStatusType.setName(request.getName()); 
    	communicationEventStatusType.setDescription(request.getDescription()); 
    	communicationEventStatusType.setEffectiveDt(request.getEffectiveDt()); 
    	communicationEventStatusType.setRecSt(request.getRecSt()); 
		return communicationEventStatusType;
	}
}
