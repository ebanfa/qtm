/**
 * 
 */
package com.nathanclaire.alantra.channel.service.entity;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.channel.model.ServiceTransactionType;
import com.nathanclaire.alantra.channel.rest.request.ServiceTransactionTypeRequest;

/**
 * @author Edward Banfa 
 *
 */
public interface ServiceTransactionTypeService {
	
public ServiceTransactionType findById(Integer id);
	
	public ServiceTransactionType findByCode(String code);
	
	public ServiceTransactionType findByName(String name);

	public List<ServiceTransactionType> findAll(MultivaluedMap<String, String> queryParameters);
	
	public ServiceTransactionType createChannelService(ServiceTransactionTypeRequest ServiceRequest);
	
	public void deleteChannelService(Integer id);

	public ServiceTransactionType updateChannelService(ServiceTransactionTypeRequest ServiceRequest);

}
