/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.base.rest.request;

import java.util.Date;
import java.math.BigDecimal;

/**
 * PartyContactMechanismPurposeRequest 
 * @author Edward Banfa
 */
public class PartyContactMechanismPurposeRequest extends BaseRequest {

    private Integer id;
    private Integer contactMechanismPurposeType;
    private Integer contactMechanism;
    private String code;
    private Date fromDt;
    private Date toDt;
    private String name;
    private String description;

    public PartyContactMechanismPurposeRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContactMechanismPurposeType() {
        return this.contactMechanismPurposeType;
    }
    
    public void setContactMechanismPurposeType(Integer contactMechanismPurposeType) {
        this.contactMechanismPurposeType = contactMechanismPurposeType;
    }

    public Integer getContactMechanism() {
        return this.contactMechanism;
    }
    
    public void setContactMechanism(Integer contactMechanism) {
        this.contactMechanism = contactMechanism;
    }

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public Date getFromDt() {
        return this.fromDt;
    }
    
    public void setFromDt(Date fromDt) {
        this.fromDt = fromDt;
    }

    public Date getToDt() {
        return this.toDt;
    }
    
    public void setToDt(Date toDt) {
        this.toDt = toDt;
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


