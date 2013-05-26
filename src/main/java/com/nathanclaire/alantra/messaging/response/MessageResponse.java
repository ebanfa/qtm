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

    private Integer messageTypeId;
    private Integer adviceId;
    private Integer messageStatusId;
    private String messageFrom;
    private String messageTo;
    private String messageSubject;
    private String messageTxt;

    public MessageResponse() {
    }

    public Integer getMessageTypeId() {
        return this.messageTypeId;
    }
    
    public void setMessageTypeId(Integer messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    public Integer getAdviceId() {
        return this.adviceId;
    }
    
    public void setAdviceId(Integer adviceId) {
        this.adviceId = adviceId;
    }

    public Integer getMessageStatusId() {
        return this.messageStatusId;
    }
    
    public void setMessageStatusId(Integer messageStatusId) {
        this.messageStatusId = messageStatusId;
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


