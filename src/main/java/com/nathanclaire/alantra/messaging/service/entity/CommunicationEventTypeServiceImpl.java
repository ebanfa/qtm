/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.messaging.model.CommunicationEventType;
import com.nathanclaire.alantra.messaging.rest.request.CommunicationEventTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class CommunicationEventTypeServiceImpl extends BaseEntityServiceImpl<CommunicationEventType, CommunicationEventTypeRequest> implements CommunicationEventTypeService
{
	/**
	 * @param entityClass
	 */
	public CommunicationEventTypeServiceImpl() {
		super(CommunicationEventType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventType#findById(java.lang.Integer)
	 */
	@Override
	public CommunicationEventType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventType#findByCode(java.lang.String)
	 */
	@Override
	public CommunicationEventType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventType#findByName(java.lang.String)
	 */
	@Override
	public CommunicationEventType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventType#findAll(java.util.Map)
	 */
	@Override
	public List<CommunicationEventType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventType#createCommunicationEventType(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public CommunicationEventType createInstance(CommunicationEventTypeRequest communicationEventTypeRequest) {
		return createInsance(communicationEventTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventType#deleteCommunicationEventType(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventType#updateCommunicationEventType(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public CommunicationEventType updateInstance(CommunicationEventTypeRequest communicationEventTypeRequest) {
		return updateInstance(communicationEventTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected CommunicationEventType loadModelFromRequest(CommunicationEventTypeRequest communicationEventTypeRequest) 
    {
		CommunicationEventType communicationEventType = new CommunicationEventType();
    	Integer communicationEventTypeId = communicationEventTypeRequest.getId();
    	// Are we editing a CommunicationEventType
    	if(communicationEventTypeId != null) 
    	{
    		communicationEventType = getEntityManager().find(CommunicationEventType.class, communicationEventTypeRequest.getId());
    		communicationEventType.setLastModifiedDt(communicationEventTypeRequest.getLastModifiedDt());
        	communicationEventType.setLastModifiedUsr(getCurrentUserName(communicationEventTypeRequest));
    	}
    	else
    	{
        	communicationEventType.setCreatedDt(getCurrentSystemDate());
        	communicationEventType.setCreatedByUsr(getCurrentUserName(communicationEventTypeRequest));
    	}
    	communicationEventType.setCode(communicationEventTypeRequest.getCode());
    	communicationEventType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	communicationEventType.setName(communicationEventTypeRequest.getName()); 
    	communicationEventType.setDescription(communicationEventTypeRequest.getDescription()); 
    	communicationEventType.setCode(communicationEventTypeRequest.getCode()); 
    	communicationEventType.setEffectiveDt(communicationEventTypeRequest.getEffectiveDt()); 
    	communicationEventType.setRecSt(communicationEventTypeRequest.getRecSt()); 
		return communicationEventType;
	}
}
