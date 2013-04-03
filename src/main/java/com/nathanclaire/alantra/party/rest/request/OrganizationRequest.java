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
}


