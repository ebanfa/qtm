/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * CustomerAccountResponse 
 * @author Edward Banfa
 */
public class CustomerAccountResponse extends BaseResponse {

    private Integer customerId;
    private String customerText;
    private Integer accountId;
    private String accountText;
    private char isDefaultFg;

    public CustomerAccountResponse() {
    }

    public Integer getCustomerId() {
        return this.customerId;
    }
    
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerText() {
        return this.customerText;
    }
    
    public void setCustomerText(String customerText) {
        this.customerText = customerText;
    }

    public Integer getAccountId() {
        return this.accountId;
    }
    
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountText() {
        return this.accountText;
    }
    
    public void setAccountText(String accountText) {
        this.accountText = accountText;
    }

    public char getIsDefaultFg() {
        return this.isDefaultFg;
    }
    
    public void setIsDefaultFg(char isDefaultFg) {
        this.isDefaultFg = isDefaultFg;
    }


}


