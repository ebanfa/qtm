/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.base.rest.request;

import java.util.Date;
import java.math.BigDecimal;

/**
 * CommunicationEventPurposeRequest 
 * @author Edward Banfa
 */
public class CommunicationEventPurposeRequest extends BaseRequest {

    private Integer id;
    private Integer communicationEventPurposeType;
    private String code;
    private String name;
    private String description;

    public CommunicationEventPurposeRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommunicationEventPurposeType() {
        return this.communicationEventPurposeType;
    }
    
    public void setCommunicationEventPurposeType(Integer communicationEventPurposeType) {
        this.communicationEventPurposeType = communicationEventPurposeType;
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


