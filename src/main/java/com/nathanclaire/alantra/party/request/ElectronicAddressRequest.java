/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.party.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * ElectronicAddressRequest 
 * @author Edward Banfa
 */
public class ElectronicAddressRequest extends BaseRequest {

    private Integer contactMechanism;
    private String elecAddrTxt;
    private String description;
    private Integer id;
    private String code;

    public ElectronicAddressRequest() {
    }

    public Integer getContactMechanism() {
        return this.contactMechanism;
    }
    
    public void setContactMechanism(Integer contactMechanism) {
        this.contactMechanism = contactMechanism;
    }

    public String getElecAddrTxt() {
        return this.elecAddrTxt;
    }
    
    public void setElecAddrTxt(String elecAddrTxt) {
        this.elecAddrTxt = elecAddrTxt;
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


