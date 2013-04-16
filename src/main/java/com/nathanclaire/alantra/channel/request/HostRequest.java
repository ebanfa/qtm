/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.channel.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * HostRequest 
 * @author Edward Banfa
 */
public class HostRequest extends BaseRequest {

    private Integer serviceProtocolAdapter;
    private Integer hostType;
    private String name;
    private String description;
    private int portNo;
    private String ipAddress;
    private Integer id;
    private String code;

    public HostRequest() {
    }

    public Integer getServiceProtocolAdapter() {
        return this.serviceProtocolAdapter;
    }
    
    public void setServiceProtocolAdapter(Integer serviceProtocolAdapter) {
        this.serviceProtocolAdapter = serviceProtocolAdapter;
    }

    public Integer getHostType() {
        return this.hostType;
    }
    
    public void setHostType(Integer hostType) {
        this.hostType = hostType;
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


