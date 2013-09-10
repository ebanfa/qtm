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
import com.nathanclaire.alantra.rule.model.TransactionRuleSpace;
import java.util.Set;
import com.nathanclaire.alantra.rule.model.TransactionRuleType;
import java.util.Set;
import com.nathanclaire.alantra.rule.model.TransactionRuleCategoryParameter;

/**
 * TransactionRuleCategory 
 * @author Edward Banfa
 */
@Entity
@Table(name="TRANSACTION_RULE_CATEGORY"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TransactionRuleCategory  extends BaseEntity implements java.io.Serializable {
	private TransactionRuleSpace transactionRuleSpace;
	private String name;
	private String description;
	private Set<TransactionRuleType> transactionRuleTypes;
	private Set<TransactionRuleCategoryParameter> transactionRuleCategoryParameters;

    public TransactionRuleCategory() {
    }

    public TransactionRuleCategory(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public TransactionRuleCategory(TransactionRuleSpace transactionRuleSpace, String name, String description, Set transactionRuleTypes, Set transactionRuleCategoryParameters, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.transactionRuleSpace = transactionRuleSpace;
        this.name = name;
        this.description = description;
        this.transactionRuleTypes = transactionRuleTypes;
        this.transactionRuleCategoryParameters = transactionRuleCategoryParameters;
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
    @JoinColumn(name="RULE_SPACE_ID")
    @JsonIgnore
    public TransactionRuleSpace getTransactionRuleSpace() 
    {
        return this.transactionRuleSpace;
    }
    
    public void setTransactionRuleSpace(TransactionRuleSpace transactionRuleSpace)
    {
        this.transactionRuleSpace = transactionRuleSpace;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="transactionRuleCategory")
    @JsonIgnore
    public Set<TransactionRuleType> getTransactionRuleTypes() 
    {
        return this.transactionRuleTypes;
    }
    
    public void setTransactionRuleTypes(Set<TransactionRuleType> transactionRuleTypes) 
    {
        this.transactionRuleTypes = transactionRuleTypes;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="transactionRuleCategory")
    @JsonIgnore
    public Set<TransactionRuleCategoryParameter> getTransactionRuleCategoryParameters() 
    {
        return this.transactionRuleCategoryParameters;
    }
    
    public void setTransactionRuleCategoryParameters(Set<TransactionRuleCategoryParameter> transactionRuleCategoryParameters) 
    {
        this.transactionRuleCategoryParameters = transactionRuleCategoryParameters;
    }			
		
    

}
