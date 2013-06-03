/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.advice.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * AdviceChannelResponse 
 * @author Edward Banfa
 */
public class AdviceChannelResponse extends BaseResponse {

    private Integer adviceChannelTypeId;
    private String adviceChannelTypeText;
    private String ipAddr;
    private int portNo;
    private String username;
    private String password;

    public AdviceChannelResponse() {
    }

    public Integer getAdviceChannelTypeId() {
        return this.adviceChannelTypeId;
    }
    
    public void setAdviceChannelTypeId(Integer adviceChannelTypeId) {
        this.adviceChannelTypeId = adviceChannelTypeId;
    }

    public String getAdviceChannelTypeText() {
        return this.adviceChannelTypeText;
    }
    
    public void setAdviceChannelTypeText(String adviceChannelTypeText) {
        this.adviceChannelTypeText = adviceChannelTypeText;
    }

    public String getIpAddr() {
        return this.ipAddr;
    }
    
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public int getPortNo() {
        return this.portNo;
    }
    
    public void setPortNo(int portNo) {
        this.portNo = portNo;
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


}


