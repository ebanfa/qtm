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

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.invoice.model.InvoiceType;
import com.nathanclaire.alantra.invoice.rest.request.InvoiceTypeRequest;

/**
 * @author administrator
 *
 */
@Path("/invoicetype")
@Stateless
public class InvoiceTypeService extends BaseEntityRESTService<InvoiceType> 
{
	/**
	 * @param entityClass
	 */
	public InvoiceTypeService() {
		super(InvoiceType.class);
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
            CriteriaBuilder criteriaBuilder, Root<InvoiceType> root) 
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
     *   Create a InvoiceType. Data is contained in the InvoiceTypeRequest object
     * </p>
     * @param InvoiceTypeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createInvoiceType(InvoiceTypeRequest request) {
        try 
        {
        	InvoiceType invoiceType = this.loadModelFromRequest(request);
        	entityManager.persist(invoiceType);
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
    public Response editInvoiceType(InvoiceTypeRequest request) 
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
    private InvoiceType loadModelFromRequest(InvoiceTypeRequest request) 
    {
		InvoiceType invoiceType = new InvoiceType();
    	Integer invoiceTypeId = request.getId();
    	// Are we editing a InvoiceType
    	if(invoiceTypeId != null) 
    	{
    		invoiceType = getEntityManager().find(InvoiceType.class, request.getId());
    		invoiceType.setLastModifiedDt(request.getLastModifiedDt());
        	invoiceType.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	invoiceType.setCreatedDt(getCurrentDate());
        	invoiceType.setCreatedByUsr(getCurrentUserName(request));
    	}
    	invoiceType.setCode(request.getCode());
    	invoiceType.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	invoiceType.setCode(request.getCode()); 
    	invoiceType.setName(request.getName()); 
    	invoiceType.setDescription(request.getDescription()); 
    	invoiceType.setEffectiveDt(request.getEffectiveDt()); 
    	invoiceType.setRecSt(request.getRecSt()); 
		return invoiceType;
	}
}
