/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * CustomerNotificationChannelResponse 
 * @author Edward Banfa
 */
public class CustomerNotificationChannelResponse extends BaseResponse {

    private Integer dataChannelCategoryId;
    private String dataChannelCategoryText;
    private Integer customerId;
    private String customerText;

    public CustomerNotificationChannelResponse() {
    }

    public Integer getDataChannelCategoryId() {
        return this.dataChannelCategoryId;
    }
    
    public void setDataChannelCategoryId(Integer dataChannelCategoryId) {
        this.dataChannelCategoryId = dataChannelCategoryId;
    }

    public String getDataChannelCategoryText() {
        return this.dataChannelCategoryText;
    }
    
    public void setDataChannelCategoryText(String dataChannelCategoryText) {
        this.dataChannelCategoryText = dataChannelCategoryText;
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


}


