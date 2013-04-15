/**
 * 
 */
package com.nathanclaire.alantra.channel.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.channel.model.ServiceCategory;
import com.nathanclaire.alantra.channel.rest.request.ServiceCategoryRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class ServiceCategoryServiceImpl extends BaseEntityServiceImpl<ServiceCategory, ServiceCategoryRequest> implements ServiceCategoryService
{
	/**
	 * @param entityClass
	 */
	public ServiceCategoryServiceImpl() {
		super(ServiceCategory.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceCategory#findById(java.lang.Integer)
	 */
	@Override
	public ServiceCategory findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceCategory#findByCode(java.lang.String)
	 */
	@Override
	public ServiceCategory findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceCategory#findByName(java.lang.String)
	 */
	@Override
	public ServiceCategory findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceCategory#findAll(java.util.Map)
	 */
	@Override
	public List<ServiceCategory> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceCategory#createServiceCategory(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceCategory createInstance(ServiceCategoryRequest serviceCategoryRequest) {
		return createInsance(serviceCategoryRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceCategory#deleteServiceCategory(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceCategory#updateServiceCategory(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceCategory updateInstance(ServiceCategoryRequest serviceCategoryRequest) {
		return updateInstance(serviceCategoryRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected ServiceCategory loadModelFromRequest(ServiceCategoryRequest serviceCategoryRequest) 
    {
		ServiceCategory serviceCategory = new ServiceCategory();
    	Integer serviceCategoryId = serviceCategoryRequest.getId();
    	// Are we editing a ServiceCategory
    	if(serviceCategoryId != null) 
    	{
    		serviceCategory = getEntityManager().find(ServiceCategory.class, serviceCategoryRequest.getId());
    		serviceCategory.setLastModifiedDt(serviceCategoryRequest.getLastModifiedDt());
        	serviceCategory.setLastModifiedUsr(getCurrentUserName(serviceCategoryRequest));
    	}
    	else
    	{
        	serviceCategory.setCreatedDt(getCurrentSystemDate());
        	serviceCategory.setCreatedByUsr(getCurrentUserName(serviceCategoryRequest));
    	}
    	serviceCategory.setCode(serviceCategoryRequest.getCode());
    	serviceCategory.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	serviceCategory.setName(serviceCategoryRequest.getName()); 
    	serviceCategory.setDescription(serviceCategoryRequest.getDescription()); 
    	serviceCategory.setCode(serviceCategoryRequest.getCode()); 
    	serviceCategory.setEffectiveDt(serviceCategoryRequest.getEffectiveDt()); 
    	serviceCategory.setRecSt(serviceCategoryRequest.getRecSt()); 
		return serviceCategory;
	}
}
