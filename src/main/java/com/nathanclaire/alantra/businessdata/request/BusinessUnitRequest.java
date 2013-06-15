/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.businessdata.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * BusinessUnitRequest 
 * @author Edward Banfa
 */
public class BusinessUnitRequest extends BaseRequest {

    private Integer countryId;
    private String countryText;
    private String name;
    private String description;
    private Integer id;
    private String code;

    public BusinessUnitRequest() {
    }

    public Integer getCountryId() {
        return this.countryId;
    }
    
    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountryText() {
        return this.countryText;
    }
    
    public void setCountryText(String countryText) {
        this.countryText = countryText;
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


