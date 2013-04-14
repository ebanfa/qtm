/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.customer.model.Customer;

/**
 * CustomerRequest 
 * @author Edward Banfa
 */
public class CustomerRequest extends BaseRequest {

    private Integer party;
    private Integer id;
    private String code;

    public CustomerRequest() {
    }

    public Integer getParty() {
        return this.party;
    }
    
    public void setParty(Integer party) {
        this.party = party;
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


