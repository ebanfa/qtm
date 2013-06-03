/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.businessdata.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * CountryResponse 
 * @author Edward Banfa
 */
public class CountryResponse extends BaseResponse {

    private Integer currencyId;
    private String currencyText;
    private String cntryCdIso2;
    private String cntryCdIso3;
    private String remarks;

    public CountryResponse() {
    }

    public Integer getCurrencyId() {
        return this.currencyId;
    }
    
    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyText() {
        return this.currencyText;
    }
    
    public void setCurrencyText(String currencyText) {
        this.currencyText = currencyText;
    }

    public String getCntryCdIso2() {
        return this.cntryCdIso2;
    }
    
    public void setCntryCdIso2(String cntryCdIso2) {
        this.cntryCdIso2 = cntryCdIso2;
    }

    public String getCntryCdIso3() {
        return this.cntryCdIso3;
    }
    
    public void setCntryCdIso3(String cntryCdIso3) {
        this.cntryCdIso3 = cntryCdIso3;
    }

    public String getRemarks() {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


}


