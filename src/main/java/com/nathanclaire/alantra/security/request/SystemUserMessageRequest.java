/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.security.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * SystemUserMessageRequest 
 * @author Edward Banfa
 */
public class SystemUserMessageRequest extends BaseRequest {

    private Integer messageId;
    private String messageText;
    private Integer systemUserId;
    private String systemUserText;
    private Integer id;
    private String code;

    public SystemUserMessageRequest() {
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


