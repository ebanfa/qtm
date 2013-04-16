/**
 * 
 */
package com.nathanclaire.alantra.party.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.party.model.PartyCase;
import com.nathanclaire.alantra.party.request.PartyCaseRequest;

import com.nathanclaire.alantra.party.model.CaseRole;
import com.nathanclaire.alantra.messaging.model.CommunicationEvent;
import com.nathanclaire.alantra.party.model.CaseStatusType;

/**
 * @author administrator
 *
 */
@Stateless
public class PartyCaseServiceImpl extends BaseEntityServiceImpl<PartyCase, PartyCaseRequest> implements PartyCaseService
{
	/**
	 * @param entityClass
	 */
	public PartyCaseServiceImpl() {
		super(PartyCase.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyCase#findById(java.lang.Integer)
	 */
	@Override
	public PartyCase findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyCase#findByCode(java.lang.String)
	 */
	@Override
	public PartyCase findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyCase#findByName(java.lang.String)
	 */
	@Override
	public PartyCase findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyCase#findAll(java.util.Map)
	 */
	@Override
	public List<PartyCase> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyCase#createPartyCase(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public PartyCase create(PartyCaseRequest partyCaseRequest) {
		return createInstance(partyCaseRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyCase#deletePartyCase(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyCase#updatePartyCase(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public PartyCase update(PartyCaseRequest partyCaseRequest) {
		return updateInstance(partyCaseRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected PartyCase loadModelFromRequest(PartyCaseRequest partyCaseRequest) 
    {
		PartyCase partyCase = new PartyCase();
    	Integer partyCaseId = partyCaseRequest.getId();
    	// Are we editing a PartyCase
    	if(partyCaseId != null) 
    	{
    		partyCase = getEntityManager().find(PartyCase.class, partyCaseRequest.getId());
    		partyCase.setLastModifiedDt(partyCaseRequest.getLastModifiedDt());
        	partyCase.setLastModifiedUsr(getCurrentUserName(partyCaseRequest));
    	}
    	else
    	{
        	partyCase.setCreatedDt(getCurrentSystemDate());
        	partyCase.setCreatedByUsr(getCurrentUserName(partyCaseRequest));
    	}
    	partyCase.setCode(partyCaseRequest.getCode());
    	partyCase.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (partyCaseRequest.getCaseRole() != null)
    	{
    		CaseRole caseRole = getEntityManager().find(CaseRole.class, partyCaseRequest.getCaseRole());
    		partyCase.setCaseRole(caseRole);
    	}
    	if (partyCaseRequest.getCommunicationEvent() != null)
    	{
    		CommunicationEvent communicationEvent = getEntityManager().find(CommunicationEvent.class, partyCaseRequest.getCommunicationEvent());
    		partyCase.setCommunicationEvent(communicationEvent);
    	}
    	if (partyCaseRequest.getCaseStatusType() != null)
    	{
    		CaseStatusType caseStatusType = getEntityManager().find(CaseStatusType.class, partyCaseRequest.getCaseStatusType());
    		partyCase.setCaseStatusType(caseStatusType);
    	}
    	partyCase.setName(partyCaseRequest.getName()); 
    	partyCase.setDescription(partyCaseRequest.getDescription()); 
    	partyCase.setCode(partyCaseRequest.getCode()); 
    	partyCase.setEffectiveDt(partyCaseRequest.getEffectiveDt()); 
    	partyCase.setRecSt(partyCaseRequest.getRecSt()); 
		return partyCase;
	}
}
