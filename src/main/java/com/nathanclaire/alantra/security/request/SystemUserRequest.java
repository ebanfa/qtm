/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.security.request;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * SystemUserRequest 
 * @author Edward Banfa
 */
public class SystemUserRequest extends BaseRequest {

    private Integer id;
    private Integer systemGroupId;
    private String systemGroupText;
    private String code;
    private String name;
    private String username;
    private String password;
    private String email;
    private String mobile;
    private String lockedFg;
    private String multiLoginFg;

    public SystemUserRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
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

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
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

    public String getLockedFg() {
        return this.lockedFg;
    }
    
    public void setLockedFg(String lockedFg) {
        this.lockedFg = lockedFg;
    }

    public String getMultiLoginFg() {
        return this.multiLoginFg;
    }
    
    public void setMultiLoginFg(String multiLoginFg) {
        this.multiLoginFg = multiLoginFg;
    }


}


