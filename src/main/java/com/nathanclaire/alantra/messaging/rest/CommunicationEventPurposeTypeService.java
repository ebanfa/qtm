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
import com.nathanclaire.alantra.base.rest.request.CommunicationEventPurposeTypeRequest;
import com.nathanclaire.alantra.messaging.model.CommunicationEventPurposeType;

/**
 * @author administrator
 *
 */
@Path("/communicationeventpurposetype")
@Stateless
public class CommunicationEventPurposeTypeService extends BaseEntityService<CommunicationEventPurposeType> 
{
	/**
	 * @param entityClass
	 */
	public CommunicationEventPurposeTypeService() {
		super(CommunicationEventPurposeType.class);
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
            CriteriaBuilder criteriaBuilder, Root<CommunicationEventPurposeType> root) 
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
     *   Create a CommunicationEventPurposeType. Data is contained in the CommunicationEventPurposeTypeRequest object
     * </p>
     * @param CommunicationEventPurposeTypeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCommunicationEventPurposeType(CommunicationEventPurposeTypeRequest request) {
        try 
        {
        	CommunicationEventPurposeType communicationEventPurposeType = this.loadModelFromRequest(request);
        	entityManager.persist(communicationEventPurposeType);
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
    public Response editCommunicationEventPurposeType(CommunicationEventPurposeTypeRequest request) 
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
    private CommunicationEventPurposeType loadModelFromRequest(CommunicationEventPurposeTypeRequest request) 
    {
		CommunicationEventPurposeType communicationEventPurposeType = new CommunicationEventPurposeType();
    	Integer communicationEventPurposeTypeId = request.getId();
    	// Are we editing a CommunicationEventPurposeType
    	if(communicationEventPurposeTypeId != null) 
    	{
    		communicationEventPurposeType = getEntityManager().find(CommunicationEventPurposeType.class, request.getId());
    		communicationEventPurposeType.setLastModifiedDt(request.getLastModifiedDt());
        	communicationEventPurposeType.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	communicationEventPurposeType.setCreatedDt(getCurrentDate());
        	communicationEventPurposeType.setCreatedByUsr(getCurrentUserName(request));
    	}
    	communicationEventPurposeType.setCode(request.getCode());
    	communicationEventPurposeType.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	communicationEventPurposeType.setCode(request.getCode()); 
    	communicationEventPurposeType.setName(request.getName()); 
    	communicationEventPurposeType.setDescription(request.getDescription()); 
    	communicationEventPurposeType.setEffectiveDt(request.getEffectiveDt()); 
    	communicationEventPurposeType.setRecSt(request.getRecSt()); 
		return communicationEventPurposeType;
	}
}
