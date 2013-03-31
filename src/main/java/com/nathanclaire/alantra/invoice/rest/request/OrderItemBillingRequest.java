/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.invoice.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;

/**
 * OrderItemBillingRequest 
 * @author Edward Banfa
 */
public class OrderItemBillingRequest extends BaseRequest {

    private Integer id;
    private Integer invoiceItem;
    private Integer productOrderItem;
    private int quantity;
    private BigDecimal amount;
    private String code;
    private String description;

    public OrderItemBillingRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInvoiceItem() {
        return this.invoiceItem;
    }
    
    public void setInvoiceItem(Integer invoiceItem) {
        this.invoiceItem = invoiceItem;
    }

    public Integer getProductOrderItem() {
        return this.productOrderItem;
    }
    
    public void setProductOrderItem(Integer productOrderItem) {
        this.productOrderItem = productOrderItem;
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

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }


}


