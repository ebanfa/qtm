/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.base.rest.request;

import java.util.Date;
import java.math.BigDecimal;

/**
 * ProductComponentRequest 
 * @author Edward Banfa
 */
public class ProductComponentRequest extends BaseRequest {

    private Integer id;
    private Integer productByInProdId;
    private Integer productByForProdId;
    private Integer locationId;
    private String code;
    private String description;
    private String instruction;
    private String remarks;
    private Integer quantitiyUsed;
    private Date fromDt;
    private Date toDt;

    public ProductComponentRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductByInProdId() {
        return this.productByInProdId;
    }
    
    public void setProductByInProdId(Integer productByInProdId) {
        this.productByInProdId = productByInProdId;
    }

    public Integer getProductByForProdId() {
        return this.productByForProdId;
    }
    
    public void setProductByForProdId(Integer productByForProdId) {
        this.productByForProdId = productByForProdId;
    }

    public Integer getLocationId() {
        return this.locationId;
    }
    
    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
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

    public String getInstruction() {
        return this.instruction;
    }
    
    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getRemarks() {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getQuantitiyUsed() {
        return this.quantitiyUsed;
    }
    
    public void setQuantitiyUsed(Integer quantitiyUsed) {
        this.quantitiyUsed = quantitiyUsed;
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


}


