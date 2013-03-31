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

import com.nathanclaire.alantra.base.rest.BaseEntityService;
import com.nathanclaire.alantra.invoice.model.InvoiceItemCategory;
import com.nathanclaire.alantra.invoice.rest.request.InvoiceItemCategoryRequest;

/**
 * @author administrator
 *
 */
@Path("/invoiceitemcategory")
@Stateless
public class InvoiceItemCategoryService extends BaseEntityService<InvoiceItemCategory> 
{
	/**
	 * @param entityClass
	 */
	public InvoiceItemCategoryService() {
		super(InvoiceItemCategory.class);
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
            CriteriaBuilder criteriaBuilder, Root<InvoiceItemCategory> root) 
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
     *   Create a InvoiceItemCategory. Data is contained in the InvoiceItemCategoryRequest object
     * </p>
     * @param InvoiceItemCategoryRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createInvoiceItemCategory(InvoiceItemCategoryRequest request) {
        try 
        {
        	InvoiceItemCategory invoiceItemCategory = this.loadModelFromRequest(request);
        	entityManager.persist(invoiceItemCategory);
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
    public Response editInvoiceItemCategory(InvoiceItemCategoryRequest request) 
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
    private InvoiceItemCategory loadModelFromRequest(InvoiceItemCategoryRequest request) 
    {
		InvoiceItemCategory invoiceItemCategory = new InvoiceItemCategory();
    	Integer invoiceItemCategoryId = request.getId();
    	// Are we editing a InvoiceItemCategory
    	if(invoiceItemCategoryId != null) 
    	{
    		invoiceItemCategory = getEntityManager().find(InvoiceItemCategory.class, request.getId());
    		invoiceItemCategory.setLastModifiedDt(request.getLastModifiedDt());
        	invoiceItemCategory.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	invoiceItemCategory.setCreatedDt(getCurrentDate());
        	invoiceItemCategory.setCreatedByUsr(getCurrentUserName(request));
    	}
    	invoiceItemCategory.setCode(request.getCode());
    	invoiceItemCategory.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	invoiceItemCategory.setCode(request.getCode()); 
    	invoiceItemCategory.setName(request.getName()); 
    	invoiceItemCategory.setDescription(request.getDescription()); 
    	invoiceItemCategory.setEffectiveDt(request.getEffectiveDt()); 
    	invoiceItemCategory.setRecSt(request.getRecSt()); 
		return invoiceItemCategory;
	}
}
