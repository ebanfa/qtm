/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.businessdata.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * BusinessUnitResponse 
 * @author Edward Banfa
 */
public class BusinessUnitResponse extends BaseResponse {

    private Integer countryId;
    private String countryText;

    public BusinessUnitResponse() {
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


}


