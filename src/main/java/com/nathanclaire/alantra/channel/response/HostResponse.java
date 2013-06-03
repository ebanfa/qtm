/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.channel.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * HostResponse 
 * @author Edward Banfa
 */
public class HostResponse extends BaseResponse {

    private Integer serviceProtocolAdapterId;
    private String serviceProtocolAdapterText;
    private Integer hostTypeId;
    private String hostTypeText;
    private int portNo;
    private String ipAddress;

    public HostResponse() {
    }

    public Integer getServiceProtocolAdapterId() {
        return this.serviceProtocolAdapterId;
    }
    
    public void setServiceProtocolAdapterId(Integer serviceProtocolAdapterId) {
        this.serviceProtocolAdapterId = serviceProtocolAdapterId;
    }

    public String getServiceProtocolAdapterText() {
        return this.serviceProtocolAdapterText;
    }
    
    public void setServiceProtocolAdapterText(String serviceProtocolAdapterText) {
        this.serviceProtocolAdapterText = serviceProtocolAdapterText;
    }

    public Integer getHostTypeId() {
        return this.hostTypeId;
    }
    
    public void setHostTypeId(Integer hostTypeId) {
        this.hostTypeId = hostTypeId;
    }

    public String getHostTypeText() {
        return this.hostTypeText;
    }
    
    public void setHostTypeText(String hostTypeText) {
        this.hostTypeText = hostTypeText;
    }

    public int getPortNo() {
        return this.portNo;
    }
    
    public void setPortNo(int portNo) {
        this.portNo = portNo;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }
    
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }


}


