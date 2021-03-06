/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.transaction.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.advice.model.AdviceType;
import com.nathanclaire.alantra.base.model.BaseEntity;

/**
 * ServiceTransactionType 
 * @author Edward Banfa
 */
@Entity
@Table(name="SERVICE_TRANSACTION_TYPE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ServiceTransactionType  extends BaseEntity implements java.io.Serializable {

	private ServiceTransactionCategory serviceTransactionCategory;
    private String name;
    private String description;
    private Character matchFg;
	private Set<AdviceType> adviceTypes = new HashSet<AdviceType>(0);
	private Set<ServiceTransaction> serviceTransactions = new HashSet<ServiceTransaction>(0);

    public ServiceTransactionType() {
    }

    public ServiceTransactionType(ServiceTransactionCategory serviceTransactionCategory, String code, String name, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public ServiceTransactionType(Character matchFg, ServiceTransactionCategory serviceTransactionCategory, String code, String name, String description, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<AdviceType> adviceTypes, Set<ServiceTransaction> serviceTransactions ) 
    {
		this.serviceTransactionCategory = serviceTransactionCategory;
		this.code = code;
		this.matchFg = matchFg;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.adviceTypes = adviceTypes;
		this.serviceTransactions = serviceTransactions;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TRANSACTION_CAT_ID", nullable=false)
    @JsonIgnore
    public ServiceTransactionCategory getServiceTransactionCategory() 
    {
        return this.serviceTransactionCategory;
    }
    
    public void setServiceTransactionCategory(ServiceTransactionCategory serviceTransactionCategory)
    {
        this.serviceTransactionCategory = serviceTransactionCategory;
    }
		
    @Column(name="NAME" , nullable=false, length=75)
    public String getName() 
    {
        return this.name;
    }
    
    public void setName(String name) 
    {
        this.name = name;
    }
		
    @Column(name="DESCRIPTION" , unique=true, length=150)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }	
    
    @Column(name="MATCH_FG" , length=1)
    public Character getMatchFg() {
        return this.matchFg;
    }
    
    public void setMatchFg(Character matchFg) {
        this.matchFg = matchFg;
    }

			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="serviceTransactionType")
    @JsonIgnore
    public Set<AdviceType> getAdviceTypes() 
    {
        return this.adviceTypes;
    }
    
    public void setAdviceTypes(Set<AdviceType> adviceTypes) 
    {
        this.adviceTypes = adviceTypes;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="serviceTransactionType")
    @JsonIgnore
    public Set<ServiceTransaction> getServiceTransactions() 
    {
        return this.serviceTransactions;
    }
    
    public void setServiceTransactions(Set<ServiceTransaction> serviceTransactions) 
    {
        this.serviceTransactions = serviceTransactions;
    }			


}


