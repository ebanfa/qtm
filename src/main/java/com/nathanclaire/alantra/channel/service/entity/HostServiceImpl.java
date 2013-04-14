/**
 * 
 */
package com.nathanclaire.alantra.channel.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.BaseEntityService;
import com.nathanclaire.alantra.channel.model.Host;
import com.nathanclaire.alantra.channel.rest.request.HostRequest;

/**
 * @author administrator
 *
 */
@Stateless
public class HostServiceImpl extends BaseEntityService<Host> implements HostService
{
	/**
	 * @param entityClass
	 */
	public HostServiceImpl() {
		super(Host.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Host#findById(java.lang.Integer)
	 */
	@Override
	public Host findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Host#findByCode(java.lang.String)
	 */
	@Override
	public Host findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Host#findByName(java.lang.String)
	 */
	@Override
	public Host findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Host#findAll(java.util.Map)
	 */
	@Override
	public List<Host> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Host#createHost(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public Host createHost(HostRequest hostRequest) {
		return createInsance(hostRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Host#deleteHost(java.lang.Integer)
	 */
	@Override
	public void deleteHost(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Host#updateHost(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public Host updateHost(HostRequest hostRequest) {
		return updateInstance(hostRequest);
	}
}
