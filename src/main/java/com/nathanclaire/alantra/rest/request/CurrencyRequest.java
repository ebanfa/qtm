/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.rest.request;

import java.util.Date;
import java.math.BigDecimal;

/**
 * CurrencyRequest 
 * @author Edward Banfa
 */
public class CurrencyRequest extends BaseRequest {

    private Integer id;
    private String code;
    private String crncyNm;
    private String crncySym;
    private String remarks;

    public CurrencyRequest() {
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

    public String getCrncyNm() {
        return this.crncyNm;
    }
    
    public void setCrncyNm(String crncyNm) {
        this.crncyNm = crncyNm;
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


