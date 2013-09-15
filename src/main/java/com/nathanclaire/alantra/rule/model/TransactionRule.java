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

import com.nathanclaire.alantra.application.model.ApplicationModule;
import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.rule.model.TransactionRuleType;
import com.nathanclaire.alantra.rule.model.TransactionRuleAction;
import java.util.Set;
import com.nathanclaire.alantra.rule.model.TransactionRuleParameter;
import java.util.Set;
import com.nathanclaire.alantra.rule.model.TransactionRuleCondition;

/**
 * TransactionRule 
 * @author Edward Banfa
 */
@Entity
@Table(name="TRANSACTION_RULE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TransactionRule  extends BaseEntity implements java.io.Serializable {
	private TransactionRuleType transactionRuleType;
	private TransactionRuleAction transactionRuleAction;
	private ApplicationModule applicationModule;
	private String name;
	private Character operatorModeFg;
	private String description;
	private Set<TransactionRuleParameter> transactionRuleParameters;
	private Set<TransactionRuleCondition> transactionRuleConditions;

    public TransactionRule() {
    }

    public TransactionRule(String name, Character operatorModeFg, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.operatorModeFg = operatorModeFg;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public TransactionRule(TransactionRuleType transactionRuleType, TransactionRuleAction transactionRuleAction, String name, Character operatorModeFg, String description, Set transactionRuleParameters, Set transactionRuleConditions, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.transactionRuleType = transactionRuleType;
        this.transactionRuleAction = transactionRuleAction;
        this.name = name;
        this.operatorModeFg = operatorModeFg;
        this.description = description;
        this.transactionRuleParameters = transactionRuleParameters;
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
    @JoinColumn(name="RULE_ACTION_ID")
    @JsonIgnore
    public TransactionRuleAction getTransactionRuleAction() 
    {
        return this.transactionRuleAction;
    }
    
    public void setTransactionRuleAction(TransactionRuleAction transactionRuleAction)
    {
        this.transactionRuleAction = transactionRuleAction;
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
	
    @Column(name="OPERATOR_MODE_FG", nullable=false)
    public Character getOperatorModeFg() 
    {
        return this.operatorModeFg;
    }
    
    public void setOperatorModeFg(Character operatorModeFg) 
    {
        this.operatorModeFg = operatorModeFg;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="transactionRule")
    @JsonIgnore
    public Set<TransactionRuleParameter> getTransactionRuleParameters() 
    {
        return this.transactionRuleParameters;
    }
    
    public void setTransactionRuleParameters(Set<TransactionRuleParameter> transactionRuleParameters) 
    {
        this.transactionRuleParameters = transactionRuleParameters;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="transactionRule")
    @JsonIgnore
    public Set<TransactionRuleCondition> getTransactionRuleConditions() 
    {
        return this.transactionRuleConditions;
    }
    
    public void setTransactionRuleConditions(Set<TransactionRuleCondition> transactionRuleConditions) 
    {
        this.transactionRuleConditions = transactionRuleConditions;
    }

	/**
	 * @return the applicationModule
	 */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROCESS_CAT_ID")
    @JsonIgnore
	public ApplicationModule getApplicationModule() {
		return applicationModule;
	}

	/**
	 * @param applicationModule the applicationModule to set
	 */
	public void setApplicationModule(ApplicationModule applicationModule) {
		this.applicationModule = applicationModule;
	}			
		
    

}
