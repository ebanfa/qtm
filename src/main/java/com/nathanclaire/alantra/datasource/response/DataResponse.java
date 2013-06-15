/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * DataResponse 
 * @author Edward Banfa
 */
public class DataResponse extends BaseResponse {

    private Integer dataTypeId;
    private String dataTypeText;
    private Integer dataStructureId;
    private String dataStructureText;
    private Integer dataChannelId;
    private String dataChannelText;

    public DataResponse() {
    }

    public Integer getDataTypeId() {
        return this.dataTypeId;
    }
    
    public void setDataTypeId(Integer dataTypeId) {
        this.dataTypeId = dataTypeId;
    }

    public String getDataTypeText() {
        return this.dataTypeText;
    }
    
    public void setDataTypeText(String dataTypeText) {
        this.dataTypeText = dataTypeText;
    }

    public Integer getDataStructureId() {
        return this.dataStructureId;
    }
    
    public void setDataStructureId(Integer dataStructureId) {
        this.dataStructureId = dataStructureId;
    }

    public String getDataStructureText() {
        return this.dataStructureText;
    }
    
    public void setDataStructureText(String dataStructureText) {
        this.dataStructureText = dataStructureText;
    }

    public Integer getDataChannelId() {
        return this.dataChannelId;
    }
    
    public void setDataChannelId(Integer dataChannelId) {
        this.dataChannelId = dataChannelId;
    }

    public String getDataChannelText() {
        return this.dataChannelText;
    }
    
    public void setDataChannelText(String dataChannelText) {
        this.dataChannelText = dataChannelText;
    }


}


