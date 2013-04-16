/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.party.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * PartyRelationshipRequest 
 * @author Edward Banfa
 */
public class PartyRelationshipRequest extends BaseRequest {

    private Integer fromPartyRole;
    private Integer toPartyRole;
    private Integer partyRelationshipType;
    private String name;
    private String description;
    private String comment;
    private Date fromDt;
    private Date thruDt;
    private Integer id;
    private String code;

    public PartyRelationshipRequest() {
    }

    public Integer getFromPartyRole() {
        return this.fromPartyRole;
    }
    
    public void setFromPartyRole(Integer fromPartyRole) {
        this.fromPartyRole = fromPartyRole;
    }

    public Integer getToPartyRole() {
        return this.toPartyRole;
    }
    
    public void setToPartyRole(Integer toPartyRole) {
        this.toPartyRole = toPartyRole;
    }

    public Integer getPartyRelationshipType() {
        return this.partyRelationshipType;
    }
    
    public void setPartyRelationshipType(Integer partyRelationshipType) {
        this.partyRelationshipType = partyRelationshipType;
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

    public String getComment() {
        return this.comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
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


