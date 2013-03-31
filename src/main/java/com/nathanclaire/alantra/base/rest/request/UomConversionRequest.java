/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.base.rest.request;

import java.util.Date;
import java.math.BigDecimal;

/**
 * UomConversionRequest 
 * @author Edward Banfa
 */
public class UomConversionRequest extends BaseRequest {

    private Integer id;
    private int fromUomId;
    private int toUomId;
    private int conversionFactor;
    private String code;
    private String name;
    private String description;

    public UomConversionRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
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


