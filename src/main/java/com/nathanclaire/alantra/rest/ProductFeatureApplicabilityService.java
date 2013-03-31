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

import com.nathanclaire.alantra.model.ProductFeatureApplicability;
import com.nathanclaire.alantra.model.Product;
import com.nathanclaire.alantra.rest.request.ProductRequest;
import com.nathanclaire.alantra.model.ProductFeature;
import com.nathanclaire.alantra.rest.request.ProductFeatureRequest;
import com.nathanclaire.alantra.model.ProductFeatureApplicabilityType;
import com.nathanclaire.alantra.rest.request.ProductFeatureApplicabilityTypeRequest;
import com.nathanclaire.alantra.rest.request.ProductFeatureApplicabilityRequest;

/**
 * @author administrator
 *
 */
@Path("/productfeatureapplicability")
@Stateless
public class ProductFeatureApplicabilityService extends BaseEntityService<ProductFeatureApplicability> 
{
	/**
	 * @param entityClass
	 */
	public ProductFeatureApplicabilityService() {
		super(ProductFeatureApplicability.class);
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
            CriteriaBuilder criteriaBuilder, Root<ProductFeatureApplicability> root) 
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
     *   Create a ProductFeatureApplicability. Data is contained in the ProductFeatureApplicabilityRequest object
     * </p>
     * @param ProductFeatureApplicabilityRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProductFeatureApplicability(ProductFeatureApplicabilityRequest request) {
        try 
        {
        	ProductFeatureApplicability productFeatureApplicability = this.loadModelFromRequest(request);
        	entityManager.persist(productFeatureApplicability);
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
    public Response editProductFeatureApplicability(ProductFeatureApplicabilityRequest request) 
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
    private ProductFeatureApplicability loadModelFromRequest(ProductFeatureApplicabilityRequest request) 
    {
		ProductFeatureApplicability productFeatureApplicability = new ProductFeatureApplicability();
    	Integer productFeatureApplicabilityId = request.getId();
    	// Are we editing a ProductFeatureApplicability
    	if(productFeatureApplicabilityId != null) 
    	{
    		productFeatureApplicability = getEntityManager().find(ProductFeatureApplicability.class, request.getId());
    		productFeatureApplicability.setLastModifiedDt(request.getLastModifiedDt());
        	productFeatureApplicability.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	productFeatureApplicability.setCreatedDt(getCurrentDate());
        	productFeatureApplicability.setCreatedByUsr(getCurrentUserName(request));
    	}
    	productFeatureApplicability.setCode(request.getCode());
    	productFeatureApplicability.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getProduct() != null)
    	{
    		Product product = entityManager.find(Product.class, request.getProduct());
    		productFeatureApplicability.setProduct(product);
    	}
    	if (request.getProductFeature() != null)
    	{
    		ProductFeature productFeature = entityManager.find(ProductFeature.class, request.getProductFeature());
    		productFeatureApplicability.setProductFeature(productFeature);
    	}
    	if (request.getProductFeatureApplicabilityType() != null)
    	{
    		ProductFeatureApplicabilityType productFeatureApplicabilityType = entityManager.find(ProductFeatureApplicabilityType.class, request.getProductFeatureApplicabilityType());
    		productFeatureApplicability.setProductFeatureApplicabilityType(productFeatureApplicabilityType);
    	}
    	productFeatureApplicability.setCode(request.getCode()); 
    	productFeatureApplicability.setName(request.getName()); 
    	productFeatureApplicability.setDescription(request.getDescription()); 
    	productFeatureApplicability.setFromDt(request.getFromDt()); 
    	productFeatureApplicability.setToDt(request.getToDt()); 
    	productFeatureApplicability.setEffectiveDt(request.getEffectiveDt()); 
    	productFeatureApplicability.setRecSt(request.getRecSt()); 
		return productFeatureApplicability;
	}
}
