/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.party.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * PartyCaseRequest 
 * @author Edward Banfa
 */
public class PartyCaseRequest extends BaseRequest {

    private Integer caseRole;
    private Integer communicationEvent;
    private Integer caseStatusType;
    private String name;
    private String description;
    private Integer id;
    private String code;

    public PartyCaseRequest() {
    }

    public Integer getCaseRole() {
        return this.caseRole;
    }
    
    public void setCaseRole(Integer caseRole) {
        this.caseRole = caseRole;
    }

    public Integer getCommunicationEvent() {
        return this.communicationEvent;
    }
    
    public void setCommunicationEvent(Integer communicationEvent) {
        this.communicationEvent = communicationEvent;
    }

    public Integer getCaseStatusType() {
        return this.caseStatusType;
    }
    
    public void setCaseStatusType(Integer caseStatusType) {
        this.caseStatusType = caseStatusType;
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


