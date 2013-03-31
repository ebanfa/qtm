/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.rest.request;

import java.util.Date;
import java.math.BigDecimal;

/**
 * PartyTypeRequest 
 * @author Edward Banfa
 */
public class PartyTypeRequest extends BaseRequest {

    private Integer id;
    private Integer partyType;
    private String code;
    private String name;
    private String description;

    public PartyTypeRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPartyType() {
        return this.partyType;
    }
    
    public void setPartyType(Integer partyType) {
        this.partyType = partyType;
    }

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
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


}


