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
import com.nathanclaire.alantra.party.model.CaseRoleType;
import com.nathanclaire.alantra.party.rest.request.CaseRoleTypeRequest;

/**
 * @author administrator
 *
 */
@Path("/caseroletype")
@Stateless
public class CaseRoleTypeService extends BaseEntityService<CaseRoleType> 
{
	/**
	 * @param entityClass
	 */
	public CaseRoleTypeService() {
		super(CaseRoleType.class);
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
            CriteriaBuilder criteriaBuilder, Root<CaseRoleType> root) 
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
     *   Create a CaseRoleType. Data is contained in the CaseRoleTypeRequest object
     * </p>
     * @param CaseRoleTypeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCaseRoleType(CaseRoleTypeRequest request) {
        try 
        {
        	CaseRoleType caseRoleType = this.loadModelFromRequest(request);
        	entityManager.persist(caseRoleType);
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
    public Response editCaseRoleType(CaseRoleTypeRequest request) 
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
    private CaseRoleType loadModelFromRequest(CaseRoleTypeRequest request) 
    {
		CaseRoleType caseRoleType = new CaseRoleType();
    	Integer caseRoleTypeId = request.getId();
    	// Are we editing a CaseRoleType
    	if(caseRoleTypeId != null) 
    	{
    		caseRoleType = getEntityManager().find(CaseRoleType.class, request.getId());
    		caseRoleType.setLastModifiedDt(request.getLastModifiedDt());
        	caseRoleType.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	caseRoleType.setCreatedDt(getCurrentDate());
        	caseRoleType.setCreatedByUsr(getCurrentUserName(request));
    	}
    	caseRoleType.setCode(request.getCode());
    	caseRoleType.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	caseRoleType.setCode(request.getCode()); 
    	caseRoleType.setName(request.getName()); 
    	caseRoleType.setDescription(request.getDescription()); 
    	caseRoleType.setEffectiveDt(request.getEffectiveDt()); 
    	caseRoleType.setRecSt(request.getRecSt()); 
		return caseRoleType;
	}
}
