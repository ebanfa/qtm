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

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.channel.model.ServicePeer;
import com.nathanclaire.alantra.channel.model.Service;
import com.nathanclaire.alantra.channel.rest.request.ServiceRequest;
import com.nathanclaire.alantra.channel.model.Host;
import com.nathanclaire.alantra.channel.rest.request.HostRequest;
import com.nathanclaire.alantra.channel.rest.request.ServicePeerRequest;

/**
 * @author administrator
 *
 */
@Path("/servicepeer")
@Stateless
public class ServicePeerRESTService extends BaseEntityRESTService<ServicePeer> 
{
	/**
	 * @param entityClass
	 */
	public ServicePeerRESTService() {
		super(ServicePeer.class);
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
            CriteriaBuilder criteriaBuilder, Root<ServicePeer> root) 
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
     *   Create a ServicePeer. Data is contained in the ServicePeerRequest object
     * </p>
     * @param ServicePeerRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createServicePeer(ServicePeerRequest request) {
        try 
        {
        	ServicePeer servicePeer = this.loadModelFromRequest(request);
        	entityManager.persist(servicePeer);
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
    public Response editServicePeer(ServicePeerRequest request) 
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
    private ServicePeer loadModelFromRequest(ServicePeerRequest request) 
    {
		ServicePeer servicePeer = new ServicePeer();
    	Integer servicePeerId = request.getId();
    	// Are we editing a ServicePeer
    	if(servicePeerId != null) 
    	{
    		servicePeer = getEntityManager().find(ServicePeer.class, request.getId());
    		servicePeer.setLastModifiedDt(request.getLastModifiedDt());
        	servicePeer.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	servicePeer.setCreatedDt(getCurrentDate());
        	servicePeer.setCreatedByUsr(getCurrentUserName(request));
    	}
    	servicePeer.setCode(request.getCode());
    	servicePeer.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getService() != null)
    	{
    		Service service = entityManager.find(Service.class, request.getService());
    		servicePeer.setService(service);
    	}
    	if (request.getHost() != null)
    	{
    		Host host = entityManager.find(Host.class, request.getHost());
    		servicePeer.setHost(host);
    	}
    	servicePeer.setName(request.getName()); 
    	servicePeer.setDescription(request.getDescription()); 
    	servicePeer.setCode(request.getCode()); 
    	servicePeer.setEffectiveDt(request.getEffectiveDt()); 
    	servicePeer.setRecSt(request.getRecSt()); 
		return servicePeer;
	}
}
