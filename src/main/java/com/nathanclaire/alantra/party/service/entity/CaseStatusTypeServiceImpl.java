/**
 * 
 */
package com.nathanclaire.alantra.party.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.party.model.CaseStatusType;
import com.nathanclaire.alantra.party.rest.request.CaseStatusTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class CaseStatusTypeServiceImpl extends BaseEntityServiceImpl<CaseStatusType, CaseStatusTypeRequest> implements CaseStatusTypeService
{
	/**
	 * @param entityClass
	 */
	public CaseStatusTypeServiceImpl() {
		super(CaseStatusType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.CaseStatusType#findById(java.lang.Integer)
	 */
	@Override
	public CaseStatusType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.CaseStatusType#findByCode(java.lang.String)
	 */
	@Override
	public CaseStatusType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.CaseStatusType#findByName(java.lang.String)
	 */
	@Override
	public CaseStatusType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.CaseStatusType#findAll(java.util.Map)
	 */
	@Override
	public List<CaseStatusType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.CaseStatusType#createCaseStatusType(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public CaseStatusType createInstance(CaseStatusTypeRequest caseStatusTypeRequest) {
		return createInsance(caseStatusTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.CaseStatusType#deleteCaseStatusType(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.CaseStatusType#updateCaseStatusType(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public CaseStatusType updateInstance(CaseStatusTypeRequest caseStatusTypeRequest) {
		return updateInstance(caseStatusTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected CaseStatusType loadModelFromRequest(CaseStatusTypeRequest caseStatusTypeRequest) 
    {
		CaseStatusType caseStatusType = new CaseStatusType();
    	Integer caseStatusTypeId = caseStatusTypeRequest.getId();
    	// Are we editing a CaseStatusType
    	if(caseStatusTypeId != null) 
    	{
    		caseStatusType = getEntityManager().find(CaseStatusType.class, caseStatusTypeRequest.getId());
    		caseStatusType.setLastModifiedDt(caseStatusTypeRequest.getLastModifiedDt());
        	caseStatusType.setLastModifiedUsr(getCurrentUserName(caseStatusTypeRequest));
    	}
    	else
    	{
        	caseStatusType.setCreatedDt(getCurrentSystemDate());
        	caseStatusType.setCreatedByUsr(getCurrentUserName(caseStatusTypeRequest));
    	}
    	caseStatusType.setCode(caseStatusTypeRequest.getCode());
    	caseStatusType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	caseStatusType.setName(caseStatusTypeRequest.getName()); 
    	caseStatusType.setDescription(caseStatusTypeRequest.getDescription()); 
    	caseStatusType.setCode(caseStatusTypeRequest.getCode()); 
    	caseStatusType.setEffectiveDt(caseStatusTypeRequest.getEffectiveDt()); 
    	caseStatusType.setRecSt(caseStatusTypeRequest.getRecSt()); 
		return caseStatusType;
	}
}
