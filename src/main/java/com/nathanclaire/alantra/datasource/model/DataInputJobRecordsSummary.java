/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.model.BaseEntity;

/**
 * DataInputJobRecordsSummary 
 * @author Edward Banfa
 */
@Entity
@Table(name="DATA_INPUT_JOB_RECORDS_SUMMARY"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class DataInputJobRecordsSummary  extends BaseEntity implements java.io.Serializable {

	private DataInputJobSummary dataInputJobSummary;
	private DataInputJobRecordsSummaryStatus dataInputJobRecordsSummaryStatus;
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

    public DataInputJobRecordsSummary() {
    }

    public DataInputJobRecordsSummary(DataInputJobSummary dataInputJobSummary, DataInputJobRecordsSummaryStatus dataInputJobRecordsSummaryStatus, String code, int recordNumber, byte recordData, String primaryEntityName, int primaryEntityId, String primaryEntityStatus, String primaryEntityStatusDesc, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.recordNumber = recordNumber;
		this.recordData = recordData;
		this.primaryEntityName = primaryEntityName;
		this.primaryEntityId = primaryEntityId;
		this.primaryEntityStatus = primaryEntityStatus;
		this.primaryEntityStatusDesc = primaryEntityStatusDesc;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public DataInputJobRecordsSummary(DataInputJobSummary dataInputJobSummary, DataInputJobRecordsSummaryStatus dataInputJobRecordsSummaryStatus, String code, int recordNumber, byte recordData, String primaryEntityName, int primaryEntityId, String primaryEntityStatus, String primaryEntityStatusDesc, String secondaryEntityName, Integer secondaryEntityId, String secondaryEntityStatus, String secondaryEntityStatusDesc, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
		this.dataInputJobSummary = dataInputJobSummary;
		this.dataInputJobRecordsSummaryStatus = dataInputJobRecordsSummaryStatus;
		this.code = code;
		this.recordNumber = recordNumber;
		this.recordData = recordData;
		this.primaryEntityName = primaryEntityName;
		this.primaryEntityId = primaryEntityId;
		this.primaryEntityStatus = primaryEntityStatus;
		this.primaryEntityStatusDesc = primaryEntityStatusDesc;
		this.secondaryEntityName = secondaryEntityName;
		this.secondaryEntityId = secondaryEntityId;
		this.secondaryEntityStatus = secondaryEntityStatus;
		this.secondaryEntityStatusDesc = secondaryEntityStatusDesc;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="JOB_SUMMARY_ID", nullable=false)
    @JsonIgnore
    public DataInputJobSummary getDataInputJobSummary() 
    {
        return this.dataInputJobSummary;
    }
    
    public void setDataInputJobSummary(DataInputJobSummary dataInputJobSummary)
    {
        this.dataInputJobSummary = dataInputJobSummary;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RECORD_SUMMARY_STATUS_ID", nullable=false)
    @JsonIgnore
    public DataInputJobRecordsSummaryStatus getDataInputJobRecordsSummaryStatus() 
    {
        return this.dataInputJobRecordsSummaryStatus;
    }
    
    public void setDataInputJobRecordsSummaryStatus(DataInputJobRecordsSummaryStatus dataInputJobRecordsSummaryStatus)
    {
        this.dataInputJobRecordsSummaryStatus = dataInputJobRecordsSummaryStatus;
    }
		
    @Column(name="RECORD_NUMBER" , nullable=false)
    public int getRecordNumber() 
    {
        return this.recordNumber;
    }
    
    public void setRecordNumber(int recordNumber) 
    {
        this.recordNumber = recordNumber;
    }
		
    @Column(name="RECORD_DATA" , nullable=false)
    public byte getRecordData() 
    {
        return this.recordData;
    }
    
    public void setRecordData(byte recordData) 
    {
        this.recordData = recordData;
    }
		
    @Column(name="PRIMARY_ENTITY_NAME" , nullable=false, length=75)
    public String getPrimaryEntityName() 
    {
        return this.primaryEntityName;
    }
    
    public void setPrimaryEntityName(String primaryEntityName) 
    {
        this.primaryEntityName = primaryEntityName;
    }
		
    @Column(name="PRIMARY_ENTITY_ID" , nullable=false)
    public int getPrimaryEntityId() 
    {
        return this.primaryEntityId;
    }
    
    public void setPrimaryEntityId(int primaryEntityId) 
    {
        this.primaryEntityId = primaryEntityId;
    }
		
    @Column(name="PRIMARY_ENTITY_STATUS" , nullable=false, length=35)
    public String getPrimaryEntityStatus() 
    {
        return this.primaryEntityStatus;
    }
    
    public void setPrimaryEntityStatus(String primaryEntityStatus) 
    {
        this.primaryEntityStatus = primaryEntityStatus;
    }
		
    @Column(name="PRIMARY_ENTITY_STATUS_DESC" , nullable=false, length=150)
    public String getPrimaryEntityStatusDesc() 
    {
        return this.primaryEntityStatusDesc;
    }
    
    public void setPrimaryEntityStatusDesc(String primaryEntityStatusDesc) 
    {
        this.primaryEntityStatusDesc = primaryEntityStatusDesc;
    }
		
    @Column(name="SECONDARY_ENTITY_NAME" , unique=true, length=75)
    public String getSecondaryEntityName() 
    {
        return this.secondaryEntityName;
    }
    
    public void setSecondaryEntityName(String secondaryEntityName) 
    {
        this.secondaryEntityName = secondaryEntityName;
    }
		
    @Column(name="SECONDARY_ENTITY_ID" , unique=true)
    public Integer getSecondaryEntityId() 
    {
        return this.secondaryEntityId;
    }
    
    public void setSecondaryEntityId(Integer secondaryEntityId) 
    {
        this.secondaryEntityId = secondaryEntityId;
    }
		
    @Column(name="SECONDARY_ENTITY_STATUS" , unique=true, length=35)
    public String getSecondaryEntityStatus() 
    {
        return this.secondaryEntityStatus;
    }
    
    public void setSecondaryEntityStatus(String secondaryEntityStatus) 
    {
        this.secondaryEntityStatus = secondaryEntityStatus;
    }
		
    @Column(name="SECONDARY_ENTITY_STATUS_DESC" , unique=true, length=150)
    public String getSecondaryEntityStatusDesc() 
    {
        return this.secondaryEntityStatusDesc;
    }
    
    public void setSecondaryEntityStatusDesc(String secondaryEntityStatusDesc) 
    {
        this.secondaryEntityStatusDesc = secondaryEntityStatusDesc;
    }


}


