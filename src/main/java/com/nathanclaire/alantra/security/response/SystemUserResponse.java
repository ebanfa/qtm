/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.security.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * SystemUserResponse 
 * @author Edward Banfa
 */
public class SystemUserResponse extends BaseResponse {

    private Integer systemGroupId;
    private String systemGroupText;
    private String username;
    private String password;
    private String email;
    private String mobile;

    public SystemUserResponse() {
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

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


}


