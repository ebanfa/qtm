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

import com.nathanclaire.alantra.model.InvoiceTerm;
import com.nathanclaire.alantra.model.TermType;
import com.nathanclaire.alantra.rest.request.TermTypeRequest;
import com.nathanclaire.alantra.rest.request.InvoiceTermRequest;

/**
 * @author administrator
 *
 */
@Path("/invoiceterm")
@Stateless
public class InvoiceTermService extends BaseEntityService<InvoiceTerm> 
{
	/**
	 * @param entityClass
	 */
	public InvoiceTermService() {
		super(InvoiceTerm.class);
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
            CriteriaBuilder criteriaBuilder, Root<InvoiceTerm> root) 
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
     *   Create a InvoiceTerm. Data is contained in the InvoiceTermRequest object
     * </p>
     * @param InvoiceTermRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createInvoiceTerm(InvoiceTermRequest request) {
        try 
        {
        	InvoiceTerm invoiceTerm = this.loadModelFromRequest(request);
        	entityManager.persist(invoiceTerm);
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
    public Response editInvoiceTerm(InvoiceTermRequest request) 
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
    private InvoiceTerm loadModelFromRequest(InvoiceTermRequest request) 
    {
		InvoiceTerm invoiceTerm = new InvoiceTerm();
    	Integer invoiceTermId = request.getId();
    	// Are we editing a InvoiceTerm
    	if(invoiceTermId != null) 
    	{
    		invoiceTerm = getEntityManager().find(InvoiceTerm.class, request.getId());
    		invoiceTerm.setLastModifiedDt(request.getLastModifiedDt());
        	invoiceTerm.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	invoiceTerm.setCreatedDt(getCurrentDate());
        	invoiceTerm.setCreatedByUsr(getCurrentUserName(request));
    	}
    	invoiceTerm.setCode(request.getCode());
    	invoiceTerm.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getTermType() != null)
    	{
    		TermType termType = entityManager.find(TermType.class, request.getTermType());
    		invoiceTerm.setTermType(termType);
    	}
    	invoiceTerm.setCode(request.getCode()); 
    	invoiceTerm.setTermVal(request.getTermVal()); 
    	invoiceTerm.setDescription(request.getDescription()); 
    	invoiceTerm.setEffectiveDt(request.getEffectiveDt()); 
    	invoiceTerm.setRecSt(request.getRecSt()); 
		return invoiceTerm;
	}
}
