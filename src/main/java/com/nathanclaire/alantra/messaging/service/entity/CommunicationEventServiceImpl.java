/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.messaging.model.CommunicationEvent;
import com.nathanclaire.alantra.messaging.rest.request.CommunicationEventRequest;

import com.nathanclaire.alantra.messaging.model.CommunicationEventType;
import com.nathanclaire.alantra.messaging.rest.request.CommunicationEventTypeRequest;
import com.nathanclaire.alantra.messaging.model.CommunicationEventPurpose;
import com.nathanclaire.alantra.messaging.rest.request.CommunicationEventPurposeRequest;
import com.nathanclaire.alantra.party.model.ContactMechanismType;
import com.nathanclaire.alantra.party.rest.request.ContactMechanismTypeRequest;
import com.nathanclaire.alantra.party.model.PartyRelationship;
import com.nathanclaire.alantra.party.rest.request.PartyRelationshipRequest;
import com.nathanclaire.alantra.messaging.model.CommunicationEventStatusType;
import com.nathanclaire.alantra.messaging.rest.request.CommunicationEventStatusTypeRequest;

/**
 * @author administrator
 *
 */
@Stateless
public class CommunicationEventServiceImpl extends BaseEntityServiceImpl<CommunicationEvent> implements CommunicationEventService
{
	/**
	 * @param entityClass
	 */
	public CommunicationEventServiceImpl() {
		super(CommunicationEvent.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEvent#findById(java.lang.Integer)
	 */
	@Override
	public CommunicationEvent findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEvent#findByCode(java.lang.String)
	 */
	@Override
	public CommunicationEvent findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEvent#findByName(java.lang.String)
	 */
	@Override
	public CommunicationEvent findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEvent#findAll(java.util.Map)
	 */
	@Override
	public List<CommunicationEvent> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEvent#createCommunicationEvent(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public CommunicationEvent createInstance(BaseRequest communicationEventRequest) {
		return createInsance(communicationEventRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEvent#deleteCommunicationEvent(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEvent#updateCommunicationEvent(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public CommunicationEvent updateInstance(BaseRequest communicationEventRequest) {
		return updateInstance(communicationEventRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected CommunicationEvent loadModelFromRequest(BaseRequest request) 
    {
    	CommunicationEventRequest communicationEventRequest = (CommunicationEventRequest) request;
		CommunicationEvent communicationEvent = new CommunicationEvent();
    	Integer communicationEventId = communicationEventRequest.getId();
    	// Are we editing a CommunicationEvent
    	if(communicationEventId != null) 
    	{
    		communicationEvent = getEntityManager().find(CommunicationEvent.class, communicationEventRequest.getId());
    		communicationEvent.setLastModifiedDt(communicationEventRequest.getLastModifiedDt());
        	communicationEvent.setLastModifiedUsr(getCurrentUserName(communicationEventRequest));
    	}
    	else
    	{
        	communicationEvent.setCreatedDt(getCurrentSystemDate());
        	communicationEvent.setCreatedByUsr(getCurrentUserName(communicationEventRequest));
    	}
    	communicationEvent.setCode(communicationEventRequest.getCode());
    	communicationEvent.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (communicationEventRequest.getCommunicationEventType() != null)
    	{
    		CommunicationEventType communicationEventType = getEntityManager().find(CommunicationEventType.class, communicationEventRequest.getCommunicationEventType());
    		communicationEvent.setCommunicationEventType(communicationEventType);
    	}
    	if (communicationEventRequest.getCommunicationEventPurpose() != null)
    	{
    		CommunicationEventPurpose communicationEventPurpose = getEntityManager().find(CommunicationEventPurpose.class, communicationEventRequest.getCommunicationEventPurpose());
    		communicationEvent.setCommunicationEventPurpose(communicationEventPurpose);
    	}
    	if (communicationEventRequest.getContactMechanismType() != null)
    	{
    		ContactMechanismType contactMechanismType = getEntityManager().find(ContactMechanismType.class, communicationEventRequest.getContactMechanismType());
    		communicationEvent.setContactMechanismType(contactMechanismType);
    	}
    	if (communicationEventRequest.getPartyRelationship() != null)
    	{
    		PartyRelationship partyRelationship = getEntityManager().find(PartyRelationship.class, communicationEventRequest.getPartyRelationship());
    		communicationEvent.setPartyRelationship(partyRelationship);
    	}
    	if (communicationEventRequest.getCommunicationEventStatusType() != null)
    	{
    		CommunicationEventStatusType communicationEventStatusType = getEntityManager().find(CommunicationEventStatusType.class, communicationEventRequest.getCommunicationEventStatusType());
    		communicationEvent.setCommunicationEventStatusType(communicationEventStatusType);
    	}
    	communicationEvent.setDescription(communicationEventRequest.getDescription()); 
    	communicationEvent.setFromEmail(communicationEventRequest.getFromEmail()); 
    	communicationEvent.setToEmail(communicationEventRequest.getToEmail()); 
    	communicationEvent.setFromPhone(communicationEventRequest.getFromPhone()); 
    	communicationEvent.setToPhone(communicationEventRequest.getToPhone()); 
    	communicationEvent.setSubject(communicationEventRequest.getSubject()); 
    	communicationEvent.setContent(communicationEventRequest.getContent()); 
    	communicationEvent.setStartDate(communicationEventRequest.getStartDate()); 
    	communicationEvent.setEndDate(communicationEventRequest.getEndDate()); 
    	communicationEvent.setCode(communicationEventRequest.getCode()); 
    	communicationEvent.setEffectiveDt(communicationEventRequest.getEffectiveDt()); 
    	communicationEvent.setRecSt(communicationEventRequest.getRecSt()); 
		return communicationEvent;
	}
}
