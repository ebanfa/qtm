/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.request;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * CustomerMessageRequest 
 * @author Edward Banfa
 */
public class CustomerMessageRequest extends BaseRequest {

    private Integer customerId;
    private String customerText;
    private Integer messageId;
    private String messageText;
    private Integer id;
    private String code;

    public CustomerMessageRequest() {
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


