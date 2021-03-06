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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.model.BaseEntity;

/**
 * DataInputJob 
 * @author Edward Banfa
 */
@Entity
@Table(name="DATA_INPUT_JOB"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class DataInputJob  extends BaseEntity implements java.io.Serializable {

	private DataInputJobStatus dataInputJobStatus;
	private DataInputJobType dataInputJobType;
	private DataInput dataInput;
    private String name;
    private String description;
    private Date startTs;
    private Date endTs;
    private Date nextRunTs;
    private int diFreqVal;
    private String diFreqCd;
	private Set<DataTable> dataTables = new HashSet<DataTable>(0);

    public DataInputJob() {
    }

    public DataInputJob(DataInputJobStatus dataInputJobStatus, DataInputJobType dataInputJobType, DataInput dataInput, String code, String name, int diFreqVal, String diFreqCd, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.diFreqVal = diFreqVal;
		this.diFreqCd = diFreqCd;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public DataInputJob(DataInputJobStatus dataInputJobStatus, DataInputJobType dataInputJobType, DataInput dataInput, String code, String name, String description, int diFreqVal, String diFreqCd, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<DataTable> dataTables ) 
    {
		this.dataInputJobStatus = dataInputJobStatus;
		this.dataInputJobType = dataInputJobType;
		this.dataInput = dataInput;
		this.code = code;
		this.name = name;
		this.description = description;
		this.diFreqVal = diFreqVal;
		this.diFreqCd = diFreqCd;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.dataTables = dataTables;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DATA_INPUT_JOB_STATUS_ID", nullable=false)
    @JsonIgnore
    public DataInputJobStatus getDataInputJobStatus() 
    {
        return this.dataInputJobStatus;
    }
    
    public void setDataInputJobStatus(DataInputJobStatus dataInputJobStatus)
    {
        this.dataInputJobStatus = dataInputJobStatus;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DATA_INPUT_JOB_TY_ID", nullable=false)
    @JsonIgnore
    public DataInputJobType getDataInputJobType() 
    {
        return this.dataInputJobType;
    }
    
    public void setDataInputJobType(DataInputJobType dataInputJobType)
    {
        this.dataInputJobType = dataInputJobType;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DATA_INPUT_ID", nullable=false)
    @JsonIgnore
    public DataInput getDataInput() 
    {
        return this.dataInput;
    }
    
    public void setDataInput(DataInput dataInput)
    {
        this.dataInput = dataInput;
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
		
    @Column(name="DI_FREQ_VAL" , nullable=false)
    public int getDiFreqVal() 
    {
        return this.diFreqVal;
    }
    
    public void setDiFreqVal(int diFreqVal) 
    {
        this.diFreqVal = diFreqVal;
    }
		
    @Column(name="DI_FREQ_CD" , nullable=false, length=35)
    public String getDiFreqCd() 
    {
        return this.diFreqCd;
    }
    
    public void setDiFreqCd(String diFreqCd) 
    {
        this.diFreqCd = diFreqCd;
    }

	/**
	 * @return the startTs
	 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="START_TS" , length=19)
	public Date getStartTs() {
		return startTs;
	}

	/**
	 * @param startTs the startTs to set
	 */
	public void setStartTs(Date startTs) {
		this.startTs = startTs;
	}

	/**
	 * @return the endTs
	 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="END_TS" , length=19)
	public Date getEndTs() {
		return endTs;
	}

	/**
	 * @param endTs the endTs to set
	 */
	public void setEndTs(Date endTs) {
		this.endTs = endTs;
	}

	/**
	 * @return the nextRunTs
	 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="NEXT_TS" , length=19)
	public Date getNextRunTs() {
		return nextRunTs;
	}

	/**
	 * @param nextRunTs the nextRunTs to set
	 */
	public void setNextRunTs(Date nextRunTs) {
		this.nextRunTs = nextRunTs;
	}

	/**
	 * @return the dataTables
	 */
    @OneToMany(fetch=FetchType.LAZY, mappedBy="dataInputJob")
    @JsonIgnore
	public Set<DataTable> getDataTables() {
		return dataTables;
	}

	/**
	 * @param dataTables the dataTables to set
	 */
	public void setDataTables(Set<DataTable> dataTables) {
		this.dataTables = dataTables;
	}			


}


