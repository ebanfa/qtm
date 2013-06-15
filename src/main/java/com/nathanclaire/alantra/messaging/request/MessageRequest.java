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

    private Integer messageClassificationId;
    private String messageClassificationText;
    private Integer messageTypeId;
    private String messageTypeText;
    private Integer messageStatusId;
    private String messageStatusText;
    private Integer dataChannelId;
    private String dataChannelText;
    private String messageFrom;
    private String messageTo;
    private String messageSubject;
    private String messageTxt;
    private Integer id;
    private String code;

    public MessageRequest() {
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

    public Integer getDataChannelId() {
        return this.dataChannelId;
    }
    
    public void setDataChannelId(Integer dataChannelId) {
        this.dataChannelId = dataChannelId;
    }

    public String getDataChannelText() {
        return this.dataChannelText;
    }
    
    public void setDataChannelText(String dataChannelText) {
        this.dataChannelText = dataChannelText;
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


