/**
 * 
 */
package com.nathanclaire.alantra.party.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.party.model.CaseRoleType;
import com.nathanclaire.alantra.party.rest.request.CaseRoleTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class CaseRoleTypeServiceImpl extends BaseEntityServiceImpl<CaseRoleType, CaseRoleTypeRequest> implements CaseRoleTypeService
{
	/**
	 * @param entityClass
	 */
	public CaseRoleTypeServiceImpl() {
		super(CaseRoleType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.CaseRoleType#findById(java.lang.Integer)
	 */
	@Override
	public CaseRoleType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.CaseRoleType#findByCode(java.lang.String)
	 */
	@Override
	public CaseRoleType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.CaseRoleType#findByName(java.lang.String)
	 */
	@Override
	public CaseRoleType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.CaseRoleType#findAll(java.util.Map)
	 */
	@Override
	public List<CaseRoleType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.CaseRoleType#createCaseRoleType(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public CaseRoleType createInstance(CaseRoleTypeRequest caseRoleTypeRequest) {
		return createInsance(caseRoleTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.CaseRoleType#deleteCaseRoleType(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.CaseRoleType#updateCaseRoleType(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public CaseRoleType updateInstance(CaseRoleTypeRequest caseRoleTypeRequest) {
		return updateInstance(caseRoleTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected CaseRoleType loadModelFromRequest(CaseRoleTypeRequest caseRoleTypeRequest) 
    {
		CaseRoleType caseRoleType = new CaseRoleType();
    	Integer caseRoleTypeId = caseRoleTypeRequest.getId();
    	// Are we editing a CaseRoleType
    	if(caseRoleTypeId != null) 
    	{
    		caseRoleType = getEntityManager().find(CaseRoleType.class, caseRoleTypeRequest.getId());
    		caseRoleType.setLastModifiedDt(caseRoleTypeRequest.getLastModifiedDt());
        	caseRoleType.setLastModifiedUsr(getCurrentUserName(caseRoleTypeRequest));
    	}
    	else
    	{
        	caseRoleType.setCreatedDt(getCurrentSystemDate());
        	caseRoleType.setCreatedByUsr(getCurrentUserName(caseRoleTypeRequest));
    	}
    	caseRoleType.setCode(caseRoleTypeRequest.getCode());
    	caseRoleType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	caseRoleType.setName(caseRoleTypeRequest.getName()); 
    	caseRoleType.setDescription(caseRoleTypeRequest.getDescription()); 
    	caseRoleType.setCode(caseRoleTypeRequest.getCode()); 
    	caseRoleType.setEffectiveDt(caseRoleTypeRequest.getEffectiveDt()); 
    	caseRoleType.setRecSt(caseRoleTypeRequest.getRecSt()); 
		return caseRoleType;
	}
}
