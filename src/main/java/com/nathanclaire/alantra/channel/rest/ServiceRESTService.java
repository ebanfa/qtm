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
import com.nathanclaire.alantra.channel.model.Service;
import com.nathanclaire.alantra.channel.model.ServiceType;
import com.nathanclaire.alantra.channel.rest.request.ServiceTypeRequest;
import com.nathanclaire.alantra.channel.model.ServiceProtocolAdapter;
import com.nathanclaire.alantra.channel.rest.request.ServiceProtocolAdapterRequest;
import com.nathanclaire.alantra.channel.model.ServiceMode;
import com.nathanclaire.alantra.channel.rest.request.ServiceModeRequest;
import com.nathanclaire.alantra.channel.rest.request.ServiceRequest;

/**
 * @author administrator
 *
 */
@Path("/service")
@Stateless
public class ServiceRESTService extends BaseEntityService<Service> 
{
	/**
	 * @param entityClass
	 */
	public ServiceRESTService() {
		super(Service.class);
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
            CriteriaBuilder criteriaBuilder, Root<Service> root) 
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
     *   Create a Service. Data is contained in the ServiceRequest object
     * </p>
     * @param ServiceRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createService(ServiceRequest request) {
        try 
        {
        	Service service = this.loadModelFromRequest(request);
        	entityManager.persist(service);
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
    public Response editService(ServiceRequest request) 
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
    private Service loadModelFromRequest(ServiceRequest request) 
    {
		Service service = new Service();
    	Integer serviceId = request.getId();
    	// Are we editing a Service
    	if(serviceId != null) 
    	{
    		service = getEntityManager().find(Service.class, request.getId());
    		service.setLastModifiedDt(request.getLastModifiedDt());
        	service.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	service.setCreatedDt(getCurrentDate());
        	service.setCreatedByUsr(getCurrentUserName(request));
    	}
    	service.setCode(request.getCode());
    	service.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getServiceType() != null)
    	{
    		ServiceType serviceType = entityManager.find(ServiceType.class, request.getServiceType());
    		service.setServiceType(serviceType);
    	}
    	if (request.getServiceProtocolAdapter() != null)
    	{
    		ServiceProtocolAdapter serviceProtocolAdapter = entityManager.find(ServiceProtocolAdapter.class, request.getServiceProtocolAdapter());
    		service.setServiceProtocolAdapter(serviceProtocolAdapter);
    	}
    	if (request.getServiceMode() != null)
    	{
    		ServiceMode serviceMode = entityManager.find(ServiceMode.class, request.getServiceMode());
    		service.setServiceMode(serviceMode);
    	}
    	service.setName(request.getName()); 
    	service.setDescription(request.getDescription()); 
    	service.setPortNo(request.getPortNo()); 
    	service.setIpAddress(request.getIpAddress()); 
    	service.setCode(request.getCode()); 
    	service.setEffectiveDt(request.getEffectiveDt()); 
    	service.setRecSt(request.getRecSt()); 
		return service;
	}
}
