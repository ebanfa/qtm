/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.advice.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * AdviceChannelRequest 
 * @author Edward Banfa
 */
public class AdviceChannelRequest extends BaseRequest {

    private Integer adviceChannelTypeId;
    private String adviceChannelTypeText;
    private String ipAddr;
    private int portNo;
    private String username;
    private String password;
    private String description;
    private Integer id;
    private String code;

    public AdviceChannelRequest() {
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

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
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


