/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.security.request;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * SystemUserNotificationChannelRequest 
 * @author Edward Banfa
 */
public class SystemUserNotificationChannelRequest extends BaseRequest {

    private Integer dataChannelCategoryId;
    private String dataChannelCategoryText;
    private Integer systemUserId;
    private String systemUserText;
    private Integer id;
    private String code;

    public SystemUserNotificationChannelRequest() {
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


