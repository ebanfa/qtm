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
import com.nathanclaire.alantra.channel.model.ServiceTransaction;
import com.nathanclaire.alantra.channel.model.Service;
import com.nathanclaire.alantra.channel.rest.request.ServiceRequest;
import com.nathanclaire.alantra.channel.model.ServiceTransactionType;
import com.nathanclaire.alantra.channel.rest.request.ServiceTransactionTypeRequest;
import com.nathanclaire.alantra.channel.rest.request.ServiceTransactionRequest;

/**
 * @author administrator
 *
 */
@Path("/servicetransaction")
@Stateless
public class ServiceTransactionRESTService extends BaseEntityRESTService<ServiceTransaction> 
{
	/**
	 * @param entityClass
	 */
	public ServiceTransactionRESTService() {
		super(ServiceTransaction.class);
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
            CriteriaBuilder criteriaBuilder, Root<ServiceTransaction> root) 
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
     *   Create a ServiceTransaction. Data is contained in the ServiceTransactionRequest object
     * </p>
     * @param ServiceTransactionRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createServiceTransaction(ServiceTransactionRequest request) {
        try 
        {
        	ServiceTransaction serviceTransaction = this.loadModelFromRequest(request);
        	entityManager.persist(serviceTransaction);
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
    public Response editServiceTransaction(ServiceTransactionRequest request) 
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
    private ServiceTransaction loadModelFromRequest(ServiceTransactionRequest request) 
    {
		ServiceTransaction serviceTransaction = new ServiceTransaction();
    	//Integer serviceTransactionId = request.getId();
    	/*// Are we editing a ServiceTransaction
    	if(serviceTransactionId != null) 
    	{
    		serviceTransaction = getEntityManager().find(ServiceTransaction.class, request.getId());
    		serviceTransaction.setLastModifiedDt(request.getLastModifiedDt());
        	serviceTransaction.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	serviceTransaction.setCreatedDt(getCurrentDate());
        	serviceTransaction.setCreatedByUsr(getCurrentUserName(request));
    	}
    	serviceTransaction.setCode(request.getCode());
    	serviceTransaction.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getService() != null)
    	{
    		Service service = entityManager.find(Service.class, request.getService());
    		serviceTransaction.setService(service);
    	}
    	if (request.getServiceTransactionType() != null)
    	{
    		ServiceTransactionType serviceTransactionType = entityManager.find(ServiceTransactionType.class, request.getServiceTransactionType());
    		serviceTransaction.setServiceTransactionType(serviceTransactionType);
    	}
    	serviceTransaction.setName(request.getName()); 
    	serviceTransaction.setAmount(request.getAmount()); 
    	serviceTransaction.setTxnDate(request.getTxnDate()); 
    	serviceTransaction.setAccountNo(request.getAccountNo()); 
    	serviceTransaction.setAccountNm(request.getAccountNm()); 
    	serviceTransaction.setDescription(request.getDescription()); 
    	serviceTransaction.setCode(request.getCode()); 
    	serviceTransaction.setEffectiveDt(request.getEffectiveDt()); 
    	serviceTransaction.setRecSt(request.getRecSt()); */
		return serviceTransaction;
	}
}
