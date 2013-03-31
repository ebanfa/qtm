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

import com.nathanclaire.alantra.model.product.ProductCategory;
import com.nathanclaire.alantra.model.product.ProductCategoryType;
import com.nathanclaire.alantra.rest.request.ProductCategoryTypeRequest;
import com.nathanclaire.alantra.rest.request.ProductCategoryRequest;

/**
 * @author administrator
 *
 */
@Path("/productcategory")
@Stateless
public class ProductCategoryService extends BaseEntityService<ProductCategory> 
{
	/**
	 * @param entityClass
	 */
	public ProductCategoryService() {
		super(ProductCategory.class);
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
            CriteriaBuilder criteriaBuilder, Root<ProductCategory> root) 
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
     *   Create a ProductCategory. Data is contained in the ProductCategoryRequest object
     * </p>
     * @param ProductCategoryRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProductCategory(ProductCategoryRequest request) {
        try 
        {
        	ProductCategory productCategory = this.loadModelFromRequest(request);
        	entityManager.persist(productCategory);
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
    public Response editProductCategory(ProductCategoryRequest request) 
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
    private ProductCategory loadModelFromRequest(ProductCategoryRequest request) 
    {
		ProductCategory productCategory = new ProductCategory();
    	Integer productCategoryId = request.getId();
    	// Are we editing a ProductCategory
    	if(productCategoryId != null) 
    	{
    		productCategory = getEntityManager().find(ProductCategory.class, request.getId());
    		productCategory.setLastModifiedDt(request.getLastModifiedDt());
        	productCategory.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	productCategory.setCreatedDt(getCurrentDate());
        	productCategory.setCreatedByUsr(getCurrentUserName(request));
    	}
    	productCategory.setCode(request.getCode());
    	productCategory.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getProductCategoryType() != null)
    	{
    		ProductCategoryType productCategoryType = entityManager.find(ProductCategoryType.class, request.getProductCategoryType());
    		productCategory.setProductCategoryType(productCategoryType);
    	}
    	productCategory.setCode(request.getCode()); 
    	productCategory.setName(request.getName()); 
    	productCategory.setDescription(request.getDescription()); 
    	productCategory.setEffectiveDt(request.getEffectiveDt()); 
    	productCategory.setRecSt(request.getRecSt()); 
		return productCategory;
	}
}
