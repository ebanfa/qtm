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

    private Integer serviceType;
    private Integer serviceProtocolAdapter;
    private Integer serviceMode;
    private Integer serviceCategory;
    private String name;
    private String description;
    private int portNo;
    private String ipAddress;
    private Integer id;
    private String code;

    public ServiceRequest() {
    }

    public Integer getServiceType() {
        return this.serviceType;
    }
    
    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getServiceProtocolAdapter() {
        return this.serviceProtocolAdapter;
    }
    
    public void setServiceProtocolAdapter(Integer serviceProtocolAdapter) {
        this.serviceProtocolAdapter = serviceProtocolAdapter;
    }

    public Integer getServiceMode() {
        return this.serviceMode;
    }
    
    public void setServiceMode(Integer serviceMode) {
        this.serviceMode = serviceMode;
    }

    public Integer getServiceCategory() {
        return this.serviceCategory;
    }
    
    public void setServiceCategory(Integer serviceCategory) {
        this.serviceCategory = serviceCategory;
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


