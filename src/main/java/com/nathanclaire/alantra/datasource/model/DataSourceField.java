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
 * DataSourceField 
 * @author Edward Banfa
 */
@Entity
@Table(name="DATA_SOURCE_FIELD"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class DataSourceField  extends BaseEntity implements java.io.Serializable {

	private DataSourceStructure dataSourceStructure;
	private DataSourceFieldType dataSourceFieldType;
    private String name;
    private String description;
    private String dsFieldTarget;

    public DataSourceField() {
    }

    public DataSourceField(DataSourceStructure dataSourceStructure, DataSourceFieldType dataSourceFieldType, String code, String name, String dsFieldTarget, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.dsFieldTarget = dsFieldTarget;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public DataSourceField(DataSourceStructure dataSourceStructure, DataSourceFieldType dataSourceFieldType, String code, String name, String description, String dsFieldTarget, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
		this.dataSourceStructure = dataSourceStructure;
		this.dataSourceFieldType = dataSourceFieldType;
		this.code = code;
		this.name = name;
		this.description = description;
		this.dsFieldTarget = dsFieldTarget;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DS_STRUCT_ID", nullable=false)
    @JsonIgnore
    public DataSourceStructure getDataSourceStructure() 
    {
        return this.dataSourceStructure;
    }
    
    public void setDataSourceStructure(DataSourceStructure dataSourceStructure)
    {
        this.dataSourceStructure = dataSourceStructure;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DS_FIELD_TY_ID", nullable=false)
    @JsonIgnore
    public DataSourceFieldType getDataSourceFieldType() 
    {
        return this.dataSourceFieldType;
    }
    
    public void setDataSourceFieldType(DataSourceFieldType dataSourceFieldType)
    {
        this.dataSourceFieldType = dataSourceFieldType;
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
		
    @Column(name="DS_FIELD_TARGET" , nullable=false, length=75)
    public String getDsFieldTarget() 
    {
        return this.dsFieldTarget;
    }
    
    public void setDsFieldTarget(String dsFieldTarget) 
    {
        this.dsFieldTarget = dsFieldTarget;
    }


}


