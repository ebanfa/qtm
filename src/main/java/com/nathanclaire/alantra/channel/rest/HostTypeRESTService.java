/**
 * 
 */
package com.nathanclaire.alantra.channel.rest;

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
import com.nathanclaire.alantra.channel.model.HostType;
import com.nathanclaire.alantra.channel.rest.request.HostTypeRequest;

/**
 * @author administrator
 *
 */
@Path("/hosttype")
@Stateless
public class HostTypeRESTService extends BaseEntityService<HostType> 
{
	/**
	 * @param entityClass
	 */
	public HostTypeRESTService() {
		super(HostType.class);
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
            CriteriaBuilder criteriaBuilder, Root<HostType> root) 
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
     *   Create a HostType. Data is contained in the HostTypeRequest object
     * </p>
     * @param HostTypeRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createHostType(HostTypeRequest request) {
        try 
        {
        	HostType hostType = this.loadModelFromRequest(request);
        	entityManager.persist(hostType);
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
    public Response editHostType(HostTypeRequest request) 
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
    private HostType loadModelFromRequest(HostTypeRequest request) 
    {
		HostType hostType = new HostType();
    	Integer hostTypeId = request.getId();
    	// Are we editing a HostType
    	if(hostTypeId != null) 
    	{
    		hostType = getEntityManager().find(HostType.class, request.getId());
    		hostType.setLastModifiedDt(request.getLastModifiedDt());
        	hostType.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	hostType.setCreatedDt(getCurrentDate());
        	hostType.setCreatedByUsr(getCurrentUserName(request));
    	}
    	hostType.setCode(request.getCode());
    	hostType.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	hostType.setName(request.getName()); 
    	hostType.setDescription(request.getDescription()); 
    	hostType.setCode(request.getCode()); 
    	hostType.setEffectiveDt(request.getEffectiveDt()); 
    	hostType.setRecSt(request.getRecSt()); 
		return hostType;
	}
}
