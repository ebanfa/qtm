/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * TxnDataInputJobStatsResponse 
 * @author Edward Banfa
 */
public class TxnDataInputJobStatsResponse extends BaseResponse {

    private int jobId;
    private int recordsRead;
    private int recordsCreated;
    private Integer txnsCreated;
    private Integer txnsMatched;
    private Integer txnsUnmatched;
    private String matchPercentage;

    public TxnDataInputJobStatsResponse() {
    }

    public int getJobId() {
        return this.jobId;
    }
    
    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getRecordsRead() {
        return this.recordsRead;
    }
    
    public void setRecordsRead(int recordsRead) {
        this.recordsRead = recordsRead;
    }

    public int getRecordsCreated() {
        return this.recordsCreated;
    }
    
    public void setRecordsCreated(int recordsCreated) {
        this.recordsCreated = recordsCreated;
    }

    public Integer getTxnsCreated() {
        return this.txnsCreated;
    }
    
    public void setTxnsCreated(Integer txnsCreated) {
        this.txnsCreated = txnsCreated;
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

    public String getMatchPercentage() {
        return this.matchPercentage;
    }
    
    public void setMatchPercentage(String matchPercentage) {
        this.matchPercentage = matchPercentage;
    }


}


