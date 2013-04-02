/**
 * 
 */
package com.nathanclaire.alantra.party.rest;

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
import com.nathanclaire.alantra.party.model.PartyContactMechanismPurpose;
import com.nathanclaire.alantra.party.model.ContactMechanismPurposeType;
import com.nathanclaire.alantra.party.rest.request.ContactMechanismPurposeTypeRequest;
import com.nathanclaire.alantra.party.model.ContactMechanism;
import com.nathanclaire.alantra.party.rest.request.ContactMechanismRequest;
import com.nathanclaire.alantra.party.rest.request.PartyContactMechanismPurposeRequest;

/**
 * @author administrator
 *
 */
@Path("/partycontactmechanismpurpose")
@Stateless
public class PartyContactMechanismPurposeRESTService extends BaseEntityService<PartyContactMechanismPurpose> 
{
	/**
	 * @param entityClass
	 */
	public PartyContactMechanismPurposeRESTService() {
		super(PartyContactMechanismPurpose.class);
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
            CriteriaBuilder criteriaBuilder, Root<PartyContactMechanismPurpose> root) 
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
     *   Create a PartyContactMechanismPurpose. Data is contained in the PartyContactMechanismPurposeRequest object
     * </p>
     * @param PartyContactMechanismPurposeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPartyContactMechanismPurpose(PartyContactMechanismPurposeRequest request) {
        try 
        {
        	PartyContactMechanismPurpose partyContactMechanismPurpose = this.loadModelFromRequest(request);
        	entityManager.persist(partyContactMechanismPurpose);
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
    public Response editPartyContactMechanismPurpose(PartyContactMechanismPurposeRequest request) 
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
    private PartyContactMechanismPurpose loadModelFromRequest(PartyContactMechanismPurposeRequest request) 
    {
		PartyContactMechanismPurpose partyContactMechanismPurpose = new PartyContactMechanismPurpose();
    	Integer partyContactMechanismPurposeId = request.getId();
    	// Are we editing a PartyContactMechanismPurpose
    	if(partyContactMechanismPurposeId != null) 
    	{
    		partyContactMechanismPurpose = getEntityManager().find(PartyContactMechanismPurpose.class, request.getId());
    		partyContactMechanismPurpose.setLastModifiedDt(request.getLastModifiedDt());
        	partyContactMechanismPurpose.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	partyContactMechanismPurpose.setCreatedDt(getCurrentDate());
        	partyContactMechanismPurpose.setCreatedByUsr(getCurrentUserName(request));
    	}
    	partyContactMechanismPurpose.setCode(request.getCode());
    	partyContactMechanismPurpose.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getContactMechanismPurposeType() != null)
    	{
    		ContactMechanismPurposeType contactMechanismPurposeType = entityManager.find(ContactMechanismPurposeType.class, request.getContactMechanismPurposeType());
    		partyContactMechanismPurpose.setContactMechanismPurposeType(contactMechanismPurposeType);
    	}
    	if (request.getContactMechanism() != null)
    	{
    		ContactMechanism contactMechanism = entityManager.find(ContactMechanism.class, request.getContactMechanism());
    		partyContactMechanismPurpose.setContactMechanism(contactMechanism);
    	}
    	partyContactMechanismPurpose.setFromDt(request.getFromDt()); 
    	partyContactMechanismPurpose.setToDt(request.getToDt()); 
    	partyContactMechanismPurpose.setName(request.getName()); 
    	partyContactMechanismPurpose.setDescription(request.getDescription()); 
    	partyContactMechanismPurpose.setCode(request.getCode()); 
    	partyContactMechanismPurpose.setEffectiveDt(request.getEffectiveDt()); 
    	partyContactMechanismPurpose.setRecSt(request.getRecSt()); 
		return partyContactMechanismPurpose;
	}
}
