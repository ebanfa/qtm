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
@Table(name="DATA_TABLE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class DataTable  extends BaseEntity implements java.io.Serializable {

	private DataInputJob dataInputJob;
    private String name;
    private String description;
    private String primEntityNm;
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
	private Set<DataRow> dataRows = new HashSet<DataRow>(0);

    public DataTable() {
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
		
    @Column(name="PRIM_ENTITY_NM" , nullable=false)
    public String getPrimEntityNm() 
    {
        return this.primEntityNm;
    }
    
    public void setPrimEntityNm(String primEntityNm) 
    {
        this.primEntityNm = primEntityNm;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="dataTable")
    @JsonIgnore
    public Set<DataRow> getDataRows() 
    {
        return this.dataRows;
    }
    
    public void setDataRows(Set<DataRow> dataRows) 
    {
        this.dataRows = dataRows;
    }			


}


