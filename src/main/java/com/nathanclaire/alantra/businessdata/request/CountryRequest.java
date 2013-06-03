/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.businessdata.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * CountryRequest 
 * @author Edward Banfa
 */
public class CountryRequest extends BaseRequest {

    private Integer currencyId;
    private String currencyText;
    private String name;
    private String cntryCdIso2;
    private String cntryCdIso3;
    private String remarks;
    private Integer id;
    private String code;

    public CountryRequest() {
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

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
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


