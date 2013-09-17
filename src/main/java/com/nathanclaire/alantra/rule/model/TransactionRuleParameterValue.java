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
import com.nathanclaire.alantra.rule.model.TransactionRule;
import com.nathanclaire.alantra.rule.model.Parameter;

/**
 * TransactionRuleParameterValue 
 * @author Edward Banfa
 */
@Entity
@Table(name="TRANSACTION_RULE_PARAMETER"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TransactionRuleParameterValue  extends BaseEntity implements java.io.Serializable {
	private TransactionRule transactionRule;
	private Parameter parameter;
	private String name;
	private String paramVal;
	private String description;

    public TransactionRuleParameterValue() {
    }

    public TransactionRuleParameterValue(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public TransactionRuleParameterValue(TransactionRule transactionRule, Parameter parameter, String name, String paramVal, String description, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.transactionRule = transactionRule;
        this.parameter = parameter;
        this.name = name;
        this.paramVal = paramVal;
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
    @JoinColumn(name="RULE_ID")
    @JsonIgnore
    public TransactionRule getTransactionRule() 
    {
        return this.transactionRule;
    }
    
    public void setTransactionRule(TransactionRule transactionRule)
    {
        this.transactionRule = transactionRule;
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
    
    @Column(name="NAME", nullable=false, length=75)
    public String getName() 
    {
        return this.name;
    }
    
    public void setName(String name) 
    {
        this.name = name;
    }
	
    @Column(name="PARAM_VALUE", length=150)
    public String getParamVal() 
    {
        return this.paramVal;
    }
    
    public void setParamVal(String paramVal) 
    {
        this.paramVal = paramVal;
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