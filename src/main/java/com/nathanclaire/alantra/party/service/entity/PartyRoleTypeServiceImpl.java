/**
 * 
 */
package com.nathanclaire.alantra.party.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.party.model.PartyRoleType;
import com.nathanclaire.alantra.party.rest.request.PartyRoleTypeRequest;

import com.nathanclaire.alantra.party.model.RoleType;
import com.nathanclaire.alantra.party.rest.request.RoleTypeRequest;
import com.nathanclaire.alantra.party.model.PartyRoleType;
import com.nathanclaire.alantra.party.rest.request.PartyRoleTypeRequest;

/**
 * @author administrator
 *
 */
@Stateless
public class PartyRoleTypeServiceImpl extends BaseEntityServiceImpl<PartyRoleType> implements PartyRoleTypeService
{
	/**
	 * @param entityClass
	 */
	public PartyRoleTypeServiceImpl() {
		super(PartyRoleType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRoleType#findById(java.lang.Integer)
	 */
	@Override
	public PartyRoleType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRoleType#findByCode(java.lang.String)
	 */
	@Override
	public PartyRoleType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRoleType#findByName(java.lang.String)
	 */
	@Override
	public PartyRoleType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRoleType#findAll(java.util.Map)
	 */
	@Override
	public List<PartyRoleType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRoleType#createPartyRoleType(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public PartyRoleType createInstance(BaseRequest partyRoleTypeRequest) {
		return createInsance(partyRoleTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRoleType#deletePartyRoleType(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRoleType#updatePartyRoleType(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public PartyRoleType updateInstance(BaseRequest partyRoleTypeRequest) {
		return updateInstance(partyRoleTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected PartyRoleType loadModelFromRequest(BaseRequest request) 
    {
    	PartyRoleTypeRequest partyRoleTypeRequest = (PartyRoleTypeRequest) request;
		PartyRoleType partyRoleType = new PartyRoleType();
    	Integer partyRoleTypeId = partyRoleTypeRequest.getId();
    	// Are we editing a PartyRoleType
    	if(partyRoleTypeId != null) 
    	{
    		partyRoleType = getEntityManager().find(PartyRoleType.class, partyRoleTypeRequest.getId());
    		partyRoleType.setLastModifiedDt(partyRoleTypeRequest.getLastModifiedDt());
        	partyRoleType.setLastModifiedUsr(getCurrentUserName(partyRoleTypeRequest));
    	}
    	else
    	{
        	partyRoleType.setCreatedDt(getCurrentSystemDate());
        	partyRoleType.setCreatedByUsr(getCurrentUserName(partyRoleTypeRequest));
    	}
    	partyRoleType.setCode(partyRoleTypeRequest.getCode());
    	partyRoleType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (partyRoleTypeRequest.getRoleType() != null)
    	{
    		RoleType roleType = getEntityManager().find(RoleType.class, partyRoleTypeRequest.getRoleType());
    		partyRoleType.setRoleType(roleType);
    	}
    	if (partyRoleTypeRequest.getPartyRoleType() != null)
    	{
    		PartyRoleType parentPartyRoleType = getEntityManager().find(PartyRoleType.class, partyRoleTypeRequest.getPartyRoleType());
    		partyRoleType.setPartyRoleType(parentPartyRoleType);
    	}
    	partyRoleType.setName(partyRoleTypeRequest.getName()); 
    	partyRoleType.setDescription(partyRoleTypeRequest.getDescription()); 
    	partyRoleType.setCode(partyRoleTypeRequest.getCode()); 
    	partyRoleType.setEffectiveDt(partyRoleTypeRequest.getEffectiveDt()); 
    	partyRoleType.setRecSt(partyRoleTypeRequest.getRecSt()); 
		return partyRoleType;
	}
}
