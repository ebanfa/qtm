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

import com.nathanclaire.alantra.rest.request.PartyRequest;
import com.nathanclaire.alantra.model.party.CaseRole;
import com.nathanclaire.alantra.model.party.CaseRoleType;
import com.nathanclaire.alantra.model.party.Party;
import com.nathanclaire.alantra.rest.request.CaseRoleTypeRequest;
import com.nathanclaire.alantra.rest.request.CaseRoleRequest;

/**
 * @author administrator
 *
 */
@Path("/caserole")
@Stateless
public class CaseRoleService extends BaseEntityService<CaseRole> 
{
	/**
	 * @param entityClass
	 */
	public CaseRoleService() {
		super(CaseRole.class);
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
            CriteriaBuilder criteriaBuilder, Root<CaseRole> root) 
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
     *   Create a CaseRole. Data is contained in the CaseRoleRequest object
     * </p>
     * @param CaseRoleRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCaseRole(CaseRoleRequest request) {
        try 
        {
        	CaseRole caseRole = this.loadModelFromRequest(request);
        	entityManager.persist(caseRole);
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
    public Response editCaseRole(CaseRoleRequest request) 
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
    private CaseRole loadModelFromRequest(CaseRoleRequest request) 
    {
		CaseRole caseRole = new CaseRole();
    	Integer caseRoleId = request.getId();
    	// Are we editing a CaseRole
    	if(caseRoleId != null) 
    	{
    		caseRole = getEntityManager().find(CaseRole.class, request.getId());
    		caseRole.setLastModifiedDt(request.getLastModifiedDt());
        	caseRole.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	caseRole.setCreatedDt(getCurrentDate());
        	caseRole.setCreatedByUsr(getCurrentUserName(request));
    	}
    	caseRole.setCode(request.getCode());
    	caseRole.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getParty() != null)
    	{
    		Party party = entityManager.find(Party.class, request.getParty());
    		caseRole.setParty(party);
    	}
    	if (request.getCaseRoleType() != null)
    	{
    		CaseRoleType caseRoleType = entityManager.find(CaseRoleType.class, request.getCaseRoleType());
    		caseRole.setCaseRoleType(caseRoleType);
    	}
    	caseRole.setCode(request.getCode()); 
    	caseRole.setName(request.getName()); 
    	caseRole.setDescription(request.getDescription()); 
    	caseRole.setEffectiveDt(request.getEffectiveDt()); 
    	caseRole.setRecSt(request.getRecSt()); 
		return caseRole;
	}
}
