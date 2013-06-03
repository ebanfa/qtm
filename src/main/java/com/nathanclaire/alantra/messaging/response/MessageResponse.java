/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.messaging.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * MessageResponse 
 * @author Edward Banfa
 */
public class MessageResponse extends BaseResponse {

    private Integer messageClassificationId;
    private String messageClassificationText;
    private Integer messageTypeId;
    private String messageTypeText;
    private Integer messageStatusId;
    private String messageStatusText;
    private String messageFrom;
    private String messageTo;
    private String messageSubject;
    private String messageTxt;

    public MessageResponse() {
    }

    public Integer getMessageClassificationId() {
        return this.messageClassificationId;
    }
    
    public void setMessageClassificationId(Integer messageClassificationId) {
        this.messageClassificationId = messageClassificationId;
    }

    public String getMessageClassificationText() {
        return this.messageClassificationText;
    }
    
    public void setMessageClassificationText(String messageClassificationText) {
        this.messageClassificationText = messageClassificationText;
    }

    public Integer getMessageTypeId() {
        return this.messageTypeId;
    }
    
    public void setMessageTypeId(Integer messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    public String getMessageTypeText() {
        return this.messageTypeText;
    }
    
    public void setMessageTypeText(String messageTypeText) {
        this.messageTypeText = messageTypeText;
    }

    public Integer getMessageStatusId() {
        return this.messageStatusId;
    }
    
    public void setMessageStatusId(Integer messageStatusId) {
        this.messageStatusId = messageStatusId;
    }

    public String getMessageStatusText() {
        return this.messageStatusText;
    }
    
    public void setMessageStatusText(String messageStatusText) {
        this.messageStatusText = messageStatusText;
    }

    public String getMessageFrom() {
        return this.messageFrom;
    }
    
    public void setMessageFrom(String messageFrom) {
        this.messageFrom = messageFrom;
    }

    public String getMessageTo() {
        return this.messageTo;
    }
    
    public void setMessageTo(String messageTo) {
        this.messageTo = messageTo;
    }

    public String getMessageSubject() {
        return this.messageSubject;
    }
    
    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public String getMessageTxt() {
        return this.messageTxt;
    }
    
    public void setMessageTxt(String messageTxt) {
        this.messageTxt = messageTxt;
    }


}

