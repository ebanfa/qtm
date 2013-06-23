/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * CustomerMessageResponse 
 * @author Edward Banfa
 */
public class CustomerMessageResponse extends BaseResponse {

    private Integer customerId;
    private String customerText;
    private Integer messageId;
    private String messageText;
    private String dataTy;
    private String dataUrl;

    public CustomerMessageResponse() {
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

    public Integer getMessageId() {
        return this.messageId;
    }
    
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageText() {
        return this.messageText;
    }
    
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getDataTy() {
        return this.dataTy;
    }
    
    public void setDataTy(String dataTy) {
        this.dataTy = dataTy;
    }

    public String getDataUrl() {
        return this.dataUrl;
    }
    
    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }


}


