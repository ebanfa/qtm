/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.base.rest.request;

import java.util.Date;
import java.math.BigDecimal;

/**
 * CaseRoleRequest 
 * @author Edward Banfa
 */
public class CaseRoleRequest extends BaseRequest {

    private Integer id;
    private Integer party;
    private Integer caseRoleType;
    private String code;
    private String name;
    private String description;

    public CaseRoleRequest() {
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

    public Integer getCaseRoleType() {
        return this.caseRoleType;
    }
    
    public void setCaseRoleType(Integer caseRoleType) {
        this.caseRoleType = caseRoleType;
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


