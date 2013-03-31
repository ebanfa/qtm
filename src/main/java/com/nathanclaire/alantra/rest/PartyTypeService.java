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

import com.nathanclaire.alantra.model.PartyType;
import com.nathanclaire.alantra.model.PartyType;
import com.nathanclaire.alantra.rest.request.PartyTypeRequest;
import com.nathanclaire.alantra.rest.request.PartyTypeRequest;

/**
 * @author administrator
 *
 */
@Path("/partytype")
@Stateless
public class PartyTypeService extends BaseEntityService<PartyType> 
{
	/**
	 * @param entityClass
	 */
	public PartyTypeService() {
		super(PartyType.class);
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
            CriteriaBuilder criteriaBuilder, Root<PartyType> root) 
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
     *   Create a PartyType. Data is contained in the PartyTypeRequest object
     * </p>
     * @param PartyTypeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPartyType(PartyTypeRequest request) {
        try 
        {
        	PartyType partyType = this.loadModelFromRequest(request);
        	entityManager.persist(partyType);
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
    public Response editPartyType(PartyTypeRequest request) 
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
    private PartyType loadModelFromRequest(PartyTypeRequest request) 
    {
		PartyType partyType = new PartyType();
    	Integer partyTypeId = request.getId();
    	// Are we editing a PartyType
    	if(partyTypeId != null) 
    	{
    		partyType = getEntityManager().find(PartyType.class, request.getId());
    		partyType.setLastModifiedDt(request.getLastModifiedDt());
        	partyType.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	partyType.setCreatedDt(getCurrentDate());
        	partyType.setCreatedByUsr(getCurrentUserName(request));
    	}
    	partyType.setCode(request.getCode());
    	partyType.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getPartyType() != null)
    	{
    		PartyType parentPartyType = entityManager.find(PartyType.class, request.getPartyType());
    		partyType.setPartyType(parentPartyType);
    	}
    	partyType.setCode(request.getCode()); 
    	partyType.setName(request.getName()); 
    	partyType.setDescription(request.getDescription()); 
    	partyType.setEffectiveDt(request.getEffectiveDt()); 
    	partyType.setRecSt(request.getRecSt()); 
		return partyType;
	}
}
