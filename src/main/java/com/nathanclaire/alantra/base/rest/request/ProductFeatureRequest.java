/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.base.rest.request;

import java.util.Date;
import java.math.BigDecimal;

/**
 * ProductFeatureRequest 
 * @author Edward Banfa
 */
public class ProductFeatureRequest extends BaseRequest {

    private Integer id;
    private Integer productFeatureType;
    private Integer productFeatureCategory;
    private String code;
    private String name;
    private String description;

    public ProductFeatureRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductFeatureType() {
        return this.productFeatureType;
    }
    
    public void setProductFeatureType(Integer productFeatureType) {
        this.productFeatureType = productFeatureType;
    }

    public Integer getProductFeatureCategory() {
        return this.productFeatureCategory;
    }
    
    public void setProductFeatureCategory(Integer productFeatureCategory) {
        this.productFeatureCategory = productFeatureCategory;
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


