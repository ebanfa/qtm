/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.model.BaseEntity;

/**
 * TxnDataInputJobStats 
 * @author Edward Banfa
 */
@Entity
@Table(name="TXN_DATA_INPUT_JOB_STATS"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TxnDataInputJobStats  extends BaseEntity implements java.io.Serializable {

    private int jobId;
    private String name;
    private String description;
    private int recordsRead;
    private int recordsCreated;
    private Integer txnsCreated;
    private Integer txnsMatched;
    private Integer txnsUnmatched;
    private String matchPercentage;

    public TxnDataInputJobStats() {
    }

    public TxnDataInputJobStats(int jobId, String code, String name, int recordsRead, int recordsCreated, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.jobId = jobId;
		this.code = code;
		this.name = name;
		this.recordsRead = recordsRead;
		this.recordsCreated = recordsCreated;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public TxnDataInputJobStats(int jobId, String code, String name, String description, int recordsRead, int recordsCreated, Integer txnsCreated, Integer txnsMatched, Integer txnsUnmatched, String matchPercentage, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
		this.jobId = jobId;
		this.code = code;
		this.name = name;
		this.description = description;
		this.recordsRead = recordsRead;
		this.recordsCreated = recordsCreated;
		this.txnsCreated = txnsCreated;
		this.txnsMatched = txnsMatched;
		this.txnsUnmatched = txnsUnmatched;
		this.matchPercentage = matchPercentage;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
    }
    
		
    @Column(name="JOB_ID" , nullable=false)
    public int getJobId() 
    {
        return this.jobId;
    }
    
    public void setJobId(int jobId) 
    {
        this.jobId = jobId;
    }
		
    @Column(name="NAME" , nullable=false, length=75)
    public String getName() 
    {
        return this.name;
    }
    
    public void setName(String name) 
    {
        this.name = name;
    }
		
    @Column(name="DESCRIPTION" , unique=true, length=150)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
		
    @Column(name="RECORDS_READ" , nullable=false)
    public int getRecordsRead() 
    {
        return this.recordsRead;
    }
    
    public void setRecordsRead(int recordsRead) 
    {
        this.recordsRead = recordsRead;
    }
		
    @Column(name="RECORDS_CREATED" , nullable=false)
    public int getRecordsCreated() 
    {
        return this.recordsCreated;
    }
    
    public void setRecordsCreated(int recordsCreated) 
    {
        this.recordsCreated = recordsCreated;
    }
		
    @Column(name="TXNS_CREATED" , unique=true)
    public Integer getTxnsCreated() 
    {
        return this.txnsCreated;
    }
    
    public void setTxnsCreated(Integer txnsCreated) 
    {
        this.txnsCreated = txnsCreated;
    }
		
    @Column(name="TXNS_MATCHED" , unique=true)
    public Integer getTxnsMatched() 
    {
        return this.txnsMatched;
    }
    
    public void setTxnsMatched(Integer txnsMatched) 
    {
        this.txnsMatched = txnsMatched;
    }
		
    @Column(name="TXNS_UNMATCHED" , unique=true)
    public Integer getTxnsUnmatched() 
    {
        return this.txnsUnmatched;
    }
    
    public void setTxnsUnmatched(Integer txnsUnmatched) 
    {
        this.txnsUnmatched = txnsUnmatched;
    }
		
    @Column(name="MATCH_PERCENTAGE" , unique=true, length=3)
    public String getMatchPercentage() 
    {
        return this.matchPercentage;
    }
    
    public void setMatchPercentage(String matchPercentage) 
    {
        this.matchPercentage = matchPercentage;
    }


}


