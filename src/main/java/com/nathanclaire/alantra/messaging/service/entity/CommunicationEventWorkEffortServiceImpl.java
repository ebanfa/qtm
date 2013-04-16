/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.messaging.model.CommunicationEventWorkEffort;
import com.nathanclaire.alantra.messaging.request.CommunicationEventWorkEffortRequest;

import com.nathanclaire.alantra.workeffort.model.WorkEffort;
import com.nathanclaire.alantra.messaging.model.CommunicationEvent;

/**
 * @author administrator
 *
 */
@Stateless
public class CommunicationEventWorkEffortServiceImpl extends BaseEntityServiceImpl<CommunicationEventWorkEffort, CommunicationEventWorkEffortRequest> implements CommunicationEventWorkEffortService
{
	/**
	 * @param entityClass
	 */
	public CommunicationEventWorkEffortServiceImpl() {
		super(CommunicationEventWorkEffort.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventWorkEffort#findById(java.lang.Integer)
	 */
	@Override
	public CommunicationEventWorkEffort findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventWorkEffort#findByCode(java.lang.String)
	 */
	@Override
	public CommunicationEventWorkEffort findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventWorkEffort#findByName(java.lang.String)
	 */
	@Override
	public CommunicationEventWorkEffort findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventWorkEffort#findAll(java.util.Map)
	 */
	@Override
	public List<CommunicationEventWorkEffort> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventWorkEffort#createCommunicationEventWorkEffort(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public CommunicationEventWorkEffort create(CommunicationEventWorkEffortRequest communicationEventWorkEffortRequest) {
		return createInstance(communicationEventWorkEffortRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventWorkEffort#deleteCommunicationEventWorkEffort(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventWorkEffort#updateCommunicationEventWorkEffort(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public CommunicationEventWorkEffort update(CommunicationEventWorkEffortRequest communicationEventWorkEffortRequest) {
		return updateInstance(communicationEventWorkEffortRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected CommunicationEventWorkEffort loadModelFromRequest(CommunicationEventWorkEffortRequest communicationEventWorkEffortRequest) 
    {
		CommunicationEventWorkEffort communicationEventWorkEffort = new CommunicationEventWorkEffort();
    	Integer communicationEventWorkEffortId = communicationEventWorkEffortRequest.getId();
    	// Are we editing a CommunicationEventWorkEffort
    	if(communicationEventWorkEffortId != null) 
    	{
    		communicationEventWorkEffort = getEntityManager().find(CommunicationEventWorkEffort.class, communicationEventWorkEffortRequest.getId());
    		communicationEventWorkEffort.setLastModifiedDt(communicationEventWorkEffortRequest.getLastModifiedDt());
        	communicationEventWorkEffort.setLastModifiedUsr(getCurrentUserName(communicationEventWorkEffortRequest));
    	}
    	else
    	{
        	communicationEventWorkEffort.setCreatedDt(getCurrentSystemDate());
        	communicationEventWorkEffort.setCreatedByUsr(getCurrentUserName(communicationEventWorkEffortRequest));
    	}
    	communicationEventWorkEffort.setCode(communicationEventWorkEffortRequest.getCode());
    	communicationEventWorkEffort.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (communicationEventWorkEffortRequest.getWorkEffort() != null)
    	{
    		WorkEffort workEffort = getEntityManager().find(WorkEffort.class, communicationEventWorkEffortRequest.getWorkEffort());
    		communicationEventWorkEffort.setWorkEffort(workEffort);
    	}
    	if (communicationEventWorkEffortRequest.getCommunicationEvent() != null)
    	{
    		CommunicationEvent communicationEvent = getEntityManager().find(CommunicationEvent.class, communicationEventWorkEffortRequest.getCommunicationEvent());
    		communicationEventWorkEffort.setCommunicationEvent(communicationEvent);
    	}
    	communicationEventWorkEffort.setDescription(communicationEventWorkEffortRequest.getDescription()); 
    	communicationEventWorkEffort.setCode(communicationEventWorkEffortRequest.getCode()); 
    	communicationEventWorkEffort.setEffectiveDt(communicationEventWorkEffortRequest.getEffectiveDt()); 
    	communicationEventWorkEffort.setRecSt(communicationEventWorkEffortRequest.getRecSt()); 
		return communicationEventWorkEffort;
	}
}
