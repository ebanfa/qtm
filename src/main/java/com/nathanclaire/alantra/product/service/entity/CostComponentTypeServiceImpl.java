/**
 * 
 */
package com.nathanclaire.alantra.product.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.product.model.CostComponentType;
import com.nathanclaire.alantra.product.rest.request.CostComponentTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class CostComponentTypeServiceImpl extends BaseEntityServiceImpl<CostComponentType> implements CostComponentTypeService
{
	/**
	 * @param entityClass
	 */
	public CostComponentTypeServiceImpl() {
		super(CostComponentType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.CostComponentType#findById(java.lang.Integer)
	 */
	@Override
	public CostComponentType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.CostComponentType#findByCode(java.lang.String)
	 */
	@Override
	public CostComponentType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.CostComponentType#findByName(java.lang.String)
	 */
	@Override
	public CostComponentType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.CostComponentType#findAll(java.util.Map)
	 */
	@Override
	public List<CostComponentType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.CostComponentType#createCostComponentType(com.nathanclaire.alantra.product.rest.request.ServiceRequest)
	 */
	@Override
	public CostComponentType createInstance(BaseRequest costComponentTypeRequest) {
		return createInsance(costComponentTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.CostComponentType#deleteCostComponentType(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.CostComponentType#updateCostComponentType(com.nathanclaire.alantra.product.rest.request.ServiceRequest)
	 */
	@Override
	public CostComponentType updateInstance(BaseRequest costComponentTypeRequest) {
		return updateInstance(costComponentTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected CostComponentType loadModelFromRequest(BaseRequest request) 
    {
    	CostComponentTypeRequest costComponentTypeRequest = (CostComponentTypeRequest) request;
		CostComponentType costComponentType = new CostComponentType();
    	Integer costComponentTypeId = costComponentTypeRequest.getId();
    	// Are we editing a CostComponentType
    	if(costComponentTypeId != null) 
    	{
    		costComponentType = getEntityManager().find(CostComponentType.class, costComponentTypeRequest.getId());
    		costComponentType.setLastModifiedDt(costComponentTypeRequest.getLastModifiedDt());
        	costComponentType.setLastModifiedUsr(getCurrentUserName(costComponentTypeRequest));
    	}
    	else
    	{
        	costComponentType.setCreatedDt(getCurrentSystemDate());
        	costComponentType.setCreatedByUsr(getCurrentUserName(costComponentTypeRequest));
    	}
    	costComponentType.setCode(costComponentTypeRequest.getCode());
    	costComponentType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	costComponentType.setName(costComponentTypeRequest.getName()); 
    	costComponentType.setDescription(costComponentTypeRequest.getDescription()); 
    	costComponentType.setCode(costComponentTypeRequest.getCode()); 
    	costComponentType.setEffectiveDt(costComponentTypeRequest.getEffectiveDt()); 
    	costComponentType.setRecSt(costComponentTypeRequest.getRecSt()); 
		return costComponentType;
	}
}
