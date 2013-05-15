/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.application.model;

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
 * ApplicationEntityField 
 * @author Edward Banfa
 */
@Entity
@Table(name="APPLICATION_ENTITY_FIELD"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ApplicationEntityField  extends BaseEntity implements java.io.Serializable {

	private ApplicationEntityFieldType applicationEntityFieldType;
	private ApplicationEntity applicationRelatedEntity;
	private ApplicationEntity applicationEntity;
    private String name;
    private String description;
    private char primarykeyFg;
    private String storage;
    private char requiredFg;
    private Character uniqueFg;
    private Character relatedFg;
    private Integer size;
    private Integer maxDigits;
    private Integer decimalPrecision;
    private int sequenceNo;
	private Set<ApplicationFormField> applicationFormFields = new HashSet<ApplicationFormField>(0);

    public ApplicationEntityField() {
    }

    public ApplicationEntityField(ApplicationEntityFieldType applicationEntityFieldType, ApplicationEntity applicationEntityByEntityId, String code, String name, char primarykeyFg, String storage, char requiredFg, int sequenceNo, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.primarykeyFg = primarykeyFg;
		this.storage = storage;
		this.requiredFg = requiredFg;
		this.sequenceNo = sequenceNo;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public ApplicationEntityField(ApplicationEntityFieldType applicationEntityFieldType, ApplicationEntity applicationEntityByRelatedEntityId, ApplicationEntity applicationEntityByEntityId, String code, String name, String description, char primarykeyFg, String storage, char requiredFg, Character uniqueFg, Character relatedFg, Integer size, Integer maxDigits, Integer decimalPrecision, int sequenceNo, Date effectiveDt, char recSt, Set<ApplicationFormField> applicationFormFields ) 
    {
		this.applicationEntityFieldType = applicationEntityFieldType;
		this.applicationRelatedEntity = applicationEntityByRelatedEntityId;
		this.applicationEntity = applicationEntityByEntityId;
		this.code = code;
		this.name = name;
		this.description = description;
		this.primarykeyFg = primarykeyFg;
		this.storage = storage;
		this.requiredFg = requiredFg;
		this.uniqueFg = uniqueFg;
		this.relatedFg = relatedFg;
		this.size = size;
		this.maxDigits = maxDigits;
		this.decimalPrecision = decimalPrecision;
		this.sequenceNo = sequenceNo;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.applicationFormFields = applicationFormFields;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FIELD_TY_ID", nullable=false)
    @JsonIgnore
    public ApplicationEntityFieldType getApplicationEntityFieldType() 
    {
        return this.applicationEntityFieldType;
    }
    
    public void setApplicationEntityFieldType(ApplicationEntityFieldType applicationEntityFieldType)
    {
        this.applicationEntityFieldType = applicationEntityFieldType;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RELATED_ENTITY_ID")
    @JsonIgnore
    public ApplicationEntity getApplicationRelatedEntity() 
    {
        return this.applicationRelatedEntity;
    }
    
    public void setApplicationRelatedEntity(ApplicationEntity applicationEntityByRelatedEntityId)
    {
        this.applicationRelatedEntity = applicationEntityByRelatedEntityId;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ENTITY_ID", nullable=false)
    @JsonIgnore
    public ApplicationEntity getApplicationEntity() 
    {
        return this.applicationEntity;
    }
    
    public void setApplicationEntity(ApplicationEntity applicationEntityByEntityId)
    {
        this.applicationEntity = applicationEntityByEntityId;
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
		
    @Column(name="DESCRIPTION" , unique=true, length=100)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
		
    @Column(name="PRIMARYKEY_FG" , nullable=false, length=1)
    public char getPrimarykeyFg() 
    {
        return this.primarykeyFg;
    }
    
    public void setPrimarykeyFg(char primarykeyFg) 
    {
        this.primarykeyFg = primarykeyFg;
    }
		
    @Column(name="STORAGE" , nullable=false, length=35)
    public String getStorage() 
    {
        return this.storage;
    }
    
    public void setStorage(String storage) 
    {
        this.storage = storage;
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
		
    @Column(name="UNIQUE_FG" , unique=true, length=1)
    public Character getUniqueFg() 
    {
        return this.uniqueFg;
    }
    
    public void setUniqueFg(Character uniqueFg) 
    {
        this.uniqueFg = uniqueFg;
    }
		
    @Column(name="RELATED_FG" , unique=true, length=1)
    public Character getRelatedFg() 
    {
        return this.relatedFg;
    }
    
    public void setRelatedFg(Character relatedFg) 
    {
        this.relatedFg = relatedFg;
    }
		
    @Column(name="SIZE" , unique=true)
    public Integer getSize() 
    {
        return this.size;
    }
    
    public void setSize(Integer size) 
    {
        this.size = size;
    }
		
    @Column(name="MAX_DIGITS" , unique=true)
    public Integer getMaxDigits() 
    {
        return this.maxDigits;
    }
    
    public void setMaxDigits(Integer maxDigits) 
    {
        this.maxDigits = maxDigits;
    }
		
    @Column(name="DECIMAL_PRECISION" , unique=true)
    public Integer getDecimalPrecision() 
    {
        return this.decimalPrecision;
    }
    
    public void setDecimalPrecision(Integer decimalPrecision) 
    {
        this.decimalPrecision = decimalPrecision;
    }
		
    @Column(name="SEQUENCE_NO" , nullable=false)
    public int getSequenceNo() 
    {
        return this.sequenceNo;
    }
    
    public void setSequenceNo(int sequenceNo) 
    {
        this.sequenceNo = sequenceNo;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="applicationEntityField")
    @JsonIgnore
    public Set<ApplicationFormField> getApplicationFormFields() 
    {
        return this.applicationFormFields;
    }
    
    public void setApplicationFormFields(Set<ApplicationFormField> applicationFormFields) 
    {
        this.applicationFormFields = applicationFormFields;
    }			


}


