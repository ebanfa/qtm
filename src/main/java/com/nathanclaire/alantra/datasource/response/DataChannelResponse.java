/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * DataChannelResponse 
 * @author Edward Banfa
 */
public class DataChannelResponse extends BaseResponse {

    private Integer dataChannelStatusId;
    private String dataChannelStatusText;
    private Integer dataChannelTypeId;
    private String dataChannelTypeText;
    private String ipAddr;
    private Integer portNo;
    private String url;
    private String dsDb;
    private String dsTblNm;
    private String username;
    private String password;
    private String inboundOutboundCd;
    private String connSecurityCd;
    private String authMethodCd;

    public DataChannelResponse() {
    }

    public Integer getDataChannelStatusId() {
        return this.dataChannelStatusId;
    }
    
    public void setDataChannelStatusId(Integer dataChannelStatusId) {
        this.dataChannelStatusId = dataChannelStatusId;
    }

    public String getDataChannelStatusText() {
        return this.dataChannelStatusText;
    }
    
    public void setDataChannelStatusText(String dataChannelStatusText) {
        this.dataChannelStatusText = dataChannelStatusText;
    }

    public Integer getDataChannelTypeId() {
        return this.dataChannelTypeId;
    }
    
    public void setDataChannelTypeId(Integer dataChannelTypeId) {
        this.dataChannelTypeId = dataChannelTypeId;
    }

    public String getDataChannelTypeText() {
        return this.dataChannelTypeText;
    }
    
    public void setDataChannelTypeText(String dataChannelTypeText) {
        this.dataChannelTypeText = dataChannelTypeText;
    }

    public String getIpAddr() {
        return this.ipAddr;
    }
    
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public Integer getPortNo() {
        return this.portNo;
    }
    
    public void setPortNo(Integer portNo) {
        this.portNo = portNo;
    }

    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public String getDsDb() {
        return this.dsDb;
    }
    
    public void setDsDb(String dsDb) {
        this.dsDb = dsDb;
    }

    public String getDsTblNm() {
        return this.dsTblNm;
    }
    
    public void setDsTblNm(String dsTblNm) {
        this.dsTblNm = dsTblNm;
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

    public String getInboundOutboundCd() {
        return this.inboundOutboundCd;
    }
    
    public void setInboundOutboundCd(String inboundOutboundCd) {
        this.inboundOutboundCd = inboundOutboundCd;
    }

    public String getConnSecurityCd() {
        return this.connSecurityCd;
    }
    
    public void setConnSecurityCd(String connSecurityCd) {
        this.connSecurityCd = connSecurityCd;
    }

    public String getAuthMethodCd() {
        return this.authMethodCd;
    }
    
    public void setAuthMethodCd(String authMethodCd) {
        this.authMethodCd = authMethodCd;
    }


}


