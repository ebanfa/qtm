/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.rest.request;

import java.util.Date;
import java.math.BigDecimal;

/**
 * ElectronicAddressRequest 
 * @author Edward Banfa
 */
public class ElectronicAddressRequest extends BaseRequest {

    private Integer id;
    private Integer contactMechanism;
    private String code;
    private String elecAddrTxt;
    private String description;

    public ElectronicAddressRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
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


}


