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

import com.nathanclaire.alantra.rest.request.InvoiceItemRequest;
import com.nathanclaire.alantra.model.invoice.InvoiceItem;
import com.nathanclaire.alantra.model.invoice.OrderItemBilling;
import com.nathanclaire.alantra.model.order.ProductOrderItem;
import com.nathanclaire.alantra.rest.request.ProductOrderItemRequest;
import com.nathanclaire.alantra.rest.request.OrderItemBillingRequest;

/**
 * @author administrator
 *
 */
@Path("/orderitembilling")
@Stateless
public class OrderItemBillingService extends BaseEntityService<OrderItemBilling> 
{
	/**
	 * @param entityClass
	 */
	public OrderItemBillingService() {
		super(OrderItemBilling.class);
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
            CriteriaBuilder criteriaBuilder, Root<OrderItemBilling> root) 
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
     *   Create a OrderItemBilling. Data is contained in the OrderItemBillingRequest object
     * </p>
     * @param OrderItemBillingRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrderItemBilling(OrderItemBillingRequest request) {
        try 
        {
        	OrderItemBilling orderItemBilling = this.loadModelFromRequest(request);
        	entityManager.persist(orderItemBilling);
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
    public Response editOrderItemBilling(OrderItemBillingRequest request) 
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
    private OrderItemBilling loadModelFromRequest(OrderItemBillingRequest request) 
    {
		OrderItemBilling orderItemBilling = new OrderItemBilling();
    	Integer orderItemBillingId = request.getId();
    	// Are we editing a OrderItemBilling
    	if(orderItemBillingId != null) 
    	{
    		orderItemBilling = getEntityManager().find(OrderItemBilling.class, request.getId());
    		orderItemBilling.setLastModifiedDt(request.getLastModifiedDt());
        	orderItemBilling.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	orderItemBilling.setCreatedDt(getCurrentDate());
        	orderItemBilling.setCreatedByUsr(getCurrentUserName(request));
    	}
    	orderItemBilling.setCode(request.getCode());
    	orderItemBilling.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getInvoiceItem() != null)
    	{
    		InvoiceItem invoiceItem = entityManager.find(InvoiceItem.class, request.getInvoiceItem());
    		orderItemBilling.setInvoiceItem(invoiceItem);
    	}
    	if (request.getProductOrderItem() != null)
    	{
    		ProductOrderItem productOrderItem = entityManager.find(ProductOrderItem.class, request.getProductOrderItem());
    		orderItemBilling.setProductOrderItem(productOrderItem);
    	}
    	orderItemBilling.setQuantity(request.getQuantity()); 
    	orderItemBilling.setAmount(request.getAmount()); 
    	orderItemBilling.setCode(request.getCode()); 
    	orderItemBilling.setDescription(request.getDescription()); 
    	orderItemBilling.setEffectiveDt(request.getEffectiveDt()); 
    	orderItemBilling.setRecSt(request.getRecSt()); 
		return orderItemBilling;
	}
}
