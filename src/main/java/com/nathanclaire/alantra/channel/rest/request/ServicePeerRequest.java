/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.channel.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;

/**
 * ServicePeerRequest 
 * @author Edward Banfa
 */
public class ServicePeerRequest extends BaseRequest {

    private Integer service;
    private Integer host;
    private String name;
    private String description;
    private Integer id;
    private String code;

    public ServicePeerRequest() {
    }

    public Integer getService() {
        return this.service;
    }
    
    public void setService(Integer service) {
        this.service = service;
    }

    public Integer getHost() {
        return this.host;
    }
    
    public void setHost(Integer host) {
        this.host = host;
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


