/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.security.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * CurrentUserSessionRequest 
 * @author Edward Banfa
 */
public class CurrentUserSessionRequest extends BaseRequest {

    private Integer systemUserId;
    private String systemUserText;
    private Date loginTime;
    private Date lastActivityTime;
    private Integer id;
    private String code;

    public CurrentUserSessionRequest() {
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

    public Date getLoginTime() {
        return this.loginTime;
    }
    
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLastActivityTime() {
        return this.lastActivityTime;
    }
    
    public void setLastActivityTime(Date lastActivityTime) {
        this.lastActivityTime = lastActivityTime;
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


