/**
 * 
 */
package com.nathanclaire.alantra.workeffort.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.workeffort.model.WorkEffortType;
import com.nathanclaire.alantra.workeffort.rest.request.WorkEffortTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class WorkEffortTypeServiceImpl extends BaseEntityServiceImpl<WorkEffortType, WorkEffortTypeRequest> implements WorkEffortTypeService
{
	/**
	 * @param entityClass
	 */
	public WorkEffortTypeServiceImpl() {
		super(WorkEffortType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.workeffort.service.WorkEffortType#findById(java.lang.Integer)
	 */
	@Override
	public WorkEffortType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.workeffort.service.WorkEffortType#findByCode(java.lang.String)
	 */
	@Override
	public WorkEffortType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.workeffort.service.WorkEffortType#findByName(java.lang.String)
	 */
	@Override
	public WorkEffortType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.workeffort.service.WorkEffortType#findAll(java.util.Map)
	 */
	@Override
	public List<WorkEffortType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.workeffort.service.WorkEffortType#createWorkEffortType(com.nathanclaire.alantra.workeffort.rest.request.ServiceRequest)
	 */
	@Override
	public WorkEffortType createInstance(WorkEffortTypeRequest workEffortTypeRequest) {
		return createInsance(workEffortTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.workeffort.service.WorkEffortType#deleteWorkEffortType(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.workeffort.service.WorkEffortType#updateWorkEffortType(com.nathanclaire.alantra.workeffort.rest.request.ServiceRequest)
	 */
	@Override
	public WorkEffortType updateInstance(WorkEffortTypeRequest workEffortTypeRequest) {
		return updateInstance(workEffortTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected WorkEffortType loadModelFromRequest(WorkEffortTypeRequest workEffortTypeRequest) 
    {
		WorkEffortType workEffortType = new WorkEffortType();
    	Integer workEffortTypeId = workEffortTypeRequest.getId();
    	// Are we editing a WorkEffortType
    	if(workEffortTypeId != null) 
    	{
    		workEffortType = getEntityManager().find(WorkEffortType.class, workEffortTypeRequest.getId());
    		workEffortType.setLastModifiedDt(workEffortTypeRequest.getLastModifiedDt());
        	workEffortType.setLastModifiedUsr(getCurrentUserName(workEffortTypeRequest));
    	}
    	else
    	{
        	workEffortType.setCreatedDt(getCurrentSystemDate());
        	workEffortType.setCreatedByUsr(getCurrentUserName(workEffortTypeRequest));
    	}
    	workEffortType.setCode(workEffortTypeRequest.getCode());
    	workEffortType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	workEffortType.setName(workEffortTypeRequest.getName()); 
    	workEffortType.setDescription(workEffortTypeRequest.getDescription()); 
    	workEffortType.setCode(workEffortTypeRequest.getCode()); 
    	workEffortType.setEffectiveDt(workEffortTypeRequest.getEffectiveDt()); 
    	workEffortType.setRecSt(workEffortTypeRequest.getRecSt()); 
		return workEffortType;
	}
}
