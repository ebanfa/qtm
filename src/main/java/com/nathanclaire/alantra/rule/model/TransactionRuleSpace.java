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
import com.nathanclaire.alantra.rule.model.TransactionRuleCategory;

/**
 * TransactionRuleSpace 
 * @author Edward Banfa
 */
@Entity
@Table(name="TRANSACTION_RULE_SPACE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TransactionRuleSpace  extends BaseEntity implements java.io.Serializable {
	private String name;
	private String description;
	private Character defaultRuleSpaceFg;
	private Set<TransactionRuleCategory> transactionRuleCategorys;

    public TransactionRuleSpace() {
    }

    public TransactionRuleSpace(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public TransactionRuleSpace(String name, String description, Set transactionRuleCategorys, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.name = name;
        this.description = description;
        this.transactionRuleCategorys = transactionRuleCategorys;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="transactionRuleSpace")
    @JsonIgnore
    public Set<TransactionRuleCategory> getTransactionRuleCategorys() 
    {
        return this.transactionRuleCategorys;
    }
    
    public void setTransactionRuleCategorys(Set<TransactionRuleCategory> transactionRuleCategorys) 
    {
        this.transactionRuleCategorys = transactionRuleCategorys;
    }

	/**
	 * @return the defaultRuleSpaceFg
	 */
    @Column(name="DEFAULT_RULE_SPACE_FG", nullable=false, length=1)
	public Character getDefaultRuleSpaceFg() {
		return defaultRuleSpaceFg;
	}

	/**
	 * @param defaultRuleSpaceFg the defaultRuleSpaceFg to set
	 */
	public void setDefaultRuleSpaceFg(Character defaultRuleSpaceFg) {
		this.defaultRuleSpaceFg = defaultRuleSpaceFg;
	}			
		
    

}
