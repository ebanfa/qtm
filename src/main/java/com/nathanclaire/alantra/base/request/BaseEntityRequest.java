/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.base.request;

import java.util.Date;
import java.math.BigDecimal;

/**
 * BaseEntityRequest 
 * @author Edward Banfa
 */
public class BaseEntityRequest extends BaseRequest {

    private Integer id;
    private String code;

    public BaseEntityRequest() {
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


