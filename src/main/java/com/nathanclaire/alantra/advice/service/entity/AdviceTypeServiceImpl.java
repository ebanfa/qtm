/**
 * 
 */
package com.nathanclaire.alantra.advice.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.advice.model.AdviceType;
import com.nathanclaire.alantra.advice.rest.request.AdviceTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class AdviceTypeServiceImpl extends BaseEntityServiceImpl<AdviceType, AdviceTypeRequest> implements AdviceTypeService
{
	/**
	 * @param entityClass
	 */
	public AdviceTypeServiceImpl() {
		super(AdviceType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceType#findById(java.lang.Integer)
	 */
	@Override
	public AdviceType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceType#findByCode(java.lang.String)
	 */
	@Override
	public AdviceType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceType#findByName(java.lang.String)
	 */
	@Override
	public AdviceType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceType#findAll(java.util.Map)
	 */
	@Override
	public List<AdviceType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceType#createAdviceType(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public AdviceType createInstance(AdviceTypeRequest adviceTypeRequest) {
		return createInsance(adviceTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceType#deleteAdviceType(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceType#updateAdviceType(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public AdviceType updateInstance(AdviceTypeRequest adviceTypeRequest) {
		return updateInstance(adviceTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected AdviceType loadModelFromRequest(AdviceTypeRequest adviceTypeRequest) 
    {
		AdviceType adviceType = new AdviceType();
    	Integer adviceTypeId = adviceTypeRequest.getId();
    	// Are we editing a AdviceType
    	if(adviceTypeId != null) 
    	{
    		adviceType = getEntityManager().find(AdviceType.class, adviceTypeRequest.getId());
    		adviceType.setLastModifiedDt(adviceTypeRequest.getLastModifiedDt());
        	adviceType.setLastModifiedUsr(getCurrentUserName(adviceTypeRequest));
    	}
    	else
    	{
        	adviceType.setCreatedDt(getCurrentSystemDate());
        	adviceType.setCreatedByUsr(getCurrentUserName(adviceTypeRequest));
    	}
    	adviceType.setCode(adviceTypeRequest.getCode());
    	adviceType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	adviceType.setName(adviceTypeRequest.getName()); 
    	adviceType.setDescription(adviceTypeRequest.getDescription()); 
    	adviceType.setReqFeedback(adviceTypeRequest.getReqFeedback()); 
    	adviceType.setFeedbackMsg(adviceTypeRequest.getFeedbackMsg()); 
    	adviceType.setCode(adviceTypeRequest.getCode()); 
    	adviceType.setEffectiveDt(adviceTypeRequest.getEffectiveDt()); 
    	adviceType.setRecSt(adviceTypeRequest.getRecSt()); 
		return adviceType;
	}
}
