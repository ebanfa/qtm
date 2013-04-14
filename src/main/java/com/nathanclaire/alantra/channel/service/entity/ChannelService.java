/**
 * 
 */
package com.nathanclaire.alantra.channel.service.entity;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.channel.model.Service;
import com.nathanclaire.alantra.channel.rest.request.ServiceRequest;

/**
 * @author Edward Banfa 
 *
 */
public interface ChannelService {
	
	public Service findById(Integer id);
	
	public Service findByCode(String code);
	
	public Service findByName(String name);

	public List<Service> findAll(MultivaluedMap<String, String> queryParameters);
	
	public Service createChannelService(ServiceRequest ServiceRequest);
	
	public void deleteChannelService(Integer id);

	public Service updateChannelService(ServiceRequest ServiceRequest);

}
