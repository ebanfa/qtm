/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.messaging.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * MessageTypeTemplateRequest 
 * @author Edward Banfa
 */
public class MessageTypeTemplateRequest extends BaseRequest {

    private Integer templateId;
    private String templateText;
    private Integer messageTypeId;
    private String messageTypeText;
    private Integer id;
    private String code;

    public MessageTypeTemplateRequest() {
    }

    public Integer getTemplateId() {
        return this.templateId;
    }
    
    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getTemplateText() {
        return this.templateText;
    }
    
    public void setTemplateText(String templateText) {
        this.templateText = templateText;
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


