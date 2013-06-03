/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.businessdata.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * CurrencyResponse 
 * @author Edward Banfa
 */
public class CurrencyResponse extends BaseResponse {

    private String crncySym;
    private String remarks;

    public CurrencyResponse() {
    }

    public String getCrncySym() {
        return this.crncySym;
    }
    
    public void setCrncySym(String crncySym) {
        this.crncySym = crncySym;
    }

    public String getRemarks() {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


}


