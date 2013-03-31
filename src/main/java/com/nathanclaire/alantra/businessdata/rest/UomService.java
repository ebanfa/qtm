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
import com.nathanclaire.alantra.base.rest.request.UomRequest;
import com.nathanclaire.alantra.businessdata.model.Uom;

/**
 * @author administrator
 *
 */
@Path("/uom")
@Stateless
public class UomService extends BaseEntityService<Uom> 
{
	/**
	 * @param entityClass
	 */
	public UomService() {
		super(Uom.class);
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
            CriteriaBuilder criteriaBuilder, Root<Uom> root) 
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
     *   Create a Uom. Data is contained in the UomRequest object
     * </p>
     * @param UomRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUom(UomRequest request) {
        try 
        {
        	Uom uom = this.loadModelFromRequest(request);
        	entityManager.persist(uom);
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
    public Response editUom(UomRequest request) 
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
    private Uom loadModelFromRequest(UomRequest request) 
    {
		Uom uom = new Uom();
    	Integer uomId = request.getId();
    	// Are we editing a Uom
    	if(uomId != null) 
    	{
    		uom = getEntityManager().find(Uom.class, request.getId());
    		uom.setLastModifiedDt(request.getLastModifiedDt());
        	uom.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	uom.setCreatedDt(getCurrentDate());
        	uom.setCreatedByUsr(getCurrentUserName(request));
    	}
    	uom.setCode(request.getCode());
    	uom.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	uom.setCode(request.getCode()); 
    	uom.setName(request.getName()); 
    	uom.setDescription(request.getDescription()); 
    	uom.setEffectiveDt(request.getEffectiveDt()); 
    	uom.setRecSt(request.getRecSt()); 
		return uom;
	}
}