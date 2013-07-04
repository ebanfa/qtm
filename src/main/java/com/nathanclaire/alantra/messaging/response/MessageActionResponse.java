/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.messaging.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * MessageActionResponse 
 * @author Edward Banfa
 */
public class MessageActionResponse extends BaseResponse {

    private Integer messageApplicationActionId;
    private String messageApplicationActionText;
    private Integer messageId;
    private String messageText;

    public MessageActionResponse() {
    }

    public Integer getMessageApplicationActionId() {
        return this.messageApplicationActionId;
    }
    
    public void setMessageApplicationActionId(Integer messageApplicationActionId) {
        this.messageApplicationActionId = messageApplicationActionId;
    }

    public String getMessageApplicationActionText() {
        return this.messageApplicationActionText;
    }
    
    public void setMessageApplicationActionText(String messageApplicationActionText) {
        this.messageApplicationActionText = messageApplicationActionText;
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


}


