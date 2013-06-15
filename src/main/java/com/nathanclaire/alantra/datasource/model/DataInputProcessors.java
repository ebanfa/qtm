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
 * DataInputProcessors 
 * @author Edward Banfa
 */
@Entity
@Table(name="DATA_INPUT_PROCESSORS"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class DataInputProcessors  extends BaseEntity implements java.io.Serializable {

	private DataProcessor dataProcessor;
	private DataInput dataInput;
    private String name;
    private String description;

    public DataInputProcessors() {
    }

    public DataInputProcessors(DataProcessor dataProcessor, DataInput dataInput, String code, String name, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public DataInputProcessors(DataProcessor dataProcessor, DataInput dataInput, String code, String name, String description, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
		this.dataProcessor = dataProcessor;
		this.dataInput = dataInput;
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DATA_PROCESSOR_ID", nullable=false)
    @JsonIgnore
    public DataProcessor getDataProcessor() 
    {
        return this.dataProcessor;
    }
    
    public void setDataProcessor(DataProcessor dataProcessor)
    {
        this.dataProcessor = dataProcessor;
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


}


