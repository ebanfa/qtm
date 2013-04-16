/**
 * 
 */
package com.nathanclaire.alantra.party.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.party.model.PartyClassificationType;
import com.nathanclaire.alantra.party.request.PartyClassificationTypeRequest;

import com.nathanclaire.alantra.party.model.PartyClassificationType;

/**
 * @author administrator
 *
 */
@Stateless
public class PartyClassificationTypeServiceImpl extends BaseEntityServiceImpl<PartyClassificationType, PartyClassificationTypeRequest> implements PartyClassificationTypeService
{
	/**
	 * @param entityClass
	 */
	public PartyClassificationTypeServiceImpl() {
		super(PartyClassificationType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyClassificationType#findById(java.lang.Integer)
	 */
	@Override
	public PartyClassificationType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyClassificationType#findByCode(java.lang.String)
	 */
	@Override
	public PartyClassificationType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyClassificationType#findByName(java.lang.String)
	 */
	@Override
	public PartyClassificationType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyClassificationType#findAll(java.util.Map)
	 */
	@Override
	public List<PartyClassificationType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyClassificationType#createPartyClassificationType(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public PartyClassificationType create(PartyClassificationTypeRequest partyClassificationTypeRequest) {
		return createInstance(partyClassificationTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyClassificationType#deletePartyClassificationType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyClassificationType#updatePartyClassificationType(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public PartyClassificationType update(PartyClassificationTypeRequest partyClassificationTypeRequest) {
		return updateInstance(partyClassificationTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected PartyClassificationType loadModelFromRequest(PartyClassificationTypeRequest partyClassificationTypeRequest) 
    {
		PartyClassificationType partyClassificationType = new PartyClassificationType();
    	Integer partyClassificationTypeId = partyClassificationTypeRequest.getId();
    	// Are we editing a PartyClassificationType
    	if(partyClassificationTypeId != null) 
    	{
    		partyClassificationType = getEntityManager().find(PartyClassificationType.class, partyClassificationTypeRequest.getId());
    		partyClassificationType.setLastModifiedDt(partyClassificationTypeRequest.getLastModifiedDt());
        	partyClassificationType.setLastModifiedUsr(getCurrentUserName(partyClassificationTypeRequest));
    	}
    	else
    	{
        	partyClassificationType.setCreatedDt(getCurrentSystemDate());
        	partyClassificationType.setCreatedByUsr(getCurrentUserName(partyClassificationTypeRequest));
    	}
    	partyClassificationType.setCode(partyClassificationTypeRequest.getCode());
    	partyClassificationType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (partyClassificationTypeRequest.getPartyClassificationType() != null)
    	{
    		PartyClassificationType parentPartyClassificationType = getEntityManager().find(PartyClassificationType.class, partyClassificationTypeRequest.getPartyClassificationType());
    		partyClassificationType.setPartyClassificationType(parentPartyClassificationType);
    	}
    	partyClassificationType.setName(partyClassificationTypeRequest.getName()); 
    	partyClassificationType.setDescription(partyClassificationTypeRequest.getDescription()); 
    	partyClassificationType.setCode(partyClassificationTypeRequest.getCode()); 
    	partyClassificationType.setEffectiveDt(partyClassificationTypeRequest.getEffectiveDt()); 
    	partyClassificationType.setRecSt(partyClassificationTypeRequest.getRecSt()); 
		return partyClassificationType;
	}
}
