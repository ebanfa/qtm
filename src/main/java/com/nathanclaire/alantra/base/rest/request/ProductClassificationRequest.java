/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.base.rest.request;

import java.util.Date;
import java.math.BigDecimal;

/**
 * ProductClassificationRequest 
 * @author Edward Banfa
 */
public class ProductClassificationRequest extends BaseRequest {

    private Integer id;
    private Integer productCategory;
    private Integer product;
    private String code;
    private String name;
    private String description;
    private String remarks;
    private Date fromDt;
    private Date toDt;
    private char primaryFg;

    public ProductClassificationRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductCategory() {
        return this.productCategory;
    }
    
    public void setProductCategory(Integer productCategory) {
        this.productCategory = productCategory;
    }

    public Integer getProduct() {
        return this.product;
    }
    
    public void setProduct(Integer product) {
        this.product = product;
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

    public String getRemarks() {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getFromDt() {
        return this.fromDt;
    }
    
    public void setFromDt(Date fromDt) {
        this.fromDt = fromDt;
    }

    public Date getToDt() {
        return this.toDt;
    }
    
    public void setToDt(Date toDt) {
        this.toDt = toDt;
    }

    public char getPrimaryFg() {
        return this.primaryFg;
    }
    
    public void setPrimaryFg(char primaryFg) {
        this.primaryFg = primaryFg;
    }


}


