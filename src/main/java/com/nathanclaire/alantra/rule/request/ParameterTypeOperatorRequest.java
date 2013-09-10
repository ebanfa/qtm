/**
 *  Alantra.
 */
package com.nathanclaire.alantra.rule.request;


import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * AdviceRequest 
 * @author Edward Banfa
 */
public class ParameterTypeOperatorRequest extends BaseRequest {
    private Integer operatorId;
    private String operatorText;
    private Integer parameterTypeId;
    private String parameterTypeText;
    private String name;
    private String description;

    public ParameterTypeOperatorRequest() {
    }
    
	public Integer getOperatorId() {
        return this.operatorId;
    }
    
    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorText() {
        return this.operatorText;
    }
    
    public void setOperatorText(String operatorText) {
        this.operatorText = operatorText;
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


