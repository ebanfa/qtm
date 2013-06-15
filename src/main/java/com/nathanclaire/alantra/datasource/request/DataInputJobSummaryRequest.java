/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * DataInputJobSummaryRequest 
 * @author Edward Banfa
 */
public class DataInputJobSummaryRequest extends BaseRequest {

    private Integer dataInputJobId;
    private String dataInputJobText;
    private Integer dataInputJobSummaryStatusId;
    private String dataInputJobSummaryStatusText;
    private int recordsRead;
    private int recordsRejected;
    private int recordsReadPercentage;
    private String primEntityName;
    private int primEntityRecordsCreated;
    private int primEntityRecordsRejected;
    private int primEntityInputPercenatge;
    private String secEntityName;
    private Integer secEntityRecordsCreated;
    private Integer secEntityRecordsRejected;
    private Integer secEntityInputPercenatge;
    private int totalEntitiesCreated;
    private int totalEntitiesRejected;
    private int totalEntitiesInputPercentage;
    private Integer txnsMatched;
    private Integer txnsUnmatched;
    private String txnMatchPercentage;
    private Integer id;
    private String code;

    public DataInputJobSummaryRequest() {
    }

    public Integer getDataInputJobId() {
        return this.dataInputJobId;
    }
    
    public void setDataInputJobId(Integer dataInputJobId) {
        this.dataInputJobId = dataInputJobId;
    }

    public String getDataInputJobText() {
        return this.dataInputJobText;
    }
    
    public void setDataInputJobText(String dataInputJobText) {
        this.dataInputJobText = dataInputJobText;
    }

    public Integer getDataInputJobSummaryStatusId() {
        return this.dataInputJobSummaryStatusId;
    }
    
    public void setDataInputJobSummaryStatusId(Integer dataInputJobSummaryStatusId) {
        this.dataInputJobSummaryStatusId = dataInputJobSummaryStatusId;
    }

    public String getDataInputJobSummaryStatusText() {
        return this.dataInputJobSummaryStatusText;
    }
    
    public void setDataInputJobSummaryStatusText(String dataInputJobSummaryStatusText) {
        this.dataInputJobSummaryStatusText = dataInputJobSummaryStatusText;
    }

    public int getRecordsRead() {
        return this.recordsRead;
    }
    
    public void setRecordsRead(int recordsRead) {
        this.recordsRead = recordsRead;
    }

    public int getRecordsRejected() {
        return this.recordsRejected;
    }
    
    public void setRecordsRejected(int recordsRejected) {
        this.recordsRejected = recordsRejected;
    }

    public int getRecordsReadPercentage() {
        return this.recordsReadPercentage;
    }
    
    public void setRecordsReadPercentage(int recordsReadPercentage) {
        this.recordsReadPercentage = recordsReadPercentage;
    }

    public String getPrimEntityName() {
        return this.primEntityName;
    }
    
    public void setPrimEntityName(String primEntityName) {
        this.primEntityName = primEntityName;
    }

    public int getPrimEntityRecordsCreated() {
        return this.primEntityRecordsCreated;
    }
    
    public void setPrimEntityRecordsCreated(int primEntityRecordsCreated) {
        this.primEntityRecordsCreated = primEntityRecordsCreated;
    }

    public int getPrimEntityRecordsRejected() {
        return this.primEntityRecordsRejected;
    }
    
    public void setPrimEntityRecordsRejected(int primEntityRecordsRejected) {
        this.primEntityRecordsRejected = primEntityRecordsRejected;
    }

    public int getPrimEntityInputPercenatge() {
        return this.primEntityInputPercenatge;
    }
    
    public void setPrimEntityInputPercenatge(int primEntityInputPercenatge) {
        this.primEntityInputPercenatge = primEntityInputPercenatge;
    }

    public String getSecEntityName() {
        return this.secEntityName;
    }
    
    public void setSecEntityName(String secEntityName) {
        this.secEntityName = secEntityName;
    }

    public Integer getSecEntityRecordsCreated() {
        return this.secEntityRecordsCreated;
    }
    
    public void setSecEntityRecordsCreated(Integer secEntityRecordsCreated) {
        this.secEntityRecordsCreated = secEntityRecordsCreated;
    }

    public Integer getSecEntityRecordsRejected() {
        return this.secEntityRecordsRejected;
    }
    
    public void setSecEntityRecordsRejected(Integer secEntityRecordsRejected) {
        this.secEntityRecordsRejected = secEntityRecordsRejected;
    }

    public Integer getSecEntityInputPercenatge() {
        return this.secEntityInputPercenatge;
    }
    
    public void setSecEntityInputPercenatge(Integer secEntityInputPercenatge) {
        this.secEntityInputPercenatge = secEntityInputPercenatge;
    }

    public int getTotalEntitiesCreated() {
        return this.totalEntitiesCreated;
    }
    
    public void setTotalEntitiesCreated(int totalEntitiesCreated) {
        this.totalEntitiesCreated = totalEntitiesCreated;
    }

    public int getTotalEntitiesRejected() {
        return this.totalEntitiesRejected;
    }
    
    public void setTotalEntitiesRejected(int totalEntitiesRejected) {
        this.totalEntitiesRejected = totalEntitiesRejected;
    }

    public int getTotalEntitiesInputPercentage() {
        return this.totalEntitiesInputPercentage;
    }
    
    public void setTotalEntitiesInputPercentage(int totalEntitiesInputPercentage) {
        this.totalEntitiesInputPercentage = totalEntitiesInputPercentage;
    }

    public Integer getTxnsMatched() {
        return this.txnsMatched;
    }
    
    public void setTxnsMatched(Integer txnsMatched) {
        this.txnsMatched = txnsMatched;
    }

    public Integer getTxnsUnmatched() {
        return this.txnsUnmatched;
    }
    
    public void setTxnsUnmatched(Integer txnsUnmatched) {
        this.txnsUnmatched = txnsUnmatched;
    }

    public String getTxnMatchPercentage() {
        return this.txnMatchPercentage;
    }
    
    public void setTxnMatchPercentage(String txnMatchPercentage) {
        this.txnMatchPercentage = txnMatchPercentage;
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


