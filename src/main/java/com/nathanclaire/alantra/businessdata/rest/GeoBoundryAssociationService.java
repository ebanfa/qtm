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
import com.nathanclaire.alantra.base.rest.request.GeoBoundryAssociationRequest;
import com.nathanclaire.alantra.base.rest.request.GeoBoundryRequest;
import com.nathanclaire.alantra.businessdata.model.GeoBoundry;
import com.nathanclaire.alantra.businessdata.model.GeoBoundryAssociation;

/**
 * @author administrator
 *
 */
@Path("/geoboundryassociation")
@Stateless
public class GeoBoundryAssociationService extends BaseEntityService<GeoBoundryAssociation> 
{
	/**
	 * @param entityClass
	 */
	public GeoBoundryAssociationService() {
		super(GeoBoundryAssociation.class);
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
            CriteriaBuilder criteriaBuilder, Root<GeoBoundryAssociation> root) 
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
     *   Create a GeoBoundryAssociation. Data is contained in the GeoBoundryAssociationRequest object
     * </p>
     * @param GeoBoundryAssociationRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createGeoBoundryAssociation(GeoBoundryAssociationRequest request) {
        try 
        {
        	GeoBoundryAssociation geoBoundryAssociation = this.loadModelFromRequest(request);
        	entityManager.persist(geoBoundryAssociation);
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
    public Response editGeoBoundryAssociation(GeoBoundryAssociationRequest request) 
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
    private GeoBoundryAssociation loadModelFromRequest(GeoBoundryAssociationRequest request) 
    {
		GeoBoundryAssociation geoBoundryAssociation = new GeoBoundryAssociation();
    	Integer geoBoundryAssociationId = request.getId();
    	// Are we editing a GeoBoundryAssociation
    	if(geoBoundryAssociationId != null) 
    	{
    		geoBoundryAssociation = getEntityManager().find(GeoBoundryAssociation.class, request.getId());
    		geoBoundryAssociation.setLastModifiedDt(request.getLastModifiedDt());
        	geoBoundryAssociation.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	geoBoundryAssociation.setCreatedDt(getCurrentDate());
        	geoBoundryAssociation.setCreatedByUsr(getCurrentUserName(request));
    	}
    	geoBoundryAssociation.setCode(request.getCode());
    	geoBoundryAssociation.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getGeoBoundryByToGeoId() != null)
    	{
    		GeoBoundry geoBoundryByToGeoId = entityManager.find(GeoBoundry.class, request.getGeoBoundryByToGeoId());
    		geoBoundryAssociation.setGeoBoundryByToGeoId(geoBoundryByToGeoId);
    	}
    	if (request.getGeoBoundryByFromGeoId() != null)
    	{
    		GeoBoundry geoBoundryByFromGeoId = entityManager.find(GeoBoundry.class, request.getGeoBoundryByFromGeoId());
    		geoBoundryAssociation.setGeoBoundryByFromGeoId(geoBoundryByFromGeoId);
    	}
    	geoBoundryAssociation.setCode(request.getCode()); 
    	geoBoundryAssociation.setDescription(request.getDescription()); 
    	geoBoundryAssociation.setEffectiveDt(request.getEffectiveDt()); 
    	geoBoundryAssociation.setRecSt(request.getRecSt()); 
		return geoBoundryAssociation;
	}
}
