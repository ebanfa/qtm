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
import com.nathanclaire.alantra.order.model.ProductOrder;
import com.nathanclaire.alantra.order.model.ProductOrderItem;
import com.nathanclaire.alantra.order.model.ProductOrderItemType;
import com.nathanclaire.alantra.order.rest.request.ProductOrderItemRequest;
import com.nathanclaire.alantra.order.rest.request.ProductOrderItemTypeRequest;
import com.nathanclaire.alantra.order.rest.request.ProductOrderRequest;
import com.nathanclaire.alantra.product.model.Product;
import com.nathanclaire.alantra.product.model.ProductFeature;
import com.nathanclaire.alantra.product.rest.request.ProductFeatureRequest;
import com.nathanclaire.alantra.product.rest.request.ProductRequest;

/**
 * @author administrator
 *
 */
@Path("/productorderitem")
@Stateless
public class ProductOrderItemService extends BaseEntityRESTService<ProductOrderItem> 
{
	/**
	 * @param entityClass
	 */
	public ProductOrderItemService() {
		super(ProductOrderItem.class);
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
            CriteriaBuilder criteriaBuilder, Root<ProductOrderItem> root) 
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
     *   Create a ProductOrderItem. Data is contained in the ProductOrderItemRequest object
     * </p>
     * @param ProductOrderItemRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProductOrderItem(ProductOrderItemRequest request) {
        try 
        {
        	ProductOrderItem productOrderItem = this.loadModelFromRequest(request);
        	entityManager.persist(productOrderItem);
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
    public Response editProductOrderItem(ProductOrderItemRequest request) 
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
    private ProductOrderItem loadModelFromRequest(ProductOrderItemRequest request) 
    {
		ProductOrderItem productOrderItem = new ProductOrderItem();
    	Integer productOrderItemId = request.getId();
    	// Are we editing a ProductOrderItem
    	if(productOrderItemId != null) 
    	{
    		productOrderItem = getEntityManager().find(ProductOrderItem.class, request.getId());
    		productOrderItem.setLastModifiedDt(request.getLastModifiedDt());
        	productOrderItem.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	productOrderItem.setCreatedDt(getCurrentDate());
        	productOrderItem.setCreatedByUsr(getCurrentUserName(request));
    	}
    	productOrderItem.setCode(request.getCode());
    	productOrderItem.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getProductOrderItemType() != null)
    	{
    		ProductOrderItemType productOrderItemType = entityManager.find(ProductOrderItemType.class, request.getProductOrderItemType());
    		productOrderItem.setProductOrderItemType(productOrderItemType);
    	}
    	if (request.getProductOrder() != null)
    	{
    		ProductOrder productOrder = entityManager.find(ProductOrder.class, request.getProductOrder());
    		productOrderItem.setProductOrder(productOrder);
    	}
    	if (request.getProduct() != null)
    	{
    		Product product = entityManager.find(Product.class, request.getProduct());
    		productOrderItem.setProduct(product);
    	}
    	if (request.getProductFeature() != null)
    	{
    		ProductFeature productFeature = entityManager.find(ProductFeature.class, request.getProductFeature());
    		productOrderItem.setProductFeature(productFeature);
    	}
    	productOrderItem.setCode(request.getCode()); 
    	productOrderItem.setName(request.getName()); 
    	productOrderItem.setDescription(request.getDescription()); 
    	productOrderItem.setQuantity(request.getQuantity()); 
    	productOrderItem.setUnitPrice(request.getUnitPrice()); 
    	productOrderItem.setExpectDeliveryDt(request.getExpectDeliveryDt()); 
    	productOrderItem.setInstructions(request.getInstructions()); 
    	productOrderItem.setRemarks(request.getRemarks()); 
    	productOrderItem.setEffectiveDt(request.getEffectiveDt()); 
    	productOrderItem.setRecSt(request.getRecSt()); 
		return productOrderItem;
	}
}
