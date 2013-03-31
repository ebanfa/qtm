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

import com.nathanclaire.alantra.model.order.ProductOrder;
import com.nathanclaire.alantra.model.order.ProductOrderType;
import com.nathanclaire.alantra.rest.request.ProductOrderTypeRequest;
import com.nathanclaire.alantra.rest.request.ProductOrderRequest;

/**
 * @author administrator
 *
 */
@Path("/productorder")
@Stateless
public class ProductOrderService extends BaseEntityService<ProductOrder> 
{
	/**
	 * @param entityClass
	 */
	public ProductOrderService() {
		super(ProductOrder.class);
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
            CriteriaBuilder criteriaBuilder, Root<ProductOrder> root) 
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
     *   Create a ProductOrder. Data is contained in the ProductOrderRequest object
     * </p>
     * @param ProductOrderRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProductOrder(ProductOrderRequest request) {
        try 
        {
        	ProductOrder productOrder = this.loadModelFromRequest(request);
        	entityManager.persist(productOrder);
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
    public Response editProductOrder(ProductOrderRequest request) 
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
    private ProductOrder loadModelFromRequest(ProductOrderRequest request) 
    {
		ProductOrder productOrder = new ProductOrder();
    	Integer productOrderId = request.getId();
    	// Are we editing a ProductOrder
    	if(productOrderId != null) 
    	{
    		productOrder = getEntityManager().find(ProductOrder.class, request.getId());
    		productOrder.setLastModifiedDt(request.getLastModifiedDt());
        	productOrder.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	productOrder.setCreatedDt(getCurrentDate());
        	productOrder.setCreatedByUsr(getCurrentUserName(request));
    	}
    	productOrder.setCode(request.getCode());
    	productOrder.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getProductOrderType() != null)
    	{
    		ProductOrderType productOrderType = entityManager.find(ProductOrderType.class, request.getProductOrderType());
    		productOrder.setProductOrderType(productOrderType);
    	}
    	productOrder.setCode(request.getCode()); 
    	productOrder.setName(request.getName()); 
    	productOrder.setDescription(request.getDescription()); 
    	productOrder.setOrderDt(request.getOrderDt()); 
    	productOrder.setEffectiveDt(request.getEffectiveDt()); 
    	productOrder.setRecSt(request.getRecSt()); 
		return productOrder;
	}
}
