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

import com.nathanclaire.alantra.base.rest.BaseEntityService;
import com.nathanclaire.alantra.base.rest.request.UomConversionRequest;
import com.nathanclaire.alantra.businessdata.model.UomConversion;

/**
 * @author administrator
 *
 */
@Path("/uomconversion")
@Stateless
public class UomConversionService extends BaseEntityService<UomConversion> 
{
	/**
	 * @param entityClass
	 */
	public UomConversionService() {
		super(UomConversion.class);
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
            CriteriaBuilder criteriaBuilder, Root<UomConversion> root) 
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
     *   Create a UomConversion. Data is contained in the UomConversionRequest object
     * </p>
     * @param UomConversionRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUomConversion(UomConversionRequest request) {
        try 
        {
        	UomConversion uomConversion = this.loadModelFromRequest(request);
        	entityManager.persist(uomConversion);
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
    public Response editUomConversion(UomConversionRequest request) 
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
    private UomConversion loadModelFromRequest(UomConversionRequest request) 
    {
		UomConversion uomConversion = new UomConversion();
    	Integer uomConversionId = request.getId();
    	// Are we editing a UomConversion
    	if(uomConversionId != null) 
    	{
    		uomConversion = getEntityManager().find(UomConversion.class, request.getId());
    		uomConversion.setLastModifiedDt(request.getLastModifiedDt());
        	uomConversion.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	uomConversion.setCreatedDt(getCurrentDate());
        	uomConversion.setCreatedByUsr(getCurrentUserName(request));
    	}
    	uomConversion.setCode(request.getCode());
    	uomConversion.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	uomConversion.setFromUomId(request.getFromUomId()); 
    	uomConversion.setToUomId(request.getToUomId()); 
    	uomConversion.setConversionFactor(request.getConversionFactor()); 
    	uomConversion.setCode(request.getCode()); 
    	uomConversion.setName(request.getName()); 
    	uomConversion.setDescription(request.getDescription()); 
    	uomConversion.setEffectiveDt(request.getEffectiveDt()); 
    	uomConversion.setRecSt(request.getRecSt()); 
		return uomConversion;
	}
}