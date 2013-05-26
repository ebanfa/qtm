/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.channel.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * ServiceRequest 
 * @author Edward Banfa
 */
public class ServiceRequest extends BaseRequest {

    private Integer serviceTypeId;
    private Integer serviceProtocolAdapterId;
    private Integer serviceModeId;
    private Integer serviceCategoryId;
    private String name;
    private String description;
    private int portNo;
    private String ipAddress;
    private Integer id;
    private String code;

    public ServiceRequest() {
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

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
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


