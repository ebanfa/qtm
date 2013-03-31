/**
 * 
 */
package com.nathanclaire.alantra.rest;

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

import com.nathanclaire.alantra.model.businessdata.Currency;
import com.nathanclaire.alantra.rest.request.CurrencyRequest;

/**
 * @author administrator
 *
 */
@Path("/currency")
@Stateless
public class CurrencyService extends BaseEntityService<Currency> 
{
	/**
	 * @param entityClass
	 */
	public CurrencyService() {
		super(Currency.class);
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
            CriteriaBuilder criteriaBuilder, Root<Currency> root) 
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
     *   Create a Currency. Data is contained in the CurrencyRequest object
     * </p>
     * @param CurrencyRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCurrency(CurrencyRequest request) {
        try 
        {
        	Currency currency = this.loadModelFromRequest(request);
        	entityManager.persist(currency);
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
    public Response editCurrency(CurrencyRequest request) 
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
    private Currency loadModelFromRequest(CurrencyRequest request) 
    {
		Currency currency = new Currency();
    	Integer currencyId = request.getId();
    	// Are we editing a Currency
    	if(currencyId != null) 
    	{
    		currency = getEntityManager().find(Currency.class, request.getId());
    		currency.setLastModifiedDt(request.getLastModifiedDt());
        	currency.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	currency.setCreatedDt(getCurrentDate());
        	currency.setCreatedByUsr(getCurrentUserName(request));
    	}
    	currency.setCode(request.getCode());
    	currency.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	currency.setCode(request.getCode()); 
    	currency.setCrncyNm(request.getCrncyNm()); 
    	currency.setCrncySym(request.getCrncySym()); 
    	currency.setRemarks(request.getRemarks()); 
    	currency.setEffectiveDt(request.getEffectiveDt()); 
    	currency.setRecSt(request.getRecSt()); 
		return currency;
	}
}
