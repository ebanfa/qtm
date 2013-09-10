/**
 *  Alantra.
 */
package com.nathanclaire.alantra.rule.request;


import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * AdviceRequest 
 * @author Edward Banfa
 */
public class ParameterTypeRequest extends BaseRequest {
    private String name;
    private String description;

    public ParameterTypeRequest() {
    }
    
	public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
	public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}


