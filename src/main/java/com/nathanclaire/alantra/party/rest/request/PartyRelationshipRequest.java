/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.party.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;

/**
 * PartyRelationshipRequest 
 * @author Edward Banfa
 */
public class PartyRelationshipRequest extends BaseRequest {

    private Integer id;
    private Integer partyRole;
    private Integer partyRelationshipType;
    private int toRoleId;
    private String code;
    private String name;
    private String description;
    private String comment;
    private Date fromDt;
    private Date thruDt;

    public PartyRelationshipRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPartyRole() {
        return this.partyRole;
    }
    
    public void setPartyRole(Integer partyRole) {
        this.partyRole = partyRole;
    }

    public Integer getPartyRelationshipType() {
        return this.partyRelationshipType;
    }
    
    public void setPartyRelationshipType(Integer partyRelationshipType) {
        this.partyRelationshipType = partyRelationshipType;
    }

    public int getToRoleId() {
        return this.toRoleId;
    }
    
    public void setToRoleId(int toRoleId) {
        this.toRoleId = toRoleId;
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


}


