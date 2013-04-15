/**
 * 
 */
package com.nathanclaire.alantra.advice.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.advice.model.AdviceStatus;
import com.nathanclaire.alantra.advice.rest.request.AdviceStatusRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class AdviceStatusServiceImpl extends BaseEntityServiceImpl<AdviceStatus, AdviceStatusRequest> implements AdviceStatusService
{
	/**
	 * @param entityClass
	 */
	public AdviceStatusServiceImpl() {
		super(AdviceStatus.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceStatus#findById(java.lang.Integer)
	 */
	@Override
	public AdviceStatus findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceStatus#findByCode(java.lang.String)
	 */
	@Override
	public AdviceStatus findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceStatus#findByName(java.lang.String)
	 */
	@Override
	public AdviceStatus findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceStatus#findAll(java.util.Map)
	 */
	@Override
	public List<AdviceStatus> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceStatus#createAdviceStatus(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public AdviceStatus createInstance(AdviceStatusRequest adviceStatusRequest) {
		return createInsance(adviceStatusRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceStatus#deleteAdviceStatus(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceStatus#updateAdviceStatus(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public AdviceStatus updateInstance(AdviceStatusRequest adviceStatusRequest) {
		return updateInstance(adviceStatusRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected AdviceStatus loadModelFromRequest(AdviceStatusRequest adviceStatusRequest) 
    {
		AdviceStatus adviceStatus = new AdviceStatus();
    	Integer adviceStatusId = adviceStatusRequest.getId();
    	// Are we editing a AdviceStatus
    	if(adviceStatusId != null) 
    	{
    		adviceStatus = getEntityManager().find(AdviceStatus.class, adviceStatusRequest.getId());
    		adviceStatus.setLastModifiedDt(adviceStatusRequest.getLastModifiedDt());
        	adviceStatus.setLastModifiedUsr(getCurrentUserName(adviceStatusRequest));
    	}
    	else
    	{
        	adviceStatus.setCreatedDt(getCurrentSystemDate());
        	adviceStatus.setCreatedByUsr(getCurrentUserName(adviceStatusRequest));
    	}
    	adviceStatus.setCode(adviceStatusRequest.getCode());
    	adviceStatus.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	adviceStatus.setName(adviceStatusRequest.getName()); 
    	adviceStatus.setDescription(adviceStatusRequest.getDescription()); 
    	adviceStatus.setCode(adviceStatusRequest.getCode()); 
    	adviceStatus.setEffectiveDt(adviceStatusRequest.getEffectiveDt()); 
    	adviceStatus.setRecSt(adviceStatusRequest.getRecSt()); 
		return adviceStatus;
	}
}
