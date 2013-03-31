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

import com.nathanclaire.alantra.model.messaging.CommunicationEventPurpose;
import com.nathanclaire.alantra.model.messaging.CommunicationEventPurposeType;
import com.nathanclaire.alantra.rest.request.CommunicationEventPurposeTypeRequest;
import com.nathanclaire.alantra.rest.request.CommunicationEventPurposeRequest;

/**
 * @author administrator
 *
 */
@Path("/communicationeventpurpose")
@Stateless
public class CommunicationEventPurposeService extends BaseEntityService<CommunicationEventPurpose> 
{
	/**
	 * @param entityClass
	 */
	public CommunicationEventPurposeService() {
		super(CommunicationEventPurpose.class);
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
            CriteriaBuilder criteriaBuilder, Root<CommunicationEventPurpose> root) 
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
     *   Create a CommunicationEventPurpose. Data is contained in the CommunicationEventPurposeRequest object
     * </p>
     * @param CommunicationEventPurposeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCommunicationEventPurpose(CommunicationEventPurposeRequest request) {
        try 
        {
        	CommunicationEventPurpose communicationEventPurpose = this.loadModelFromRequest(request);
        	entityManager.persist(communicationEventPurpose);
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
    public Response editCommunicationEventPurpose(CommunicationEventPurposeRequest request) 
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
    private CommunicationEventPurpose loadModelFromRequest(CommunicationEventPurposeRequest request) 
    {
		CommunicationEventPurpose communicationEventPurpose = new CommunicationEventPurpose();
    	Integer communicationEventPurposeId = request.getId();
    	// Are we editing a CommunicationEventPurpose
    	if(communicationEventPurposeId != null) 
    	{
    		communicationEventPurpose = getEntityManager().find(CommunicationEventPurpose.class, request.getId());
    		communicationEventPurpose.setLastModifiedDt(request.getLastModifiedDt());
        	communicationEventPurpose.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	communicationEventPurpose.setCreatedDt(getCurrentDate());
        	communicationEventPurpose.setCreatedByUsr(getCurrentUserName(request));
    	}
    	communicationEventPurpose.setCode(request.getCode());
    	communicationEventPurpose.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getCommunicationEventPurposeType() != null)
    	{
    		CommunicationEventPurposeType communicationEventPurposeType = entityManager.find(CommunicationEventPurposeType.class, request.getCommunicationEventPurposeType());
    		communicationEventPurpose.setCommunicationEventPurposeType(communicationEventPurposeType);
    	}
    	communicationEventPurpose.setCode(request.getCode()); 
    	communicationEventPurpose.setName(request.getName()); 
    	communicationEventPurpose.setDescription(request.getDescription()); 
    	communicationEventPurpose.setEffectiveDt(request.getEffectiveDt()); 
    	communicationEventPurpose.setRecSt(request.getRecSt()); 
		return communicationEventPurpose;
	}
}
