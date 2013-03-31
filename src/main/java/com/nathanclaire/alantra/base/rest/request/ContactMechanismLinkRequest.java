/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.base.rest.request;

import java.util.Date;
import java.math.BigDecimal;

/**
 * ContactMechanismLinkRequest 
 * @author Edward Banfa
 */
public class ContactMechanismLinkRequest extends BaseRequest {

    private Integer id;
    private Integer contactMechanismByToId;
    private Integer contactMechanismByFromId;
    private String code;
    private String description;

    public ContactMechanismLinkRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
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

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }


}


