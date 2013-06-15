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
 * DataInputJobRecordsSummaryStatus 
 * @author Edward Banfa
 */
@Entity
@Table(name="DATA_INPUT_JOB_RECORDS_SUMMARY_STATUS"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class DataInputJobRecordsSummaryStatus  extends BaseEntity implements java.io.Serializable {

    private String name;
    private String description;
	private Set<DataInputJobRecordsSummary> dataInputJobRecordsSummaries = new HashSet<DataInputJobRecordsSummary>(0);

    public DataInputJobRecordsSummaryStatus() {
    }

    public DataInputJobRecordsSummaryStatus(String code, String name, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public DataInputJobRecordsSummaryStatus(String code, String name, String description, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<DataInputJobRecordsSummary> dataInputJobRecordsSummaries ) 
    {
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.dataInputJobRecordsSummaries = dataInputJobRecordsSummaries;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="dataInputJobRecordsSummaryStatus")
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


