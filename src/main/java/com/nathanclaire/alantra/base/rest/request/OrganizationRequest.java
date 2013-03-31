/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.base.rest.request;

import java.util.Date;
import java.math.BigDecimal;

/**
 * OrganizationRequest 
 * @author Edward Banfa
 */
public class OrganizationRequest extends BaseRequest {

    private Integer id;
    private Integer party;
    private String code;
    private String taxIdNo;

    public OrganizationRequest() {
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

    public String getTaxIdNo() {
        return this.taxIdNo;
    }
    
    public void setTaxIdNo(String taxIdNo) {
        this.taxIdNo = taxIdNo;
    }


}


