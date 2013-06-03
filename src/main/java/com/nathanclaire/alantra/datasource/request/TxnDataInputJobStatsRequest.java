/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * TxnDataInputJobStatsRequest 
 * @author Edward Banfa
 */
public class TxnDataInputJobStatsRequest extends BaseRequest {

    private int jobId;
    private String name;
    private String description;
    private int recordsRead;
    private int recordsCreated;
    private Integer txnsCreated;
    private Integer txnsMatched;
    private Integer txnsUnmatched;
    private String matchPercentage;
    private Integer id;
    private String code;

    public TxnDataInputJobStatsRequest() {
    }

    public int getJobId() {
        return this.jobId;
    }
    
    public void setJobId(int jobId) {
        this.jobId = jobId;
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


