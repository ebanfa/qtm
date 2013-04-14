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
import com.nathanclaire.alantra.channel.model.Host;
import com.nathanclaire.alantra.channel.model.ServiceProtocolAdapter;
import com.nathanclaire.alantra.channel.rest.request.ServiceProtocolAdapterRequest;
import com.nathanclaire.alantra.channel.model.HostType;
import com.nathanclaire.alantra.channel.rest.request.HostTypeRequest;
import com.nathanclaire.alantra.channel.rest.request.HostRequest;

/**
 * @author administrator
 *
 */
@Path("/host")
@Stateless
public class HostRESTService extends BaseEntityRESTService<Host> 
{
	/**
	 * @param entityClass
	 */
	public HostRESTService() {
		super(Host.class);
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
            CriteriaBuilder criteriaBuilder, Root<Host> root) 
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
     *   Create a Host. Data is contained in the HostRequest object
     * </p>
     * @param HostRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createHost(HostRequest request) {
        try 
        {
        	Host host = this.loadModelFromRequest(request);
        	entityManager.persist(host);
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
    public Response editHost(HostRequest request) 
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
    private Host loadModelFromRequest(HostRequest request) 
    {
		Host host = new Host();
    	Integer hostId = request.getId();
    	// Are we editing a Host
    	if(hostId != null) 
    	{
    		host = getEntityManager().find(Host.class, request.getId());
    		host.setLastModifiedDt(request.getLastModifiedDt());
        	host.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	host.setCreatedDt(getCurrentDate());
        	host.setCreatedByUsr(getCurrentUserName(request));
    	}
    	host.setCode(request.getCode());
    	host.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getServiceProtocolAdapter() != null)
    	{
    		ServiceProtocolAdapter serviceProtocolAdapter = entityManager.find(ServiceProtocolAdapter.class, request.getServiceProtocolAdapter());
    		host.setServiceProtocolAdapter(serviceProtocolAdapter);
    	}
    	if (request.getHostType() != null)
    	{
    		HostType hostType = entityManager.find(HostType.class, request.getHostType());
    		host.setHostType(hostType);
    	}
    	host.setName(request.getName()); 
    	host.setDescription(request.getDescription()); 
    	host.setPortNo(request.getPortNo()); 
    	host.setIpAddress(request.getIpAddress()); 
    	host.setCode(request.getCode()); 
    	host.setEffectiveDt(request.getEffectiveDt()); 
    	host.setRecSt(request.getRecSt()); 
		return host;
	}
}
