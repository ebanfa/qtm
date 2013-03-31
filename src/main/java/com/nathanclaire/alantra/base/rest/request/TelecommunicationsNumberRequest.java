/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.base.rest.request;

import java.util.Date;
import java.math.BigDecimal;

/**
 * TelecommunicationsNumberRequest 
 * @author Edward Banfa
 */
public class TelecommunicationsNumberRequest extends BaseRequest {

    private Integer id;
    private Integer contactMechanism;
    private String code;
    private String areaCode;
    private String contactNo;
    private String countryCd;
    private String description;

    public TelecommunicationsNumberRequest() {
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

    public String getAreaCode() {
        return this.areaCode;
    }
    
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getContactNo() {
        return this.contactNo;
    }
    
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getCountryCd() {
        return this.countryCd;
    }
    
    public void setCountryCd(String countryCd) {
        this.countryCd = countryCd;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }


}


