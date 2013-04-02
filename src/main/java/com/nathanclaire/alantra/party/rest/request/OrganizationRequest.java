/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.party.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;

/**
 * OrganizationRequest 
 * @author Edward Banfa
 */
public class OrganizationRequest extends BaseRequest {

    private Integer party;
    private String taxIdNo;
    private Integer id;
    private String code;

    public OrganizationRequest() {
    }

    public Integer getParty() {
        return this.party;
    }
    
    public void setParty(Integer party) {
        this.party = party;
    }

    public String getTaxIdNo() {
        return this.taxIdNo;
    }
    
    public void setTaxIdNo(String taxIdNo) {
        this.taxIdNo = taxIdNo;
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


