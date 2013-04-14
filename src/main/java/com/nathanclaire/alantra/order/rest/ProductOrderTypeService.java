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

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.order.model.ProductOrderType;
import com.nathanclaire.alantra.order.rest.request.ProductOrderTypeRequest;

/**
 * @author administrator
 *
 */
@Path("/productordertype")
@Stateless
public class ProductOrderTypeService extends BaseEntityRESTService<ProductOrderType> 
{
	/**
	 * @param entityClass
	 */
	public ProductOrderTypeService() {
		super(ProductOrderType.class);
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
            CriteriaBuilder criteriaBuilder, Root<ProductOrderType> root) 
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
     *   Create a ProductOrderType. Data is contained in the ProductOrderTypeRequest object
     * </p>
     * @param ProductOrderTypeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProductOrderType(ProductOrderTypeRequest request) {
        try 
        {
        	ProductOrderType productOrderType = this.loadModelFromRequest(request);
        	entityManager.persist(productOrderType);
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
    public Response editProductOrderType(ProductOrderTypeRequest request) 
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
    private ProductOrderType loadModelFromRequest(ProductOrderTypeRequest request) 
    {
		ProductOrderType productOrderType = new ProductOrderType();
    	Integer productOrderTypeId = request.getId();
    	// Are we editing a ProductOrderType
    	if(productOrderTypeId != null) 
    	{
    		productOrderType = getEntityManager().find(ProductOrderType.class, request.getId());
    		productOrderType.setLastModifiedDt(request.getLastModifiedDt());
        	productOrderType.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	productOrderType.setCreatedDt(getCurrentDate());
        	productOrderType.setCreatedByUsr(getCurrentUserName(request));
    	}
    	productOrderType.setCode(request.getCode());
    	productOrderType.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	productOrderType.setCode(request.getCode()); 
    	productOrderType.setName(request.getName()); 
    	productOrderType.setDescription(request.getDescription()); 
    	productOrderType.setEffectiveDt(request.getEffectiveDt()); 
    	productOrderType.setRecSt(request.getRecSt()); 
		return productOrderType;
	}
}
