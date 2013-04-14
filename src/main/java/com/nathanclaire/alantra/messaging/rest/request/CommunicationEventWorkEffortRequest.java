/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.messaging.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.messaging.model.CommunicationEventWorkEffort;

/**
 * CommunicationEventWorkEffortRequest 
 * @author Edward Banfa
 */
public class CommunicationEventWorkEffortRequest extends BaseRequest {

    private Integer workEffort;
    private Integer communicationEvent;
    private String description;
    private Integer id;
    private String code;

    public CommunicationEventWorkEffortRequest() {
    }

    public Integer getWorkEffort() {
        return this.workEffort;
    }
    
    public void setWorkEffort(Integer workEffort) {
        this.workEffort = workEffort;
    }

    public Integer getCommunicationEvent() {
        return this.communicationEvent;
    }
    
    public void setCommunicationEvent(Integer communicationEvent) {
        this.communicationEvent = communicationEvent;
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


