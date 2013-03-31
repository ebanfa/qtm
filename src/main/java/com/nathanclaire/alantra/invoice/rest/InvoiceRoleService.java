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

import com.nathanclaire.alantra.party.model.Party;
import com.nathanclaire.alantra.base.rest.BaseEntityService;
import com.nathanclaire.alantra.base.rest.request.InvoiceRequest;
import com.nathanclaire.alantra.base.rest.request.InvoiceRoleRequest;
import com.nathanclaire.alantra.base.rest.request.InvoiceRoleTypeRequest;
import com.nathanclaire.alantra.base.rest.request.PartyRequest;
import com.nathanclaire.alantra.invoice.model.Invoice;
import com.nathanclaire.alantra.invoice.model.InvoiceRole;
import com.nathanclaire.alantra.invoice.model.InvoiceRoleType;

/**
 * @author administrator
 *
 */
@Path("/invoicerole")
@Stateless
public class InvoiceRoleService extends BaseEntityService<InvoiceRole> 
{
	/**
	 * @param entityClass
	 */
	public InvoiceRoleService() {
		super(InvoiceRole.class);
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
            CriteriaBuilder criteriaBuilder, Root<InvoiceRole> root) 
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
     *   Create a InvoiceRole. Data is contained in the InvoiceRoleRequest object
     * </p>
     * @param InvoiceRoleRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createInvoiceRole(InvoiceRoleRequest request) {
        try 
        {
        	InvoiceRole invoiceRole = this.loadModelFromRequest(request);
        	entityManager.persist(invoiceRole);
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
    public Response editInvoiceRole(InvoiceRoleRequest request) 
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
    private InvoiceRole loadModelFromRequest(InvoiceRoleRequest request) 
    {
		InvoiceRole invoiceRole = new InvoiceRole();
    	Integer invoiceRoleId = request.getId();
    	// Are we editing a InvoiceRole
    	if(invoiceRoleId != null) 
    	{
    		invoiceRole = getEntityManager().find(InvoiceRole.class, request.getId());
    		invoiceRole.setLastModifiedDt(request.getLastModifiedDt());
        	invoiceRole.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	invoiceRole.setCreatedDt(getCurrentDate());
        	invoiceRole.setCreatedByUsr(getCurrentUserName(request));
    	}
    	invoiceRole.setCode(request.getCode());
    	invoiceRole.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getInvoiceRoleType() != null)
    	{
    		InvoiceRoleType invoiceRoleType = entityManager.find(InvoiceRoleType.class, request.getInvoiceRoleType());
    		invoiceRole.setInvoiceRoleType(invoiceRoleType);
    	}
    	if (request.getParty() != null)
    	{
    		Party party = entityManager.find(Party.class, request.getParty());
    		invoiceRole.setParty(party);
    	}
    	if (request.getInvoice() != null)
    	{
    		Invoice invoice = entityManager.find(Invoice.class, request.getInvoice());
    		invoiceRole.setInvoice(invoice);
    	}
    	invoiceRole.setCode(request.getCode()); 
    	invoiceRole.setDescription(request.getDescription()); 
    	invoiceRole.setPercentage(request.getPercentage()); 
    	invoiceRole.setEffectiveDt(request.getEffectiveDt()); 
    	invoiceRole.setRecSt(request.getRecSt()); 
		return invoiceRole;
	}
}
