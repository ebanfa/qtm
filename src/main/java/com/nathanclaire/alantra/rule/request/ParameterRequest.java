/**
 *  Alantra.
 */
package com.nathanclaire.alantra.rule.request;


import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * AdviceRequest 
 * @author Edward Banfa
 */
public class ParameterRequest extends BaseRequest {
    private Integer parameterTypeId;
    private String parameterTypeText;
    private String defaultVal;
    private String name;
    private String description;

    public ParameterRequest() {
    }
    
	public Integer getParameterTypeId() {
        return this.parameterTypeId;
    }
    
    public void setParameterTypeId(Integer parameterTypeId) {
        this.parameterTypeId = parameterTypeId;
    }

    public String getParameterTypeText() {
        return this.parameterTypeText;
    }
    
    public void setParameterTypeText(String parameterTypeText) {
        this.parameterTypeText = parameterTypeText;
    }
	public String getDefaultVal() {
        return this.defaultVal;
    }
    
    public void setDefaultVal(String defaultVal) {
        this.defaultVal = defaultVal;
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


