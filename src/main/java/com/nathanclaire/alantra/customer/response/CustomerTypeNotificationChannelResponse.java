/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * CustomerTypeNotificationChannelResponse 
 * @author Edward Banfa
 */
public class CustomerTypeNotificationChannelResponse extends BaseResponse {

    private Integer dataChannelCategoryId;
    private String dataChannelCategoryText;
    private Integer customerTypeId;
    private String customerTypeText;

    public CustomerTypeNotificationChannelResponse() {
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


}


