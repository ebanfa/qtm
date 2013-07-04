/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * AccountTypeResponse 
 * @author Edward Banfa
 */
public class AccountTypeResponse extends BaseResponse {

    private String accountNoFormat;
    private String chequeNoFormat;
    private String cardNoFormat;

    public AccountTypeResponse() {
    }

    public String getAccountNoFormat() {
        return this.accountNoFormat;
    }
    
    public void setAccountNoFormat(String accountNoFormat) {
        this.accountNoFormat = accountNoFormat;
    }

    public String getChequeNoFormat() {
        return this.chequeNoFormat;
    }
    
    public void setChequeNoFormat(String chequeNoFormat) {
        this.chequeNoFormat = chequeNoFormat;
    }

    public String getCardNoFormat() {
        return this.cardNoFormat;
    }
    
    public void setCardNoFormat(String cardNoFormat) {
        this.cardNoFormat = cardNoFormat;
    }


}


