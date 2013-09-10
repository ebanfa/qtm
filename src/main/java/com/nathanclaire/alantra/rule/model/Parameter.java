/**
 *  Alantra.
 */
package com.nathanclaire.alantra.rule.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Column;
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
import com.nathanclaire.alantra.rule.model.ParameterType;

/**
 * Parameter 
 * @author Edward Banfa
 */
@Entity
@Table(name="PARAMETER"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Parameter  extends BaseEntity implements java.io.Serializable {
	private ParameterType parameterType;
	private String defaultVal;
	private String name;
	private String description;

    public Parameter() {
    }

    public Parameter(String defaultVal, String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.defaultVal = defaultVal;
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public Parameter(ParameterType parameterType, String defaultVal, String name, String description, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.parameterType = parameterType;
        this.defaultVal = defaultVal;
        this.name = name;
        this.description = description;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.versionNo = versionNo;
        this.rowTs = rowTs;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
        this.lastModifiedDt = lastModifiedDt;
        this.lastModifiedUsr = lastModifiedUsr;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARAMETER_TY_ID")
    @JsonIgnore
    public ParameterType getParameterType() 
    {
        return this.parameterType;
    }
    
    public void setParameterType(ParameterType parameterType)
    {
        this.parameterType = parameterType;
    }
    
    @Column(name="DEFAULT_VAL", nullable=false, length=150)
    public String getDefaultVal() 
    {
        return this.defaultVal;
    }
    
    public void setDefaultVal(String defaultVal) 
    {
        this.defaultVal = defaultVal;
    }
	
    @Column(name="NAME", nullable=false, length=75)
    public String getName() 
    {
        return this.name;
    }
    
    public void setName(String name) 
    {
        this.name = name;
    }
	
    @Column(name="DESCRIPTION", length=150)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
	
    

}
