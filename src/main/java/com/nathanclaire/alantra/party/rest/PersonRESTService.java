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
import com.nathanclaire.alantra.party.model.Person;
import com.nathanclaire.alantra.party.model.Party;
import com.nathanclaire.alantra.party.rest.request.PartyRequest;
import com.nathanclaire.alantra.party.rest.request.PersonRequest;

/**
 * @author administrator
 *
 */
@Path("/person")
@Stateless
public class PersonRESTService extends BaseEntityService<Person> 
{
	/**
	 * @param entityClass
	 */
	public PersonRESTService() {
		super(Person.class);
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
            CriteriaBuilder criteriaBuilder, Root<Person> root) 
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
     *   Create a Person. Data is contained in the PersonRequest object
     * </p>
     * @param PersonRequest
     * @return
     */
    @SuppressWarnings("unchecked")
    @POST
    /**
     * <p> Data is received in JSON format. For easy handling, it will be unmarshalled in the support
     * {@link BookingRequest} class.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPerson(PersonRequest request) {
        try 
        {
        	Person person = this.loadModelFromRequest(request);
        	entityManager.persist(person);
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
    public Response editPerson(PersonRequest request) 
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
    private Person loadModelFromRequest(PersonRequest request) 
    {
		Person person = new Person();
    	Integer personId = request.getId();
    	// Are we editing a Person
    	if(personId != null) 
    	{
    		person = getEntityManager().find(Person.class, request.getId());
    		person.setLastModifiedDt(request.getLastModifiedDt());
        	person.setLastModifiedUsr(getCurrentUserName(request));
    	}
    	else
    	{
        	person.setCreatedDt(getCurrentDate());
        	person.setCreatedByUsr(getCurrentUserName(request));
    	}
    	person.setCode(request.getCode());
    	person.setEffectiveDt(getCurrentDate());
    	//Process many to one relationships
    	if (request.getParty() != null)
    	{
    		Party party = entityManager.find(Party.class, request.getParty());
    		person.setParty(party);
    	}
    	person.setCurrentFNm(request.getCurrentFNm()); 
    	person.setCurrentLNm(request.getCurrentLNm()); 
    	person.setCurrentMNm(request.getCurrentMNm()); 
    	person.setCurrentSuffix(request.getCurrentSuffix()); 
    	person.setCurrentPtitle(request.getCurrentPtitle()); 
    	person.setCurrentNNm(request.getCurrentNNm()); 
    	person.setMotherMaidenNm(request.getMotherMaidenNm()); 
    	person.setGender(request.getGender()); 
    	person.setMaritalSt(request.getMaritalSt()); 
    	person.setSsNo(request.getSsNo()); 
    	person.setCurrentPpNo(request.getCurrentPpNo()); 
    	person.setCurrentPpExpDt(request.getCurrentPpExpDt()); 
    	person.setWeight(request.getWeight()); 
    	person.setHeight(request.getHeight()); 
    	person.setBirthDt(request.getBirthDt()); 
    	person.setTotYrWorkExp(request.getTotYrWorkExp()); 
    	person.setRemarks(request.getRemarks()); 
    	person.setCode(request.getCode()); 
    	person.setEffectiveDt(request.getEffectiveDt()); 
    	person.setRecSt(request.getRecSt()); 
		return person;
	}
}
