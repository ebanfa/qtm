/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.messaging.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.messaging.model.CommunicationEvent;

/**
 * CommunicationEventRequest 
 * @author Edward Banfa
 */
public class CommunicationEventRequest extends BaseRequest {

    private Integer communicationEventType;
    private Integer communicationEventPurpose;
    private Integer contactMechanismType;
    private Integer partyRelationship;
    private Integer communicationEventStatusType;
    private String description;
    private String fromEmail;
    private String toEmail;
    private String fromPhone;
    private String toPhone;
    private String subject;
    private String content;
    private Date startDate;
    private Date endDate;
    private Integer id;
    private String code;

    public CommunicationEventRequest() {
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

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getFromEmail() {
        return this.fromEmail;
    }
    
    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getToEmail() {
        return this.toEmail;
    }
    
    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getFromPhone() {
        return this.fromPhone;
    }
    
    public void setFromPhone(String fromPhone) {
        this.fromPhone = fromPhone;
    }

    public String getToPhone() {
        return this.toPhone;
    }
    
    public void setToPhone(String toPhone) {
        this.toPhone = toPhone;
    }

    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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


