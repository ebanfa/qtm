/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.party.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;

/**
 * PartyRelationshipTypeRequest 
 * @author Edward Banfa
 */
public class PartyRelationshipTypeRequest extends BaseRequest {

    private Integer partyRoleTypeByFromRoleTyId;
    private Integer partyRoleTypeByToRoleTyId;
    private String name;
    private String description;
    private Integer id;
    private String code;

    public PartyRelationshipTypeRequest() {
    }

    public Integer getPartyRoleTypeByFromRoleTyId() {
        return this.partyRoleTypeByFromRoleTyId;
    }
    
    public void setPartyRoleTypeByFromRoleTyId(Integer partyRoleTypeByFromRoleTyId) {
        this.partyRoleTypeByFromRoleTyId = partyRoleTypeByFromRoleTyId;
    }

    public Integer getPartyRoleTypeByToRoleTyId() {
        return this.partyRoleTypeByToRoleTyId;
    }
    
    public void setPartyRoleTypeByToRoleTyId(Integer partyRoleTypeByToRoleTyId) {
        this.partyRoleTypeByToRoleTyId = partyRoleTypeByToRoleTyId;
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


