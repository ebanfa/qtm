/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * DataInputRequest 
 * @author Edward Banfa
 */
public class DataInputRequest extends BaseRequest {

    private Integer dataLoaderId;
    private String dataLoaderText;
    private Integer dataId;
    private String dataText;
    private String name;
    private String description;
    private Integer id;
    private String code;

    public DataInputRequest() {
    }

    public Integer getDataLoaderId() {
        return this.dataLoaderId;
    }
    
    public void setDataLoaderId(Integer dataLoaderId) {
        this.dataLoaderId = dataLoaderId;
    }

    public String getDataLoaderText() {
        return this.dataLoaderText;
    }
    
    public void setDataLoaderText(String dataLoaderText) {
        this.dataLoaderText = dataLoaderText;
    }

    public Integer getDataId() {
        return this.dataId;
    }
    
    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public String getDataText() {
        return this.dataText;
    }
    
    public void setDataText(String dataText) {
        this.dataText = dataText;
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


