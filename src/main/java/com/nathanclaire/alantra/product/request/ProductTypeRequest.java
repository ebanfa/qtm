/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.product.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * ProductTypeRequest 
 * @author Edward Banfa
 */
public class ProductTypeRequest extends BaseRequest {

    private String names;
    private String description;
    private Integer id;
    private String code;

    public ProductTypeRequest() {
    }

    public String getNames() {
        return this.names;
    }
    
    public void setNames(String names) {
        this.names = names;
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


