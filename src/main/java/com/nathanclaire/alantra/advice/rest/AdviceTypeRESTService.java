/**
 * 
 */
package com.nathanclaire.alantra.advice.rest;

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

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.advice.model.AdviceType;
import com.nathanclaire.alantra.advice.rest.request.AdviceTypeRequest;

/**
 * @author administrator
 *
 */
@Path("/advicetype")
@Stateless
public class AdviceTypeRESTService extends BaseEntityRESTService<AdviceType> 
{
	/**
	 * @param entityClass
	 */
	public AdviceTypeRESTService() {
		super(AdviceType.class);
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
            CriteriaBuilder criteriaBuilder, Root<AdviceType> root) 
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
     *   Create a AdviceType. Data is contained in the AdviceTypeRequest object
     * </p>
     * @param AdviceTypeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAdviceType(AdviceTypeRequest request) {
        try 
        {
        	AdviceType adviceType = this.loadModelFromRequest(request);
        	entityManager.persist(adviceType);
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
    public Response editAdviceType(AdviceTypeRequest request) 
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
    private AdviceType loadModelFromRequest(AdviceTypeRequest request) 
    {
		AdviceType adviceType = new AdviceType();
    	Integer adviceTypeId = request.getId();
    	// Are we editing a AdviceType
    	if(adviceTypeId != null) 
    	{
    		adviceType = getEntityManager().find(AdviceType.class, request.getId());
    		adviceType.setLastModifiedDt(request.getLastModifiedDt());
        	adviceType.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	adviceType.setCreatedDt(getCurrentDate());
        	adviceType.setCreatedByUsr(getCurrentUserName(request));
    	}
    	adviceType.setCode(request.getCode());
    	adviceType.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	adviceType.setName(request.getName()); 
    	adviceType.setDescription(request.getDescription()); 
    	adviceType.setReqFeedback(request.getReqFeedback()); 
    	adviceType.setFeedbackMsg(request.getFeedbackMsg()); 
    	adviceType.setCode(request.getCode()); 
    	adviceType.setEffectiveDt(request.getEffectiveDt()); 
    	adviceType.setRecSt(request.getRecSt()); 
		return adviceType;
	}
}
