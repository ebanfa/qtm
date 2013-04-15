/**
 * 
 */
package com.nathanclaire.alantra.party.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.party.model.PartyRelationship;
import com.nathanclaire.alantra.party.rest.request.PartyRelationshipRequest;

import com.nathanclaire.alantra.party.model.PartyRole;
import com.nathanclaire.alantra.party.model.PartyRole;
import com.nathanclaire.alantra.party.model.PartyRelationshipType;

/**
 * @author administrator
 *
 */
@Stateless
public class PartyRelationshipServiceImpl extends BaseEntityServiceImpl<PartyRelationship, PartyRelationshipRequest> implements PartyRelationshipService
{
	/**
	 * @param entityClass
	 */
	public PartyRelationshipServiceImpl() {
		super(PartyRelationship.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRelationship#findById(java.lang.Integer)
	 */
	@Override
	public PartyRelationship findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRelationship#findByCode(java.lang.String)
	 */
	@Override
	public PartyRelationship findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRelationship#findByName(java.lang.String)
	 */
	@Override
	public PartyRelationship findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRelationship#findAll(java.util.Map)
	 */
	@Override
	public List<PartyRelationship> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRelationship#createPartyRelationship(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public PartyRelationship createInstance(PartyRelationshipRequest partyRelationshipRequest) {
		return createInsance(partyRelationshipRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRelationship#deletePartyRelationship(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRelationship#updatePartyRelationship(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public PartyRelationship updateInstance(PartyRelationshipRequest partyRelationshipRequest) {
		return updateInstance(partyRelationshipRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected PartyRelationship loadModelFromRequest(PartyRelationshipRequest partyRelationshipRequest) 
    {
		PartyRelationship partyRelationship = new PartyRelationship();
    	Integer partyRelationshipId = partyRelationshipRequest.getId();
    	// Are we editing a PartyRelationship
    	if(partyRelationshipId != null) 
    	{
    		partyRelationship = getEntityManager().find(PartyRelationship.class, partyRelationshipRequest.getId());
    		partyRelationship.setLastModifiedDt(partyRelationshipRequest.getLastModifiedDt());
        	partyRelationship.setLastModifiedUsr(getCurrentUserName(partyRelationshipRequest));
    	}
    	else
    	{
        	partyRelationship.setCreatedDt(getCurrentSystemDate());
        	partyRelationship.setCreatedByUsr(getCurrentUserName(partyRelationshipRequest));
    	}
    	partyRelationship.setCode(partyRelationshipRequest.getCode());
    	partyRelationship.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (partyRelationshipRequest.getFromPartyRole() != null)
    	{
    		PartyRole fromPartyRole = getEntityManager().find(PartyRole.class, partyRelationshipRequest.getFromPartyRole());
    		partyRelationship.setFromPartyRole(fromPartyRole);
    	}
    	if (partyRelationshipRequest.getToPartyRole() != null)
    	{
    		PartyRole toPartyRole = getEntityManager().find(PartyRole.class, partyRelationshipRequest.getToPartyRole());
    		partyRelationship.setToPartyRole(toPartyRole);
    	}
    	if (partyRelationshipRequest.getPartyRelationshipType() != null)
    	{
    		PartyRelationshipType partyRelationshipType = getEntityManager().find(PartyRelationshipType.class, partyRelationshipRequest.getPartyRelationshipType());
    		partyRelationship.setPartyRelationshipType(partyRelationshipType);
    	}
    	partyRelationship.setName(partyRelationshipRequest.getName()); 
    	partyRelationship.setDescription(partyRelationshipRequest.getDescription()); 
    	partyRelationship.setComment(partyRelationshipRequest.getComment()); 
    	partyRelationship.setFromDt(partyRelationshipRequest.getFromDt()); 
    	partyRelationship.setThruDt(partyRelationshipRequest.getThruDt()); 
    	partyRelationship.setCode(partyRelationshipRequest.getCode()); 
    	partyRelationship.setEffectiveDt(partyRelationshipRequest.getEffectiveDt()); 
    	partyRelationship.setRecSt(partyRelationshipRequest.getRecSt()); 
		return partyRelationship;
	}
}
