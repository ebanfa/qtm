/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.channel.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.channel.model.ServiceProtocolAdapter;

/**
 * ServiceProtocolAdapterRequest 
 * @author Edward Banfa
 */
public class ServiceProtocolAdapterRequest extends BaseRequest {

    private String name;
    private String description;
    private String className;
    private Integer id;
    private String code;

    public ServiceProtocolAdapterRequest() {
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

    public String getClassName() {
        return this.className;
    }
    
    public void setClassName(String className) {
        this.className = className;
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


