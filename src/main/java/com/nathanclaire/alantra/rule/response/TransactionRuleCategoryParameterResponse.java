/**
 *  Alantra.
 */
package com.nathanclaire.alantra.rule.response;


import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * AdviceResponse 
 * @author Edward Banfa
 */
public class TransactionRuleCategoryParameterResponse extends BaseResponse {
    private Integer transactionRuleCategoryId;
    private String transactionRuleCategoryText;
    private Integer parameterId;
    private String parameterText;
    private String name;
    private String defaultVal;
    private String description;

    public TransactionRuleCategoryParameterResponse() {
    }
    
	public Integer getTransactionRuleCategoryId() {
        return this.transactionRuleCategoryId;
    }
    
    public void setTransactionRuleCategoryId(Integer transactionRuleCategoryId) {
        this.transactionRuleCategoryId = transactionRuleCategoryId;
    }

    public String getTransactionRuleCategoryText() {
        return this.transactionRuleCategoryText;
    }
    
    public void setTransactionRuleCategoryText(String transactionRuleCategoryText) {
        this.transactionRuleCategoryText = transactionRuleCategoryText;
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


