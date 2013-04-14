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

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.party.model.PartyClassification;
import com.nathanclaire.alantra.party.model.PartyType;
import com.nathanclaire.alantra.party.rest.request.PartyTypeRequest;
import com.nathanclaire.alantra.party.model.PartyClassificationType;
import com.nathanclaire.alantra.party.rest.request.PartyClassificationTypeRequest;
import com.nathanclaire.alantra.party.model.Party;
import com.nathanclaire.alantra.party.rest.request.PartyRequest;
import com.nathanclaire.alantra.party.rest.request.PartyClassificationRequest;

/**
 * @author administrator
 *
 */
@Path("/partyclassification")
@Stateless
public class PartyClassificationRESTService extends BaseEntityRESTService<PartyClassification> 
{
	/**
	 * @param entityClass
	 */
	public PartyClassificationRESTService() {
		super(PartyClassification.class);
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
            CriteriaBuilder criteriaBuilder, Root<PartyClassification> root) 
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
     *   Create a PartyClassification. Data is contained in the PartyClassificationRequest object
     * </p>
     * @param PartyClassificationRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPartyClassification(PartyClassificationRequest request) {
        try 
        {
        	PartyClassification partyClassification = this.loadModelFromRequest(request);
        	entityManager.persist(partyClassification);
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
    public Response editPartyClassification(PartyClassificationRequest request) 
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
    private PartyClassification loadModelFromRequest(PartyClassificationRequest request) 
    {
		PartyClassification partyClassification = new PartyClassification();
    	Integer partyClassificationId = request.getId();
    	// Are we editing a PartyClassification
    	if(partyClassificationId != null) 
    	{
    		partyClassification = getEntityManager().find(PartyClassification.class, request.getId());
    		partyClassification.setLastModifiedDt(request.getLastModifiedDt());
        	partyClassification.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	partyClassification.setCreatedDt(getCurrentDate());
        	partyClassification.setCreatedByUsr(getCurrentUserName(request));
    	}
    	partyClassification.setCode(request.getCode());
    	partyClassification.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getPartyType() != null)
    	{
    		PartyType partyType = entityManager.find(PartyType.class, request.getPartyType());
    		partyClassification.setPartyType(partyType);
    	}
    	if (request.getPartyClassificationType() != null)
    	{
    		PartyClassificationType partyClassificationType = entityManager.find(PartyClassificationType.class, request.getPartyClassificationType());
    		partyClassification.setPartyClassificationType(partyClassificationType);
    	}
    	if (request.getParty() != null)
    	{
    		Party party = entityManager.find(Party.class, request.getParty());
    		partyClassification.setParty(party);
    	}
    	partyClassification.setName(request.getName()); 
    	partyClassification.setDescription(request.getDescription()); 
    	partyClassification.setFromDt(request.getFromDt()); 
    	partyClassification.setThruDt(request.getThruDt()); 
    	partyClassification.setCode(request.getCode()); 
    	partyClassification.setEffectiveDt(request.getEffectiveDt()); 
    	partyClassification.setRecSt(request.getRecSt()); 
		return partyClassification;
	}
}
