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

import com.nathanclaire.alantra.model.ProductFeature;
import com.nathanclaire.alantra.model.ProductFeatureType;
import com.nathanclaire.alantra.rest.request.ProductFeatureTypeRequest;
import com.nathanclaire.alantra.model.ProductFeatureCategory;
import com.nathanclaire.alantra.rest.request.ProductFeatureCategoryRequest;
import com.nathanclaire.alantra.rest.request.ProductFeatureRequest;

/**
 * @author administrator
 *
 */
@Path("/productfeature")
@Stateless
public class ProductFeatureService extends BaseEntityService<ProductFeature> 
{
	/**
	 * @param entityClass
	 */
	public ProductFeatureService() {
		super(ProductFeature.class);
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
            CriteriaBuilder criteriaBuilder, Root<ProductFeature> root) 
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
     *   Create a ProductFeature. Data is contained in the ProductFeatureRequest object
     * </p>
     * @param ProductFeatureRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProductFeature(ProductFeatureRequest request) {
        try 
        {
        	ProductFeature productFeature = this.loadModelFromRequest(request);
        	entityManager.persist(productFeature);
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
    public Response editProductFeature(ProductFeatureRequest request) 
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
    private ProductFeature loadModelFromRequest(ProductFeatureRequest request) 
    {
		ProductFeature productFeature = new ProductFeature();
    	Integer productFeatureId = request.getId();
    	// Are we editing a ProductFeature
    	if(productFeatureId != null) 
    	{
    		productFeature = getEntityManager().find(ProductFeature.class, request.getId());
    		productFeature.setLastModifiedDt(request.getLastModifiedDt());
        	productFeature.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	productFeature.setCreatedDt(getCurrentDate());
        	productFeature.setCreatedByUsr(getCurrentUserName(request));
    	}
    	productFeature.setCode(request.getCode());
    	productFeature.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getProductFeatureType() != null)
    	{
    		ProductFeatureType productFeatureType = entityManager.find(ProductFeatureType.class, request.getProductFeatureType());
    		productFeature.setProductFeatureType(productFeatureType);
    	}
    	if (request.getProductFeatureCategory() != null)
    	{
    		ProductFeatureCategory productFeatureCategory = entityManager.find(ProductFeatureCategory.class, request.getProductFeatureCategory());
    		productFeature.setProductFeatureCategory(productFeatureCategory);
    	}
    	productFeature.setCode(request.getCode()); 
    	productFeature.setName(request.getName()); 
    	productFeature.setDescription(request.getDescription()); 
    	productFeature.setEffectiveDt(request.getEffectiveDt()); 
    	productFeature.setRecSt(request.getRecSt()); 
		return productFeature;
	}
}
