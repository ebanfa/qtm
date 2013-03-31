/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.rest.request;

import java.util.Date;
import java.math.BigDecimal;

/**
 * PartyClassificationRequest 
 * @author Edward Banfa
 */
public class PartyClassificationRequest extends BaseRequest {

    private Integer id;
    private Integer partyType;
    private Integer partyClassificationType;
    private Integer party;
    private String code;
    private String name;
    private String description;
    private Date fromDt;
    private Date thruDt;

    public PartyClassificationRequest() {
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

    public Integer getPartyClassificationType() {
        return this.partyClassificationType;
    }
    
    public void setPartyClassificationType(Integer partyClassificationType) {
        this.partyClassificationType = partyClassificationType;
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


