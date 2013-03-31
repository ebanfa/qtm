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

import com.nathanclaire.alantra.model.product.ProductFeatureCategory;
import com.nathanclaire.alantra.rest.request.ProductFeatureCategoryRequest;

/**
 * @author administrator
 *
 */
@Path("/productfeaturecategory")
@Stateless
public class ProductFeatureCategoryService extends BaseEntityService<ProductFeatureCategory> 
{
	/**
	 * @param entityClass
	 */
	public ProductFeatureCategoryService() {
		super(ProductFeatureCategory.class);
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
            CriteriaBuilder criteriaBuilder, Root<ProductFeatureCategory> root) 
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
     *   Create a ProductFeatureCategory. Data is contained in the ProductFeatureCategoryRequest object
     * </p>
     * @param ProductFeatureCategoryRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProductFeatureCategory(ProductFeatureCategoryRequest request) {
        try 
        {
        	ProductFeatureCategory productFeatureCategory = this.loadModelFromRequest(request);
        	entityManager.persist(productFeatureCategory);
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
    public Response editProductFeatureCategory(ProductFeatureCategoryRequest request) 
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
    private ProductFeatureCategory loadModelFromRequest(ProductFeatureCategoryRequest request) 
    {
		ProductFeatureCategory productFeatureCategory = new ProductFeatureCategory();
    	Integer productFeatureCategoryId = request.getId();
    	// Are we editing a ProductFeatureCategory
    	if(productFeatureCategoryId != null) 
    	{
    		productFeatureCategory = getEntityManager().find(ProductFeatureCategory.class, request.getId());
    		productFeatureCategory.setLastModifiedDt(request.getLastModifiedDt());
        	productFeatureCategory.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	productFeatureCategory.setCreatedDt(getCurrentDate());
        	productFeatureCategory.setCreatedByUsr(getCurrentUserName(request));
    	}
    	productFeatureCategory.setCode(request.getCode());
    	productFeatureCategory.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	productFeatureCategory.setCode(request.getCode()); 
    	productFeatureCategory.setName(request.getName()); 
    	productFeatureCategory.setDescription(request.getDescription()); 
    	productFeatureCategory.setEffectiveDt(request.getEffectiveDt()); 
    	productFeatureCategory.setRecSt(request.getRecSt()); 
		return productFeatureCategory;
	}
}
