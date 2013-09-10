/**
 *  Alantra.
 */
package com.nathanclaire.alantra.rule.response;


import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * AdviceResponse 
 * @author Edward Banfa
 */
public class TransactionRuleCategoryResponse extends BaseResponse {
    private Integer transactionRuleSpaceId;
    private String transactionRuleSpaceText;
    private String name;
    private String description;

    public TransactionRuleCategoryResponse() {
    }
    
	public Integer getTransactionRuleSpaceId() {
        return this.transactionRuleSpaceId;
    }
    
    public void setTransactionRuleSpaceId(Integer transactionRuleSpaceId) {
        this.transactionRuleSpaceId = transactionRuleSpaceId;
    }

    public String getTransactionRuleSpaceText() {
        return this.transactionRuleSpaceText;
    }
    
    public void setTransactionRuleSpaceText(String transactionRuleSpaceText) {
        this.transactionRuleSpaceText = transactionRuleSpaceText;
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


