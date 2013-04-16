/**
 * 
 */
package com.nathanclaire.alantra.workeffort.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.workeffort.model.WorkEffort;
import com.nathanclaire.alantra.workeffort.request.WorkEffortRequest;

import com.nathanclaire.alantra.workeffort.model.WorkEffortType;

/**
 * @author administrator
 *
 */
@Stateless
public class WorkEffortServiceImpl extends BaseEntityServiceImpl<WorkEffort, WorkEffortRequest> implements WorkEffortService
{
	/**
	 * @param entityClass
	 */
	public WorkEffortServiceImpl() {
		super(WorkEffort.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.workeffort.service.WorkEffort#findById(java.lang.Integer)
	 */
	@Override
	public WorkEffort findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.workeffort.service.WorkEffort#findByCode(java.lang.String)
	 */
	@Override
	public WorkEffort findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.workeffort.service.WorkEffort#findByName(java.lang.String)
	 */
	@Override
	public WorkEffort findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.workeffort.service.WorkEffort#findAll(java.util.Map)
	 */
	@Override
	public List<WorkEffort> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.workeffort.service.WorkEffort#createWorkEffort(com.nathanclaire.alantra.workeffort.rest.request.ServiceRequest)
	 */
	@Override
	public WorkEffort create(WorkEffortRequest workEffortRequest) {
		return createInstance(workEffortRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.workeffort.service.WorkEffort#deleteWorkEffort(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.workeffort.service.WorkEffort#updateWorkEffort(com.nathanclaire.alantra.workeffort.rest.request.ServiceRequest)
	 */
	@Override
	public WorkEffort update(WorkEffortRequest workEffortRequest) {
		return updateInstance(workEffortRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected WorkEffort loadModelFromRequest(WorkEffortRequest workEffortRequest) 
    {
		WorkEffort workEffort = new WorkEffort();
    	Integer workEffortId = workEffortRequest.getId();
    	// Are we editing a WorkEffort
    	if(workEffortId != null) 
    	{
    		workEffort = getEntityManager().find(WorkEffort.class, workEffortRequest.getId());
    		workEffort.setLastModifiedDt(workEffortRequest.getLastModifiedDt());
        	workEffort.setLastModifiedUsr(getCurrentUserName(workEffortRequest));
    	}
    	else
    	{
        	workEffort.setCreatedDt(getCurrentSystemDate());
        	workEffort.setCreatedByUsr(getCurrentUserName(workEffortRequest));
    	}
    	workEffort.setCode(workEffortRequest.getCode());
    	workEffort.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (workEffortRequest.getWorkEffortType() != null)
    	{
    		WorkEffortType workEffortType = getEntityManager().find(WorkEffortType.class, workEffortRequest.getWorkEffortType());
    		workEffort.setWorkEffortType(workEffortType);
    	}
    	workEffort.setName(workEffortRequest.getName()); 
    	workEffort.setDescription(workEffortRequest.getDescription()); 
    	workEffort.setSchedStartDt(workEffortRequest.getSchedStartDt()); 
    	workEffort.setSchedEndDt(workEffortRequest.getSchedEndDt()); 
    	workEffort.setEstHours(workEffortRequest.getEstHours()); 
    	workEffort.setTotalHrAllowed(workEffortRequest.getTotalHrAllowed()); 
    	workEffort.setTotalAmountAllowed(workEffortRequest.getTotalAmountAllowed()); 
    	workEffort.setCode(workEffortRequest.getCode()); 
    	workEffort.setEffectiveDt(workEffortRequest.getEffectiveDt()); 
    	workEffort.setRecSt(workEffortRequest.getRecSt()); 
		return workEffort;
	}
}
