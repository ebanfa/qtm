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
import com.nathanclaire.alantra.product.model.ProductType;
import com.nathanclaire.alantra.product.rest.request.ProductTypeRequest;

/**
 * @author administrator
 *
 */
@Path("/producttype")
@Stateless
public class ProductTypeService extends BaseEntityRESTService<ProductType> 
{
	/**
	 * @param entityClass
	 */
	public ProductTypeService() {
		super(ProductType.class);
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
            CriteriaBuilder criteriaBuilder, Root<ProductType> root) 
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
     *   Create a ProductType. Data is contained in the ProductTypeRequest object
     * </p>
     * @param ProductTypeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProductType(ProductTypeRequest request) {
        try 
        {
        	ProductType productType = this.loadModelFromRequest(request);
        	entityManager.persist(productType);
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
    public Response editProductType(ProductTypeRequest request) 
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
    private ProductType loadModelFromRequest(ProductTypeRequest request) 
    {
		ProductType productType = new ProductType();
    	Integer productTypeId = request.getId();
    	// Are we editing a ProductType
    	if(productTypeId != null) 
    	{
    		productType = getEntityManager().find(ProductType.class, request.getId());
    		productType.setLastModifiedDt(request.getLastModifiedDt());
        	productType.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	productType.setCreatedDt(getCurrentDate());
        	productType.setCreatedByUsr(getCurrentUserName(request));
    	}
    	productType.setCode(request.getCode());
    	productType.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	productType.setCode(request.getCode()); 
    	productType.setNames(request.getNames()); 
    	productType.setDescription(request.getDescription()); 
    	productType.setEffectiveDt(request.getEffectiveDt()); 
    	productType.setRecSt(request.getRecSt()); 
		return productType;
	}
}
