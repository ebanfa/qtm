/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.invoice.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.invoice.model.InvoiceRole;

/**
 * InvoiceRoleRequest 
 * @author Edward Banfa
 */
public class InvoiceRoleRequest extends BaseRequest {

    private Integer invoiceRoleType;
    private Integer party;
    private Integer invoice;
    private String description;
    private Integer percentage;
    private Integer id;
    private String code;

    public InvoiceRoleRequest() {
    }

    public Integer getInvoiceRoleType() {
        return this.invoiceRoleType;
    }
    
    public void setInvoiceRoleType(Integer invoiceRoleType) {
        this.invoiceRoleType = invoiceRoleType;
    }

    public Integer getParty() {
        return this.party;
    }
    
    public void setParty(Integer party) {
        this.party = party;
    }

    public Integer getInvoice() {
        return this.invoice;
    }
    
    public void setInvoice(Integer invoice) {
        this.invoice = invoice;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPercentage() {
        return this.percentage;
    }
    
    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
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


