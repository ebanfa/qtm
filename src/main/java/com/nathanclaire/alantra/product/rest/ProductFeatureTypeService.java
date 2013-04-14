/**
 * 
 */
package com.nathanclaire.alantra.product.rest;

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
import com.nathanclaire.alantra.product.model.ProductFeatureType;
import com.nathanclaire.alantra.product.rest.request.ProductFeatureTypeRequest;

/**
 * @author administrator
 *
 */
@Path("/productfeaturetype")
@Stateless
public class ProductFeatureTypeService extends BaseEntityRESTService<ProductFeatureType> 
{
	/**
	 * @param entityClass
	 */
	public ProductFeatureTypeService() {
		super(ProductFeatureType.class);
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
            CriteriaBuilder criteriaBuilder, Root<ProductFeatureType> root) 
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
     *   Create a ProductFeatureType. Data is contained in the ProductFeatureTypeRequest object
     * </p>
     * @param ProductFeatureTypeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProductFeatureType(ProductFeatureTypeRequest request) {
        try 
        {
        	ProductFeatureType productFeatureType = this.loadModelFromRequest(request);
        	entityManager.persist(productFeatureType);
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
    public Response editProductFeatureType(ProductFeatureTypeRequest request) 
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
    private ProductFeatureType loadModelFromRequest(ProductFeatureTypeRequest request) 
    {
		ProductFeatureType productFeatureType = new ProductFeatureType();
    	Integer productFeatureTypeId = request.getId();
    	// Are we editing a ProductFeatureType
    	if(productFeatureTypeId != null) 
    	{
    		productFeatureType = getEntityManager().find(ProductFeatureType.class, request.getId());
    		productFeatureType.setLastModifiedDt(request.getLastModifiedDt());
        	productFeatureType.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	productFeatureType.setCreatedDt(getCurrentDate());
        	productFeatureType.setCreatedByUsr(getCurrentUserName(request));
    	}
    	productFeatureType.setCode(request.getCode());
    	productFeatureType.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	productFeatureType.setCode(request.getCode()); 
    	productFeatureType.setName(request.getName()); 
    	productFeatureType.setDescription(request.getDescription()); 
    	productFeatureType.setEffectiveDt(request.getEffectiveDt()); 
    	productFeatureType.setRecSt(request.getRecSt()); 
		return productFeatureType;
	}
}
