/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.party.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * PartyTypeRequest 
 * @author Edward Banfa
 */
public class PartyTypeRequest extends BaseRequest {

    private Integer partyType;
    private String name;
    private String description;
    private Integer id;
    private String code;

    public PartyTypeRequest() {
    }

    public Integer getPartyType() {
        return this.partyType;
    }
    
    public void setPartyType(Integer partyType) {
        this.partyType = partyType;
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

