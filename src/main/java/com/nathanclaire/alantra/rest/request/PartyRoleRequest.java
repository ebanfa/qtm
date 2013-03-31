/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.rest.request;

import java.util.Date;
import java.math.BigDecimal;

/**
 * PartyRoleRequest 
 * @author Edward Banfa
 */
public class PartyRoleRequest extends BaseRequest {

    private Integer id;
    private Integer partyRoleType;
    private Integer party;
    private String code;
    private String name;
    private String description;
    private Date fromDt;
    private Date thruDt;

    public PartyRoleRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
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


}


