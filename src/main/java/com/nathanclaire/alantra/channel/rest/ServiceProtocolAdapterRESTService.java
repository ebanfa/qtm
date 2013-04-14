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
import com.nathanclaire.alantra.channel.model.ServiceProtocolAdapter;
import com.nathanclaire.alantra.channel.rest.request.ServiceProtocolAdapterRequest;

/**
 * @author administrator
 *
 */
@Path("/serviceprotocoladapter")
@Stateless
public class ServiceProtocolAdapterRESTService extends BaseEntityRESTService<ServiceProtocolAdapter> 
{
	/**
	 * @param entityClass
	 */
	public ServiceProtocolAdapterRESTService() {
		super(ServiceProtocolAdapter.class);
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
            CriteriaBuilder criteriaBuilder, Root<ServiceProtocolAdapter> root) 
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
     *   Create a ServiceProtocolAdapter. Data is contained in the ServiceProtocolAdapterRequest object
     * </p>
     * @param ServiceProtocolAdapterRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createServiceProtocolAdapter(ServiceProtocolAdapterRequest request) {
        try 
        {
        	ServiceProtocolAdapter serviceProtocolAdapter = this.loadModelFromRequest(request);
        	entityManager.persist(serviceProtocolAdapter);
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
    public Response editServiceProtocolAdapter(ServiceProtocolAdapterRequest request) 
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
    private ServiceProtocolAdapter loadModelFromRequest(ServiceProtocolAdapterRequest request) 
    {
		ServiceProtocolAdapter serviceProtocolAdapter = new ServiceProtocolAdapter();
    	Integer serviceProtocolAdapterId = request.getId();
    	// Are we editing a ServiceProtocolAdapter
    	if(serviceProtocolAdapterId != null) 
    	{
    		serviceProtocolAdapter = getEntityManager().find(ServiceProtocolAdapter.class, request.getId());
    		serviceProtocolAdapter.setLastModifiedDt(request.getLastModifiedDt());
        	serviceProtocolAdapter.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	serviceProtocolAdapter.setCreatedDt(getCurrentDate());
        	serviceProtocolAdapter.setCreatedByUsr(getCurrentUserName(request));
    	}
    	serviceProtocolAdapter.setCode(request.getCode());
    	serviceProtocolAdapter.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	serviceProtocolAdapter.setName(request.getName()); 
    	serviceProtocolAdapter.setDescription(request.getDescription()); 
    	serviceProtocolAdapter.setClassName(request.getClassName()); 
    	serviceProtocolAdapter.setCode(request.getCode()); 
    	serviceProtocolAdapter.setEffectiveDt(request.getEffectiveDt()); 
    	serviceProtocolAdapter.setRecSt(request.getRecSt()); 
		return serviceProtocolAdapter;
	}
}
