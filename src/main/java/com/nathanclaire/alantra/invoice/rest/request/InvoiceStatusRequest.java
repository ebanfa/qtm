/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.invoice.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;

/**
 * InvoiceStatusRequest 
 * @author Edward Banfa
 */
public class InvoiceStatusRequest extends BaseRequest {

    private Integer id;
    private Integer invoiceStatusType;
    private Integer invoice;
    private String code;
    private String name;
    private String description;

    public InvoiceStatusRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
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

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
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


}


