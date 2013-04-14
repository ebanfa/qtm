/**
 * 
 */
package com.nathanclaire.alantra.party.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.party.model.PartyRole;
import com.nathanclaire.alantra.party.rest.request.PartyRoleRequest;

import com.nathanclaire.alantra.party.model.PartyRoleType;
import com.nathanclaire.alantra.party.rest.request.PartyRoleTypeRequest;
import com.nathanclaire.alantra.party.model.Party;
import com.nathanclaire.alantra.party.rest.request.PartyRequest;

/**
 * @author administrator
 *
 */
@Stateless
public class PartyRoleServiceImpl extends BaseEntityServiceImpl<PartyRole> implements PartyRoleService
{
	/**
	 * @param entityClass
	 */
	public PartyRoleServiceImpl() {
		super(PartyRole.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRole#findById(java.lang.Integer)
	 */
	@Override
	public PartyRole findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRole#findByCode(java.lang.String)
	 */
	@Override
	public PartyRole findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRole#findByName(java.lang.String)
	 */
	@Override
	public PartyRole findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRole#findAll(java.util.Map)
	 */
	@Override
	public List<PartyRole> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRole#createPartyRole(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public PartyRole createInstance(BaseRequest partyRoleRequest) {
		return createInsance(partyRoleRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRole#deletePartyRole(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRole#updatePartyRole(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public PartyRole updateInstance(BaseRequest partyRoleRequest) {
		return updateInstance(partyRoleRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected PartyRole loadModelFromRequest(BaseRequest request) 
    {
    	PartyRoleRequest partyRoleRequest = (PartyRoleRequest) request;
		PartyRole partyRole = new PartyRole();
    	Integer partyRoleId = partyRoleRequest.getId();
    	// Are we editing a PartyRole
    	if(partyRoleId != null) 
    	{
    		partyRole = getEntityManager().find(PartyRole.class, partyRoleRequest.getId());
    		partyRole.setLastModifiedDt(partyRoleRequest.getLastModifiedDt());
        	partyRole.setLastModifiedUsr(getCurrentUserName(partyRoleRequest));
    	}
    	else
    	{
        	partyRole.setCreatedDt(getCurrentSystemDate());
        	partyRole.setCreatedByUsr(getCurrentUserName(partyRoleRequest));
    	}
    	partyRole.setCode(partyRoleRequest.getCode());
    	partyRole.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (partyRoleRequest.getPartyRoleType() != null)
    	{
    		PartyRoleType partyRoleType = getEntityManager().find(PartyRoleType.class, partyRoleRequest.getPartyRoleType());
    		partyRole.setPartyRoleType(partyRoleType);
    	}
    	if (partyRoleRequest.getParty() != null)
    	{
    		Party party = getEntityManager().find(Party.class, partyRoleRequest.getParty());
    		partyRole.setParty(party);
    	}
    	partyRole.setName(partyRoleRequest.getName()); 
    	partyRole.setDescription(partyRoleRequest.getDescription()); 
    	partyRole.setFromDt(partyRoleRequest.getFromDt()); 
    	partyRole.setThruDt(partyRoleRequest.getThruDt()); 
    	partyRole.setCode(partyRoleRequest.getCode()); 
    	partyRole.setEffectiveDt(partyRoleRequest.getEffectiveDt()); 
    	partyRole.setRecSt(partyRoleRequest.getRecSt()); 
		return partyRole;
	}
}
