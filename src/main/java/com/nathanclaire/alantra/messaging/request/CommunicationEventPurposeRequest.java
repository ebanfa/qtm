/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.messaging.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * CommunicationEventPurposeRequest 
 * @author Edward Banfa
 */
public class CommunicationEventPurposeRequest extends BaseRequest {

    private Integer communicationEventPurposeType;
    private String name;
    private String description;
    private Integer id;
    private String code;

    public CommunicationEventPurposeRequest() {
    }

    public Integer getCommunicationEventPurposeType() {
        return this.communicationEventPurposeType;
    }
    
    public void setCommunicationEventPurposeType(Integer communicationEventPurposeType) {
        this.communicationEventPurposeType = communicationEventPurposeType;
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


