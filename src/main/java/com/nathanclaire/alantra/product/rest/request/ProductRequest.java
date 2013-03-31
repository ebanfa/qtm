/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.product.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;

/**
 * ProductRequest 
 * @author Edward Banfa
 */
public class ProductRequest extends BaseRequest {

    private Integer id;
    private Integer productType;
    private String code;
    private String name;
    private String description;
    private Date introductionDt;
    private Date discountinuedDt;

    public ProductRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductType() {
        return this.productType;
    }
    
    public void setProductType(Integer productType) {
        this.productType = productType;
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

    public Date getIntroductionDt() {
        return this.introductionDt;
    }
    
    public void setIntroductionDt(Date introductionDt) {
        this.introductionDt = introductionDt;
    }

    public Date getDiscountinuedDt() {
        return this.discountinuedDt;
    }
    
    public void setDiscountinuedDt(Date discountinuedDt) {
        this.discountinuedDt = discountinuedDt;
    }


}


