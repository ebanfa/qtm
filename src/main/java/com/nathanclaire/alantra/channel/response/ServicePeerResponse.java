/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.channel.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * ServicePeerResponse 
 * @author Edward Banfa
 */
public class ServicePeerResponse extends BaseResponse {

    private Integer serviceId;
    private String serviceText;
    private Integer hostId;
    private String hostText;

    public ServicePeerResponse() {
    }

    public Integer getServiceId() {
        return this.serviceId;
    }
    
    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceText() {
        return this.serviceText;
    }
    
    public void setServiceText(String serviceText) {
        this.serviceText = serviceText;
    }

    public Integer getHostId() {
        return this.hostId;
    }
    
    public void setHostId(Integer hostId) {
        this.hostId = hostId;
    }

    public String getHostText() {
        return this.hostText;
    }
    
    public void setHostText(String hostText) {
        this.hostText = hostText;
    }


}


