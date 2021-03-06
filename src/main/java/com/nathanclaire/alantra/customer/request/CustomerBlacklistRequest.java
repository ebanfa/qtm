/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * CustomerBlacklistRequest 
 * @author Edward Banfa
 */
public class CustomerBlacklistRequest extends BaseRequest {

    private Integer customerId;
    private String customerText;
    private String description;
    private Integer id;
    private String code;

    public CustomerBlacklistRequest() {
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

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
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


