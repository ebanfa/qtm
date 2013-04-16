/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.invoice.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * InvoiceRequest 
 * @author Edward Banfa
 */
public class InvoiceRequest extends BaseRequest {

    private Integer invoiceType;
    private Integer partyByToPartyId;
    private Integer contactMechanism;
    private Integer partyByFromPartyId;
    private Integer invoiceTerm;
    private String message;
    private String description;
    private Date invoiceDt;
    private Integer id;
    private String code;

    public InvoiceRequest() {
    }

    public Integer getInvoiceType() {
        return this.invoiceType;
    }
    
    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public Integer getPartyByToPartyId() {
        return this.partyByToPartyId;
    }
    
    public void setPartyByToPartyId(Integer partyByToPartyId) {
        this.partyByToPartyId = partyByToPartyId;
    }

    public Integer getContactMechanism() {
        return this.contactMechanism;
    }
    
    public void setContactMechanism(Integer contactMechanism) {
        this.contactMechanism = contactMechanism;
    }

    public Integer getPartyByFromPartyId() {
        return this.partyByFromPartyId;
    }
    
    public void setPartyByFromPartyId(Integer partyByFromPartyId) {
        this.partyByFromPartyId = partyByFromPartyId;
    }

    public Integer getInvoiceTerm() {
        return this.invoiceTerm;
    }
    
    public void setInvoiceTerm(Integer invoiceTerm) {
        this.invoiceTerm = invoiceTerm;
    }

    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public Date getInvoiceDt() {
        return this.invoiceDt;
    }
    
    public void setInvoiceDt(Date invoiceDt) {
        this.invoiceDt = invoiceDt;
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


