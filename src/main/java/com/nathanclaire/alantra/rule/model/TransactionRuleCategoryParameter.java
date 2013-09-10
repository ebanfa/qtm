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
import com.nathanclaire.alantra.rule.model.TransactionRuleCategory;
import com.nathanclaire.alantra.rule.model.Parameter;

/**
 * TransactionRuleCategoryParameter 
 * @author Edward Banfa
 */
@Entity
@Table(name="TRANSACTION_RULE_CATEGORY_PARAMETER"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TransactionRuleCategoryParameter  extends BaseEntity implements java.io.Serializable {
	private TransactionRuleCategory transactionRuleCategory;
	private Parameter parameter;
	private String name;
	private String defaultVal;
	private String description;

    public TransactionRuleCategoryParameter() {
    }

    public TransactionRuleCategoryParameter(String name, String defaultVal, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
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

    public TransactionRuleCategoryParameter(TransactionRuleCategory transactionRuleCategory, Parameter parameter, String name, String defaultVal, String description, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.transactionRuleCategory = transactionRuleCategory;
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
    @JoinColumn(name="RULE_CAT_ID")
    @JsonIgnore
    public TransactionRuleCategory getTransactionRuleCategory() 
    {
        return this.transactionRuleCategory;
    }
    
    public void setTransactionRuleCategory(TransactionRuleCategory transactionRuleCategory)
    {
        this.transactionRuleCategory = transactionRuleCategory;
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
