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
import com.nathanclaire.alantra.rule.model.TransactionRuleType;
import com.nathanclaire.alantra.rule.model.Parameter;

/**
 * TransactionRuleTypeParameter 
 * @author Edward Banfa
 */
@Entity
@Table(name="TRANSACTION_RULE_TYPE_PARAMETER"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TransactionRuleTypeParameter  extends BaseEntity implements java.io.Serializable {
	private TransactionRuleType transactionRuleType;
	private Parameter parameter;
	private String name;
	private String defaultVal;
	private String description;

    public TransactionRuleTypeParameter() {
    }

    public TransactionRuleTypeParameter(String name, String defaultVal, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.defaultVal = defaultVal;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public TransactionRuleTypeParameter(TransactionRuleType transactionRuleType, Parameter parameter, String name, String defaultVal, String description, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.transactionRuleType = transactionRuleType;
        this.parameter = parameter;
        this.name = name;
        this.defaultVal = defaultVal;
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
    @JoinColumn(name="RULE_TY_ID")
    @JsonIgnore
    public TransactionRuleType getTransactionRuleType() 
    {
        return this.transactionRuleType;
    }
    
    public void setTransactionRuleType(TransactionRuleType transactionRuleType)
    {
        this.transactionRuleType = transactionRuleType;
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
	
    @Column(name="DEFAULT_VAL", nullable=false, length=150)
    public String getDefaultVal() 
    {
        return this.defaultVal;
    }
    
    public void setDefaultVal(String defaultVal) 
    {
        this.defaultVal = defaultVal;
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
