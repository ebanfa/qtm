/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * DataInputProcessorsResponse 
 * @author Edward Banfa
 */
public class DataInputProcessorsResponse extends BaseResponse {

    private Integer dataProcessorId;
    private String dataProcessorText;
    private Integer dataInputId;
    private String dataInputText;

    public DataInputProcessorsResponse() {
    }

    public Integer getDataProcessorId() {
        return this.dataProcessorId;
    }
    
    public void setDataProcessorId(Integer dataProcessorId) {
        this.dataProcessorId = dataProcessorId;
    }

    public String getDataProcessorText() {
        return this.dataProcessorText;
    }
    
    public void setDataProcessorText(String dataProcessorText) {
        this.dataProcessorText = dataProcessorText;
    }

    public Integer getDataInputId() {
        return this.dataInputId;
    }
    
    public void setDataInputId(Integer dataInputId) {
        this.dataInputId = dataInputId;
    }

    public String getDataInputText() {
        return this.dataInputText;
    }
    
    public void setDataInputText(String dataInputText) {
        this.dataInputText = dataInputText;
    }


}


