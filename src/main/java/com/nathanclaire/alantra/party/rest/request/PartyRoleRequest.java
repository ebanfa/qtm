/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.party.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.party.model.PartyRole;

/**
 * PartyRoleRequest 
 * @author Edward Banfa
 */
public class PartyRoleRequest extends BaseRequest {

    private Integer partyRoleType;
    private Integer party;
    private String name;
    private String description;
    private Date fromDt;
    private Date thruDt;
    private Integer id;
    private String code;

    public PartyRoleRequest() {
    }

    public Integer getPartyRoleType() {
        return this.partyRoleType;
    }
    
    public void setPartyRoleType(Integer partyRoleType) {
        this.partyRoleType = partyRoleType;
    }

    public Integer getParty() {
        return this.party;
    }
    
    public void setParty(Integer party) {
        this.party = party;
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

    public Date getFromDt() {
        return this.fromDt;
    }
    
    public void setFromDt(Date fromDt) {
        this.fromDt = fromDt;
    }

    public Date getThruDt() {
        return this.thruDt;
    }
    
    public void setThruDt(Date thruDt) {
        this.thruDt = thruDt;
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


