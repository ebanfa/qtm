/**
 * 
 */
package com.nathanclaire.alantra.party.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.party.model.PartyType;
import com.nathanclaire.alantra.party.rest.request.PartyTypeRequest;

import com.nathanclaire.alantra.party.model.PartyType;

/**
 * @author administrator
 *
 */
@Stateless
public class PartyTypeServiceImpl extends BaseEntityServiceImpl<PartyType, PartyTypeRequest> implements PartyTypeService
{
	/**
	 * @param entityClass
	 */
	public PartyTypeServiceImpl() {
		super(PartyType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyType#findById(java.lang.Integer)
	 */
	@Override
	public PartyType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyType#findByCode(java.lang.String)
	 */
	@Override
	public PartyType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyType#findByName(java.lang.String)
	 */
	@Override
	public PartyType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyType#findAll(java.util.Map)
	 */
	@Override
	public List<PartyType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyType#createPartyType(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public PartyType createInstance(PartyTypeRequest partyTypeRequest) {
		return createInsance(partyTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyType#deletePartyType(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyType#updatePartyType(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public PartyType updateInstance(PartyTypeRequest partyTypeRequest) {
		return updateInstance(partyTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected PartyType loadModelFromRequest(PartyTypeRequest partyTypeRequest) 
    {
		PartyType partyType = new PartyType();
    	Integer partyTypeId = partyTypeRequest.getId();
    	// Are we editing a PartyType
    	if(partyTypeId != null) 
    	{
    		partyType = getEntityManager().find(PartyType.class, partyTypeRequest.getId());
    		partyType.setLastModifiedDt(partyTypeRequest.getLastModifiedDt());
        	partyType.setLastModifiedUsr(getCurrentUserName(partyTypeRequest));
    	}
    	else
    	{
        	partyType.setCreatedDt(getCurrentSystemDate());
        	partyType.setCreatedByUsr(getCurrentUserName(partyTypeRequest));
    	}
    	partyType.setCode(partyTypeRequest.getCode());
    	partyType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (partyTypeRequest.getPartyType() != null)
    	{
    		PartyType parentPartyType = getEntityManager().find(PartyType.class, partyTypeRequest.getPartyType());
    		partyType.setPartyType(parentPartyType);
    	}
    	partyType.setName(partyTypeRequest.getName()); 
    	partyType.setDescription(partyTypeRequest.getDescription()); 
    	partyType.setCode(partyTypeRequest.getCode()); 
    	partyType.setEffectiveDt(partyTypeRequest.getEffectiveDt()); 
    	partyType.setRecSt(partyTypeRequest.getRecSt()); 
		return partyType;
	}
}
