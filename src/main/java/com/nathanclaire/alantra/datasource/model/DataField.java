/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.model;

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
 * DataField 
 * @author Edward Banfa
 */
@Entity
@Table(name="DATA_FIELD"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class DataField  extends BaseEntity implements java.io.Serializable {

	private DataStructure dataStructure;
	private DataFieldType dataFieldType;
    private String name;
    private String description;
    private String relBusinessObjectCd;
    private String fieldFormat;
    private char requiredFg;
    private int seqNo;
    private Character virtualField;
    private String defaultValue;
	private Set<DataCell> dataCells = new HashSet<DataCell>(0);
	private Set<DataFieldMap> dataFieldMaps = new HashSet<DataFieldMap>();

	public DataField() {
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
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DATA_FIELD_TY_ID", nullable=false)
    @JsonIgnore
    public DataFieldType getDataFieldType() 
    {
        return this.dataFieldType;
    }
    
    public void setDataFieldType(DataFieldType dataFieldType)
    {
        this.dataFieldType = dataFieldType;
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
		
    @Column(name="REL_BUSINESS_OBJ_CD" , unique=true, length=75)
    public String getRelBusinessObjectCd() 
    {
        return this.relBusinessObjectCd;
    }
    
    public void setRelBusinessObjectCd(String relTargetEntityCd) 
    {
        this.relBusinessObjectCd = relTargetEntityCd;
    }
		
    @Column(name="FIELD_FORMAT" , unique=true, length=75)
    public String getFieldFormat() 
    {
        return this.fieldFormat;
    }
    
    public void setFieldFormat(String fieldFormat) 
    {
        this.fieldFormat = fieldFormat;
    }
		
    @Column(name="REQUIRED_FG" , nullable=false, length=1)
    public char getRequiredFg() 
    {
        return this.requiredFg;
    }
    
    public void setRequiredFg(char requiredFg) 
    {
        this.requiredFg = requiredFg;
    }
		
    @Column(name="SEQ_NO" , nullable=false)
    public int getSeqNo() 
    {
        return this.seqNo;
    }
    
    public void setSeqNo(int seqNo) 
    {
        this.seqNo = seqNo;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="dataField")
    @JsonIgnore
    public Set<DataCell> getDataCells() 
    {
        return this.dataCells;
    }
    
    public void setDataCells(Set<DataCell> dataCells) 
    {
        this.dataCells = dataCells;
    }	
    /**
	 * @return the dataFieldMaps
	 */
    @OneToMany(fetch=FetchType.LAZY, mappedBy="dataField")
    @JsonIgnore
	public Set<DataFieldMap> getDataFieldMaps() {
		return dataFieldMaps;
	}

	/**
	 * @param dataFieldMaps the dataFieldMaps to set
	 */
	public void setDataFieldMaps(Set<DataFieldMap> dataFieldMaps) {
		this.dataFieldMaps = dataFieldMaps;
	}

	/**
	 * @return the virtualField
	 */
    @Column(name="VIRTUAL_FIELD" , nullable=false, length=1)
	public Character getVirtualField() {
		return virtualField;
	}

	/**
	 * @param virtualField the virtualField to set
	 */
	public void setVirtualField(Character virtualField) {
		this.virtualField = virtualField;
	}

	/**
	 * @return the defaultValue
	 */
    @Column(name="DEFAULT_VALUE" , length=255)
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}		


}


