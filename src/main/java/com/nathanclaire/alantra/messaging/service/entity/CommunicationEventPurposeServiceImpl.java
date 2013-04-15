/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.messaging.model.CommunicationEventPurpose;
import com.nathanclaire.alantra.messaging.rest.request.CommunicationEventPurposeRequest;

import com.nathanclaire.alantra.messaging.model.CommunicationEventPurposeType;

/**
 * @author administrator
 *
 */
@Stateless
public class CommunicationEventPurposeServiceImpl extends BaseEntityServiceImpl<CommunicationEventPurpose, CommunicationEventPurposeRequest> implements CommunicationEventPurposeService
{
	/**
	 * @param entityClass
	 */
	public CommunicationEventPurposeServiceImpl() {
		super(CommunicationEventPurpose.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventPurpose#findById(java.lang.Integer)
	 */
	@Override
	public CommunicationEventPurpose findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventPurpose#findByCode(java.lang.String)
	 */
	@Override
	public CommunicationEventPurpose findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventPurpose#findByName(java.lang.String)
	 */
	@Override
	public CommunicationEventPurpose findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventPurpose#findAll(java.util.Map)
	 */
	@Override
	public List<CommunicationEventPurpose> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventPurpose#createCommunicationEventPurpose(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public CommunicationEventPurpose createInstance(CommunicationEventPurposeRequest communicationEventPurposeRequest) {
		return createInsance(communicationEventPurposeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventPurpose#deleteCommunicationEventPurpose(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventPurpose#updateCommunicationEventPurpose(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public CommunicationEventPurpose updateInstance(CommunicationEventPurposeRequest communicationEventPurposeRequest) {
		return updateInstance(communicationEventPurposeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected CommunicationEventPurpose loadModelFromRequest(CommunicationEventPurposeRequest communicationEventPurposeRequest) 
    {
		CommunicationEventPurpose communicationEventPurpose = new CommunicationEventPurpose();
    	Integer communicationEventPurposeId = communicationEventPurposeRequest.getId();
    	// Are we editing a CommunicationEventPurpose
    	if(communicationEventPurposeId != null) 
    	{
    		communicationEventPurpose = getEntityManager().find(CommunicationEventPurpose.class, communicationEventPurposeRequest.getId());
    		communicationEventPurpose.setLastModifiedDt(communicationEventPurposeRequest.getLastModifiedDt());
        	communicationEventPurpose.setLastModifiedUsr(getCurrentUserName(communicationEventPurposeRequest));
    	}
    	else
    	{
        	communicationEventPurpose.setCreatedDt(getCurrentSystemDate());
        	communicationEventPurpose.setCreatedByUsr(getCurrentUserName(communicationEventPurposeRequest));
    	}
    	communicationEventPurpose.setCode(communicationEventPurposeRequest.getCode());
    	communicationEventPurpose.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (communicationEventPurposeRequest.getCommunicationEventPurposeType() != null)
    	{
    		CommunicationEventPurposeType communicationEventPurposeType = getEntityManager().find(CommunicationEventPurposeType.class, communicationEventPurposeRequest.getCommunicationEventPurposeType());
    		communicationEventPurpose.setCommunicationEventPurposeType(communicationEventPurposeType);
    	}
    	communicationEventPurpose.setName(communicationEventPurposeRequest.getName()); 
    	communicationEventPurpose.setDescription(communicationEventPurposeRequest.getDescription()); 
    	communicationEventPurpose.setCode(communicationEventPurposeRequest.getCode()); 
    	communicationEventPurpose.setEffectiveDt(communicationEventPurposeRequest.getEffectiveDt()); 
    	communicationEventPurpose.setRecSt(communicationEventPurposeRequest.getRecSt()); 
		return communicationEventPurpose;
	}
}
