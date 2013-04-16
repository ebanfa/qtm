/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.product.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * ProductFeatureApplicabilityRequest 
 * @author Edward Banfa
 */
public class ProductFeatureApplicabilityRequest extends BaseRequest {

    private Integer product;
    private Integer productFeature;
    private Integer productFeatureApplicabilityType;
    private String name;
    private String description;
    private Date fromDt;
    private Date toDt;
    private Integer id;
    private String code;

    public ProductFeatureApplicabilityRequest() {
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

    public Integer getProductFeatureApplicabilityType() {
        return this.productFeatureApplicabilityType;
    }
    
    public void setProductFeatureApplicabilityType(Integer productFeatureApplicabilityType) {
        this.productFeatureApplicabilityType = productFeatureApplicabilityType;
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


