/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.messaging.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * MessageRequest 
 * @author Edward Banfa
 */
public class MessageRequest extends BaseRequest {

    private Integer messageTypeId;
    private Integer adviceId;
    private Integer messageStatusId;
    private String messageFrom;
    private String messageTo;
    private String messageSubject;
    private String messageTxt;
    private Integer id;
    private String code;

    public MessageRequest() {
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


