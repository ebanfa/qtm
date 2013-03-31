/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.base.rest.request;

import java.util.Date;
import java.math.BigDecimal;

/**
 * CommunicationEventRequest 
 * @author Edward Banfa
 */
public class CommunicationEventRequest extends BaseRequest {

    private Integer id;
    private Integer communicationEventType;
    private Integer communicationEventPurpose;
    private Integer contactMechanismType;
    private Integer partyRelationship;
    private Integer communicationEventStatusType;
    private String code;
    private String name;
    private String description;

    public CommunicationEventRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommunicationEventType() {
        return this.communicationEventType;
    }
    
    public void setCommunicationEventType(Integer communicationEventType) {
        this.communicationEventType = communicationEventType;
    }

    public Integer getCommunicationEventPurpose() {
        return this.communicationEventPurpose;
    }
    
    public void setCommunicationEventPurpose(Integer communicationEventPurpose) {
        this.communicationEventPurpose = communicationEventPurpose;
    }

    public Integer getContactMechanismType() {
        return this.contactMechanismType;
    }
    
    public void setContactMechanismType(Integer contactMechanismType) {
        this.contactMechanismType = contactMechanismType;
    }

    public Integer getPartyRelationship() {
        return this.partyRelationship;
    }
    
    public void setPartyRelationship(Integer partyRelationship) {
        this.partyRelationship = partyRelationship;
    }

    public Integer getCommunicationEventStatusType() {
        return this.communicationEventStatusType;
    }
    
    public void setCommunicationEventStatusType(Integer communicationEventStatusType) {
        this.communicationEventStatusType = communicationEventStatusType;
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


