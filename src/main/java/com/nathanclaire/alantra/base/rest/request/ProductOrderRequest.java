/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.base.rest.request;

import java.util.Date;
import java.math.BigDecimal;

/**
 * ProductOrderRequest 
 * @author Edward Banfa
 */
public class ProductOrderRequest extends BaseRequest {

    private Integer id;
    private Integer productOrderType;
    private String code;
    private String name;
    private String description;
    private Date orderDt;

    public ProductOrderRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductOrderType() {
        return this.productOrderType;
    }
    
    public void setProductOrderType(Integer productOrderType) {
        this.productOrderType = productOrderType;
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

    public Date getOrderDt() {
        return this.orderDt;
    }
    
    public void setOrderDt(Date orderDt) {
        this.orderDt = orderDt;
    }


}


