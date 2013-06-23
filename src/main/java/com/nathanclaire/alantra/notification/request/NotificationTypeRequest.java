/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.notification.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * NotificationTypeRequest 
 * @author Edward Banfa
 */
public class NotificationTypeRequest extends BaseRequest {

    private Integer templateId;
    private String templateText;
    private Integer notificationCategoryId;
    private String notificationCategoryText;
    private String name;
    private String description;
    private Character autoRespFg;
    private Integer id;
    private String code;

    public NotificationTypeRequest() {
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

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public Character getAutoRespFg() {
        return this.autoRespFg;
    }
    
    public void setAutoRespFg(Character autoRespFg) {
        this.autoRespFg = autoRespFg;
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


