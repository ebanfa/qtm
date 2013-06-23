/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.notification.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * NotificationTypeResponse 
 * @author Edward Banfa
 */
public class NotificationTypeResponse extends BaseResponse {

    private Integer templateId;
    private String templateText;
    private Integer notificationCategoryId;
    private String notificationCategoryText;
    private Character autoRespFg;

    public NotificationTypeResponse() {
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

    public Integer getNotificationCategoryId() {
        return this.notificationCategoryId;
    }
    
    public void setNotificationCategoryId(Integer notificationCategoryId) {
        this.notificationCategoryId = notificationCategoryId;
    }

    public String getNotificationCategoryText() {
        return this.notificationCategoryText;
    }
    
    public void setNotificationCategoryText(String notificationCategoryText) {
        this.notificationCategoryText = notificationCategoryText;
    }

    public Character getAutoRespFg() {
        return this.autoRespFg;
    }
    
    public void setAutoRespFg(Character autoRespFg) {
        this.autoRespFg = autoRespFg;
    }


}


