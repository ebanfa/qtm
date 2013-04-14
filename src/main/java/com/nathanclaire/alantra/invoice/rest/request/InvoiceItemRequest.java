/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.invoice.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.invoice.model.InvoiceItem;

/**
 * InvoiceItemRequest 
 * @author Edward Banfa
 */
public class InvoiceItemRequest extends BaseRequest {

    private Integer invoiceItemType;
    private Integer invoiceItemCategory;
    private Integer product;
    private Integer productFeature;
    private Integer invoice;
    private String name;
    private String description;
    private Character taxableFg;
    private int quantity;
    private BigDecimal amount;
    private Integer id;
    private String code;

    public InvoiceItemRequest() {
    }

    public Integer getInvoiceItemType() {
        return this.invoiceItemType;
    }
    
    public void setInvoiceItemType(Integer invoiceItemType) {
        this.invoiceItemType = invoiceItemType;
    }

    public Integer getInvoiceItemCategory() {
        return this.invoiceItemCategory;
    }
    
    public void setInvoiceItemCategory(Integer invoiceItemCategory) {
        this.invoiceItemCategory = invoiceItemCategory;
    }

    public Integer getProduct() {
        return this.product;
    }
    
    public void setProduct(Integer product) {
        this.product = product;
    }

    public Integer getProductFeature() {
        return this.productFeature;
    }
    
    public void setProductFeature(Integer productFeature) {
        this.productFeature = productFeature;
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

    public Character getTaxableFg() {
        return this.taxableFg;
    }
    
    public void setTaxableFg(Character taxableFg) {
        this.taxableFg = taxableFg;
    }

    public int getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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


