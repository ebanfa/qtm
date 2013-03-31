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
import com.nathanclaire.alantra.invoice.model.InvoiceRoleType;
import com.nathanclaire.alantra.invoice.rest.request.InvoiceRoleTypeRequest;

/**
 * @author administrator
 *
 */
@Path("/invoiceroletype")
@Stateless
public class InvoiceRoleTypeService extends BaseEntityService<InvoiceRoleType> 
{
	/**
	 * @param entityClass
	 */
	public InvoiceRoleTypeService() {
		super(InvoiceRoleType.class);
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
            CriteriaBuilder criteriaBuilder, Root<InvoiceRoleType> root) 
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
     *   Create a InvoiceRoleType. Data is contained in the InvoiceRoleTypeRequest object
     * </p>
     * @param InvoiceRoleTypeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createInvoiceRoleType(InvoiceRoleTypeRequest request) {
        try 
        {
        	InvoiceRoleType invoiceRoleType = this.loadModelFromRequest(request);
        	entityManager.persist(invoiceRoleType);
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
    public Response editInvoiceRoleType(InvoiceRoleTypeRequest request) 
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
    private InvoiceRoleType loadModelFromRequest(InvoiceRoleTypeRequest request) 
    {
		InvoiceRoleType invoiceRoleType = new InvoiceRoleType();
    	Integer invoiceRoleTypeId = request.getId();
    	// Are we editing a InvoiceRoleType
    	if(invoiceRoleTypeId != null) 
    	{
    		invoiceRoleType = getEntityManager().find(InvoiceRoleType.class, request.getId());
    		invoiceRoleType.setLastModifiedDt(request.getLastModifiedDt());
        	invoiceRoleType.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	invoiceRoleType.setCreatedDt(getCurrentDate());
        	invoiceRoleType.setCreatedByUsr(getCurrentUserName(request));
    	}
    	invoiceRoleType.setCode(request.getCode());
    	invoiceRoleType.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	invoiceRoleType.setCode(request.getCode()); 
    	invoiceRoleType.setName(request.getName()); 
    	invoiceRoleType.setDescription(request.getDescription()); 
    	invoiceRoleType.setEffectiveDt(request.getEffectiveDt()); 
    	invoiceRoleType.setRecSt(request.getRecSt()); 
		return invoiceRoleType;
	}
}
