/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.invoice.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.invoice.model.InvoiceStatus;

/**
 * InvoiceStatusRequest 
 * @author Edward Banfa
 */
public class InvoiceStatusRequest extends BaseRequest {

    private Integer invoiceStatusType;
    private Integer invoice;
    private String name;
    private String description;
    private Integer id;
    private String code;

    public InvoiceStatusRequest() {
    }

    public Integer getInvoiceStatusType() {
        return this.invoiceStatusType;
    }
    
    public void setInvoiceStatusType(Integer invoiceStatusType) {
        this.invoiceStatusType = invoiceStatusType;
    }

    public Integer getInvoice() {
        return this.invoice;
    }
    
    public void setInvoice(Integer invoice) {
        this.invoice = invoice;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
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


