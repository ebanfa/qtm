/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * AccountTypeRequest 
 * @author Edward Banfa
 */
public class AccountTypeRequest extends BaseRequest {

    private String name;
    private String description;
    private String accountNoFormat;
    private String chequeNoFormat;
    private String cardNoFormat;
    private Integer id;
    private String code;

    public AccountTypeRequest() {
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

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }


}


