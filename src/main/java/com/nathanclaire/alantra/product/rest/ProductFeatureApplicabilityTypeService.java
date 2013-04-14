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
import com.nathanclaire.alantra.product.model.ProductFeatureApplicabilityType;
import com.nathanclaire.alantra.product.rest.request.ProductFeatureApplicabilityTypeRequest;

/**
 * @author administrator
 *
 */
@Path("/productfeatureapplicabilitytype")
@Stateless
public class ProductFeatureApplicabilityTypeService extends BaseEntityRESTService<ProductFeatureApplicabilityType> 
{
	/**
	 * @param entityClass
	 */
	public ProductFeatureApplicabilityTypeService() {
		super(ProductFeatureApplicabilityType.class);
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
            CriteriaBuilder criteriaBuilder, Root<ProductFeatureApplicabilityType> root) 
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
     *   Create a ProductFeatureApplicabilityType. Data is contained in the ProductFeatureApplicabilityTypeRequest object
     * </p>
     * @param ProductFeatureApplicabilityTypeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProductFeatureApplicabilityType(ProductFeatureApplicabilityTypeRequest request) {
        try 
        {
        	ProductFeatureApplicabilityType productFeatureApplicabilityType = this.loadModelFromRequest(request);
        	entityManager.persist(productFeatureApplicabilityType);
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
    public Response editProductFeatureApplicabilityType(ProductFeatureApplicabilityTypeRequest request) 
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
    private ProductFeatureApplicabilityType loadModelFromRequest(ProductFeatureApplicabilityTypeRequest request) 
    {
		ProductFeatureApplicabilityType productFeatureApplicabilityType = new ProductFeatureApplicabilityType();
    	Integer productFeatureApplicabilityTypeId = request.getId();
    	// Are we editing a ProductFeatureApplicabilityType
    	if(productFeatureApplicabilityTypeId != null) 
    	{
    		productFeatureApplicabilityType = getEntityManager().find(ProductFeatureApplicabilityType.class, request.getId());
    		productFeatureApplicabilityType.setLastModifiedDt(request.getLastModifiedDt());
        	productFeatureApplicabilityType.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	productFeatureApplicabilityType.setCreatedDt(getCurrentDate());
        	productFeatureApplicabilityType.setCreatedByUsr(getCurrentUserName(request));
    	}
    	productFeatureApplicabilityType.setCode(request.getCode());
    	productFeatureApplicabilityType.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	productFeatureApplicabilityType.setCode(request.getCode()); 
    	productFeatureApplicabilityType.setName(request.getName()); 
    	productFeatureApplicabilityType.setDescription(request.getDescription()); 
    	productFeatureApplicabilityType.setEffectiveDt(request.getEffectiveDt()); 
    	productFeatureApplicabilityType.setRecSt(request.getRecSt()); 
		return productFeatureApplicabilityType;
	}
}
