/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.invoice.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.invoice.model.InvoiceTerm;

/**
 * InvoiceTermRequest 
 * @author Edward Banfa
 */
public class InvoiceTermRequest extends BaseRequest {

    private Integer termType;
    private BigDecimal termVal;
    private String description;
    private Integer id;
    private String code;

    public InvoiceTermRequest() {
    }

    public Integer getTermType() {
        return this.termType;
    }
    
    public void setTermType(Integer termType) {
        this.termType = termType;
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


