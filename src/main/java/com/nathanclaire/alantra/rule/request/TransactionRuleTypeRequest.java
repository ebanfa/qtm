/**
 *  Alantra.
 */
package com.nathanclaire.alantra.rule.request;


import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * AdviceRequest 
 * @author Edward Banfa
 */
public class TransactionRuleTypeRequest extends BaseRequest {
    private Integer transactionRuleCategoryId;
    private String transactionRuleCategoryText;
    private String name;
    private String description;

    public TransactionRuleTypeRequest() {
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


