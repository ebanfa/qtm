/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * CustomerTypeRequest 
 * @author Edward Banfa
 */
public class CustomerTypeRequest extends BaseRequest {

    private Integer id;
    private Integer customerCategoryId;
    private String customerCategoryText;
    private String name;
    private String description;
    private String code;

    public CustomerTypeRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerCategoryId() {
        return this.customerCategoryId;
    }
    
    public void setCustomerCategoryId(Integer customerCategoryId) {
        this.customerCategoryId = customerCategoryId;
    }

    public String getCustomerCategoryText() {
        return this.customerCategoryText;
    }
    
    public void setCustomerCategoryText(String customerCategoryText) {
        this.customerCategoryText = customerCategoryText;
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


