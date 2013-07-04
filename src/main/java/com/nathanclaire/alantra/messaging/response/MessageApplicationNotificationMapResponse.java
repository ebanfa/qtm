/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.messaging.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * MessageApplicationNotificationMapResponse 
 * @author Edward Banfa
 */
public class MessageApplicationNotificationMapResponse extends BaseResponse {

    private Integer messageApplicationActionId;
    private String messageApplicationActionText;
    private Integer notificationTypeId;
    private String notificationTypeText;
    private String tagVal;
    private char isRegexFg;

    public MessageApplicationNotificationMapResponse() {
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

    public Integer getNotificationTypeId() {
        return this.notificationTypeId;
    }
    
    public void setNotificationTypeId(Integer notificationTypeId) {
        this.notificationTypeId = notificationTypeId;
    }

    public String getNotificationTypeText() {
        return this.notificationTypeText;
    }
    
    public void setNotificationTypeText(String notificationTypeText) {
        this.notificationTypeText = notificationTypeText;
    }

    public String getTagVal() {
        return this.tagVal;
    }
    
    public void setTagVal(String tagVal) {
        this.tagVal = tagVal;
    }

    public char getIsRegexFg() {
        return this.isRegexFg;
    }
    
    public void setIsRegexFg(char isRegexFg) {
        this.isRegexFg = isRegexFg;
    }


}


