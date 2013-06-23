/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.messaging.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * MessageTypeTemplateResponse 
 * @author Edward Banfa
 */
public class MessageTypeTemplateResponse extends BaseResponse {

    private Integer templateId;
    private String templateText;
    private Integer messageTypeId;
    private String messageTypeText;

    public MessageTypeTemplateResponse() {
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


}


