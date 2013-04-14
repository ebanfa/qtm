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
import com.nathanclaire.alantra.advice.model.AdviceTypeTag;
import com.nathanclaire.alantra.advice.model.AdviceType;
import com.nathanclaire.alantra.advice.rest.request.AdviceTypeRequest;
import com.nathanclaire.alantra.advice.rest.request.AdviceTypeTagRequest;

/**
 * @author administrator
 *
 */
@Path("/advicetypetag")
@Stateless
public class AdviceTypeTagRESTService extends BaseEntityRESTService<AdviceTypeTag> 
{
	/**
	 * @param entityClass
	 */
	public AdviceTypeTagRESTService() {
		super(AdviceTypeTag.class);
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
            CriteriaBuilder criteriaBuilder, Root<AdviceTypeTag> root) 
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
     *   Create a AdviceTypeTag. Data is contained in the AdviceTypeTagRequest object
     * </p>
     * @param AdviceTypeTagRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAdviceTypeTag(AdviceTypeTagRequest request) {
        try 
        {
        	AdviceTypeTag adviceTypeTag = this.loadModelFromRequest(request);
        	entityManager.persist(adviceTypeTag);
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
    public Response editAdviceTypeTag(AdviceTypeTagRequest request) 
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
    private AdviceTypeTag loadModelFromRequest(AdviceTypeTagRequest request) 
    {
		AdviceTypeTag adviceTypeTag = new AdviceTypeTag();
    	Integer adviceTypeTagId = request.getId();
    	// Are we editing a AdviceTypeTag
    	if(adviceTypeTagId != null) 
    	{
    		adviceTypeTag = getEntityManager().find(AdviceTypeTag.class, request.getId());
    		adviceTypeTag.setLastModifiedDt(request.getLastModifiedDt());
        	adviceTypeTag.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	adviceTypeTag.setCreatedDt(getCurrentDate());
        	adviceTypeTag.setCreatedByUsr(getCurrentUserName(request));
    	}
    	adviceTypeTag.setCode(request.getCode());
    	adviceTypeTag.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getAdviceType() != null)
    	{
    		AdviceType adviceType = entityManager.find(AdviceType.class, request.getAdviceType());
    		adviceTypeTag.setAdviceType(adviceType);
    	}
    	adviceTypeTag.setName(request.getName()); 
    	adviceTypeTag.setDescription(request.getDescription()); 
    	adviceTypeTag.setAdviceTyTagVal(request.getAdviceTyTagVal()); 
    	adviceTypeTag.setIsRegexFg(request.getIsRegexFg()); 
    	adviceTypeTag.setCode(request.getCode()); 
    	adviceTypeTag.setEffectiveDt(request.getEffectiveDt()); 
    	adviceTypeTag.setRecSt(request.getRecSt()); 
		return adviceTypeTag;
	}
}
