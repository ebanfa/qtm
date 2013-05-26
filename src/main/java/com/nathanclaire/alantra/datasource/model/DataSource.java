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
 * DataSource 
 * @author Edward Banfa
 */
@Entity
@Table(name="DATA_SOURCE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class DataSource  extends BaseEntity implements java.io.Serializable {

	private DataSourceStructure dataSourceStructure;
	private DataSourceType dataSourceType;
    private String name;
    private String description;
    private String dsUrl;
    private String dsDb;
    private String dsTblNm;
    private String usrNm;
    private String password;
	private Set<DataInputJob> dataInputJobs = new HashSet<DataInputJob>(0);

    public DataSource() {
    }

    public DataSource(DataSourceStructure dataSourceStructure, DataSourceType dataSourceType, String code, String name, String dsUrl, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.dsUrl = dsUrl;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public DataSource(DataSourceStructure dataSourceStructure, DataSourceType dataSourceType, String code, String name, String description, String dsUrl, String dsDb, String dsTblNm, String usrNm, String password, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<DataInputJob> dataInputJobs ) 
    {
		this.dataSourceStructure = dataSourceStructure;
		this.dataSourceType = dataSourceType;
		this.code = code;
		this.name = name;
		this.description = description;
		this.dsUrl = dsUrl;
		this.dsDb = dsDb;
		this.dsTblNm = dsTblNm;
		this.usrNm = usrNm;
		this.password = password;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.dataInputJobs = dataInputJobs;
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
    @JoinColumn(name="DS_TY_ID", nullable=false)
    @JsonIgnore
    public DataSourceType getDataSourceType() 
    {
        return this.dataSourceType;
    }
    
    public void setDataSourceType(DataSourceType dataSourceType)
    {
        this.dataSourceType = dataSourceType;
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
		
    @Column(name="DS_URL" , nullable=false, length=75)
    public String getDsUrl() 
    {
        return this.dsUrl;
    }
    
    public void setDsUrl(String dsUrl) 
    {
        this.dsUrl = dsUrl;
    }
		
    @Column(name="DS_DB" , unique=true, length=75)
    public String getDsDb() 
    {
        return this.dsDb;
    }
    
    public void setDsDb(String dsDb) 
    {
        this.dsDb = dsDb;
    }
		
    @Column(name="DS_TBL_NM" , unique=true, length=75)
    public String getDsTblNm() 
    {
        return this.dsTblNm;
    }
    
    public void setDsTblNm(String dsTblNm) 
    {
        this.dsTblNm = dsTblNm;
    }
		
    @Column(name="USR_NM" , unique=true, length=75)
    public String getUsrNm() 
    {
        return this.usrNm;
    }
    
    public void setUsrNm(String usrNm) 
    {
        this.usrNm = usrNm;
    }
		
    @Column(name="PASSWORD" , unique=true, length=75)
    public String getPassword() 
    {
        return this.password;
    }
    
    public void setPassword(String password) 
    {
        this.password = password;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="dataSource")
    @JsonIgnore
    public Set<DataInputJob> getDataInputJobs() 
    {
        return this.dataInputJobs;
    }
    
    public void setDataInputJobs(Set<DataInputJob> dataInputJobs) 
    {
        this.dataInputJobs = dataInputJobs;
    }			


}


