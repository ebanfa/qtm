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
    private String serviceTypeText;
    private Integer serviceProtocolAdapterId;
    private String serviceProtocolAdapterText;
    private Integer serviceModeId;
    private String serviceModeText;
    private Integer serviceCategoryId;
    private String serviceCategoryText;
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

    public String getServiceTypeText() {
        return this.serviceTypeText;
    }
    
    public void setServiceTypeText(String serviceTypeText) {
        this.serviceTypeText = serviceTypeText;
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

    public Integer getServiceModeId() {
        return this.serviceModeId;
    }
    
    public void setServiceModeId(Integer serviceModeId) {
        this.serviceModeId = serviceModeId;
    }

    public String getServiceModeText() {
        return this.serviceModeText;
    }
    
    public void setServiceModeText(String serviceModeText) {
        this.serviceModeText = serviceModeText;
    }

    public Integer getServiceCategoryId() {
        return this.serviceCategoryId;
    }
    
    public void setServiceCategoryId(Integer serviceCategoryId) {
        this.serviceCategoryId = serviceCategoryId;
    }

    public String getServiceCategoryText() {
        return this.serviceCategoryText;
    }
    
    public void setServiceCategoryText(String serviceCategoryText) {
        this.serviceCategoryText = serviceCategoryText;
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


