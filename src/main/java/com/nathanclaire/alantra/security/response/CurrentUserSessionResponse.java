/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.security.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * CurrentUserSessionResponse 
 * @author Edward Banfa
 */
public class CurrentUserSessionResponse extends BaseResponse {

    private Integer systemUserId;
    private String systemUserText;
    private Date loginTime;
    private Date lastActivityTime;

    public CurrentUserSessionResponse() {
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


}


