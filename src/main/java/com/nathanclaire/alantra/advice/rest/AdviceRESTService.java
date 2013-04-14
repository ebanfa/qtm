/**
 * 
 */
package com.nathanclaire.alantra.advice.rest;

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
import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.party.model.Party;
import com.nathanclaire.alantra.party.rest.request.PartyRequest;
import com.nathanclaire.alantra.advice.model.AdviceStatus;
import com.nathanclaire.alantra.advice.rest.request.AdviceStatusRequest;
import com.nathanclaire.alantra.messaging.model.CommunicationEvent;
import com.nathanclaire.alantra.messaging.rest.request.CommunicationEventRequest;
import com.nathanclaire.alantra.advice.model.AdviceType;
import com.nathanclaire.alantra.advice.rest.request.AdviceTypeRequest;
import com.nathanclaire.alantra.advice.rest.request.AdviceRequest;

/**
 * @author administrator
 *
 */
@Path("/advice")
@Stateless
public class AdviceRESTService extends BaseEntityRESTService<Advice> 
{
	/**
	 * @param entityClass
	 */
	public AdviceRESTService() {
		super(Advice.class);
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
            CriteriaBuilder criteriaBuilder, Root<Advice> root) 
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
     *   Create a Advice. Data is contained in the AdviceRequest object
     * </p>
     * @param AdviceRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAdvice(AdviceRequest request) {
        try 
        {
        	Advice advice = this.loadModelFromRequest(request);
        	entityManager.persist(advice);
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
    public Response editAdvice(AdviceRequest request) 
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
    private Advice loadModelFromRequest(AdviceRequest request) 
    {
		Advice advice = new Advice();
    	Integer adviceId = request.getId();
    	// Are we editing a Advice
    	if(adviceId != null) 
    	{
    		advice = getEntityManager().find(Advice.class, request.getId());
    		advice.setLastModifiedDt(request.getLastModifiedDt());
        	advice.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	advice.setCreatedDt(getCurrentDate());
        	advice.setCreatedByUsr(getCurrentUserName(request));
    	}
    	advice.setCode(request.getCode());
    	advice.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getParty() != null)
    	{
    		Party party = entityManager.find(Party.class, request.getParty());
    		advice.setParty(party);
    	}
    	if (request.getAdviceStatus() != null)
    	{
    		AdviceStatus adviceStatus = entityManager.find(AdviceStatus.class, request.getAdviceStatus());
    		advice.setAdviceStatus(adviceStatus);
    	}
    	if (request.getCommunicationEvent() != null)
    	{
    		CommunicationEvent communicationEvent = entityManager.find(CommunicationEvent.class, request.getCommunicationEvent());
    		advice.setCommunicationEvent(communicationEvent);
    	}
    	if (request.getAdviceType() != null)
    	{
    		AdviceType adviceType = entityManager.find(AdviceType.class, request.getAdviceType());
    		advice.setAdviceType(adviceType);
    	}
    	advice.setName(request.getName()); 
    	advice.setDescription(request.getDescription()); 
    	advice.setAdviceTxt(request.getAdviceTxt()); 
    	advice.setAccountNo(request.getAccountNo()); 
    	advice.setCode(request.getCode()); 
    	advice.setEffectiveDt(request.getEffectiveDt()); 
    	advice.setRecSt(request.getRecSt()); 
		return advice;
	}
}
