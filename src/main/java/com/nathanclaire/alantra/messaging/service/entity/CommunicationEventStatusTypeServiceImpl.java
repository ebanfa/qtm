/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.messaging.model.CommunicationEventStatusType;
import com.nathanclaire.alantra.messaging.request.CommunicationEventStatusTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class CommunicationEventStatusTypeServiceImpl extends BaseEntityServiceImpl<CommunicationEventStatusType, CommunicationEventStatusTypeRequest> implements CommunicationEventStatusTypeService
{
	/**
	 * @param entityClass
	 */
	public CommunicationEventStatusTypeServiceImpl() {
		super(CommunicationEventStatusType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventStatusType#findById(java.lang.Integer)
	 */
	@Override
	public CommunicationEventStatusType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventStatusType#findByCode(java.lang.String)
	 */
	@Override
	public CommunicationEventStatusType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventStatusType#findByName(java.lang.String)
	 */
	@Override
	public CommunicationEventStatusType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventStatusType#findAll(java.util.Map)
	 */
	@Override
	public List<CommunicationEventStatusType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventStatusType#createCommunicationEventStatusType(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public CommunicationEventStatusType create(CommunicationEventStatusTypeRequest communicationEventStatusTypeRequest) {
		return createInstance(communicationEventStatusTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventStatusType#deleteCommunicationEventStatusType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventStatusType#updateCommunicationEventStatusType(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public CommunicationEventStatusType update(CommunicationEventStatusTypeRequest communicationEventStatusTypeRequest) {
		return updateInstance(communicationEventStatusTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected CommunicationEventStatusType loadModelFromRequest(CommunicationEventStatusTypeRequest communicationEventStatusTypeRequest) 
    {
		CommunicationEventStatusType communicationEventStatusType = new CommunicationEventStatusType();
    	Integer communicationEventStatusTypeId = communicationEventStatusTypeRequest.getId();
    	// Are we editing a CommunicationEventStatusType
    	if(communicationEventStatusTypeId != null) 
    	{
    		communicationEventStatusType = getEntityManager().find(CommunicationEventStatusType.class, communicationEventStatusTypeRequest.getId());
    		communicationEventStatusType.setLastModifiedDt(communicationEventStatusTypeRequest.getLastModifiedDt());
        	communicationEventStatusType.setLastModifiedUsr(getCurrentUserName(communicationEventStatusTypeRequest));
    	}
    	else
    	{
        	communicationEventStatusType.setCreatedDt(getCurrentSystemDate());
        	communicationEventStatusType.setCreatedByUsr(getCurrentUserName(communicationEventStatusTypeRequest));
    	}
    	communicationEventStatusType.setCode(communicationEventStatusTypeRequest.getCode());
    	communicationEventStatusType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	communicationEventStatusType.setName(communicationEventStatusTypeRequest.getName()); 
    	communicationEventStatusType.setDescription(communicationEventStatusTypeRequest.getDescription()); 
    	communicationEventStatusType.setCode(communicationEventStatusTypeRequest.getCode()); 
    	communicationEventStatusType.setEffectiveDt(communicationEventStatusTypeRequest.getEffectiveDt()); 
    	communicationEventStatusType.setRecSt(communicationEventStatusTypeRequest.getRecSt()); 
		return communicationEventStatusType;
	}
}
