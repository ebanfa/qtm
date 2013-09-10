/**
 *  Alantra.
 */
package com.nathanclaire.alantra.rule.response;


import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * AdviceResponse 
 * @author Edward Banfa
 */
public class TransactionRuleConditionResponse extends BaseResponse {
    private Integer transactionRuleId;
    private String transactionRuleText;
    private Integer operatorId;
    private String operatorText;
    private Integer transactionRuleConditionAttributeId;
    private String transactionRuleConditionAttributeText;
    private Integer transactionRuleConditionParameterId;
    private String transactionRuleConditionParameterText;
    private String name;
    private String description;

    public TransactionRuleConditionResponse() {
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
	public Integer getTransactionRuleConditionAttributeId() {
        return this.transactionRuleConditionAttributeId;
    }
    
    public void setTransactionRuleConditionAttributeId(Integer transactionRuleConditionAttributeId) {
        this.transactionRuleConditionAttributeId = transactionRuleConditionAttributeId;
    }

    public String getTransactionRuleConditionAttributeText() {
        return this.transactionRuleConditionAttributeText;
    }
    
    public void setTransactionRuleConditionAttributeText(String transactionRuleConditionAttributeText) {
        this.transactionRuleConditionAttributeText = transactionRuleConditionAttributeText;
    }
	public Integer getTransactionRuleConditionParameterId() {
        return this.transactionRuleConditionParameterId;
    }
    
    public void setTransactionRuleConditionParameterId(Integer transactionRuleConditionParameterId) {
        this.transactionRuleConditionParameterId = transactionRuleConditionParameterId;
    }

    public String getTransactionRuleConditionParameterText() {
        return this.transactionRuleConditionParameterText;
    }
    
    public void setTransactionRuleConditionParameterText(String transactionRuleConditionParameterText) {
        this.transactionRuleConditionParameterText = transactionRuleConditionParameterText;
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


