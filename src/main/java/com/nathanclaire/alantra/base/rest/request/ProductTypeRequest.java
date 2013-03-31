/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.base.rest.request;

import java.util.Date;
import java.math.BigDecimal;

/**
 * ProductTypeRequest 
 * @author Edward Banfa
 */
public class ProductTypeRequest extends BaseRequest {

    private Integer id;
    private String code;
    private String names;
    private String description;

    public ProductTypeRequest() {
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


}

