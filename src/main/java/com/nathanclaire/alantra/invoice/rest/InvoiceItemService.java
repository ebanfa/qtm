/**
 * 
 */
package com.nathanclaire.alantra.invoice.rest;

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

import com.nathanclaire.alantra.product.model.Product;
import com.nathanclaire.alantra.product.model.ProductFeature;
import com.nathanclaire.alantra.product.rest.request.ProductFeatureRequest;
import com.nathanclaire.alantra.product.rest.request.ProductRequest;
import com.nathanclaire.alantra.base.rest.BaseEntityService;
import com.nathanclaire.alantra.invoice.model.Invoice;
import com.nathanclaire.alantra.invoice.model.InvoiceItem;
import com.nathanclaire.alantra.invoice.model.InvoiceItemCategory;
import com.nathanclaire.alantra.invoice.model.InvoiceItemType;
import com.nathanclaire.alantra.invoice.rest.request.InvoiceItemCategoryRequest;
import com.nathanclaire.alantra.invoice.rest.request.InvoiceItemRequest;
import com.nathanclaire.alantra.invoice.rest.request.InvoiceItemTypeRequest;
import com.nathanclaire.alantra.invoice.rest.request.InvoiceRequest;

/**
 * @author administrator
 *
 */
@Path("/invoiceitem")
@Stateless
public class InvoiceItemService extends BaseEntityService<InvoiceItem> 
{
	/**
	 * @param entityClass
	 */
	public InvoiceItemService() {
		super(InvoiceItem.class);
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
            CriteriaBuilder criteriaBuilder, Root<InvoiceItem> root) 
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
     *   Create a InvoiceItem. Data is contained in the InvoiceItemRequest object
     * </p>
     * @param InvoiceItemRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createInvoiceItem(InvoiceItemRequest request) {
        try 
        {
        	InvoiceItem invoiceItem = this.loadModelFromRequest(request);
        	entityManager.persist(invoiceItem);
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
    public Response editInvoiceItem(InvoiceItemRequest request) 
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
    private InvoiceItem loadModelFromRequest(InvoiceItemRequest request) 
    {
		InvoiceItem invoiceItem = new InvoiceItem();
    	Integer invoiceItemId = request.getId();
    	// Are we editing a InvoiceItem
    	if(invoiceItemId != null) 
    	{
    		invoiceItem = getEntityManager().find(InvoiceItem.class, request.getId());
    		invoiceItem.setLastModifiedDt(request.getLastModifiedDt());
        	invoiceItem.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	invoiceItem.setCreatedDt(getCurrentDate());
        	invoiceItem.setCreatedByUsr(getCurrentUserName(request));
    	}
    	invoiceItem.setCode(request.getCode());
    	invoiceItem.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getInvoiceItemType() != null)
    	{
    		InvoiceItemType invoiceItemType = entityManager.find(InvoiceItemType.class, request.getInvoiceItemType());
    		invoiceItem.setInvoiceItemType(invoiceItemType);
    	}
    	if (request.getInvoiceItemCategory() != null)
    	{
    		InvoiceItemCategory invoiceItemCategory = entityManager.find(InvoiceItemCategory.class, request.getInvoiceItemCategory());
    		invoiceItem.setInvoiceItemCategory(invoiceItemCategory);
    	}
    	if (request.getProduct() != null)
    	{
    		Product product = entityManager.find(Product.class, request.getProduct());
    		invoiceItem.setProduct(product);
    	}
    	if (request.getProductFeature() != null)
    	{
    		ProductFeature productFeature = entityManager.find(ProductFeature.class, request.getProductFeature());
    		invoiceItem.setProductFeature(productFeature);
    	}
    	if (request.getInvoice() != null)
    	{
    		Invoice invoice = entityManager.find(Invoice.class, request.getInvoice());
    		invoiceItem.setInvoice(invoice);
    	}
    	invoiceItem.setCode(request.getCode()); 
    	invoiceItem.setName(request.getName()); 
    	invoiceItem.setDescription(request.getDescription()); 
    	invoiceItem.setTaxableFg(request.getTaxableFg()); 
    	invoiceItem.setQuantity(request.getQuantity()); 
    	invoiceItem.setAmount(request.getAmount()); 
    	invoiceItem.setEffectiveDt(request.getEffectiveDt()); 
    	invoiceItem.setRecSt(request.getRecSt()); 
		return invoiceItem;
	}
}
