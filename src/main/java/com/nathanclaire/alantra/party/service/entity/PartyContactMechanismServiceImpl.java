/**
 * 
 */
package com.nathanclaire.alantra.party.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.party.model.PartyContactMechanism;
import com.nathanclaire.alantra.party.rest.request.PartyContactMechanismRequest;

import com.nathanclaire.alantra.party.model.ContactMechanism;
import com.nathanclaire.alantra.party.model.Party;

/**
 * @author administrator
 *
 */
@Stateless
public class PartyContactMechanismServiceImpl extends BaseEntityServiceImpl<PartyContactMechanism, PartyContactMechanismRequest> implements PartyContactMechanismService
{
	/**
	 * @param entityClass
	 */
	public PartyContactMechanismServiceImpl() {
		super(PartyContactMechanism.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyContactMechanism#findById(java.lang.Integer)
	 */
	@Override
	public PartyContactMechanism findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyContactMechanism#findByCode(java.lang.String)
	 */
	@Override
	public PartyContactMechanism findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyContactMechanism#findByName(java.lang.String)
	 */
	@Override
	public PartyContactMechanism findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyContactMechanism#findAll(java.util.Map)
	 */
	@Override
	public List<PartyContactMechanism> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyContactMechanism#createPartyContactMechanism(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public PartyContactMechanism createInstance(PartyContactMechanismRequest partyContactMechanismRequest) {
		return createInsance(partyContactMechanismRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyContactMechanism#deletePartyContactMechanism(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyContactMechanism#updatePartyContactMechanism(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public PartyContactMechanism updateInstance(PartyContactMechanismRequest partyContactMechanismRequest) {
		return updateInstance(partyContactMechanismRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected PartyContactMechanism loadModelFromRequest(PartyContactMechanismRequest partyContactMechanismRequest) 
    {
		PartyContactMechanism partyContactMechanism = new PartyContactMechanism();
    	Integer partyContactMechanismId = partyContactMechanismRequest.getId();
    	// Are we editing a PartyContactMechanism
    	if(partyContactMechanismId != null) 
    	{
    		partyContactMechanism = getEntityManager().find(PartyContactMechanism.class, partyContactMechanismRequest.getId());
    		partyContactMechanism.setLastModifiedDt(partyContactMechanismRequest.getLastModifiedDt());
        	partyContactMechanism.setLastModifiedUsr(getCurrentUserName(partyContactMechanismRequest));
    	}
    	else
    	{
        	partyContactMechanism.setCreatedDt(getCurrentSystemDate());
        	partyContactMechanism.setCreatedByUsr(getCurrentUserName(partyContactMechanismRequest));
    	}
    	partyContactMechanism.setCode(partyContactMechanismRequest.getCode());
    	partyContactMechanism.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (partyContactMechanismRequest.getContactMechanism() != null)
    	{
    		ContactMechanism contactMechanism = getEntityManager().find(ContactMechanism.class, partyContactMechanismRequest.getContactMechanism());
    		partyContactMechanism.setContactMechanism(contactMechanism);
    	}
    	if (partyContactMechanismRequest.getParty() != null)
    	{
    		Party party = getEntityManager().find(Party.class, partyContactMechanismRequest.getParty());
    		partyContactMechanism.setParty(party);
    	}
    	partyContactMechanism.setFromDt(partyContactMechanismRequest.getFromDt()); 
    	partyContactMechanism.setToDt(partyContactMechanismRequest.getToDt()); 
    	partyContactMechanism.setDescription(partyContactMechanismRequest.getDescription()); 
    	partyContactMechanism.setNoSolicitationFg(partyContactMechanismRequest.getNoSolicitationFg()); 
    	partyContactMechanism.setExtension(partyContactMechanismRequest.getExtension()); 
    	partyContactMechanism.setRemarks(partyContactMechanismRequest.getRemarks()); 
    	partyContactMechanism.setCode(partyContactMechanismRequest.getCode()); 
    	partyContactMechanism.setEffectiveDt(partyContactMechanismRequest.getEffectiveDt()); 
    	partyContactMechanism.setRecSt(partyContactMechanismRequest.getRecSt()); 
		return partyContactMechanism;
	}
}
