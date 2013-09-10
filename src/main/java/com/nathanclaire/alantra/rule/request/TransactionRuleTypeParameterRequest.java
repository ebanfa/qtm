/**
 *  Alantra.
 */
package com.nathanclaire.alantra.rule.request;


import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * AdviceRequest 
 * @author Edward Banfa
 */
public class TransactionRuleTypeParameterRequest extends BaseRequest {
    private Integer transactionRuleTypeId;
    private String transactionRuleTypeText;
    private Integer parameterId;
    private String parameterText;
    private String name;
    private String defaultVal;
    private String description;

    public TransactionRuleTypeParameterRequest() {
    }
    
	public Integer getTransactionRuleTypeId() {
        return this.transactionRuleTypeId;
    }
    
    public void setTransactionRuleTypeId(Integer transactionRuleTypeId) {
        this.transactionRuleTypeId = transactionRuleTypeId;
    }

    public String getTransactionRuleTypeText() {
        return this.transactionRuleTypeText;
    }
    
    public void setTransactionRuleTypeText(String transactionRuleTypeText) {
        this.transactionRuleTypeText = transactionRuleTypeText;
    }
	public Integer getParameterId() {
        return this.parameterId;
    }
    
    public void setParameterId(Integer parameterId) {
        this.parameterId = parameterId;
    }

    public String getParameterText() {
        return this.parameterText;
    }
    
    public void setParameterText(String parameterText) {
        this.parameterText = parameterText;
    }
	public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
	public String getDefaultVal() {
        return this.defaultVal;
    }
    
    public void setDefaultVal(String defaultVal) {
        this.defaultVal = defaultVal;
    }
	public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}


