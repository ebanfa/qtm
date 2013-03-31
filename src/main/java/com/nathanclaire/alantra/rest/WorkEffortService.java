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

import com.nathanclaire.alantra.model.WorkEffort;
import com.nathanclaire.alantra.model.WorkEffortType;
import com.nathanclaire.alantra.rest.request.WorkEffortTypeRequest;
import com.nathanclaire.alantra.rest.request.WorkEffortRequest;

/**
 * @author administrator
 *
 */
@Path("/workeffort")
@Stateless
public class WorkEffortService extends BaseEntityService<WorkEffort> 
{
	/**
	 * @param entityClass
	 */
	public WorkEffortService() {
		super(WorkEffort.class);
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
            CriteriaBuilder criteriaBuilder, Root<WorkEffort> root) 
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
     *   Create a WorkEffort. Data is contained in the WorkEffortRequest object
     * </p>
     * @param WorkEffortRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createWorkEffort(WorkEffortRequest request) {
        try 
        {
        	WorkEffort workEffort = this.loadModelFromRequest(request);
        	entityManager.persist(workEffort);
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
    public Response editWorkEffort(WorkEffortRequest request) 
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
    private WorkEffort loadModelFromRequest(WorkEffortRequest request) 
    {
		WorkEffort workEffort = new WorkEffort();
    	Integer workEffortId = request.getId();
    	// Are we editing a WorkEffort
    	if(workEffortId != null) 
    	{
    		workEffort = getEntityManager().find(WorkEffort.class, request.getId());
    		workEffort.setLastModifiedDt(request.getLastModifiedDt());
        	workEffort.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	workEffort.setCreatedDt(getCurrentDate());
        	workEffort.setCreatedByUsr(getCurrentUserName(request));
    	}
    	workEffort.setCode(request.getCode());
    	workEffort.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getWorkEffortType() != null)
    	{
    		WorkEffortType workEffortType = entityManager.find(WorkEffortType.class, request.getWorkEffortType());
    		workEffort.setWorkEffortType(workEffortType);
    	}
    	workEffort.setCode(request.getCode()); 
    	workEffort.setName(request.getName()); 
    	workEffort.setDescription(request.getDescription()); 
    	workEffort.setSchedStartDt(request.getSchedStartDt()); 
    	workEffort.setSchedEndDt(request.getSchedEndDt()); 
    	workEffort.setEstHours(request.getEstHours()); 
    	workEffort.setTotalHrAllowed(request.getTotalHrAllowed()); 
    	workEffort.setTotalAmountAllowed(request.getTotalAmountAllowed()); 
    	workEffort.setEffectiveDt(request.getEffectiveDt()); 
    	workEffort.setRecSt(request.getRecSt()); 
		return workEffort;
	}
}
