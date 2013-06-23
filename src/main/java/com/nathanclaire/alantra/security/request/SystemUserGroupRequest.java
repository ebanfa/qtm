/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.security.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * SystemUserGroupRequest 
 * @author Edward Banfa
 */
public class SystemUserGroupRequest extends BaseRequest {

    private Integer systemUserId;
    private String systemUserText;
    private Integer systemGroupId;
    private String systemGroupText;
    private Integer id;
    private String code;

    public SystemUserGroupRequest() {
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


