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
import com.nathanclaire.alantra.party.model.Organization;
import com.nathanclaire.alantra.party.model.Party;
import com.nathanclaire.alantra.party.rest.request.OrganizationRequest;
import com.nathanclaire.alantra.party.rest.request.PartyRequest;

/**
 * @author administrator
 *
 */
@Path("/organization")
@Stateless
public class OrganizationService extends BaseEntityService<Organization> 
{
	/**
	 * @param entityClass
	 */
	public OrganizationService() {
		super(Organization.class);
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
            CriteriaBuilder criteriaBuilder, Root<Organization> root) 
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
     *   Create a Organization. Data is contained in the OrganizationRequest object
     * </p>
     * @param OrganizationRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrganization(OrganizationRequest request) {
        try 
        {
        	Organization organization = this.loadModelFromRequest(request);
        	entityManager.persist(organization);
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
    public Response editOrganization(OrganizationRequest request) 
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
    private Organization loadModelFromRequest(OrganizationRequest request) 
    {
		Organization organization = new Organization();
    	Integer organizationId = request.getId();
    	// Are we editing a Organization
    	if(organizationId != null) 
    	{
    		organization = getEntityManager().find(Organization.class, request.getId());
    		organization.setLastModifiedDt(request.getLastModifiedDt());
        	organization.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	organization.setCreatedDt(getCurrentDate());
        	organization.setCreatedByUsr(getCurrentUserName(request));
    	}
    	organization.setCode(request.getCode());
    	organization.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getParty() != null)
    	{
    		Party party = entityManager.find(Party.class, request.getParty());
    		organization.setParty(party);
    	}
    	organization.setCode(request.getCode()); 
    	organization.setTaxIdNo(request.getTaxIdNo()); 
    	organization.setEffectiveDt(request.getEffectiveDt()); 
    	organization.setRecSt(request.getRecSt()); 
		return organization;
	}
}
