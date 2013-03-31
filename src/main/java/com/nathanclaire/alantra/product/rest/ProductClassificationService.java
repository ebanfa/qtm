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

import com.nathanclaire.alantra.base.rest.BaseEntityService;
import com.nathanclaire.alantra.base.rest.request.ProductCategoryRequest;
import com.nathanclaire.alantra.base.rest.request.ProductClassificationRequest;
import com.nathanclaire.alantra.base.rest.request.ProductRequest;
import com.nathanclaire.alantra.product.model.Product;
import com.nathanclaire.alantra.product.model.ProductCategory;
import com.nathanclaire.alantra.product.model.ProductClassification;

/**
 * @author administrator
 *
 */
@Path("/productclassification")
@Stateless
public class ProductClassificationService extends BaseEntityService<ProductClassification> 
{
	/**
	 * @param entityClass
	 */
	public ProductClassificationService() {
		super(ProductClassification.class);
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
            CriteriaBuilder criteriaBuilder, Root<ProductClassification> root) 
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
     *   Create a ProductClassification. Data is contained in the ProductClassificationRequest object
     * </p>
     * @param ProductClassificationRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProductClassification(ProductClassificationRequest request) {
        try 
        {
        	ProductClassification productClassification = this.loadModelFromRequest(request);
        	entityManager.persist(productClassification);
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
    public Response editProductClassification(ProductClassificationRequest request) 
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
    private ProductClassification loadModelFromRequest(ProductClassificationRequest request) 
    {
		ProductClassification productClassification = new ProductClassification();
    	Integer productClassificationId = request.getId();
    	// Are we editing a ProductClassification
    	if(productClassificationId != null) 
    	{
    		productClassification = getEntityManager().find(ProductClassification.class, request.getId());
    		productClassification.setLastModifiedDt(request.getLastModifiedDt());
        	productClassification.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	productClassification.setCreatedDt(getCurrentDate());
        	productClassification.setCreatedByUsr(getCurrentUserName(request));
    	}
    	productClassification.setCode(request.getCode());
    	productClassification.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getProductCategory() != null)
    	{
    		ProductCategory productCategory = entityManager.find(ProductCategory.class, request.getProductCategory());
    		productClassification.setProductCategory(productCategory);
    	}
    	if (request.getProduct() != null)
    	{
    		Product product = entityManager.find(Product.class, request.getProduct());
    		productClassification.setProduct(product);
    	}
    	productClassification.setCode(request.getCode()); 
    	productClassification.setName(request.getName()); 
    	productClassification.setDescription(request.getDescription()); 
    	productClassification.setRemarks(request.getRemarks()); 
    	productClassification.setFromDt(request.getFromDt()); 
    	productClassification.setToDt(request.getToDt()); 
    	productClassification.setPrimaryFg(request.getPrimaryFg()); 
    	productClassification.setEffectiveDt(request.getEffectiveDt()); 
    	productClassification.setRecSt(request.getRecSt()); 
		return productClassification;
	}
}
