/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.messaging.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * MessageActionRequest 
 * @author Edward Banfa
 */
public class MessageActionRequest extends BaseRequest {

    private Integer messageApplicationActionId;
    private String messageApplicationActionText;
    private Integer messageId;
    private String messageText;
    private Integer id;
    private String code;

    public MessageActionRequest() {
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


