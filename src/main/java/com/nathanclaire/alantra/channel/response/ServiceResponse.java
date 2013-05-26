/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.channel.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * ServiceResponse 
 * @author Edward Banfa
 */
public class ServiceResponse extends BaseResponse {

    private Integer serviceTypeId;
    private Integer serviceProtocolAdapterId;
    private Integer serviceModeId;
    private Integer serviceCategoryId;
    private int portNo;
    private String ipAddress;

    public ServiceResponse() {
    }

    public Integer getServiceTypeId() {
        return this.serviceTypeId;
    }
    
    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public Integer getServiceProtocolAdapterId() {
        return this.serviceProtocolAdapterId;
    }
    
    public void setServiceProtocolAdapterId(Integer serviceProtocolAdapterId) {
        this.serviceProtocolAdapterId = serviceProtocolAdapterId;
    }

    public Integer getServiceModeId() {
        return this.serviceModeId;
    }
    
    public void setServiceModeId(Integer serviceModeId) {
        this.serviceModeId = serviceModeId;
    }

    public Integer getServiceCategoryId() {
        return this.serviceCategoryId;
    }
    
    public void setServiceCategoryId(Integer serviceCategoryId) {
        this.serviceCategoryId = serviceCategoryId;
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


