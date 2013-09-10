/**
 *  Alantra.
 */
package com.nathanclaire.alantra.rule.response;


import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * AdviceResponse 
 * @author Edward Banfa
 */
public class TransactionRuleResponse extends BaseResponse {
    private Integer transactionRuleTypeId;
    private String transactionRuleTypeText;
    private Integer transactionRuleActionId;
    private String transactionRuleActionText;
    private String name;
    private Character operatorModeFg;
    private String description;

    public TransactionRuleResponse() {
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
	public Integer getTransactionRuleActionId() {
        return this.transactionRuleActionId;
    }
    
    public void setTransactionRuleActionId(Integer transactionRuleActionId) {
        this.transactionRuleActionId = transactionRuleActionId;
    }

    public String getTransactionRuleActionText() {
        return this.transactionRuleActionText;
    }
    
    public void setTransactionRuleActionText(String transactionRuleActionText) {
        this.transactionRuleActionText = transactionRuleActionText;
    }
	public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
	public Character getOperatorModeFg() {
        return this.operatorModeFg;
    }
    
    public void setOperatorModeFg(Character operatorModeFg) {
        this.operatorModeFg = operatorModeFg;
    }
	public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}


