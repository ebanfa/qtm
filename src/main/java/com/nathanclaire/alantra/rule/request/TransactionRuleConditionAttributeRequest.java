/**
 *  Alantra.
 */
package com.nathanclaire.alantra.rule.request;


import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * AdviceRequest 
 * @author Edward Banfa
 */
public class TransactionRuleConditionAttributeRequest extends BaseRequest {
    private String name;
    private String description;
    private Integer parameterTypeId;
    private String parameterTypeText;

    public TransactionRuleConditionAttributeRequest() {
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
}


