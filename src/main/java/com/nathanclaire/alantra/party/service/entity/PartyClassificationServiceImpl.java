/**
 * 
 */
package com.nathanclaire.alantra.party.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.party.model.PartyClassification;
import com.nathanclaire.alantra.party.rest.request.PartyClassificationRequest;

import com.nathanclaire.alantra.party.model.PartyType;
import com.nathanclaire.alantra.party.model.PartyClassificationType;
import com.nathanclaire.alantra.party.model.Party;

/**
 * @author administrator
 *
 */
@Stateless
public class PartyClassificationServiceImpl extends BaseEntityServiceImpl<PartyClassification, PartyClassificationRequest> implements PartyClassificationService
{
	/**
	 * @param entityClass
	 */
	public PartyClassificationServiceImpl() {
		super(PartyClassification.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyClassification#findById(java.lang.Integer)
	 */
	@Override
	public PartyClassification findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyClassification#findByCode(java.lang.String)
	 */
	@Override
	public PartyClassification findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyClassification#findByName(java.lang.String)
	 */
	@Override
	public PartyClassification findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyClassification#findAll(java.util.Map)
	 */
	@Override
	public List<PartyClassification> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyClassification#createPartyClassification(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public PartyClassification createInstance(PartyClassificationRequest partyClassificationRequest) {
		return createInsance(partyClassificationRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyClassification#deletePartyClassification(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyClassification#updatePartyClassification(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public PartyClassification updateInstance(PartyClassificationRequest partyClassificationRequest) {
		return updateInstance(partyClassificationRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected PartyClassification loadModelFromRequest(PartyClassificationRequest partyClassificationRequest) 
    {
		PartyClassification partyClassification = new PartyClassification();
    	Integer partyClassificationId = partyClassificationRequest.getId();
    	// Are we editing a PartyClassification
    	if(partyClassificationId != null) 
    	{
    		partyClassification = getEntityManager().find(PartyClassification.class, partyClassificationRequest.getId());
    		partyClassification.setLastModifiedDt(partyClassificationRequest.getLastModifiedDt());
        	partyClassification.setLastModifiedUsr(getCurrentUserName(partyClassificationRequest));
    	}
    	else
    	{
        	partyClassification.setCreatedDt(getCurrentSystemDate());
        	partyClassification.setCreatedByUsr(getCurrentUserName(partyClassificationRequest));
    	}
    	partyClassification.setCode(partyClassificationRequest.getCode());
    	partyClassification.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (partyClassificationRequest.getPartyType() != null)
    	{
    		PartyType partyType = getEntityManager().find(PartyType.class, partyClassificationRequest.getPartyType());
    		partyClassification.setPartyType(partyType);
    	}
    	if (partyClassificationRequest.getPartyClassificationType() != null)
    	{
    		PartyClassificationType partyClassificationType = getEntityManager().find(PartyClassificationType.class, partyClassificationRequest.getPartyClassificationType());
    		partyClassification.setPartyClassificationType(partyClassificationType);
    	}
    	if (partyClassificationRequest.getParty() != null)
    	{
    		Party party = getEntityManager().find(Party.class, partyClassificationRequest.getParty());
    		partyClassification.setParty(party);
    	}
    	partyClassification.setName(partyClassificationRequest.getName()); 
    	partyClassification.setDescription(partyClassificationRequest.getDescription()); 
    	partyClassification.setFromDt(partyClassificationRequest.getFromDt()); 
    	partyClassification.setThruDt(partyClassificationRequest.getThruDt()); 
    	partyClassification.setCode(partyClassificationRequest.getCode()); 
    	partyClassification.setEffectiveDt(partyClassificationRequest.getEffectiveDt()); 
    	partyClassification.setRecSt(partyClassificationRequest.getRecSt()); 
		return partyClassification;
	}
}
