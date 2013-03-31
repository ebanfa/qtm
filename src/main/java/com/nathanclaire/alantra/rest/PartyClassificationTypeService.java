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

import com.nathanclaire.alantra.model.party.PartyClassificationType;
import com.nathanclaire.alantra.rest.request.PartyClassificationTypeRequest;
import com.nathanclaire.alantra.rest.request.PartyClassificationTypeRequest;

/**
 * @author administrator
 *
 */
@Path("/partyclassificationtype")
@Stateless
public class PartyClassificationTypeService extends BaseEntityService<PartyClassificationType> 
{
	/**
	 * @param entityClass
	 */
	public PartyClassificationTypeService() {
		super(PartyClassificationType.class);
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
            CriteriaBuilder criteriaBuilder, Root<PartyClassificationType> root) 
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
     *   Create a PartyClassificationType. Data is contained in the PartyClassificationTypeRequest object
     * </p>
     * @param PartyClassificationTypeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPartyClassificationType(PartyClassificationTypeRequest request) {
        try 
        {
        	PartyClassificationType partyClassificationType = this.loadModelFromRequest(request);
        	entityManager.persist(partyClassificationType);
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
    public Response editPartyClassificationType(PartyClassificationTypeRequest request) 
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
    private PartyClassificationType loadModelFromRequest(PartyClassificationTypeRequest request) 
    {
		PartyClassificationType partyClassificationType = new PartyClassificationType();
    	Integer partyClassificationTypeId = request.getId();
    	// Are we editing a PartyClassificationType
    	if(partyClassificationTypeId != null) 
    	{
    		partyClassificationType = getEntityManager().find(PartyClassificationType.class, request.getId());
    		partyClassificationType.setLastModifiedDt(request.getLastModifiedDt());
        	partyClassificationType.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	partyClassificationType.setCreatedDt(getCurrentDate());
        	partyClassificationType.setCreatedByUsr(getCurrentUserName(request));
    	}
    	partyClassificationType.setCode(request.getCode());
    	partyClassificationType.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getPartyClassificationType() != null)
    	{
    		PartyClassificationType parentPartyClassificationType = entityManager.find(PartyClassificationType.class, request.getPartyClassificationType());
    		partyClassificationType.setPartyClassificationType(parentPartyClassificationType);
    	}
    	partyClassificationType.setCode(request.getCode()); 
    	partyClassificationType.setName(request.getName()); 
    	partyClassificationType.setDescription(request.getDescription()); 
    	partyClassificationType.setEffectiveDt(request.getEffectiveDt()); 
    	partyClassificationType.setRecSt(request.getRecSt()); 
		return partyClassificationType;
	}
}
