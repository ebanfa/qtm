/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.application.model;

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
 * ApplicationFormField 
 * @author Edward Banfa
 */
@Entity
@Table(name="APPLICATION_FORM_FIELD"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ApplicationFormField  extends BaseEntity implements java.io.Serializable {

	private ApplicationFormFieldType applicationFormFieldType;
	private ApplicationForm applicationForm;
	private ApplicationEntityField applicationEntityField;
    private String name;
    private String description;
    private char primarykeyFg;
    private char requiredFg;
    private Character relatedFg;
    private Integer size;
    private Integer maxDigits;
    private Integer decimalPrecision;
    private int sequenceNo;

    public ApplicationFormField() {
    }

    public ApplicationFormField(ApplicationFormFieldType applicationFormFieldType, ApplicationForm applicationForm, String code, String name, char primarykeyFg, char requiredFg, int sequenceNo, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.primarykeyFg = primarykeyFg;
		this.requiredFg = requiredFg;
		this.sequenceNo = sequenceNo;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public ApplicationFormField(ApplicationFormFieldType applicationFormFieldType, ApplicationForm applicationForm, ApplicationEntityField applicationEntityField, String code, String name, String description, char primarykeyFg, char requiredFg, Character relatedFg, Integer size, Integer maxDigits, Integer decimalPrecision, int sequenceNo, Date effectiveDt, char recSt) 
    {
		this.applicationFormFieldType = applicationFormFieldType;
		this.applicationForm = applicationForm;
		this.applicationEntityField = applicationEntityField;
		this.code = code;
		this.name = name;
		this.description = description;
		this.primarykeyFg = primarykeyFg;
		this.requiredFg = requiredFg;
		this.relatedFg = relatedFg;
		this.size = size;
		this.maxDigits = maxDigits;
		this.decimalPrecision = decimalPrecision;
		this.sequenceNo = sequenceNo;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FIELD_TY_ID", nullable=false)
    @JsonIgnore
    public ApplicationFormFieldType getApplicationFormFieldType() 
    {
        return this.applicationFormFieldType;
    }
    
    public void setApplicationFormFieldType(ApplicationFormFieldType applicationFormFieldType)
    {
        this.applicationFormFieldType = applicationFormFieldType;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FORM_ID", nullable=false)
    @JsonIgnore
    public ApplicationForm getApplicationForm() 
    {
        return this.applicationForm;
    }
    
    public void setApplicationForm(ApplicationForm applicationForm)
    {
        this.applicationForm = applicationForm;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ENTITY_FIELD_ID")
    @JsonIgnore
    public ApplicationEntityField getApplicationEntityField() 
    {
        return this.applicationEntityField;
    }
    
    public void setApplicationEntityField(ApplicationEntityField applicationEntityField)
    {
        this.applicationEntityField = applicationEntityField;
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
		
    @Column(name="REQUIRED_FG" , nullable=false, length=1)
    public char getRequiredFg() 
    {
        return this.requiredFg;
    }
    
    public void setRequiredFg(char requiredFg) 
    {
        this.requiredFg = requiredFg;
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


}


