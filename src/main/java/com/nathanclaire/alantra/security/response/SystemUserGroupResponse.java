/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.security.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * SystemUserGroupResponse 
 * @author Edward Banfa
 */
public class SystemUserGroupResponse extends BaseResponse {

    private Integer systemUserId;
    private String systemUserText;
    private Integer systemGroupId;
    private String systemGroupText;

    public SystemUserGroupResponse() {
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

    public Integer getSystemGroupId() {
        return this.systemGroupId;
    }
    
    public void setSystemGroupId(Integer systemGroupId) {
        this.systemGroupId = systemGroupId;
    }

    public String getSystemGroupText() {
        return this.systemGroupText;
    }
    
    public void setSystemGroupText(String systemGroupText) {
        this.systemGroupText = systemGroupText;
    }


}


