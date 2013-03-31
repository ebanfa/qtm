/**
 * 
 */
package com.nathanclaire.alantra.businessdata.rest;

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
import com.nathanclaire.alantra.base.rest.request.TermTypeRequest;
import com.nathanclaire.alantra.businessdata.model.TermType;

/**
 * @author administrator
 *
 */
@Path("/termtype")
@Stateless
public class TermTypeService extends BaseEntityService<TermType> 
{
	/**
	 * @param entityClass
	 */
	public TermTypeService() {
		super(TermType.class);
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
            CriteriaBuilder criteriaBuilder, Root<TermType> root) 
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
     *   Create a TermType. Data is contained in the TermTypeRequest object
     * </p>
     * @param TermTypeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTermType(TermTypeRequest request) {
        try 
        {
        	TermType termType = this.loadModelFromRequest(request);
        	entityManager.persist(termType);
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
    public Response editTermType(TermTypeRequest request) 
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
    private TermType loadModelFromRequest(TermTypeRequest request) 
    {
		TermType termType = new TermType();
    	Integer termTypeId = request.getId();
    	// Are we editing a TermType
    	if(termTypeId != null) 
    	{
    		termType = getEntityManager().find(TermType.class, request.getId());
    		termType.setLastModifiedDt(request.getLastModifiedDt());
        	termType.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	termType.setCreatedDt(getCurrentDate());
        	termType.setCreatedByUsr(getCurrentUserName(request));
    	}
    	termType.setCode(request.getCode());
    	termType.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	termType.setCode(request.getCode()); 
    	termType.setName(request.getName()); 
    	termType.setDescription(request.getDescription()); 
    	termType.setEffectiveDt(request.getEffectiveDt()); 
    	termType.setRecSt(request.getRecSt()); 
		return termType;
	}
}
