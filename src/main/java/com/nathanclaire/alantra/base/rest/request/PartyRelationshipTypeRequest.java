/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.base.rest.request;

import java.util.Date;
import java.math.BigDecimal;

/**
 * PartyRelationshipTypeRequest 
 * @author Edward Banfa
 */
public class PartyRelationshipTypeRequest extends BaseRequest {

    private Integer id;
    private Integer partyRoleTypeByFromRoleTyId;
    private Integer partyRoleTypeByToRoleTyId;
    private String code;
    private String name;
    private String description;

    public PartyRelationshipTypeRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
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


