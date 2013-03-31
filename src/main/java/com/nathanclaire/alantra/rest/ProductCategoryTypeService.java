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

import com.nathanclaire.alantra.model.product.ProductCategoryType;
import com.nathanclaire.alantra.rest.request.ProductCategoryTypeRequest;

/**
 * @author administrator
 *
 */
@Path("/productcategorytype")
@Stateless
public class ProductCategoryTypeService extends BaseEntityService<ProductCategoryType> 
{
	/**
	 * @param entityClass
	 */
	public ProductCategoryTypeService() {
		super(ProductCategoryType.class);
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
            CriteriaBuilder criteriaBuilder, Root<ProductCategoryType> root) 
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
     *   Create a ProductCategoryType. Data is contained in the ProductCategoryTypeRequest object
     * </p>
     * @param ProductCategoryTypeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProductCategoryType(ProductCategoryTypeRequest request) {
        try 
        {
        	ProductCategoryType productCategoryType = this.loadModelFromRequest(request);
        	entityManager.persist(productCategoryType);
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
    public Response editProductCategoryType(ProductCategoryTypeRequest request) 
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
    private ProductCategoryType loadModelFromRequest(ProductCategoryTypeRequest request) 
    {
		ProductCategoryType productCategoryType = new ProductCategoryType();
    	Integer productCategoryTypeId = request.getId();
    	// Are we editing a ProductCategoryType
    	if(productCategoryTypeId != null) 
    	{
    		productCategoryType = getEntityManager().find(ProductCategoryType.class, request.getId());
    		productCategoryType.setLastModifiedDt(request.getLastModifiedDt());
        	productCategoryType.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	productCategoryType.setCreatedDt(getCurrentDate());
        	productCategoryType.setCreatedByUsr(getCurrentUserName(request));
    	}
    	productCategoryType.setCode(request.getCode());
    	productCategoryType.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	productCategoryType.setCode(request.getCode()); 
    	productCategoryType.setName(request.getName()); 
    	productCategoryType.setDescription(request.getDescription()); 
    	productCategoryType.setEffectiveDt(request.getEffectiveDt()); 
    	productCategoryType.setRecSt(request.getRecSt()); 
		return productCategoryType;
	}
}
