/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.party.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;

/**
 * EstimatedProductCostRequest 
 * @author Edward Banfa
 */
public class EstimatedProductCostRequest extends BaseRequest {

    private Integer product;
    private Integer costComponentType;
    private Integer productFeature;
    private Integer geoBoundry;
    private BigDecimal cost;
    private String description;
    private Date fromDt;
    private Date toDt;
    private Integer id;
    private String code;

    public EstimatedProductCostRequest() {
    }

    public Integer getProduct() {
        return this.product;
    }
    
    public void setProduct(Integer product) {
        this.product = product;
    }

    public Integer getCostComponentType() {
        return this.costComponentType;
    }
    
    public void setCostComponentType(Integer costComponentType) {
        this.costComponentType = costComponentType;
    }

    public Integer getProductFeature() {
        return this.productFeature;
    }
    
    public void setProductFeature(Integer productFeature) {
        this.productFeature = productFeature;
    }

    public Integer getGeoBoundry() {
        return this.geoBoundry;
    }
    
    public void setGeoBoundry(Integer geoBoundry) {
        this.geoBoundry = geoBoundry;
    }

    public BigDecimal getCost() {
        return this.cost;
    }
    
    public void setCost(BigDecimal cost) {
        this.cost = cost;
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


