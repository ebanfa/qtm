/**
 * 
 */
package com.nathanclaire.alantra.party.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.party.model.Party;
import com.nathanclaire.alantra.party.rest.request.PartyRequest;

import com.nathanclaire.alantra.party.model.PartyType;

/**
 * @author administrator
 *
 */
@Stateless
public class PartyServiceImpl extends BaseEntityServiceImpl<Party, PartyRequest> implements PartyService
{
	/**
	 * @param entityClass
	 */
	public PartyServiceImpl() {
		super(Party.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.Party#findById(java.lang.Integer)
	 */
	@Override
	public Party findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.Party#findByCode(java.lang.String)
	 */
	@Override
	public Party findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.Party#findByName(java.lang.String)
	 */
	@Override
	public Party findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.Party#findAll(java.util.Map)
	 */
	@Override
	public List<Party> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.Party#createParty(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public Party createInstance(PartyRequest partyRequest) {
		return createInsance(partyRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.Party#deleteParty(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.Party#updateParty(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public Party updateInstance(PartyRequest partyRequest) {
		return updateInstance(partyRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected Party loadModelFromRequest(PartyRequest partyRequest) 
    {
		Party party = new Party();
    	Integer partyId = partyRequest.getId();
    	// Are we editing a Party
    	if(partyId != null) 
    	{
    		party = getEntityManager().find(Party.class, partyRequest.getId());
    		party.setLastModifiedDt(partyRequest.getLastModifiedDt());
        	party.setLastModifiedUsr(getCurrentUserName(partyRequest));
    	}
    	else
    	{
        	party.setCreatedDt(getCurrentSystemDate());
        	party.setCreatedByUsr(getCurrentUserName(partyRequest));
    	}
    	party.setCode(partyRequest.getCode());
    	party.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (partyRequest.getPartyType() != null)
    	{
    		PartyType partyType = getEntityManager().find(PartyType.class, partyRequest.getPartyType());
    		party.setPartyType(partyType);
    	}
    	party.setName(partyRequest.getName()); 
    	party.setDescription(partyRequest.getDescription()); 
    	party.setCode(partyRequest.getCode()); 
    	party.setEffectiveDt(partyRequest.getEffectiveDt()); 
    	party.setRecSt(partyRequest.getRecSt()); 
		return party;
	}
}
