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
 * Data 
 * @author Edward Banfa
 */
@Entity
@Table(name="DATA"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Data  extends BaseEntity implements java.io.Serializable {

	private DataType dataType;
	private DataStructure dataStructure;
    private String name;
    private String description;
	private Set<DataInput> dataInputs = new HashSet<DataInput>(0);

    public Data() {
    }

    public Data(DataType dataType, DataStructure dataStructure, String code, String name, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public Data(DataType dataType, DataStructure dataStructure, String code, String name, String description, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<DataInput> dataInputs ) 
    {
		this.dataType = dataType;
		this.dataStructure = dataStructure;
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.dataInputs = dataInputs;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DATA_TY_ID", nullable=false)
    @JsonIgnore
    public DataType getDataType() 
    {
        return this.dataType;
    }
    
    public void setDataType(DataType dataType)
    {
        this.dataType = dataType;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DATA_STRUCT_ID", nullable=false)
    @JsonIgnore
    public DataStructure getDataStructure() 
    {
        return this.dataStructure;
    }
    
    public void setDataStructure(DataStructure dataStructure)
    {
        this.dataStructure = dataStructure;
    }
    		
    
		
    @Column(name="NAME" , nullable=false, length=150)
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="data")
    @JsonIgnore
    public Set<DataInput> getDataInputs() 
    {
        return this.dataInputs;
    }
    
    public void setDataInputs(Set<DataInput> dataInputs) 
    {
        this.dataInputs = dataInputs;
    }			


}


