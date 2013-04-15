/**
 * 
 */
package com.nathanclaire.alantra.businessdata.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.businessdata.model.StatusType;
import com.nathanclaire.alantra.businessdata.rest.request.StatusTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class StatusTypeServiceImpl extends BaseEntityServiceImpl<StatusType, StatusTypeRequest> implements StatusTypeService
{
	/**
	 * @param entityClass
	 */
	public StatusTypeServiceImpl() {
		super(StatusType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.StatusType#findById(java.lang.Integer)
	 */
	@Override
	public StatusType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.StatusType#findByCode(java.lang.String)
	 */
	@Override
	public StatusType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.StatusType#findByName(java.lang.String)
	 */
	@Override
	public StatusType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.StatusType#findAll(java.util.Map)
	 */
	@Override
	public List<StatusType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.StatusType#createStatusType(com.nathanclaire.alantra.businessdata.rest.request.ServiceRequest)
	 */
	@Override
	public StatusType createInstance(StatusTypeRequest statusTypeRequest) {
		return createInsance(statusTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.StatusType#deleteStatusType(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.StatusType#updateStatusType(com.nathanclaire.alantra.businessdata.rest.request.ServiceRequest)
	 */
	@Override
	public StatusType updateInstance(StatusTypeRequest statusTypeRequest) {
		return updateInstance(statusTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected StatusType loadModelFromRequest(StatusTypeRequest statusTypeRequest) 
    {
		StatusType statusType = new StatusType();
    	Integer statusTypeId = statusTypeRequest.getId();
    	// Are we editing a StatusType
    	if(statusTypeId != null) 
    	{
    		statusType = getEntityManager().find(StatusType.class, statusTypeRequest.getId());
    		statusType.setLastModifiedDt(statusTypeRequest.getLastModifiedDt());
        	statusType.setLastModifiedUsr(getCurrentUserName(statusTypeRequest));
    	}
    	else
    	{
        	statusType.setCreatedDt(getCurrentSystemDate());
        	statusType.setCreatedByUsr(getCurrentUserName(statusTypeRequest));
    	}
    	statusType.setCode(statusTypeRequest.getCode());
    	statusType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	statusType.setName(statusTypeRequest.getName()); 
    	statusType.setDescription(statusTypeRequest.getDescription()); 
    	statusType.setCode(statusTypeRequest.getCode()); 
    	statusType.setEffectiveDt(statusTypeRequest.getEffectiveDt()); 
    	statusType.setRecSt(statusTypeRequest.getRecSt()); 
		return statusType;
	}
}
