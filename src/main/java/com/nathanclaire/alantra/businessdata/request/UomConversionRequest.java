/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.businessdata.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * UomConversionRequest 
 * @author Edward Banfa
 */
public class UomConversionRequest extends BaseRequest {

    private int fromUomId;
    private int toUomId;
    private int conversionFactor;
    private String name;
    private String description;
    private Integer id;
    private String code;

    public UomConversionRequest() {
    }

    public int getFromUomId() {
        return this.fromUomId;
    }
    
    public void setFromUomId(int fromUomId) {
        this.fromUomId = fromUomId;
    }

    public int getToUomId() {
        return this.toUomId;
    }
    
    public void setToUomId(int toUomId) {
        this.toUomId = toUomId;
    }

    public int getConversionFactor() {
        return this.conversionFactor;
    }
    
    public void setConversionFactor(int conversionFactor) {
        this.conversionFactor = conversionFactor;
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


