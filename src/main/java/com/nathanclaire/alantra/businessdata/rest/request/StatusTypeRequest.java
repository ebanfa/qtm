/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.businessdata.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.businessdata.model.StatusType;

/**
 * StatusTypeRequest 
 * @author Edward Banfa
 */
public class StatusTypeRequest extends BaseRequest {

    private String name;
    private String description;
    private Integer id;
    private String code;

    public StatusTypeRequest() {
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


