/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;

/**
 * CustomerRequest 
 * @author Edward Banfa
 */
public class CustomerRequest extends BaseRequest {

    private Integer id;
    private Integer party;
    private String code;

    public CustomerRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParty() {
        return this.party;
    }
    
    public void setParty(Integer party) {
        this.party = party;
    }

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }


}


