/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * DataInputJobRecordsSummaryResponse 
 * @author Edward Banfa
 */
public class DataInputJobRecordsSummaryResponse extends BaseResponse {

    private Integer dataInputJobSummaryId;
    private String dataInputJobSummaryText;
    private Integer dataInputJobRecordsSummaryStatusId;
    private String dataInputJobRecordsSummaryStatusText;
    private int recordNumber;
    private byte recordData;
    private String primaryEntityName;
    private int primaryEntityId;
    private String primaryEntityStatus;
    private String primaryEntityStatusDesc;
    private String secondaryEntityName;
    private Integer secondaryEntityId;
    private String secondaryEntityStatus;
    private String secondaryEntityStatusDesc;

    public DataInputJobRecordsSummaryResponse() {
    }

    public Integer getDataInputJobSummaryId() {
        return this.dataInputJobSummaryId;
    }
    
    public void setDataInputJobSummaryId(Integer dataInputJobSummaryId) {
        this.dataInputJobSummaryId = dataInputJobSummaryId;
    }

    public String getDataInputJobSummaryText() {
        return this.dataInputJobSummaryText;
    }
    
    public void setDataInputJobSummaryText(String dataInputJobSummaryText) {
        this.dataInputJobSummaryText = dataInputJobSummaryText;
    }

    public Integer getDataInputJobRecordsSummaryStatusId() {
        return this.dataInputJobRecordsSummaryStatusId;
    }
    
    public void setDataInputJobRecordsSummaryStatusId(Integer dataInputJobRecordsSummaryStatusId) {
        this.dataInputJobRecordsSummaryStatusId = dataInputJobRecordsSummaryStatusId;
    }

    public String getDataInputJobRecordsSummaryStatusText() {
        return this.dataInputJobRecordsSummaryStatusText;
    }
    
    public void setDataInputJobRecordsSummaryStatusText(String dataInputJobRecordsSummaryStatusText) {
        this.dataInputJobRecordsSummaryStatusText = dataInputJobRecordsSummaryStatusText;
    }

    public int getRecordNumber() {
        return this.recordNumber;
    }
    
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }

    public byte getRecordData() {
        return this.recordData;
    }
    
    public void setRecordData(byte recordData) {
        this.recordData = recordData;
    }

    public String getPrimaryEntityName() {
        return this.primaryEntityName;
    }
    
    public void setPrimaryEntityName(String primaryEntityName) {
        this.primaryEntityName = primaryEntityName;
    }

    public int getPrimaryEntityId() {
        return this.primaryEntityId;
    }
    
    public void setPrimaryEntityId(int primaryEntityId) {
        this.primaryEntityId = primaryEntityId;
    }

    public String getPrimaryEntityStatus() {
        return this.primaryEntityStatus;
    }
    
    public void setPrimaryEntityStatus(String primaryEntityStatus) {
        this.primaryEntityStatus = primaryEntityStatus;
    }

    public String getPrimaryEntityStatusDesc() {
        return this.primaryEntityStatusDesc;
    }
    
    public void setPrimaryEntityStatusDesc(String primaryEntityStatusDesc) {
        this.primaryEntityStatusDesc = primaryEntityStatusDesc;
    }

    public String getSecondaryEntityName() {
        return this.secondaryEntityName;
    }
    
    public void setSecondaryEntityName(String secondaryEntityName) {
        this.secondaryEntityName = secondaryEntityName;
    }

    public Integer getSecondaryEntityId() {
        return this.secondaryEntityId;
    }
    
    public void setSecondaryEntityId(Integer secondaryEntityId) {
        this.secondaryEntityId = secondaryEntityId;
    }

    public String getSecondaryEntityStatus() {
        return this.secondaryEntityStatus;
    }
    
    public void setSecondaryEntityStatus(String secondaryEntityStatus) {
        this.secondaryEntityStatus = secondaryEntityStatus;
    }

    public String getSecondaryEntityStatusDesc() {
        return this.secondaryEntityStatusDesc;
    }
    
    public void setSecondaryEntityStatusDesc(String secondaryEntityStatusDesc) {
        this.secondaryEntityStatusDesc = secondaryEntityStatusDesc;
    }


}


