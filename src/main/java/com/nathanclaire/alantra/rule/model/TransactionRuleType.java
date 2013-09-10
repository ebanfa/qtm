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
import java.util.Set;
import com.nathanclaire.alantra.rule.model.TransactionRule;
import java.util.Set;
import com.nathanclaire.alantra.rule.model.TransactionRuleTypeParameter;

/**
 * TransactionRuleType 
 * @author Edward Banfa
 */
@Entity
@Table(name="TRANSACTION_RULE_TYPE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TransactionRuleType  extends BaseEntity implements java.io.Serializable {
	private TransactionRuleCategory transactionRuleCategory;
	private String name;
	private String description;
	private Set<TransactionRule> transactionRules;
	private Set<TransactionRuleTypeParameter> transactionRuleTypeParameters;

    public TransactionRuleType() {
    }

    public TransactionRuleType(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public TransactionRuleType(TransactionRuleCategory transactionRuleCategory, String name, String description, Set transactionRules, Set transactionRuleTypeParameters, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.transactionRuleCategory = transactionRuleCategory;
        this.name = name;
        this.description = description;
        this.transactionRules = transactionRules;
        this.transactionRuleTypeParameters = transactionRuleTypeParameters;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="transactionRuleType")
    @JsonIgnore
    public Set<TransactionRule> getTransactionRules() 
    {
        return this.transactionRules;
    }
    
    public void setTransactionRules(Set<TransactionRule> transactionRules) 
    {
        this.transactionRules = transactionRules;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="transactionRuleType")
    @JsonIgnore
    public Set<TransactionRuleTypeParameter> getTransactionRuleTypeParameters() 
    {
        return this.transactionRuleTypeParameters;
    }
    
    public void setTransactionRuleTypeParameters(Set<TransactionRuleTypeParameter> transactionRuleTypeParameters) 
    {
        this.transactionRuleTypeParameters = transactionRuleTypeParameters;
    }			
		
    

}
