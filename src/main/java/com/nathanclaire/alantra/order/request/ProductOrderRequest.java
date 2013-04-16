/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.order.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * ProductOrderRequest 
 * @author Edward Banfa
 */
public class ProductOrderRequest extends BaseRequest {

    private Integer productOrderType;
    private String name;
    private String description;
    private Date orderDt;
    private Integer fromParty;
    private Integer toParty;
    private BigDecimal amount;
    private Integer id;
    private String code;

    public ProductOrderRequest() {
    }

    public Integer getProductOrderType() {
        return this.productOrderType;
    }
    
    public void setProductOrderType(Integer productOrderType) {
        this.productOrderType = productOrderType;
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

    public Date getOrderDt() {
        return this.orderDt;
    }
    
    public void setOrderDt(Date orderDt) {
        this.orderDt = orderDt;
    }

    public Integer getFromParty() {
        return this.fromParty;
    }
    
    public void setFromParty(Integer fromParty) {
        this.fromParty = fromParty;
    }

    public Integer getToParty() {
        return this.toParty;
    }
    
    public void setToParty(Integer toParty) {
        this.toParty = toParty;
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


