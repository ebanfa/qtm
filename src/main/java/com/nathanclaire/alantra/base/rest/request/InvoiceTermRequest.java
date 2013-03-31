/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.base.rest.request;

import java.util.Date;
import java.math.BigDecimal;

/**
 * InvoiceTermRequest 
 * @author Edward Banfa
 */
public class InvoiceTermRequest extends BaseRequest {

    private Integer id;
    private Integer termType;
    private String code;
    private BigDecimal termVal;
    private String description;

    public InvoiceTermRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTermType() {
        return this.termType;
    }
    
    public void setTermType(Integer termType) {
        this.termType = termType;
    }

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getTermVal() {
        return this.termVal;
    }
    
    public void setTermVal(BigDecimal termVal) {
        this.termVal = termVal;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }


}


