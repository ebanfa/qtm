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
import com.nathanclaire.alantra.businessdata.model.GeoBoundaryType;
import com.nathanclaire.alantra.businessdata.rest.request.GeoBoundaryTypeRequest;

/**
 * @author administrator
 *
 */
@Path("/geoboundarytype")
@Stateless
public class GeoBoundaryTypeService extends BaseEntityService<GeoBoundaryType> 
{
	/**
	 * @param entityClass
	 */
	public GeoBoundaryTypeService() {
		super(GeoBoundaryType.class);
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
            CriteriaBuilder criteriaBuilder, Root<GeoBoundaryType> root) 
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
     *   Create a GeoBoundaryType. Data is contained in the GeoBoundaryTypeRequest object
     * </p>
     * @param GeoBoundaryTypeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createGeoBoundaryType(GeoBoundaryTypeRequest request) {
        try 
        {
        	GeoBoundaryType geoBoundaryType = this.loadModelFromRequest(request);
        	entityManager.persist(geoBoundaryType);
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
    public Response editGeoBoundaryType(GeoBoundaryTypeRequest request) 
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
    private GeoBoundaryType loadModelFromRequest(GeoBoundaryTypeRequest request) 
    {
		GeoBoundaryType geoBoundaryType = new GeoBoundaryType();
    	Integer geoBoundaryTypeId = request.getId();
    	// Are we editing a GeoBoundaryType
    	if(geoBoundaryTypeId != null) 
    	{
    		geoBoundaryType = getEntityManager().find(GeoBoundaryType.class, request.getId());
    		geoBoundaryType.setLastModifiedDt(request.getLastModifiedDt());
        	geoBoundaryType.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	geoBoundaryType.setCreatedDt(getCurrentDate());
        	geoBoundaryType.setCreatedByUsr(getCurrentUserName(request));
    	}
    	geoBoundaryType.setCode(request.getCode());
    	geoBoundaryType.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getGeoBoundaryType() != null)
    	{
    		GeoBoundaryType parentGeoBoundaryType = entityManager.find(GeoBoundaryType.class, request.getGeoBoundaryType());
    		geoBoundaryType.setGeoBoundaryType(parentGeoBoundaryType);
    	}
    	geoBoundaryType.setCode(request.getCode()); 
    	geoBoundaryType.setName(request.getName()); 
    	geoBoundaryType.setDescription(request.getDescription()); 
    	geoBoundaryType.setEffectiveDt(request.getEffectiveDt()); 
    	geoBoundaryType.setRecSt(request.getRecSt()); 
		return geoBoundaryType;
	}
}
