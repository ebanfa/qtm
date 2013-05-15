/**
 * 
 */
package com.nathanclaire.alantra.base.response;

import java.util.ArrayList;
import java.util.List;

import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;


/**
 * @author Edward Banfa 
 * @param <T>
 *
 */
public class ListActivityResponse<T> extends BaseActivityResponse
{
	private List<T> data = new ArrayList<T>();
	
	/**
	 * 
	 */
	public ListActivityResponse() {
	}

	/**
	 * @return the data
	 */
	public List<T> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<T> data) {
		this.data = data;
	}

	

}
