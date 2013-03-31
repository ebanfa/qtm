/**
 * 
 */
package com.nathanclaire.alantra.order.rest;

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
import com.nathanclaire.alantra.base.rest.request.ProductOrderItemTypeRequest;
import com.nathanclaire.alantra.order.model.ProductOrderItemType;

/**
 * @author administrator
 *
 */
@Path("/productorderitemtype")
@Stateless
public class ProductOrderItemTypeService extends BaseEntityService<ProductOrderItemType> 
{
	/**
	 * @param entityClass
	 */
	public ProductOrderItemTypeService() {
		super(ProductOrderItemType.class);
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
            CriteriaBuilder criteriaBuilder, Root<ProductOrderItemType> root) 
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
     *   Create a ProductOrderItemType. Data is contained in the ProductOrderItemTypeRequest object
     * </p>
     * @param ProductOrderItemTypeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProductOrderItemType(ProductOrderItemTypeRequest request) {
        try 
        {
        	ProductOrderItemType productOrderItemType = this.loadModelFromRequest(request);
        	entityManager.persist(productOrderItemType);
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
    public Response editProductOrderItemType(ProductOrderItemTypeRequest request) 
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
    private ProductOrderItemType loadModelFromRequest(ProductOrderItemTypeRequest request) 
    {
		ProductOrderItemType productOrderItemType = new ProductOrderItemType();
    	Integer productOrderItemTypeId = request.getId();
    	// Are we editing a ProductOrderItemType
    	if(productOrderItemTypeId != null) 
    	{
    		productOrderItemType = getEntityManager().find(ProductOrderItemType.class, request.getId());
    		productOrderItemType.setLastModifiedDt(request.getLastModifiedDt());
        	productOrderItemType.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	productOrderItemType.setCreatedDt(getCurrentDate());
        	productOrderItemType.setCreatedByUsr(getCurrentUserName(request));
    	}
    	productOrderItemType.setCode(request.getCode());
    	productOrderItemType.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	productOrderItemType.setCode(request.getCode()); 
    	productOrderItemType.setName(request.getName()); 
    	productOrderItemType.setDescription(request.getDescription()); 
    	productOrderItemType.setEffectiveDt(request.getEffectiveDt()); 
    	productOrderItemType.setRecSt(request.getRecSt()); 
		return productOrderItemType;
	}
}
