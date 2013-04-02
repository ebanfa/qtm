/**
 * 
 */
package com.nathanclaire.alantra.invoice.rest;

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
import com.nathanclaire.alantra.invoice.model.Invoice;
import com.nathanclaire.alantra.invoice.model.InvoiceTerm;
import com.nathanclaire.alantra.invoice.model.InvoiceType;
import com.nathanclaire.alantra.invoice.rest.request.InvoiceRequest;
import com.nathanclaire.alantra.party.model.ContactMechanism;
import com.nathanclaire.alantra.party.model.Party;

/**
 * @author administrator
 *
 */
@Path("/invoice")
@Stateless
public class InvoiceService extends BaseEntityService<Invoice> 
{
	/**
	 * @param entityClass
	 */
	public InvoiceService() {
		super(Invoice.class);
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
            CriteriaBuilder criteriaBuilder, Root<Invoice> root) 
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
     *   Create a Invoice. Data is contained in the InvoiceRequest object
     * </p>
     * @param InvoiceRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createInvoice(InvoiceRequest request) {
        try 
        {
        	Invoice invoice = this.loadModelFromRequest(request);
        	entityManager.persist(invoice);
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
    public Response editInvoice(InvoiceRequest request) 
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
    private Invoice loadModelFromRequest(InvoiceRequest request) 
    {
		Invoice invoice = new Invoice();
    	Integer invoiceId = request.getId();
    	// Are we editing a Invoice
    	if(invoiceId != null) 
    	{
    		invoice = getEntityManager().find(Invoice.class, request.getId());
    		invoice.setLastModifiedDt(request.getLastModifiedDt());
        	invoice.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	invoice.setCreatedDt(getCurrentDate());
        	invoice.setCreatedByUsr(getCurrentUserName(request));
    	}
    	invoice.setCode(request.getCode());
    	invoice.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getInvoiceType() != null)
    	{
    		InvoiceType invoiceType = entityManager.find(InvoiceType.class, request.getInvoiceType());
    		invoice.setInvoiceType(invoiceType);
    	}
    	if (request.getPartyByToPartyId() != null)
    	{
    		Party partyByToPartyId = entityManager.find(Party.class, request.getPartyByToPartyId());
    		invoice.setPartyByToPartyId(partyByToPartyId);
    	}
    	if (request.getContactMechanism() != null)
    	{
    		ContactMechanism contactMechanism = entityManager.find(ContactMechanism.class, request.getContactMechanism());
    		invoice.setContactMechanism(contactMechanism);
    	}
    	if (request.getPartyByFromPartyId() != null)
    	{
    		Party partyByFromPartyId = entityManager.find(Party.class, request.getPartyByFromPartyId());
    		invoice.setPartyByFromPartyId(partyByFromPartyId);
    	}
    	if (request.getInvoiceTerm() != null)
    	{
    		InvoiceTerm invoiceTerm = entityManager.find(InvoiceTerm.class, request.getInvoiceTerm());
    		invoice.setInvoiceTerm(invoiceTerm);
    	}
    	invoice.setCode(request.getCode()); 
    	invoice.setMessage(request.getMessage()); 
    	invoice.setDescription(request.getDescription()); 
    	invoice.setInvoiceDt(request.getInvoiceDt()); 
    	invoice.setEffectiveDt(request.getEffectiveDt()); 
    	invoice.setRecSt(request.getRecSt()); 
		return invoice;
	}
}
