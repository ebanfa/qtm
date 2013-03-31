/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;

/**
 * BillingAccountRoleTypeRequest 
 * @author Edward Banfa
 */
public class BillingAccountRoleTypeRequest extends BaseRequest {

    private Integer id;
    private String code;
    private String name;
    private String description;

    public BillingAccountRoleTypeRequest() {
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


}


