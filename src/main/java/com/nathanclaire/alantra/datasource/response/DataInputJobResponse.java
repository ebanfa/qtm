/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * DataInputJobResponse 
 * @author Edward Banfa
 */
public class DataInputJobResponse extends BaseResponse {

    private Integer dataInputJobStatusId;
    private String dataInputJobStatusText;
    private Integer dataInputJobTypeId;
    private String dataInputJobTypeText;
    private Integer dataInputId;
    private String dataInputText;
    private int diFreqVal;
    private String diFreqCd;

    public DataInputJobResponse() {
    }

    public Integer getDataInputJobStatusId() {
        return this.dataInputJobStatusId;
    }
    
    public void setDataInputJobStatusId(Integer dataInputJobStatusId) {
        this.dataInputJobStatusId = dataInputJobStatusId;
    }

    public String getDataInputJobStatusText() {
        return this.dataInputJobStatusText;
    }
    
    public void setDataInputJobStatusText(String dataInputJobStatusText) {
        this.dataInputJobStatusText = dataInputJobStatusText;
    }

    public Integer getDataInputJobTypeId() {
        return this.dataInputJobTypeId;
    }
    
    public void setDataInputJobTypeId(Integer dataInputJobTypeId) {
        this.dataInputJobTypeId = dataInputJobTypeId;
    }

    public String getDataInputJobTypeText() {
        return this.dataInputJobTypeText;
    }
    
    public void setDataInputJobTypeText(String dataInputJobTypeText) {
        this.dataInputJobTypeText = dataInputJobTypeText;
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

    public int getDiFreqVal() {
        return this.diFreqVal;
    }
    
    public void setDiFreqVal(int diFreqVal) {
        this.diFreqVal = diFreqVal;
    }

    public String getDiFreqCd() {
        return this.diFreqCd;
    }
    
    public void setDiFreqCd(String diFreqCd) {
        this.diFreqCd = diFreqCd;
    }


}


