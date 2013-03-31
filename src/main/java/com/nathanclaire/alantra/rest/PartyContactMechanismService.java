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

import com.nathanclaire.alantra.model.PartyContactMechanism;
import com.nathanclaire.alantra.model.ContactMechanism;
import com.nathanclaire.alantra.rest.request.ContactMechanismRequest;
import com.nathanclaire.alantra.model.Party;
import com.nathanclaire.alantra.rest.request.PartyRequest;
import com.nathanclaire.alantra.rest.request.PartyContactMechanismRequest;

/**
 * @author administrator
 *
 */
@Path("/partycontactmechanism")
@Stateless
public class PartyContactMechanismService extends BaseEntityService<PartyContactMechanism> 
{
	/**
	 * @param entityClass
	 */
	public PartyContactMechanismService() {
		super(PartyContactMechanism.class);
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
            CriteriaBuilder criteriaBuilder, Root<PartyContactMechanism> root) 
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
     *   Create a PartyContactMechanism. Data is contained in the PartyContactMechanismRequest object
     * </p>
     * @param PartyContactMechanismRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPartyContactMechanism(PartyContactMechanismRequest request) {
        try 
        {
        	PartyContactMechanism partyContactMechanism = this.loadModelFromRequest(request);
        	entityManager.persist(partyContactMechanism);
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
    public Response editPartyContactMechanism(PartyContactMechanismRequest request) 
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
    private PartyContactMechanism loadModelFromRequest(PartyContactMechanismRequest request) 
    {
		PartyContactMechanism partyContactMechanism = new PartyContactMechanism();
    	Integer partyContactMechanismId = request.getId();
    	// Are we editing a PartyContactMechanism
    	if(partyContactMechanismId != null) 
    	{
    		partyContactMechanism = getEntityManager().find(PartyContactMechanism.class, request.getId());
    		partyContactMechanism.setLastModifiedDt(request.getLastModifiedDt());
        	partyContactMechanism.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	partyContactMechanism.setCreatedDt(getCurrentDate());
        	partyContactMechanism.setCreatedByUsr(getCurrentUserName(request));
    	}
    	partyContactMechanism.setCode(request.getCode());
    	partyContactMechanism.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getContactMechanism() != null)
    	{
    		ContactMechanism contactMechanism = entityManager.find(ContactMechanism.class, request.getContactMechanism());
    		partyContactMechanism.setContactMechanism(contactMechanism);
    	}
    	if (request.getParty() != null)
    	{
    		Party party = entityManager.find(Party.class, request.getParty());
    		partyContactMechanism.setParty(party);
    	}
    	partyContactMechanism.setCode(request.getCode()); 
    	partyContactMechanism.setFromDt(request.getFromDt()); 
    	partyContactMechanism.setToDt(request.getToDt()); 
    	partyContactMechanism.setDescription(request.getDescription()); 
    	partyContactMechanism.setNoSolicitationFg(request.getNoSolicitationFg()); 
    	partyContactMechanism.setEffectiveDt(request.getEffectiveDt()); 
    	partyContactMechanism.setExtension(request.getExtension()); 
    	partyContactMechanism.setRemarks(request.getRemarks()); 
    	partyContactMechanism.setRecSt(request.getRecSt()); 
		return partyContactMechanism;
	}
}
