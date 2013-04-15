/**
 * 
 */
package com.nathanclaire.alantra.advice.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.advice.rest.request.AdviceRequest;

import com.nathanclaire.alantra.party.model.Party;
import com.nathanclaire.alantra.advice.model.AdviceStatus;
import com.nathanclaire.alantra.messaging.model.CommunicationEvent;
import com.nathanclaire.alantra.advice.model.AdviceType;

/**
 * @author administrator
 *
 */
@Stateless
public class AdviceServiceImpl extends BaseEntityServiceImpl<Advice, AdviceRequest> implements AdviceService
{
	/**
	 * @param entityClass
	 */
	public AdviceServiceImpl() {
		super(Advice.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.Advice#findById(java.lang.Integer)
	 */
	@Override
	public Advice findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.Advice#findByCode(java.lang.String)
	 */
	@Override
	public Advice findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.Advice#findByName(java.lang.String)
	 */
	@Override
	public Advice findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.Advice#findAll(java.util.Map)
	 */
	@Override
	public List<Advice> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.Advice#createAdvice(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public Advice createInstance(AdviceRequest adviceRequest) {
		return createInsance(adviceRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.Advice#deleteAdvice(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.Advice#updateAdvice(com.nathanclaire.alantra.advice.rest.request.ServiceRequest)
	 */
	@Override
	public Advice updateInstance(AdviceRequest adviceRequest) {
		return updateInstance(adviceRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected Advice loadModelFromRequest(AdviceRequest adviceRequest) 
    {
		Advice advice = new Advice();
    	Integer adviceId = adviceRequest.getId();
    	// Are we editing a Advice
    	if(adviceId != null) 
    	{
    		advice = getEntityManager().find(Advice.class, adviceRequest.getId());
    		advice.setLastModifiedDt(adviceRequest.getLastModifiedDt());
        	advice.setLastModifiedUsr(getCurrentUserName(adviceRequest));
    	}
    	else
    	{
        	advice.setCreatedDt(getCurrentSystemDate());
        	advice.setCreatedByUsr(getCurrentUserName(adviceRequest));
    	}
    	advice.setCode(adviceRequest.getCode());
    	advice.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (adviceRequest.getParty() != null)
    	{
    		Party party = getEntityManager().find(Party.class, adviceRequest.getParty());
    		advice.setParty(party);
    	}
    	if (adviceRequest.getAdviceStatus() != null)
    	{
    		AdviceStatus adviceStatus = getEntityManager().find(AdviceStatus.class, adviceRequest.getAdviceStatus());
    		advice.setAdviceStatus(adviceStatus);
    	}
    	if (adviceRequest.getCommunicationEvent() != null)
    	{
    		CommunicationEvent communicationEvent = getEntityManager().find(CommunicationEvent.class, adviceRequest.getCommunicationEvent());
    		advice.setCommunicationEvent(communicationEvent);
    	}
    	if (adviceRequest.getAdviceType() != null)
    	{
    		AdviceType adviceType = getEntityManager().find(AdviceType.class, adviceRequest.getAdviceType());
    		advice.setAdviceType(adviceType);
    	}
    	advice.setName(adviceRequest.getName()); 
    	advice.setDescription(adviceRequest.getDescription()); 
    	advice.setAdviceTxt(adviceRequest.getAdviceTxt()); 
    	advice.setAccountNo(adviceRequest.getAccountNo()); 
    	advice.setCode(adviceRequest.getCode()); 
    	advice.setEffectiveDt(adviceRequest.getEffectiveDt()); 
    	advice.setRecSt(adviceRequest.getRecSt()); 
		return advice;
	}
}
