/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.businessdata.rest.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;

/**
 * GeoBoundryAssociationRequest 
 * @author Edward Banfa
 */
public class GeoBoundryAssociationRequest extends BaseRequest {

    private Integer id;
    private Integer geoBoundryByToGeoId;
    private Integer geoBoundryByFromGeoId;
    private String code;
    private String description;

    public GeoBoundryAssociationRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGeoBoundryByToGeoId() {
        return this.geoBoundryByToGeoId;
    }
    
    public void setGeoBoundryByToGeoId(Integer geoBoundryByToGeoId) {
        this.geoBoundryByToGeoId = geoBoundryByToGeoId;
    }

    public Integer getGeoBoundryByFromGeoId() {
        return this.geoBoundryByFromGeoId;
    }
    
    public void setGeoBoundryByFromGeoId(Integer geoBoundryByFromGeoId) {
        this.geoBoundryByFromGeoId = geoBoundryByFromGeoId;
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


}


