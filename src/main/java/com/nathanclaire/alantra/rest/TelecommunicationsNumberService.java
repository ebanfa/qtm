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

import com.nathanclaire.alantra.model.party.ContactMechanism;
import com.nathanclaire.alantra.model.party.TelecommunicationsNumber;
import com.nathanclaire.alantra.rest.request.ContactMechanismRequest;
import com.nathanclaire.alantra.rest.request.TelecommunicationsNumberRequest;

/**
 * @author administrator
 *
 */
@Path("/telecommunicationsnumber")
@Stateless
public class TelecommunicationsNumberService extends BaseEntityService<TelecommunicationsNumber> 
{
	/**
	 * @param entityClass
	 */
	public TelecommunicationsNumberService() {
		super(TelecommunicationsNumber.class);
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
            CriteriaBuilder criteriaBuilder, Root<TelecommunicationsNumber> root) 
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
     *   Create a TelecommunicationsNumber. Data is contained in the TelecommunicationsNumberRequest object
     * </p>
     * @param TelecommunicationsNumberRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTelecommunicationsNumber(TelecommunicationsNumberRequest request) {
        try 
        {
        	TelecommunicationsNumber telecommunicationsNumber = this.loadModelFromRequest(request);
        	entityManager.persist(telecommunicationsNumber);
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
    public Response editTelecommunicationsNumber(TelecommunicationsNumberRequest request) 
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
    private TelecommunicationsNumber loadModelFromRequest(TelecommunicationsNumberRequest request) 
    {
		TelecommunicationsNumber telecommunicationsNumber = new TelecommunicationsNumber();
    	Integer telecommunicationsNumberId = request.getId();
    	// Are we editing a TelecommunicationsNumber
    	if(telecommunicationsNumberId != null) 
    	{
    		telecommunicationsNumber = getEntityManager().find(TelecommunicationsNumber.class, request.getId());
    		telecommunicationsNumber.setLastModifiedDt(request.getLastModifiedDt());
        	telecommunicationsNumber.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	telecommunicationsNumber.setCreatedDt(getCurrentDate());
        	telecommunicationsNumber.setCreatedByUsr(getCurrentUserName(request));
    	}
    	telecommunicationsNumber.setCode(request.getCode());
    	telecommunicationsNumber.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getContactMechanism() != null)
    	{
    		ContactMechanism contactMechanism = entityManager.find(ContactMechanism.class, request.getContactMechanism());
    		telecommunicationsNumber.setContactMechanism(contactMechanism);
    	}
    	telecommunicationsNumber.setCode(request.getCode()); 
    	telecommunicationsNumber.setAreaCode(request.getAreaCode()); 
    	telecommunicationsNumber.setContactNo(request.getContactNo()); 
    	telecommunicationsNumber.setCountryCd(request.getCountryCd()); 
    	telecommunicationsNumber.setDescription(request.getDescription()); 
    	telecommunicationsNumber.setEffectiveDt(request.getEffectiveDt()); 
    	telecommunicationsNumber.setRecSt(request.getRecSt()); 
		return telecommunicationsNumber;
	}
}
