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
 * TableData 
 * @author Edward Banfa
 */
@Entity
@Table(name="TABLE_DATA"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TableData  extends BaseEntity implements java.io.Serializable {

	private DataInputJob dataInputJob;
    private String name;
    private String description;
    private int primEntity;
    private int primRecRead;
    private int primRecCreated;
    private int primRecRejected;
    private Integer secEntity;
    private Integer secRecRead;
    private Integer secRecCreated;
    private Integer secRecRejected;
    private int totRecRead;
    private int totRecCreated;
    private int totRecRejected;
    private char importStatusFg;
	private Set<RowData> rowDatas = new HashSet<RowData>(0);

    public TableData() {
    }

    public TableData(DataInputJob dataInputJob, String code, String name, int primEntity, int primRecRead, int primRecCreated, int primRecRejected, int totRecRead, int totRecCreated, int totRecRejected, char importStatusFg, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.primEntity = primEntity;
		this.primRecRead = primRecRead;
		this.primRecCreated = primRecCreated;
		this.primRecRejected = primRecRejected;
		this.totRecRead = totRecRead;
		this.totRecCreated = totRecCreated;
		this.totRecRejected = totRecRejected;
		this.importStatusFg = importStatusFg;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public TableData(DataInputJob dataInputJob, String code, String name, String description, int primEntity, int primRecRead, int primRecCreated, int primRecRejected, Integer secEntity, Integer secRecRead, Integer secRecCreated, Integer secRecRejected, int totRecRead, int totRecCreated, int totRecRejected, char importStatusFg, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<RowData> rowDatas ) 
    {
		this.dataInputJob = dataInputJob;
		this.code = code;
		this.name = name;
		this.description = description;
		this.primEntity = primEntity;
		this.primRecRead = primRecRead;
		this.primRecCreated = primRecCreated;
		this.primRecRejected = primRecRejected;
		this.secEntity = secEntity;
		this.secRecRead = secRecRead;
		this.secRecCreated = secRecCreated;
		this.secRecRejected = secRecRejected;
		this.totRecRead = totRecRead;
		this.totRecCreated = totRecCreated;
		this.totRecRejected = totRecRejected;
		this.importStatusFg = importStatusFg;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.rowDatas = rowDatas;
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
		
    @Column(name="PRIM_ENTITY" , nullable=false)
    public int getPrimEntity() 
    {
        return this.primEntity;
    }
    
    public void setPrimEntity(int primEntity) 
    {
        this.primEntity = primEntity;
    }
		
    @Column(name="PRIM_REC_READ" , nullable=false)
    public int getPrimRecRead() 
    {
        return this.primRecRead;
    }
    
    public void setPrimRecRead(int primRecRead) 
    {
        this.primRecRead = primRecRead;
    }
		
    @Column(name="PRIM_REC_CREATED" , nullable=false)
    public int getPrimRecCreated() 
    {
        return this.primRecCreated;
    }
    
    public void setPrimRecCreated(int primRecCreated) 
    {
        this.primRecCreated = primRecCreated;
    }
		
    @Column(name="PRIM_REC_REJECTED" , nullable=false)
    public int getPrimRecRejected() 
    {
        return this.primRecRejected;
    }
    
    public void setPrimRecRejected(int primRecRejected) 
    {
        this.primRecRejected = primRecRejected;
    }
		
    @Column(name="SEC_ENTITY" , unique=true)
    public Integer getSecEntity() 
    {
        return this.secEntity;
    }
    
    public void setSecEntity(Integer secEntity) 
    {
        this.secEntity = secEntity;
    }
		
    @Column(name="SEC_REC_READ" , unique=true)
    public Integer getSecRecRead() 
    {
        return this.secRecRead;
    }
    
    public void setSecRecRead(Integer secRecRead) 
    {
        this.secRecRead = secRecRead;
    }
		
    @Column(name="SEC_REC_CREATED" , unique=true)
    public Integer getSecRecCreated() 
    {
        return this.secRecCreated;
    }
    
    public void setSecRecCreated(Integer secRecCreated) 
    {
        this.secRecCreated = secRecCreated;
    }
		
    @Column(name="SEC_REC_REJECTED" , unique=true)
    public Integer getSecRecRejected() 
    {
        return this.secRecRejected;
    }
    
    public void setSecRecRejected(Integer secRecRejected) 
    {
        this.secRecRejected = secRecRejected;
    }
		
    @Column(name="TOT_REC_READ" , nullable=false)
    public int getTotRecRead() 
    {
        return this.totRecRead;
    }
    
    public void setTotRecRead(int totRecRead) 
    {
        this.totRecRead = totRecRead;
    }
		
    @Column(name="TOT_REC_CREATED" , nullable=false)
    public int getTotRecCreated() 
    {
        return this.totRecCreated;
    }
    
    public void setTotRecCreated(int totRecCreated) 
    {
        this.totRecCreated = totRecCreated;
    }
		
    @Column(name="TOT_REC_REJECTED" , nullable=false)
    public int getTotRecRejected() 
    {
        return this.totRecRejected;
    }
    
    public void setTotRecRejected(int totRecRejected) 
    {
        this.totRecRejected = totRecRejected;
    }
		
    @Column(name="IMPORT_STATUS_FG" , nullable=false, length=1)
    public char getImportStatusFg() 
    {
        return this.importStatusFg;
    }
    
    public void setImportStatusFg(char importStatusFg) 
    {
        this.importStatusFg = importStatusFg;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="tableData")
    @JsonIgnore
    public Set<RowData> getRowDatas() 
    {
        return this.rowDatas;
    }
    
    public void setRowDatas(Set<RowData> rowDatas) 
    {
        this.rowDatas = rowDatas;
    }			


}


