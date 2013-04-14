/**
 * 
 */
package com.nathanclaire.alantra.channel.rest;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.channel.model.ServiceCategory;
import com.nathanclaire.alantra.channel.rest.request.ServiceCategoryRequest;
import com.nathanclaire.alantra.channel.service.entity.ServiceCategoryService;

/**
 * @author administrator
 *
 */
@Path("/servicecategory")
@Stateless
public class ServiceCategoryRESTService extends BaseEntityRESTService<ServiceCategory, ServiceCategoryRequest> 
{
	@Inject
	ServiceCategoryService serviceCategoryService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<ServiceCategory> getAll(MultivaluedMap<String, String> queryParameters) {
		return serviceCategoryService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected ServiceCategory getSingleInstance(Integer id) {
		return serviceCategoryService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return serviceCategoryService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(ServiceCategoryRequest request) {
		serviceCategoryService.createInstance(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(ServiceCategoryRequest request) {
		serviceCategoryService.updateInstance(request);
		return null;
	}
	
}
