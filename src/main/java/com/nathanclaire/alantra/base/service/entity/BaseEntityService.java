/**
 * 
 */
package com.nathanclaire.alantra.base.service.entity;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

/**
 * @author Edward Banfa 
 *
 */
public interface BaseEntityService<T,V> {
	
	public T findById(Integer id);
	
	public T findByCode(String code);
	
	public T findByName(String name);

	public List<T> findAll(MultivaluedMap<String, String> queryParameters);

	public void delete(Integer id);

	public T update(V request);

	public T create(V request);
	
	public Map<String, Long> getCount(MultivaluedMap<String, String> queryParameters);

}
