/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.security.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * SystemUserMessageResponse 
 * @author Edward Banfa
 */
public class SystemUserMessageResponse extends BaseResponse {

    private Integer messageId;
    private String messageText;
    private Integer systemUserId;
    private String systemUserText;

    public SystemUserMessageResponse() {
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

    public Integer getSystemUserId() {
        return this.systemUserId;
    }
    
    public void setSystemUserId(Integer systemUserId) {
        this.systemUserId = systemUserId;
    }

    public String getSystemUserText() {
        return this.systemUserText;
    }
    
    public void setSystemUserText(String systemUserText) {
        this.systemUserText = systemUserText;
    }


}


