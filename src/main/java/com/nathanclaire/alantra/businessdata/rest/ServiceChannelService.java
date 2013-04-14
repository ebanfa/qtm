/**
 * 
 */
package com.nathanclaire.alantra.businessdata.rest;

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
import com.nathanclaire.alantra.businessdata.model.ServiceChannel;
import com.nathanclaire.alantra.businessdata.rest.request.ServiceChannelRequest;

/**
 * @author administrator
 *
 */
@Path("/servicechannel")
@Stateless
public class ServiceChannelService extends BaseEntityRESTService<ServiceChannel> 
{
	/**
	 * @param entityClass
	 */
	public ServiceChannelService() {
		super(ServiceChannel.class);
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
            CriteriaBuilder criteriaBuilder, Root<ServiceChannel> root) 
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
     *   Create a ServiceChannel. Data is contained in the ServiceChannelRequest object
     * </p>
     * @param ServiceChannelRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createServiceChannel(ServiceChannelRequest request) {
        try 
        {
        	ServiceChannel serviceChannel = this.loadModelFromRequest(request);
        	entityManager.persist(serviceChannel);
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
    public Response editServiceChannel(ServiceChannelRequest request) 
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
    private ServiceChannel loadModelFromRequest(ServiceChannelRequest request) 
    {
		ServiceChannel serviceChannel = new ServiceChannel();
    	Integer serviceChannelId = request.getId();
    	// Are we editing a ServiceChannel
    	if(serviceChannelId != null) 
    	{
    		serviceChannel = getEntityManager().find(ServiceChannel.class, request.getId());
    		serviceChannel.setLastModifiedDt(request.getLastModifiedDt());
        	serviceChannel.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	serviceChannel.setCreatedDt(getCurrentDate());
        	serviceChannel.setCreatedByUsr(getCurrentUserName(request));
    	}
    	serviceChannel.setCode(request.getCode());
    	serviceChannel.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	serviceChannel.setCode(request.getCode()); 
    	serviceChannel.setName(request.getName()); 
    	serviceChannel.setDescription(request.getDescription()); 
    	serviceChannel.setEffectiveDt(request.getEffectiveDt()); 
    	serviceChannel.setRecSt(request.getRecSt()); 
		return serviceChannel;
	}
}
