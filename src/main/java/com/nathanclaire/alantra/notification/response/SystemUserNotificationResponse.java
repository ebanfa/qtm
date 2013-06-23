/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.notification.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * SystemUserNotificationResponse 
 * @author Edward Banfa
 */
public class SystemUserNotificationResponse extends BaseResponse {

    private Integer systemUserId;
    private String systemUserText;
    private Integer notificationTypeId;
    private String notificationTypeText;

    public SystemUserNotificationResponse() {
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


}


