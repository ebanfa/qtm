/**
 * 
 */
package com.nathanclaire.alantra.base.service.entity;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;

/**
 * @author Edward Banfa 
 *
 */
public interface BaseEntityService<M, T,V> {
	
	public M update(V request) throws ApplicationException;

	public M create(V request) throws ApplicationException;

	public void delete(Integer id) throws ApplicationException;
	
	public M findById(Integer id) throws ApplicationException;
	
	public M findByCode(String code) throws ApplicationException;
	
	public M findByName(String name) throws ApplicationException;
    
    public String getEntityName() throws ApplicationException;
	
    public String getListActivityCode() throws ApplicationException;
    
    public String getEditActivityCode() throws ApplicationException;
    
    public M convertRequestToModel(V request) throws ApplicationException;
    
    public T convertModelToResponse(M model) throws ApplicationException;
    
    public List<ApplicationEntityField> getEntityFields() throws ApplicationException;

	public List<M> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException;
	
	public Map<String, Long> getCount(MultivaluedMap<String, String> queryParameters) throws ApplicationException;
	
	public Map<String, List<ListItemResponse>> relatedEntitesToListItems() throws ApplicationException;
	
	public List<ListItemResponse> asListItem() throws ApplicationException;
	
	public List<M> findByCriteria(Map<String, String> searchCriteria) throws ApplicationException;
}
