/**
 * 
 */
package com.nathanclaire.alantra.party.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.party.model.Person;
import com.nathanclaire.alantra.party.rest.request.PersonRequest;

import com.nathanclaire.alantra.party.model.Party;
import com.nathanclaire.alantra.party.rest.request.PartyRequest;

/**
 * @author administrator
 *
 */
@Stateless
public class PersonServiceImpl extends BaseEntityServiceImpl<Person> implements PersonService
{
	/**
	 * @param entityClass
	 */
	public PersonServiceImpl() {
		super(Person.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.Person#findById(java.lang.Integer)
	 */
	@Override
	public Person findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.Person#findByCode(java.lang.String)
	 */
	@Override
	public Person findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.Person#findByName(java.lang.String)
	 */
	@Override
	public Person findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.Person#findAll(java.util.Map)
	 */
	@Override
	public List<Person> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.Person#createPerson(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public Person createInstance(BaseRequest personRequest) {
		return createInsance(personRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.Person#deletePerson(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.Person#updatePerson(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public Person updateInstance(BaseRequest personRequest) {
		return updateInstance(personRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected Person loadModelFromRequest(BaseRequest request) 
    {
    	PersonRequest personRequest = (PersonRequest) request;
		Person person = new Person();
    	Integer personId = personRequest.getId();
    	// Are we editing a Person
    	if(personId != null) 
    	{
    		person = getEntityManager().find(Person.class, personRequest.getId());
    		person.setLastModifiedDt(personRequest.getLastModifiedDt());
        	person.setLastModifiedUsr(getCurrentUserName(personRequest));
    	}
    	else
    	{
        	person.setCreatedDt(getCurrentSystemDate());
        	person.setCreatedByUsr(getCurrentUserName(personRequest));
    	}
    	person.setCode(personRequest.getCode());
    	person.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (personRequest.getParty() != null)
    	{
    		Party party = getEntityManager().find(Party.class, personRequest.getParty());
    		person.setParty(party);
    	}
    	person.setCurrentFNm(personRequest.getCurrentFNm()); 
    	person.setCurrentLNm(personRequest.getCurrentLNm()); 
    	person.setCurrentMNm(personRequest.getCurrentMNm()); 
    	person.setCurrentSuffix(personRequest.getCurrentSuffix()); 
    	person.setCurrentPtitle(personRequest.getCurrentPtitle()); 
    	person.setCurrentNNm(personRequest.getCurrentNNm()); 
    	person.setMotherMaidenNm(personRequest.getMotherMaidenNm()); 
    	person.setGender(personRequest.getGender()); 
    	person.setMaritalSt(personRequest.getMaritalSt()); 
    	person.setSsNo(personRequest.getSsNo()); 
    	person.setCurrentPpNo(personRequest.getCurrentPpNo()); 
    	person.setCurrentPpExpDt(personRequest.getCurrentPpExpDt()); 
    	person.setWeight(personRequest.getWeight()); 
    	person.setHeight(personRequest.getHeight()); 
    	person.setBirthDt(personRequest.getBirthDt()); 
    	person.setTotYrWorkExp(personRequest.getTotYrWorkExp()); 
    	person.setRemarks(personRequest.getRemarks()); 
    	person.setCode(personRequest.getCode()); 
    	person.setEffectiveDt(personRequest.getEffectiveDt()); 
    	person.setRecSt(personRequest.getRecSt()); 
		return person;
	}
}
