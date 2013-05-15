/**
 * 
 */
package com.nathanclaire.alantra.base.response;

/**
 * @author Edward Banfa 
 *
 */
public class EditActivityResponse<T> extends BaseActivityResponse {
	
	private T entity;

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

}
