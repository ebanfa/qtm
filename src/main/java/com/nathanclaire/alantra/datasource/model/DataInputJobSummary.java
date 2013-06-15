/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.model.BaseEntity;

/**
 * DataInputJobSummary 
 * @author Edward Banfa
 */
@Entity
@Table(name="DATA_INPUT_JOB_SUMMARY"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class DataInputJobSummary  extends BaseEntity implements java.io.Serializable {

	private DataInputJob dataInputJob;
	private DataInputJobSummaryStatus dataInputJobSummaryStatus;
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
	private Set<DataInputJobRecordsSummary> dataInputJobRecordsSummaries = new HashSet<DataInputJobRecordsSummary>(0);

    public DataInputJobSummary() {
    }

    public DataInputJobSummary(DataInputJob dataInputJob, DataInputJobSummaryStatus dataInputJobSummaryStatus, String code, int recordsRead, int recordsRejected, int recordsReadPercentage, String primEntityName, int primEntityRecordsCreated, int primEntityRecordsRejected, int primEntityInputPercenatge, int totalEntitiesCreated, int totalEntitiesRejected, int totalEntitiesInputPercentage, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.recordsRead = recordsRead;
		this.recordsRejected = recordsRejected;
		this.recordsReadPercentage = recordsReadPercentage;
		this.primEntityName = primEntityName;
		this.primEntityRecordsCreated = primEntityRecordsCreated;
		this.primEntityRecordsRejected = primEntityRecordsRejected;
		this.primEntityInputPercenatge = primEntityInputPercenatge;
		this.totalEntitiesCreated = totalEntitiesCreated;
		this.totalEntitiesRejected = totalEntitiesRejected;
		this.totalEntitiesInputPercentage = totalEntitiesInputPercentage;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public DataInputJobSummary(DataInputJob dataInputJob, DataInputJobSummaryStatus dataInputJobSummaryStatus, String code, int recordsRead, int recordsRejected, int recordsReadPercentage, String primEntityName, int primEntityRecordsCreated, int primEntityRecordsRejected, int primEntityInputPercenatge, String secEntityName, Integer secEntityRecordsCreated, Integer secEntityRecordsRejected, Integer secEntityInputPercenatge, int totalEntitiesCreated, int totalEntitiesRejected, int totalEntitiesInputPercentage, Integer txnsMatched, Integer txnsUnmatched, String txnMatchPercentage, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<DataInputJobRecordsSummary> dataInputJobRecordsSummaries ) 
    {
		this.dataInputJob = dataInputJob;
		this.dataInputJobSummaryStatus = dataInputJobSummaryStatus;
		this.code = code;
		this.recordsRead = recordsRead;
		this.recordsRejected = recordsRejected;
		this.recordsReadPercentage = recordsReadPercentage;
		this.primEntityName = primEntityName;
		this.primEntityRecordsCreated = primEntityRecordsCreated;
		this.primEntityRecordsRejected = primEntityRecordsRejected;
		this.primEntityInputPercenatge = primEntityInputPercenatge;
		this.secEntityName = secEntityName;
		this.secEntityRecordsCreated = secEntityRecordsCreated;
		this.secEntityRecordsRejected = secEntityRecordsRejected;
		this.secEntityInputPercenatge = secEntityInputPercenatge;
		this.totalEntitiesCreated = totalEntitiesCreated;
		this.totalEntitiesRejected = totalEntitiesRejected;
		this.totalEntitiesInputPercentage = totalEntitiesInputPercentage;
		this.txnsMatched = txnsMatched;
		this.txnsUnmatched = txnsUnmatched;
		this.txnMatchPercentage = txnMatchPercentage;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.dataInputJobRecordsSummaries = dataInputJobRecordsSummaries;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="JOB_ID", nullable=false)
    @JsonIgnore
    public DataInputJob getDataInputJob() 
    {
        return this.dataInputJob;
    }
    
    public void setDataInputJob(DataInputJob dataInputJob)
    {
        this.dataInputJob = dataInputJob;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="STATUS_ID", nullable=false)
    @JsonIgnore
    public DataInputJobSummaryStatus getDataInputJobSummaryStatus() 
    {
        return this.dataInputJobSummaryStatus;
    }
    
    public void setDataInputJobSummaryStatus(DataInputJobSummaryStatus dataInputJobSummaryStatus)
    {
        this.dataInputJobSummaryStatus = dataInputJobSummaryStatus;
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
		
    @Column(name="RECORDS_REJECTED" , nullable=false)
    public int getRecordsRejected() 
    {
        return this.recordsRejected;
    }
    
    public void setRecordsRejected(int recordsRejected) 
    {
        this.recordsRejected = recordsRejected;
    }
		
    @Column(name="RECORDS_READ_PERCENTAGE" , nullable=false)
    public int getRecordsReadPercentage() 
    {
        return this.recordsReadPercentage;
    }
    
    public void setRecordsReadPercentage(int recordsReadPercentage) 
    {
        this.recordsReadPercentage = recordsReadPercentage;
    }
		
    @Column(name="PRIM_ENTITY_NAME" , nullable=false, length=150)
    public String getPrimEntityName() 
    {
        return this.primEntityName;
    }
    
    public void setPrimEntityName(String primEntityName) 
    {
        this.primEntityName = primEntityName;
    }
		
    @Column(name="PRIM_ENTITY_RECORDS_CREATED" , nullable=false)
    public int getPrimEntityRecordsCreated() 
    {
        return this.primEntityRecordsCreated;
    }
    
    public void setPrimEntityRecordsCreated(int primEntityRecordsCreated) 
    {
        this.primEntityRecordsCreated = primEntityRecordsCreated;
    }
		
    @Column(name="PRIM_ENTITY_RECORDS_REJECTED" , nullable=false)
    public int getPrimEntityRecordsRejected() 
    {
        return this.primEntityRecordsRejected;
    }
    
    public void setPrimEntityRecordsRejected(int primEntityRecordsRejected) 
    {
        this.primEntityRecordsRejected = primEntityRecordsRejected;
    }
		
    @Column(name="PRIM_ENTITY_INPUT_PERCENATGE" , nullable=false)
    public int getPrimEntityInputPercenatge() 
    {
        return this.primEntityInputPercenatge;
    }
    
    public void setPrimEntityInputPercenatge(int primEntityInputPercenatge) 
    {
        this.primEntityInputPercenatge = primEntityInputPercenatge;
    }
		
    @Column(name="SEC_ENTITY_NAME" , unique=true, length=150)
    public String getSecEntityName() 
    {
        return this.secEntityName;
    }
    
    public void setSecEntityName(String secEntityName) 
    {
        this.secEntityName = secEntityName;
    }
		
    @Column(name="SEC_ENTITY_RECORDS_CREATED" , unique=true)
    public Integer getSecEntityRecordsCreated() 
    {
        return this.secEntityRecordsCreated;
    }
    
    public void setSecEntityRecordsCreated(Integer secEntityRecordsCreated) 
    {
        this.secEntityRecordsCreated = secEntityRecordsCreated;
    }
		
    @Column(name="SEC_ENTITY_RECORDS_REJECTED" , unique=true)
    public Integer getSecEntityRecordsRejected() 
    {
        return this.secEntityRecordsRejected;
    }
    
    public void setSecEntityRecordsRejected(Integer secEntityRecordsRejected) 
    {
        this.secEntityRecordsRejected = secEntityRecordsRejected;
    }
		
    @Column(name="SEC_ENTITY_INPUT_PERCENATGE" , unique=true)
    public Integer getSecEntityInputPercenatge() 
    {
        return this.secEntityInputPercenatge;
    }
    
    public void setSecEntityInputPercenatge(Integer secEntityInputPercenatge) 
    {
        this.secEntityInputPercenatge = secEntityInputPercenatge;
    }
		
    @Column(name="TOTAL_ENTITIES_CREATED" , nullable=false)
    public int getTotalEntitiesCreated() 
    {
        return this.totalEntitiesCreated;
    }
    
    public void setTotalEntitiesCreated(int totalEntitiesCreated) 
    {
        this.totalEntitiesCreated = totalEntitiesCreated;
    }
		
    @Column(name="TOTAL_ENTITIES_REJECTED" , nullable=false)
    public int getTotalEntitiesRejected() 
    {
        return this.totalEntitiesRejected;
    }
    
    public void setTotalEntitiesRejected(int totalEntitiesRejected) 
    {
        this.totalEntitiesRejected = totalEntitiesRejected;
    }
		
    @Column(name="TOTAL_ENTITIES_INPUT_PERCENTAGE" , nullable=false)
    public int getTotalEntitiesInputPercentage() 
    {
        return this.totalEntitiesInputPercentage;
    }
    
    public void setTotalEntitiesInputPercentage(int totalEntitiesInputPercentage) 
    {
        this.totalEntitiesInputPercentage = totalEntitiesInputPercentage;
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
		
    @Column(name="TXN_MATCH_PERCENTAGE" , unique=true, length=3)
    public String getTxnMatchPercentage() 
    {
        return this.txnMatchPercentage;
    }
    
    public void setTxnMatchPercentage(String txnMatchPercentage) 
    {
        this.txnMatchPercentage = txnMatchPercentage;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="dataInputJobSummary")
    @JsonIgnore
    public Set<DataInputJobRecordsSummary> getDataInputJobRecordsSummaries() 
    {
        return this.dataInputJobRecordsSummaries;
    }
    
    public void setDataInputJobRecordsSummaries(Set<DataInputJobRecordsSummary> dataInputJobRecordsSummaries) 
    {
        this.dataInputJobRecordsSummaries = dataInputJobRecordsSummaries;
    }			


}


