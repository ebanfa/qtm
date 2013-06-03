/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.businessdata.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * CurrencyRequest 
 * @author Edward Banfa
 */
public class CurrencyRequest extends BaseRequest {

    private String name;
    private String crncySym;
    private String remarks;
    private Integer id;
    private String code;

    public CurrencyRequest() {
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
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


