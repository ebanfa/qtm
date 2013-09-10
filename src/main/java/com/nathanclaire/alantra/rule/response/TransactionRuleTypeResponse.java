/**
 *  Alantra.
 */
package com.nathanclaire.alantra.rule.response;


import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * AdviceResponse 
 * @author Edward Banfa
 */
public class TransactionRuleTypeResponse extends BaseResponse {
    private Integer transactionRuleCategoryId;
    private String transactionRuleCategoryText;
    private String name;
    private String description;

    public TransactionRuleTypeResponse() {
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


