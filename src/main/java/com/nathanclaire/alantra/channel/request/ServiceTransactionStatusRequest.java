/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.channel.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * ServiceTransactionStatusRequest 
 * @author Edward Banfa
 */
public class ServiceTransactionStatusRequest extends BaseRequest {

    private String name;
    private String description;
    private Integer id;
    private String code;

    public ServiceTransactionStatusRequest() {
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


