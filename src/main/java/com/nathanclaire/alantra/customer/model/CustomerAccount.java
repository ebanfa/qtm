/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.model;

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

import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.transaction.model.ServiceTransaction;

/**
 * CustomerAccount 
 * @author Edward Banfa
 */
@Entity
@Table(name="CUSTOMER_ACCOUNT"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CustomerAccount  extends BaseEntity implements java.io.Serializable {

	private Customer customer;
	private Account account;
    private String name;
    private String description;
    private char isDefaultFg;
	private Set<Advice> advices = new HashSet<Advice>(0);
	private Set<ServiceTransaction> serviceTransactions = new HashSet<ServiceTransaction>(0);

    public CustomerAccount() {
    }

    public CustomerAccount(Customer customer, Account account, String code, String name, char isDefaultFg, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.isDefaultFg = isDefaultFg;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public CustomerAccount(Customer customer, Account account, String code, String name, String description, char isDefaultFg, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<Advice> advices, Set<ServiceTransaction> serviceTransactions ) 
    {
		this.customer = customer;
		this.account = account;
		this.code = code;
		this.name = name;
		this.description = description;
		this.isDefaultFg = isDefaultFg;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.advices = advices;
		this.serviceTransactions = serviceTransactions;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CUST_ID", nullable=false)
    @JsonIgnore
    public Customer getCustomer() 
    {
        return this.customer;
    }
    
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ACCOUNT_ID", nullable=false)
    @JsonIgnore
    public Account getAccount() 
    {
        return this.account;
    }
    
    public void setAccount(Account account)
    {
        this.account = account;
    }
		
    @Column(name="NAME" , nullable=false, length=150)
    public String getName() 
    {
        return this.name;
    }
    
    public void setName(String name) 
    {
        this.name = name;
    }
		
    @Column(name="DESCRIPTION" , unique=true, length=200)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
		
    @Column(name="IS_DEFAULT_FG" , nullable=false, length=1)
    public char getIsDefaultFg() 
    {
        return this.isDefaultFg;
    }
    
    public void setIsDefaultFg(char isDefaultFg) 
    {
        this.isDefaultFg = isDefaultFg;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="customerAccount")
    @JsonIgnore
    public Set<Advice> getAdvices() 
    {
        return this.advices;
    }
    
    public void setAdvices(Set<Advice> advices) 
    {
        this.advices = advices;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="customerAccount")
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


