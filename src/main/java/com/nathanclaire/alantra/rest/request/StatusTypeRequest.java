/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.rest.request;

import java.util.Date;
import java.math.BigDecimal;

/**
 * StatusTypeRequest 
 * @author Edward Banfa
 */
public class StatusTypeRequest extends BaseRequest {

    private Integer id;
    private String name;
    private String description;
    private String code;

    public StatusTypeRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
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

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }


}


