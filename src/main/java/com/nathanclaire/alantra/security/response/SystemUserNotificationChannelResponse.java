/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.security.response;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * SystemUserNotificationChannelResponse 
 * @author Edward Banfa
 */
public class SystemUserNotificationChannelResponse extends BaseResponse {

    private Integer dataChannelCategoryId;
    private String dataChannelCategoryText;
    private Integer systemUserId;
    private String systemUserText;

    public SystemUserNotificationChannelResponse() {
    }

    public Integer getDataChannelCategoryId() {
        return this.dataChannelCategoryId;
    }
    
    public void setDataChannelCategoryId(Integer dataChannelCategoryId) {
        this.dataChannelCategoryId = dataChannelCategoryId;
    }

    public String getDataChannelCategoryText() {
        return this.dataChannelCategoryText;
    }
    
    public void setDataChannelCategoryText(String dataChannelCategoryText) {
        this.dataChannelCategoryText = dataChannelCategoryText;
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


