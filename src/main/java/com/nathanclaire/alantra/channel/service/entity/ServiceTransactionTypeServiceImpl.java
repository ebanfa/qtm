/**
 * 
 */
package com.nathanclaire.alantra.channel.service.entity;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.BaseEntityService;
import com.nathanclaire.alantra.channel.model.ServiceTransactionType;
import com.nathanclaire.alantra.channel.rest.request.ServiceTransactionTypeRequest;

/**
 * @author Edward Banfa 
 *
 */
public class ServiceTransactionTypeServiceImpl extends BaseEntityService<ServiceTransactionType> implements
		ServiceTransactionTypeService {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionTypeService#findById(java.lang.Integer)
	 */
	@Override
	public ServiceTransactionType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionTypeService#findByCode(java.lang.String)
	 */
	@Override
	public ServiceTransactionType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionTypeService#findByName(java.lang.String)
	 */
	@Override
	public ServiceTransactionType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionTypeService#findAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	public List<ServiceTransactionType> findAll(
			MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionTypeService#createChannelService(com.nathanclaire.alantra.channel.rest.request.ServiceTransactionTypeRequest)
	 */
	@Override
	public ServiceTransactionType createChannelService(
			ServiceTransactionTypeRequest serviceTransactionTypeRequest) {
		return createInsance(serviceTransactionTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionTypeService#deleteChannelService(java.lang.Integer)
	 */
	@Override
	public void deleteChannelService(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionTypeService#updateChannelService(com.nathanclaire.alantra.channel.rest.request.ServiceTransactionTypeRequest)
	 */
	@Override
	public ServiceTransactionType updateChannelService(
			ServiceTransactionTypeRequest serviceTransactionTypeRequest) {
		return updateInstance(serviceTransactionTypeRequest);
	}

}
