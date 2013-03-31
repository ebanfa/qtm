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

import com.nathanclaire.alantra.model.CostComponentType;
import com.nathanclaire.alantra.rest.request.CostComponentTypeRequest;

/**
 * @author administrator
 *
 */
@Path("/costcomponenttype")
@Stateless
public class CostComponentTypeService extends BaseEntityService<CostComponentType> 
{
	/**
	 * @param entityClass
	 */
	public CostComponentTypeService() {
		super(CostComponentType.class);
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
            CriteriaBuilder criteriaBuilder, Root<CostComponentType> root) 
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
     *   Create a CostComponentType. Data is contained in the CostComponentTypeRequest object
     * </p>
     * @param CostComponentTypeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCostComponentType(CostComponentTypeRequest request) {
        try 
        {
        	CostComponentType costComponentType = this.loadModelFromRequest(request);
        	entityManager.persist(costComponentType);
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
    public Response editCostComponentType(CostComponentTypeRequest request) 
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
    private CostComponentType loadModelFromRequest(CostComponentTypeRequest request) 
    {
		CostComponentType costComponentType = new CostComponentType();
    	Integer costComponentTypeId = request.getId();
    	// Are we editing a CostComponentType
    	if(costComponentTypeId != null) 
    	{
    		costComponentType = getEntityManager().find(CostComponentType.class, request.getId());
    		costComponentType.setLastModifiedDt(request.getLastModifiedDt());
        	costComponentType.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	costComponentType.setCreatedDt(getCurrentDate());
        	costComponentType.setCreatedByUsr(getCurrentUserName(request));
    	}
    	costComponentType.setCode(request.getCode());
    	costComponentType.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	costComponentType.setCode(request.getCode()); 
    	costComponentType.setName(request.getName()); 
    	costComponentType.setDescription(request.getDescription()); 
    	costComponentType.setEffectiveDt(request.getEffectiveDt()); 
    	costComponentType.setRecSt(request.getRecSt()); 
		return costComponentType;
	}
}
