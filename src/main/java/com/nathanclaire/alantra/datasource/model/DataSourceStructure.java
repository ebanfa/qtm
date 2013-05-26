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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.model.BaseEntity;

/**
 * DataSourceStructure 
 * @author Edward Banfa
 */
@Entity
@Table(name="DATA_SOURCE_STRUCTURE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class DataSourceStructure  extends BaseEntity implements java.io.Serializable {

    private String name;
    private String description;
    private Character dsStructDelimeter;
    private String targetEntityCd;
	private Set<DataSource> dataSources = new HashSet<DataSource>(0);
	private Set<DataSourceField> dataSourceFields = new HashSet<DataSourceField>(0);

    public DataSourceStructure() {
    }

    public DataSourceStructure(String code, String name, String targetEntityCd, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.targetEntityCd = targetEntityCd;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public DataSourceStructure(String code, String name, String description, Character dsStructDelimeter, String targetEntityCd, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<DataSource> dataSources, Set<DataSourceField> dataSourceFields ) 
    {
		this.code = code;
		this.name = name;
		this.description = description;
		this.dsStructDelimeter = dsStructDelimeter;
		this.targetEntityCd = targetEntityCd;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.dataSources = dataSources;
		this.dataSourceFields = dataSourceFields;
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
		
    @Column(name="DS_STRUCT_DELIMETER" , unique=true, length=1)
    public Character getDsStructDelimeter() 
    {
        return this.dsStructDelimeter;
    }
    
    public void setDsStructDelimeter(Character dsStructDelimeter) 
    {
        this.dsStructDelimeter = dsStructDelimeter;
    }
		
    @Column(name="TARGET_ENTITY_CD" , nullable=false, length=35)
    public String getTargetEntityCd() 
    {
        return this.targetEntityCd;
    }
    
    public void setTargetEntityCd(String targetEntityCd) 
    {
        this.targetEntityCd = targetEntityCd;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="dataSourceStructure")
    @JsonIgnore
    public Set<DataSource> getDataSources() 
    {
        return this.dataSources;
    }
    
    public void setDataSources(Set<DataSource> dataSources) 
    {
        this.dataSources = dataSources;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="dataSourceStructure")
    @JsonIgnore
    public Set<DataSourceField> getDataSourceFields() 
    {
        return this.dataSourceFields;
    }
    
    public void setDataSourceFields(Set<DataSourceField> dataSourceFields) 
    {
        this.dataSourceFields = dataSourceFields;
    }			


}


