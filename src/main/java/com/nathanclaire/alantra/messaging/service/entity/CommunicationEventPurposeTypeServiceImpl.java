/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.messaging.model.CommunicationEventPurposeType;
import com.nathanclaire.alantra.messaging.rest.request.CommunicationEventPurposeTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class CommunicationEventPurposeTypeServiceImpl extends BaseEntityServiceImpl<CommunicationEventPurposeType> implements CommunicationEventPurposeTypeService
{
	/**
	 * @param entityClass
	 */
	public CommunicationEventPurposeTypeServiceImpl() {
		super(CommunicationEventPurposeType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventPurposeType#findById(java.lang.Integer)
	 */
	@Override
	public CommunicationEventPurposeType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventPurposeType#findByCode(java.lang.String)
	 */
	@Override
	public CommunicationEventPurposeType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventPurposeType#findByName(java.lang.String)
	 */
	@Override
	public CommunicationEventPurposeType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventPurposeType#findAll(java.util.Map)
	 */
	@Override
	public List<CommunicationEventPurposeType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventPurposeType#createCommunicationEventPurposeType(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public CommunicationEventPurposeType createInstance(BaseRequest communicationEventPurposeTypeRequest) {
		return createInsance(communicationEventPurposeTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventPurposeType#deleteCommunicationEventPurposeType(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.CommunicationEventPurposeType#updateCommunicationEventPurposeType(com.nathanclaire.alantra.messaging.rest.request.ServiceRequest)
	 */
	@Override
	public CommunicationEventPurposeType updateInstance(BaseRequest communicationEventPurposeTypeRequest) {
		return updateInstance(communicationEventPurposeTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected CommunicationEventPurposeType loadModelFromRequest(BaseRequest request) 
    {
    	CommunicationEventPurposeTypeRequest communicationEventPurposeTypeRequest = (CommunicationEventPurposeTypeRequest) request;
		CommunicationEventPurposeType communicationEventPurposeType = new CommunicationEventPurposeType();
    	Integer communicationEventPurposeTypeId = communicationEventPurposeTypeRequest.getId();
    	// Are we editing a CommunicationEventPurposeType
    	if(communicationEventPurposeTypeId != null) 
    	{
    		communicationEventPurposeType = getEntityManager().find(CommunicationEventPurposeType.class, communicationEventPurposeTypeRequest.getId());
    		communicationEventPurposeType.setLastModifiedDt(communicationEventPurposeTypeRequest.getLastModifiedDt());
        	communicationEventPurposeType.setLastModifiedUsr(getCurrentUserName(communicationEventPurposeTypeRequest));
    	}
    	else
    	{
        	communicationEventPurposeType.setCreatedDt(getCurrentSystemDate());
        	communicationEventPurposeType.setCreatedByUsr(getCurrentUserName(communicationEventPurposeTypeRequest));
    	}
    	communicationEventPurposeType.setCode(communicationEventPurposeTypeRequest.getCode());
    	communicationEventPurposeType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	communicationEventPurposeType.setName(communicationEventPurposeTypeRequest.getName()); 
    	communicationEventPurposeType.setDescription(communicationEventPurposeTypeRequest.getDescription()); 
    	communicationEventPurposeType.setCode(communicationEventPurposeTypeRequest.getCode()); 
    	communicationEventPurposeType.setEffectiveDt(communicationEventPurposeTypeRequest.getEffectiveDt()); 
    	communicationEventPurposeType.setRecSt(communicationEventPurposeTypeRequest.getRecSt()); 
		return communicationEventPurposeType;
	}
}
