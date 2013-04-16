/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.order.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * ProductOrderItemRequest 
 * @author Edward Banfa
 */
public class ProductOrderItemRequest extends BaseRequest {

    private Integer productOrderItemType;
    private Integer productOrder;
    private Integer product;
    private Integer productFeature;
    private String name;
    private String description;
    private int quantity;
    private BigDecimal unitPrice;
    private Date expectDeliveryDt;
    private String instructions;
    private String remarks;
    private Integer id;
    private String code;

    public ProductOrderItemRequest() {
    }

    public Integer getProductOrderItemType() {
        return this.productOrderItemType;
    }
    
    public void setProductOrderItemType(Integer productOrderItemType) {
        this.productOrderItemType = productOrderItemType;
    }

    public Integer getProductOrder() {
        return this.productOrder;
    }
    
    public void setProductOrder(Integer productOrder) {
        this.productOrder = productOrder;
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

    public int getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return this.unitPrice;
    }
    
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Date getExpectDeliveryDt() {
        return this.expectDeliveryDt;
    }
    
    public void setExpectDeliveryDt(Date expectDeliveryDt) {
        this.expectDeliveryDt = expectDeliveryDt;
    }

    public String getInstructions() {
        return this.instructions;
    }
    
    public void setInstructions(String instructions) {
        this.instructions = instructions;
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


