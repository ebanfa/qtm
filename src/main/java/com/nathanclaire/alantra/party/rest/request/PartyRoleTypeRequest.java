/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.party.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.party.model.PartyRoleType;

/**
 * PartyRoleTypeRequest 
 * @author Edward Banfa
 */
public class PartyRoleTypeRequest extends BaseRequest {

    private Integer roleType;
    private Integer partyRoleType;
    private String name;
    private String description;
    private Integer id;
    private String code;

    public PartyRoleTypeRequest() {
    }

    public Integer getRoleType() {
        return this.roleType;
    }
    
    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public Integer getPartyRoleType() {
        return this.partyRoleType;
    }
    
    public void setPartyRoleType(Integer partyRoleType) {
        this.partyRoleType = partyRoleType;
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


