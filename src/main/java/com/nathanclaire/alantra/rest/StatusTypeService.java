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

import com.nathanclaire.alantra.model.businessdata.StatusType;
import com.nathanclaire.alantra.rest.request.StatusTypeRequest;

/**
 * @author administrator
 *
 */
@Path("/statustype")
@Stateless
public class StatusTypeService extends BaseEntityService<StatusType> 
{
	/**
	 * @param entityClass
	 */
	public StatusTypeService() {
		super(StatusType.class);
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
            CriteriaBuilder criteriaBuilder, Root<StatusType> root) 
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
     *   Create a StatusType. Data is contained in the StatusTypeRequest object
     * </p>
     * @param StatusTypeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createStatusType(StatusTypeRequest request) {
        try 
        {
        	StatusType statusType = this.loadModelFromRequest(request);
        	entityManager.persist(statusType);
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
    public Response editStatusType(StatusTypeRequest request) 
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
    private StatusType loadModelFromRequest(StatusTypeRequest request) 
    {
		StatusType statusType = new StatusType();
    	Integer statusTypeId = request.getId();
    	// Are we editing a StatusType
    	if(statusTypeId != null) 
    	{
    		statusType = getEntityManager().find(StatusType.class, request.getId());
    		statusType.setLastModifiedDt(request.getLastModifiedDt());
        	statusType.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	statusType.setCreatedDt(getCurrentDate());
        	statusType.setCreatedByUsr(getCurrentUserName(request));
    	}
    	statusType.setCode(request.getCode());
    	statusType.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	statusType.setName(request.getName()); 
    	statusType.setDescription(request.getDescription()); 
    	statusType.setCode(request.getCode()); 
    	statusType.setEffectiveDt(request.getEffectiveDt()); 
    	statusType.setRecSt(request.getRecSt()); 
		return statusType;
	}
}
