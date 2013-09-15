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
 * DataFieldMap 
 * @author Edward Banfa
 */
@Entity
@Table(name="DATA_FIELD_MAP"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class DataFieldMap  extends BaseEntity implements java.io.Serializable {

	private String opCd;
    private String name;
	private DataField dataField;
    private String description;
    private String businessObjectFieldCd;
	private DataTransformer dataTransformer;
	private TransformationMap transformationMap;;

    public DataFieldMap() {
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FIELD_ID", nullable=false)
    @JsonIgnore
    public DataField getDataField() 
    {
        return this.dataField;
    }
    
    public void setDataField(DataField dataField)
    {
        this.dataField = dataField;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TRANSFORMER_ID", nullable=false)
    @JsonIgnore
    public DataTransformer getDataTransformer() 
    {
        return this.dataTransformer;
    }
    
	/**
	 * @param dataTransformer the dataTransformer to set
	 */
	public void setDataTransformer(DataTransformer dataTransformer) {
		this.dataTransformer = dataTransformer;
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

	/**
	 * @return the businessObjectFieldCd
	 */
    @Column(name="BUSINESS_OBJ_FIELD_CD" , nullable=false, length=75)
	public String getBusinessObjectFieldCd() {
		return businessObjectFieldCd;
	}

	/**
	 * @param businessObjectFieldCd the businessObjectFieldCd to set
	 */
	public void setBusinessObjectFieldCd(String businessObjectFieldCd) {
		this.businessObjectFieldCd = businessObjectFieldCd;
	}

	/**
	 * @return the opCd
	 */
    @Column(name="OP_CD" , nullable=false, length=150)
	public String getOpCd() {
		return opCd;
	}


	/**
	 * @param opCd the opCd to set
	 */
	public void setOpCd(String opCd) {
		this.opCd = opCd;
	}


	/**
	 * @return the transformationMap
	 */
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TRANSFORMATION_MAP_ID", nullable=false)
    @JsonIgnore
	public TransformationMap getTransformationMap() {
		return transformationMap;
	}


	/**
	 * @param transformationMap the transformationMap to set
	 */
	public void setTransformationMap(TransformationMap transformationMap) {
		this.transformationMap = transformationMap;
	}

	
}


