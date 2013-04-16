/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.businessdata.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * PostalAddressBoundryRequest 
 * @author Edward Banfa
 */
public class PostalAddressBoundryRequest extends BaseRequest {

    private Integer postalAddress;
    private Integer geoBoundry;
    private String description;
    private Integer id;
    private String code;

    public PostalAddressBoundryRequest() {
    }

    public Integer getPostalAddress() {
        return this.postalAddress;
    }
    
    public void setPostalAddress(Integer postalAddress) {
        this.postalAddress = postalAddress;
    }

    public Integer getGeoBoundry() {
        return this.geoBoundry;
    }
    
    public void setGeoBoundry(Integer geoBoundry) {
        this.geoBoundry = geoBoundry;
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


