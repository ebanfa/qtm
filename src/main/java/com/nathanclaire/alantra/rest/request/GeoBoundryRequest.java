/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.rest.request;

import java.util.Date;
import java.math.BigDecimal;

/**
 * GeoBoundryRequest 
 * @author Edward Banfa
 */
public class GeoBoundryRequest extends BaseRequest {

    private Integer id;
    private Integer geoBoundaryType;
    private String code;
    private String name;
    private String abbreviation;
    private String description;

    public GeoBoundryRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGeoBoundaryType() {
        return this.geoBoundaryType;
    }
    
    public void setGeoBoundaryType(Integer geoBoundaryType) {
        this.geoBoundaryType = geoBoundaryType;
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


}


