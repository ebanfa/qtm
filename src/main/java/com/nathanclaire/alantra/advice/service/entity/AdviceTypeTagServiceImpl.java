/**
 * 
 */
package com.nathanclaire.alantra.advice.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.advice.model.AdviceTypeTag;
import com.nathanclaire.alantra.advice.rest.request.AdviceTypeTagRequest;

import com.nathanclaire.alantra.advice.model.AdviceType;

/**
 * @author administrator
 *
 */
@Stateless
public class AdviceTypeTagServiceImpl extends BaseEntityServiceImpl<AdviceTypeTag, AdviceTypeTagRequest> implements AdviceTypeTagService
{
	/**
	 * @param entityClass
	 */
	public AdviceTypeTagServiceImpl() {
		super(AdviceTypeTag.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceTypeTag#findById(java.lang.Integer)
	 */
	@Override
	public AdviceTypeTag findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceTypeTag#findByCode(java.lang.String)
	 */
	@Override
	public AdviceTypeTag findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceTypeTag#findByName(java.lang.String)
	 */
	@Override
	public AdviceTypeTag findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceTypeTag#findAll(java.util.Map)
	 */
	@Override
	public List<AdviceTypeTag> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceTypeTag#createAdviceTypeTag(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public AdviceTypeTag createInstance(AdviceTypeTagRequest adviceTypeTagRequest) {
		return createInsance(adviceTypeTagRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceTypeTag#deleteAdviceTypeTag(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.AdviceTypeTag#updateAdviceTypeTag(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public AdviceTypeTag updateInstance(AdviceTypeTagRequest adviceTypeTagRequest) {
		return updateInstance(adviceTypeTagRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected AdviceTypeTag loadModelFromRequest(AdviceTypeTagRequest adviceTypeTagRequest) 
    {
		AdviceTypeTag adviceTypeTag = new AdviceTypeTag();
    	Integer adviceTypeTagId = adviceTypeTagRequest.getId();
    	// Are we editing a AdviceTypeTag
    	if(adviceTypeTagId != null) 
    	{
    		adviceTypeTag = getEntityManager().find(AdviceTypeTag.class, adviceTypeTagRequest.getId());
    		adviceTypeTag.setLastModifiedDt(adviceTypeTagRequest.getLastModifiedDt());
        	adviceTypeTag.setLastModifiedUsr(getCurrentUserName(adviceTypeTagRequest));
    	}
    	else
    	{
        	adviceTypeTag.setCreatedDt(getCurrentSystemDate());
        	adviceTypeTag.setCreatedByUsr(getCurrentUserName(adviceTypeTagRequest));
    	}
    	adviceTypeTag.setCode(adviceTypeTagRequest.getCode());
    	adviceTypeTag.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (adviceTypeTagRequest.getAdviceType() != null)
    	{
    		AdviceType adviceType = getEntityManager().find(AdviceType.class, adviceTypeTagRequest.getAdviceType());
    		adviceTypeTag.setAdviceType(adviceType);
    	}
    	adviceTypeTag.setName(adviceTypeTagRequest.getName()); 
    	adviceTypeTag.setDescription(adviceTypeTagRequest.getDescription()); 
    	adviceTypeTag.setAdviceTyTagVal(adviceTypeTagRequest.getAdviceTyTagVal()); 
    	adviceTypeTag.setIsRegexFg(adviceTypeTagRequest.getIsRegexFg()); 
    	adviceTypeTag.setCode(adviceTypeTagRequest.getCode()); 
    	adviceTypeTag.setEffectiveDt(adviceTypeTagRequest.getEffectiveDt()); 
    	adviceTypeTag.setRecSt(adviceTypeTagRequest.getRecSt()); 
		return adviceTypeTag;
	}
}
