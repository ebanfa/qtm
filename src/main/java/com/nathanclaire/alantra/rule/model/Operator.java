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
import java.util.Set;
import com.nathanclaire.alantra.rule.model.ParameterTypeOperator;

/**
 * Operator 
 * @author Edward Banfa
 */
@Entity
@Table(name="OPERATOR"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Operator  extends BaseEntity implements java.io.Serializable {
	private String name;
	private String description;
	private String operatorSymbol;
	private Set<ParameterTypeOperator> parameterTypeOperators;

    public Operator() {
    }

    public Operator(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public Operator(String name, String description, String operatorSymbol, Set parameterTypeOperators, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.name = name;
        this.description = description;
        this.operatorSymbol = operatorSymbol;
        this.parameterTypeOperators = parameterTypeOperators;
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
	
    @Column(name="OPERATOR_SYMBOL", length=5)
    public String getOperatorSymbol() 
    {
        return this.operatorSymbol;
    }
    
    public void setOperatorSymbol(String operatorSymbol) 
    {
        this.operatorSymbol = operatorSymbol;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="operator")
    @JsonIgnore
    public Set<ParameterTypeOperator> getParameterTypeOperators() 
    {
        return this.parameterTypeOperators;
    }
    
    public void setParameterTypeOperators(Set<ParameterTypeOperator> parameterTypeOperators) 
    {
        this.parameterTypeOperators = parameterTypeOperators;
    }			
		
    

}
