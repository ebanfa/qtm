/**
 * 
 */
package com.nathanclaire.alantra.channel.service.entity;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.BaseEntityService;
import com.nathanclaire.alantra.channel.model.Service;
import com.nathanclaire.alantra.channel.rest.request.ServiceRequest;

/**
 * @author Edward Banfa 
 *
 */
public class ChannelServiceImpl extends BaseEntityService<Service> implements ChannelService {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ChannelService#findById(java.lang.Integer)
	 */
	@Override
	public Service findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ChannelService#findByCode(java.lang.String)
	 */
	@Override
	public Service findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ChannelService#findByName(java.lang.String)
	 */
	@Override
	public Service findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ChannelService#findAll(java.util.Map)
	 */
	@Override
	public List<Service> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ChannelService#createChannelService(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public Service createChannelService(ServiceRequest serviceRequest) {
		return createInsance(serviceRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ChannelService#deleteChannelService(java.lang.Integer)
	 */
	@Override
	public void deleteChannelService(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ChannelService#updateChannelService(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public Service updateChannelService(ServiceRequest serviceRequest) {
		return updateInstance(serviceRequest);
	}
	
	

}
