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
	private DataInputJobCategory dataInputJobCategory;
	private DataInputJobType dataInputJobType;
	private DataSource dataSource;
    private String name;
    private String description;
    private int diFreqVal;
    private String diFreqCd;

    public DataInputJob() {
    }

    public DataInputJob(DataInputJobStatus dataInputJobStatus, DataInputJobCategory dataInputJobCategory, DataInputJobType dataInputJobType, DataSource dataSource, String code, String name, int diFreqVal, String diFreqCd, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
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
    public DataInputJob(DataInputJobStatus dataInputJobStatus, DataInputJobCategory dataInputJobCategory, DataInputJobType dataInputJobType, DataSource dataSource, String code, String name, String description, int diFreqVal, String diFreqCd, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
		this.dataInputJobStatus = dataInputJobStatus;
		this.dataInputJobCategory = dataInputJobCategory;
		this.dataInputJobType = dataInputJobType;
		this.dataSource = dataSource;
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
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="JOB_STATUS_ID", nullable=false)
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
    @JoinColumn(name="JOB_CAT_ID", nullable=false)
    @JsonIgnore
    public DataInputJobCategory getDataInputJobCategory() 
    {
        return this.dataInputJobCategory;
    }
    
    public void setDataInputJobCategory(DataInputJobCategory dataInputJobCategory)
    {
        this.dataInputJobCategory = dataInputJobCategory;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="JOB_TY_ID", nullable=false)
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
    @JoinColumn(name="DS_ID", nullable=false)
    @JsonIgnore
    public DataSource getDataSource() 
    {
        return this.dataSource;
    }
    
    public void setDataSource(DataSource dataSource)
    {
        this.dataSource = dataSource;
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


}


