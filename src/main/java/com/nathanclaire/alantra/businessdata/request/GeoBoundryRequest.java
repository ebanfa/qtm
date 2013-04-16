/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.businessdata.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * GeoBoundryRequest 
 * @author Edward Banfa
 */
public class GeoBoundryRequest extends BaseRequest {

    private Integer geoBoundaryType;
    private String name;
    private String abbreviation;
    private String description;
    private Integer id;
    private String code;

    public GeoBoundryRequest() {
    }

    public Integer getGeoBoundaryType() {
        return this.geoBoundaryType;
    }
    
    public void setGeoBoundaryType(Integer geoBoundaryType) {
        this.geoBoundaryType = geoBoundaryType;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }
    
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
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


