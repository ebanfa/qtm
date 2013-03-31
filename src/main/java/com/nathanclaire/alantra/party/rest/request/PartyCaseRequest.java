/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.party.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;

/**
 * PartyCaseRequest 
 * @author Edward Banfa
 */
public class PartyCaseRequest extends BaseRequest {

    private Integer id;
    private Integer caseRole;
    private Integer communicationEvent;
    private Integer caseStatusType;
    private String code;
    private String name;
    private String description;

    public PartyCaseRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
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


