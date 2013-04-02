/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.advice.model;

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

import com.nathanclaire.alantra.base.BaseEntity;

/**
 * AdviceTypeTag 
 * @author Edward Banfa
 */
@Entity
@Table(name="ADVICE_TYPE_TAG"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AdviceTypeTag  extends BaseEntity implements java.io.Serializable {

	private AdviceType adviceType;
    private String name;
    private String description;
    private String adviceTyTagVal;
    private char isRegexFg;

    public AdviceTypeTag() {
    }

    public AdviceTypeTag(AdviceType adviceType, String code, String name, String adviceTyTagVal, char isRegexFg, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.adviceTyTagVal = adviceTyTagVal;
		this.isRegexFg = isRegexFg;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public AdviceTypeTag(AdviceType adviceType, String code, String name, String description, String adviceTyTagVal, char isRegexFg, Date effectiveDt, char recSt) 
    {
		this.adviceType = adviceType;
		this.code = code;
		this.name = name;
		this.description = description;
		this.adviceTyTagVal = adviceTyTagVal;
		this.isRegexFg = isRegexFg;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ADVICE_TY_ID", nullable=false)
    @JsonIgnore
    public AdviceType getAdviceType() 
    {
        return this.adviceType;
    }
    
    public void setAdviceType(AdviceType adviceType)
    {
        this.adviceType = adviceType;
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
		
    @Column(name="ADVICE_TY_TAG_VAL" , nullable=false, length=500)
    public String getAdviceTyTagVal() 
    {
        return this.adviceTyTagVal;
    }
    
    public void setAdviceTyTagVal(String adviceTyTagVal) 
    {
        this.adviceTyTagVal = adviceTyTagVal;
    }
		
    @Column(name="IS_REGEX_FG" , nullable=false, length=1)
    public char getIsRegexFg() 
    {
        return this.isRegexFg;
    }
    
    public void setIsRegexFg(char isRegexFg) 
    {
        this.isRegexFg = isRegexFg;
    }


}


