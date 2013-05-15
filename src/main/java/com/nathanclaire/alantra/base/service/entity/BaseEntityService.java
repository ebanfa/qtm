/**
 * 
 */
package com.nathanclaire.alantra.base.service.entity;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.base.response.ListItemResponse;

/**
 * @author Edward Banfa 
 *
 */
public interface BaseEntityService<M, T,V> {
	
	public M update(V request);

	public M create(V request);

	public void delete(Integer id);
	
	public M findById(Integer id);
	
	public M findByCode(String code);
	
	public M findByName(String name);
    
    public String getEntityName();
	
    public String getListActivityCode();
    
    public String getEditActivityCode();
    
    public M convertRequestToModel(V request);
    
    public T convertModelToResponse(M model);
    
    public List<ApplicationEntityField> getEntityFields();

	public List<M> findAll(MultivaluedMap<String, String> queryParameters);
	
	public Map<String, Long> getCount(MultivaluedMap<String, String> queryParameters);
	
	public Map<String, List<ListItemResponse>> relatedEntitesToListItems();
	
	public List<ListItemResponse> asListItem();

}
