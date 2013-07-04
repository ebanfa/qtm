/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * CustomerClassificationNotificationChannelResponse 
 * @author Edward Banfa
 */
public class CustomerClassificationNotificationChannelResponse extends BaseResponse {

    private Integer dataChannelCategoryId;
    private String dataChannelCategoryText;
    private Integer customerClassificationId;
    private String customerClassificationText;

    public CustomerClassificationNotificationChannelResponse() {
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


}


