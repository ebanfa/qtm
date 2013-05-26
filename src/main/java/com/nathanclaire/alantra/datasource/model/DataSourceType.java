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
 * DataSourceType 
 * @author Edward Banfa
 */
@Entity
@Table(name="DATA_SOURCE_TYPE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class DataSourceType  extends BaseEntity implements java.io.Serializable {

	private DataSourceCategory dataSourceCategory;
    private String name;
    private String description;
	private Set<DataSource> dataSources = new HashSet<DataSource>(0);

    public DataSourceType() {
    }

    public DataSourceType(DataSourceCategory dataSourceCategory, String code, String name, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public DataSourceType(DataSourceCategory dataSourceCategory, String code, String name, String description, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<DataSource> dataSources ) 
    {
		this.dataSourceCategory = dataSourceCategory;
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.dataSources = dataSources;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DS_CAT_ID", nullable=false)
    @JsonIgnore
    public DataSourceCategory getDataSourceCategory() 
    {
        return this.dataSourceCategory;
    }
    
    public void setDataSourceCategory(DataSourceCategory dataSourceCategory)
    {
        this.dataSourceCategory = dataSourceCategory;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="dataSourceType")
    @JsonIgnore
    public Set<DataSource> getDataSources() 
    {
        return this.dataSources;
    }
    
    public void setDataSources(Set<DataSource> dataSources) 
    {
        this.dataSources = dataSources;
    }			


}


