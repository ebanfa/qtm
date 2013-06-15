/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * DataInputResponse 
 * @author Edward Banfa
 */
public class DataInputResponse extends BaseResponse {

    private Integer dataLoaderId;
    private String dataLoaderText;
    private Integer dataId;
    private String dataText;

    public DataInputResponse() {
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


}


