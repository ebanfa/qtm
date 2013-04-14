/**
 * 
 */
package com.nathanclaire.alantra.channel.service.entity;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.channel.model.Host;
import com.nathanclaire.alantra.channel.rest.request.HostRequest;

/**
 * @author Edward Banfa
 *
 */
public interface HostService
{
	public Host findById(Integer id);
	
	public Host findByCode(String code);
	
	public Host findByName(String name);

	public List<Host> findAll(MultivaluedMap<String, String> queryParameters);
	
	public Host createHost(HostRequest hostRequest);
	
	public void deleteHost(Integer id);

	public Host updateHost(HostRequest hostRequest);
}
