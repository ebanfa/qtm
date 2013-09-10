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
import com.nathanclaire.alantra.rule.model.Parameter;
import java.util.Set;
import com.nathanclaire.alantra.rule.model.TransactionRuleCondition;

/**
 * TransactionRuleConditionParameter 
 * @author Edward Banfa
 */
@Entity
@Table(name="TRANSACTION_RULE_CONDITION_PARAMETER"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TransactionRuleConditionParameter  extends BaseEntity implements java.io.Serializable {
	private String name;
	private Parameter parameter;
	private String description;
	private String parameterValue;
	private Set<TransactionRuleCondition> transactionRuleConditions;

    public TransactionRuleConditionParameter() {
    }

    public TransactionRuleConditionParameter(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public TransactionRuleConditionParameter(String name, Parameter parameter, String description, String parameterValue, Set transactionRuleConditions, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.name = name;
        this.parameter = parameter;
        this.description = description;
        this.parameterValue = parameterValue;
        this.transactionRuleConditions = transactionRuleConditions;
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
	
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARAMETER_ID")
    @JsonIgnore
    public Parameter getParameter() 
    {
        return this.parameter;
    }
    
    public void setParameter(Parameter parameter)
    {
        this.parameter = parameter;
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
	
    @Column(name="PARAMETER_VALUE", length=150)
    public String getParameterValue() 
    {
        return this.parameterValue;
    }
    
    public void setParameterValue(String parameterValue) 
    {
        this.parameterValue = parameterValue;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="transactionRuleConditionParameter")
    @JsonIgnore
    public Set<TransactionRuleCondition> getTransactionRuleConditions() 
    {
        return this.transactionRuleConditions;
    }
    
    public void setTransactionRuleConditions(Set<TransactionRuleCondition> transactionRuleConditions) 
    {
        this.transactionRuleConditions = transactionRuleConditions;
    }			
		
    

}
