/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.transaction.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * ServiceTransactionTypeRequest 
 * @author Edward Banfa
 */
public class ServiceTransactionTypeRequest extends BaseRequest {

    private Integer serviceTransactionCategoryId;
    private String serviceTransactionCategoryText;
    private String name;
    private String description;
    private Integer id;
    private String code;

    public ServiceTransactionTypeRequest() {
    }

    public Integer getServiceTransactionCategoryId() {
        return this.serviceTransactionCategoryId;
    }
    
    public void setServiceTransactionCategoryId(Integer serviceTransactionCategoryId) {
        this.serviceTransactionCategoryId = serviceTransactionCategoryId;
    }

    public String getServiceTransactionCategoryText() {
        return this.serviceTransactionCategoryText;
    }
    
    public void setServiceTransactionCategoryText(String serviceTransactionCategoryText) {
        this.serviceTransactionCategoryText = serviceTransactionCategoryText;
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


