/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * CustomerResponse 
 * @author Edward Banfa
 */
public class CustomerResponse extends BaseResponse {

    private Integer customerClassificationId;
    private String customerClassificationText;
    private Integer customerTypeId;
    private String customerTypeText;
    private String pin;
    private String primaryEmail;
    private String secondaryEmail;
    private String primaryMobile;
    private String secondaryMobile;

    public CustomerResponse() {
    }

    public Integer getCustomerClassificationId() {
        return this.customerClassificationId;
    }
    
    public void setCustomerClassificationId(Integer customerClassificationId) {
        this.customerClassificationId = customerClassificationId;
    }

    public String getCustomerClassificationText() {
        return this.customerClassificationText;
    }
    
    public void setCustomerClassificationText(String customerClassificationText) {
        this.customerClassificationText = customerClassificationText;
    }

    public Integer getCustomerTypeId() {
        return this.customerTypeId;
    }
    
    public void setCustomerTypeId(Integer customerTypeId) {
        this.customerTypeId = customerTypeId;
    }

    public String getCustomerTypeText() {
        return this.customerTypeText;
    }
    
    public void setCustomerTypeText(String customerTypeText) {
        this.customerTypeText = customerTypeText;
    }

    public String getPin() {
        return this.pin;
    }
    
    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPrimaryEmail() {
        return this.primaryEmail;
    }
    
    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getSecondaryEmail() {
        return this.secondaryEmail;
    }
    
    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public String getPrimaryMobile() {
        return this.primaryMobile;
    }
    
    public void setPrimaryMobile(String primaryMobile) {
        this.primaryMobile = primaryMobile;
    }

    public String getSecondaryMobile() {
        return this.secondaryMobile;
    }
    
    public void setSecondaryMobile(String secondaryMobile) {
        this.secondaryMobile = secondaryMobile;
    }


}


