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
import com.nathanclaire.alantra.base.rest.request.RoleTypeRequest;
import com.nathanclaire.alantra.party.model.RoleType;

/**
 * @author administrator
 *
 */
@Path("/roletype")
@Stateless
public class RoleTypeService extends BaseEntityService<RoleType> 
{
	/**
	 * @param entityClass
	 */
	public RoleTypeService() {
		super(RoleType.class);
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
            CriteriaBuilder criteriaBuilder, Root<RoleType> root) 
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
     *   Create a RoleType. Data is contained in the RoleTypeRequest object
     * </p>
     * @param RoleTypeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRoleType(RoleTypeRequest request) {
        try 
        {
        	RoleType roleType = this.loadModelFromRequest(request);
        	entityManager.persist(roleType);
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
    public Response editRoleType(RoleTypeRequest request) 
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
    private RoleType loadModelFromRequest(RoleTypeRequest request) 
    {
		RoleType roleType = new RoleType();
    	Integer roleTypeId = request.getId();
    	// Are we editing a RoleType
    	if(roleTypeId != null) 
    	{
    		roleType = getEntityManager().find(RoleType.class, request.getId());
    		roleType.setLastModifiedDt(request.getLastModifiedDt());
        	roleType.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	roleType.setCreatedDt(getCurrentDate());
        	roleType.setCreatedByUsr(getCurrentUserName(request));
    	}
    	roleType.setCode(request.getCode());
    	roleType.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	roleType.setName(request.getName()); 
    	roleType.setDescription(request.getDescription()); 
    	roleType.setCode(request.getCode()); 
    	roleType.setEffectiveDt(request.getEffectiveDt()); 
    	roleType.setRecSt(request.getRecSt()); 
		return roleType;
	}
}
