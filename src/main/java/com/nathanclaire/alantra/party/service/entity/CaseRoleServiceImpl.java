/**
 * 
 */
package com.nathanclaire.alantra.party.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.party.model.CaseRole;
import com.nathanclaire.alantra.party.rest.request.CaseRoleRequest;

import com.nathanclaire.alantra.party.model.Party;
import com.nathanclaire.alantra.party.rest.request.PartyRequest;
import com.nathanclaire.alantra.party.model.CaseRoleType;
import com.nathanclaire.alantra.party.rest.request.CaseRoleTypeRequest;

/**
 * @author administrator
 *
 */
@Stateless
public class CaseRoleServiceImpl extends BaseEntityServiceImpl<CaseRole> implements CaseRoleService
{
	/**
	 * @param entityClass
	 */
	public CaseRoleServiceImpl() {
		super(CaseRole.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.CaseRole#findById(java.lang.Integer)
	 */
	@Override
	public CaseRole findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.CaseRole#findByCode(java.lang.String)
	 */
	@Override
	public CaseRole findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.CaseRole#findByName(java.lang.String)
	 */
	@Override
	public CaseRole findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.CaseRole#findAll(java.util.Map)
	 */
	@Override
	public List<CaseRole> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.CaseRole#createCaseRole(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public CaseRole createInstance(BaseRequest caseRoleRequest) {
		return createInsance(caseRoleRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.CaseRole#deleteCaseRole(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.CaseRole#updateCaseRole(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public CaseRole updateInstance(BaseRequest caseRoleRequest) {
		return updateInstance(caseRoleRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected CaseRole loadModelFromRequest(BaseRequest request) 
    {
    	CaseRoleRequest caseRoleRequest = (CaseRoleRequest) request;
		CaseRole caseRole = new CaseRole();
    	Integer caseRoleId = caseRoleRequest.getId();
    	// Are we editing a CaseRole
    	if(caseRoleId != null) 
    	{
    		caseRole = getEntityManager().find(CaseRole.class, caseRoleRequest.getId());
    		caseRole.setLastModifiedDt(caseRoleRequest.getLastModifiedDt());
        	caseRole.setLastModifiedUsr(getCurrentUserName(caseRoleRequest));
    	}
    	else
    	{
        	caseRole.setCreatedDt(getCurrentSystemDate());
        	caseRole.setCreatedByUsr(getCurrentUserName(caseRoleRequest));
    	}
    	caseRole.setCode(caseRoleRequest.getCode());
    	caseRole.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (caseRoleRequest.getParty() != null)
    	{
    		Party party = getEntityManager().find(Party.class, caseRoleRequest.getParty());
    		caseRole.setParty(party);
    	}
    	if (caseRoleRequest.getCaseRoleType() != null)
    	{
    		CaseRoleType caseRoleType = getEntityManager().find(CaseRoleType.class, caseRoleRequest.getCaseRoleType());
    		caseRole.setCaseRoleType(caseRoleType);
    	}
    	caseRole.setName(caseRoleRequest.getName()); 
    	caseRole.setDescription(caseRoleRequest.getDescription()); 
    	caseRole.setCode(caseRoleRequest.getCode()); 
    	caseRole.setEffectiveDt(caseRoleRequest.getEffectiveDt()); 
    	caseRole.setRecSt(caseRoleRequest.getRecSt()); 
		return caseRole;
	}
}
