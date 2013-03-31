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

import com.nathanclaire.alantra.model.InvoiceStatus;
import com.nathanclaire.alantra.model.InvoiceStatusType;
import com.nathanclaire.alantra.rest.request.InvoiceStatusTypeRequest;
import com.nathanclaire.alantra.model.Invoice;
import com.nathanclaire.alantra.rest.request.InvoiceRequest;
import com.nathanclaire.alantra.rest.request.InvoiceStatusRequest;

/**
 * @author administrator
 *
 */
@Path("/invoicestatus")
@Stateless
public class InvoiceStatusService extends BaseEntityService<InvoiceStatus> 
{
	/**
	 * @param entityClass
	 */
	public InvoiceStatusService() {
		super(InvoiceStatus.class);
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
            CriteriaBuilder criteriaBuilder, Root<InvoiceStatus> root) 
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
     *   Create a InvoiceStatus. Data is contained in the InvoiceStatusRequest object
     * </p>
     * @param InvoiceStatusRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createInvoiceStatus(InvoiceStatusRequest request) {
        try 
        {
        	InvoiceStatus invoiceStatus = this.loadModelFromRequest(request);
        	entityManager.persist(invoiceStatus);
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
    public Response editInvoiceStatus(InvoiceStatusRequest request) 
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
    private InvoiceStatus loadModelFromRequest(InvoiceStatusRequest request) 
    {
		InvoiceStatus invoiceStatus = new InvoiceStatus();
    	Integer invoiceStatusId = request.getId();
    	// Are we editing a InvoiceStatus
    	if(invoiceStatusId != null) 
    	{
    		invoiceStatus = getEntityManager().find(InvoiceStatus.class, request.getId());
    		invoiceStatus.setLastModifiedDt(request.getLastModifiedDt());
        	invoiceStatus.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	invoiceStatus.setCreatedDt(getCurrentDate());
        	invoiceStatus.setCreatedByUsr(getCurrentUserName(request));
    	}
    	invoiceStatus.setCode(request.getCode());
    	invoiceStatus.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getInvoiceStatusType() != null)
    	{
    		InvoiceStatusType invoiceStatusType = entityManager.find(InvoiceStatusType.class, request.getInvoiceStatusType());
    		invoiceStatus.setInvoiceStatusType(invoiceStatusType);
    	}
    	if (request.getInvoice() != null)
    	{
    		Invoice invoice = entityManager.find(Invoice.class, request.getInvoice());
    		invoiceStatus.setInvoice(invoice);
    	}
    	invoiceStatus.setCode(request.getCode()); 
    	invoiceStatus.setName(request.getName()); 
    	invoiceStatus.setDescription(request.getDescription()); 
    	invoiceStatus.setEffectiveDt(request.getEffectiveDt()); 
    	invoiceStatus.setRecSt(request.getRecSt()); 
		return invoiceStatus;
	}
}
