/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.product.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.product.model.ProductCategory;

/**
 * ProductCategoryRequest 
 * @author Edward Banfa
 */
public class ProductCategoryRequest extends BaseRequest {

    private Integer productCategoryType;
    private String name;
    private String description;
    private Integer id;
    private String code;

    public ProductCategoryRequest() {
    }

    public Integer getProductCategoryType() {
        return this.productCategoryType;
    }
    
    public void setProductCategoryType(Integer productCategoryType) {
        this.productCategoryType = productCategoryType;
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


