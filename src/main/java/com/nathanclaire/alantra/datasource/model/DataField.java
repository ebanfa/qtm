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

	private DataTransformer dataTransformer;
	private DataStructure dataStructure;
	private DataFieldType dataFieldType;
    private String name;
    private String description;
    private String targetEntityCd;
    private String targetEntityField;
    private String relTargetEntityCd;
    private String fieldFormat;
    private char requiredFg;
    private int seqNo;
	private Set<CellData> cellDatas = new HashSet<CellData>(0);

    public DataField() {
    }

    public DataField(DataTransformer dataTransformer, DataStructure dataStructure, DataFieldType dataFieldType, String code, String name, String targetEntityCd, String targetEntityField, char requiredFg, int seqNo, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.targetEntityCd = targetEntityCd;
		this.targetEntityField = targetEntityField;
		this.requiredFg = requiredFg;
		this.seqNo = seqNo;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public DataField(DataTransformer dataTransformer, DataStructure dataStructure, DataFieldType dataFieldType, String code, String name, String description, String targetEntityCd, String targetEntityField, String relTargetEntityCd, String fieldFormat, char requiredFg, int seqNo, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<CellData> cellDatas ) 
    {
		this.dataTransformer = dataTransformer;
		this.dataStructure = dataStructure;
		this.dataFieldType = dataFieldType;
		this.code = code;
		this.name = name;
		this.description = description;
		this.targetEntityCd = targetEntityCd;
		this.targetEntityField = targetEntityField;
		this.relTargetEntityCd = relTargetEntityCd;
		this.fieldFormat = fieldFormat;
		this.requiredFg = requiredFg;
		this.seqNo = seqNo;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.cellDatas = cellDatas;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DATA_TRANSFORMER_ID", nullable=false)
    @JsonIgnore
    public DataTransformer getDataTransformer() 
    {
        return this.dataTransformer;
    }
    
    public void setDataTransformer(DataTransformer dataTransformer)
    {
        this.dataTransformer = dataTransformer;
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
		
    @Column(name="TARGET_ENTITY_CD" , nullable=false, length=75)
    public String getTargetEntityCd() 
    {
        return this.targetEntityCd;
    }
    
    public void setTargetEntityCd(String targetEntityCd) 
    {
        this.targetEntityCd = targetEntityCd;
    }
		
    @Column(name="TARGET_ENTITY_FIELD" , nullable=false, length=75)
    public String getTargetEntityField() 
    {
        return this.targetEntityField;
    }
    
    public void setTargetEntityField(String targetEntityField) 
    {
        this.targetEntityField = targetEntityField;
    }
		
    @Column(name="REL_TARGET_ENTITY_CD" , unique=true, length=75)
    public String getRelTargetEntityCd() 
    {
        return this.relTargetEntityCd;
    }
    
    public void setRelTargetEntityCd(String relTargetEntityCd) 
    {
        this.relTargetEntityCd = relTargetEntityCd;
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
    public Set<CellData> getCellDatas() 
    {
        return this.cellDatas;
    }
    
    public void setCellDatas(Set<CellData> cellDatas) 
    {
        this.cellDatas = cellDatas;
    }			


}


