/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.party.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.party.model.CaseRole;

/**
 * CaseRoleRequest 
 * @author Edward Banfa
 */
public class CaseRoleRequest extends BaseRequest {

    private Integer party;
    private Integer caseRoleType;
    private String name;
    private String description;
    private Integer id;
    private String code;

    public CaseRoleRequest() {
    }

    public Integer getParty() {
        return this.party;
    }
    
    public void setParty(Integer party) {
        this.party = party;
    }

    public Integer getCaseRoleType() {
        return this.caseRoleType;
    }
    
    public void setCaseRoleType(Integer caseRoleType) {
        this.caseRoleType = caseRoleType;
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


