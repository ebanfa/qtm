/**
 *  Alantra.
 */
package com.nathanclaire.alantra.rule.request;


import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * AdviceRequest 
 * @author Edward Banfa
 */
public class TransactionRuleParameterValueRequest extends BaseRequest {
    private Integer transactionRuleId;
    private String transactionRuleText;
    private Integer parameterId;
    private String parameterText;
    private String name;
    private String paramVal;
    private String description;

    public TransactionRuleParameterValueRequest() {
    }
    
	public Integer getTransactionRuleId() {
        return this.transactionRuleId;
    }
    
    public void setTransactionRuleId(Integer transactionRuleId) {
        this.transactionRuleId = transactionRuleId;
    }

    public String getTransactionRuleText() {
        return this.transactionRuleText;
    }
    
    public void setTransactionRuleText(String transactionRuleText) {
        this.transactionRuleText = transactionRuleText;
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
	public String getParamVal() {
        return this.paramVal;
    }
    
    public void setParamVal(String paramVal) {
        this.paramVal = paramVal;
    }
	public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}


