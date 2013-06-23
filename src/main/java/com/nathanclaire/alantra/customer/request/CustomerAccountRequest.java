/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * CustomerAccountRequest 
 * @author Edward Banfa
 */
public class CustomerAccountRequest extends BaseRequest {

    private Integer customerId;
    private String customerText;
    private Integer accountId;
    private String accountText;
    private String name;
    private String description;
    private char isDefaultFg;
    private Integer id;
    private String code;

    public CustomerAccountRequest() {
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

    public char getIsDefaultFg() {
        return this.isDefaultFg;
    }
    
    public void setIsDefaultFg(char isDefaultFg) {
        this.isDefaultFg = isDefaultFg;
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


