/**
 * 
 */
package com.nathanclaire.alantra.base.response;

import java.util.List;
import java.util.Map;

/**
 * @author Edward Banfa 
 *
 */
public class EditActivityResponse<T> extends BaseActivityResponse {
	
	private T entity;
	/**
	 * Data for the relationship fields of this entity that are displayed as choice list.
	 */
	private Map<String, List<ListItemResponse>> relatedEntitiesListData;

	/**
	 * @return the entity
	 */
	public T getEntity() {
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	public void setEntity(T entity) {
		this.entity = entity;
	}

	/**
	 * @return the relatedEntitiesListData
	 */
	public Map<String, List<ListItemResponse>> getRelatedEntitiesListData() {
		return relatedEntitiesListData;
	}

	/**
	 * @param relatedEntitiesListData the relatedEntitiesListData to set
	 */
	public void setRelatedEntitiesListData(
			Map<String, List<ListItemResponse>> relatedEntitiesListData) {
		this.relatedEntitiesListData = relatedEntitiesListData;
	}

}
