/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.party.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.party.model.ContactMechanismLink;

/**
 * ContactMechanismLinkRequest 
 * @author Edward Banfa
 */
public class ContactMechanismLinkRequest extends BaseRequest {

    private Integer contactMechanismByToId;
    private Integer contactMechanismByFromId;
    private String description;
    private Integer id;
    private String code;

    public ContactMechanismLinkRequest() {
    }

    public Integer getContactMechanismByToId() {
        return this.contactMechanismByToId;
    }
    
    public void setContactMechanismByToId(Integer contactMechanismByToId) {
        this.contactMechanismByToId = contactMechanismByToId;
    }

    public Integer getContactMechanismByFromId() {
        return this.contactMechanismByFromId;
    }
    
    public void setContactMechanismByFromId(Integer contactMechanismByFromId) {
        this.contactMechanismByFromId = contactMechanismByFromId;
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


