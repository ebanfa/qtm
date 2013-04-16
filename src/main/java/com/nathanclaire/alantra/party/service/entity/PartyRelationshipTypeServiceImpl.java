/**
 * 
 */
package com.nathanclaire.alantra.party.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.party.model.PartyRelationshipType;
import com.nathanclaire.alantra.party.request.PartyRelationshipTypeRequest;

import com.nathanclaire.alantra.party.model.PartyRoleType;
import com.nathanclaire.alantra.party.model.PartyRoleType;

/**
 * @author administrator
 *
 */
@Stateless
public class PartyRelationshipTypeServiceImpl extends BaseEntityServiceImpl<PartyRelationshipType, PartyRelationshipTypeRequest> implements PartyRelationshipTypeService
{
	/**
	 * @param entityClass
	 */
	public PartyRelationshipTypeServiceImpl() {
		super(PartyRelationshipType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRelationshipType#findById(java.lang.Integer)
	 */
	@Override
	public PartyRelationshipType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRelationshipType#findByCode(java.lang.String)
	 */
	@Override
	public PartyRelationshipType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRelationshipType#findByName(java.lang.String)
	 */
	@Override
	public PartyRelationshipType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRelationshipType#findAll(java.util.Map)
	 */
	@Override
	public List<PartyRelationshipType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRelationshipType#createPartyRelationshipType(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public PartyRelationshipType create(PartyRelationshipTypeRequest partyRelationshipTypeRequest) {
		return createInstance(partyRelationshipTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRelationshipType#deletePartyRelationshipType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.PartyRelationshipType#updatePartyRelationshipType(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public PartyRelationshipType update(PartyRelationshipTypeRequest partyRelationshipTypeRequest) {
		return updateInstance(partyRelationshipTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected PartyRelationshipType loadModelFromRequest(PartyRelationshipTypeRequest partyRelationshipTypeRequest) 
    {
		PartyRelationshipType partyRelationshipType = new PartyRelationshipType();
    	Integer partyRelationshipTypeId = partyRelationshipTypeRequest.getId();
    	// Are we editing a PartyRelationshipType
    	if(partyRelationshipTypeId != null) 
    	{
    		partyRelationshipType = getEntityManager().find(PartyRelationshipType.class, partyRelationshipTypeRequest.getId());
    		partyRelationshipType.setLastModifiedDt(partyRelationshipTypeRequest.getLastModifiedDt());
        	partyRelationshipType.setLastModifiedUsr(getCurrentUserName(partyRelationshipTypeRequest));
    	}
    	else
    	{
        	partyRelationshipType.setCreatedDt(getCurrentSystemDate());
        	partyRelationshipType.setCreatedByUsr(getCurrentUserName(partyRelationshipTypeRequest));
    	}
    	partyRelationshipType.setCode(partyRelationshipTypeRequest.getCode());
    	partyRelationshipType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (partyRelationshipTypeRequest.getPartyRoleTypeByFromRoleTyId() != null)
    	{
    		PartyRoleType partyRoleTypeByFromRoleTyId = getEntityManager().find(PartyRoleType.class, partyRelationshipTypeRequest.getPartyRoleTypeByFromRoleTyId());
    		partyRelationshipType.setPartyRoleTypeByFromRoleTyId(partyRoleTypeByFromRoleTyId);
    	}
    	if (partyRelationshipTypeRequest.getPartyRoleTypeByToRoleTyId() != null)
    	{
    		PartyRoleType partyRoleTypeByToRoleTyId = getEntityManager().find(PartyRoleType.class, partyRelationshipTypeRequest.getPartyRoleTypeByToRoleTyId());
    		partyRelationshipType.setPartyRoleTypeByToRoleTyId(partyRoleTypeByToRoleTyId);
    	}
    	partyRelationshipType.setName(partyRelationshipTypeRequest.getName()); 
    	partyRelationshipType.setDescription(partyRelationshipTypeRequest.getDescription()); 
    	partyRelationshipType.setCode(partyRelationshipTypeRequest.getCode()); 
    	partyRelationshipType.setEffectiveDt(partyRelationshipTypeRequest.getEffectiveDt()); 
    	partyRelationshipType.setRecSt(partyRelationshipTypeRequest.getRecSt()); 
		return partyRelationshipType;
	}
}
