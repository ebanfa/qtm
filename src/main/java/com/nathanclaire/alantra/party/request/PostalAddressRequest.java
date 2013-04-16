/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.party.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * PostalAddressRequest 
 * @author Edward Banfa
 */
public class PostalAddressRequest extends BaseRequest {

    private Integer contactMechanism;
    private String address1;
    private String address2;
    private String directions;
    private String description;
    private Integer id;
    private String code;

    public PostalAddressRequest() {
    }

    public Integer getContactMechanism() {
        return this.contactMechanism;
    }
    
    public void setContactMechanism(Integer contactMechanism) {
        this.contactMechanism = contactMechanism;
    }

    public String getAddress1() {
        return this.address1;
    }
    
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return this.address2;
    }
    
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getDirections() {
        return this.directions;
    }
    
    public void setDirections(String directions) {
        this.directions = directions;
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

