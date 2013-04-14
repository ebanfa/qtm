/**
 * 
 */
package com.nathanclaire.alantra.base.service.entity;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;

/**
 * @author Edward Banfa 
 *
 */
public interface BaseEntityService<T> {
	
	public T findById(Integer id);
	
	public T findByCode(String code);
	
	public T findByName(String name);

	public List<T> findAll(MultivaluedMap<String, String> queryParameters);

	public void deleteInstance(Integer id);

	public T updateInstance(BaseRequest hostRequest);

	public T createInstance(BaseRequest hostRequest);
	
	public Map<String, Long> getCount(MultivaluedMap<String, String> queryParameters);

}
