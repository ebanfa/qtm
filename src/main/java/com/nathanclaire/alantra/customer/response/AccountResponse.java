/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * AccountResponse 
 * @author Edward Banfa
 */
public class AccountResponse extends BaseResponse {

    private Integer currencyId;
    private String currencyText;
    private Integer accountTypeId;
    private String accountTypeText;
    private String accountNo;
    private char isJointFg;

    public AccountResponse() {
    }

    public Integer getCurrencyId() {
        return this.currencyId;
    }
    
    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyText() {
        return this.currencyText;
    }
    
    public void setCurrencyText(String currencyText) {
        this.currencyText = currencyText;
    }

    public Integer getAccountTypeId() {
        return this.accountTypeId;
    }
    
    public void setAccountTypeId(Integer accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getAccountTypeText() {
        return this.accountTypeText;
    }
    
    public void setAccountTypeText(String accountTypeText) {
        this.accountTypeText = accountTypeText;
    }

    public String getAccountNo() {
        return this.accountNo;
    }
    
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public char getIsJointFg() {
        return this.isJointFg;
    }
    
    public void setIsJointFg(char isJointFg) {
        this.isJointFg = isJointFg;
    }


}


