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

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.businessdata.model.Currency;

/**
 * Account 
 * @author Edward Banfa
 */
@Entity
@Table(name="ACCOUNT"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Account  extends BaseEntity implements java.io.Serializable {

	private Currency currency;
	private AccountType accountType;
    private String name;
    private String accountNo;
    private char isJointFg;
    private String description;
	private Set<CustomerAccount> customerAccounts = new HashSet<CustomerAccount>(0);

    public Account() {
    }

    public Account(Currency currency, AccountType accountType, String code, String name, String accountNo, char isJointFg, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.accountNo = accountNo;
		this.isJointFg = isJointFg;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public Account(Currency currency, AccountType accountType, String code, String name, String accountNo, char isJointFg, String description, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<CustomerAccount> customerAccounts ) 
    {
		this.currency = currency;
		this.accountType = accountType;
		this.code = code;
		this.name = name;
		this.accountNo = accountNo;
		this.isJointFg = isJointFg;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.customerAccounts = customerAccounts;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CURRENCY_ID", nullable=false)
    @JsonIgnore
    public Currency getCurrency() 
    {
        return this.currency;
    }
    
    public void setCurrency(Currency currency)
    {
        this.currency = currency;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ACCOUNT_TY_ID", nullable=false)
    @JsonIgnore
    public AccountType getAccountType() 
    {
        return this.accountType;
    }
    
    public void setAccountType(AccountType accountType)
    {
        this.accountType = accountType;
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
		
    @Column(name="ACCOUNT_NO" , nullable=false, length=35)
    public String getAccountNo() 
    {
        return this.accountNo;
    }
    
    public void setAccountNo(String accountNo) 
    {
        this.accountNo = accountNo;
    }
		
    @Column(name="IS_JOINT_FG" , nullable=false, length=1)
    public char getIsJointFg() 
    {
        return this.isJointFg;
    }
    
    public void setIsJointFg(char isJointFg) 
    {
        this.isJointFg = isJointFg;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="account")
    @JsonIgnore
    public Set<CustomerAccount> getCustomerAccounts() 
    {
        return this.customerAccounts;
    }
    
    public void setCustomerAccounts(Set<CustomerAccount> customerAccounts) 
    {
        this.customerAccounts = customerAccounts;
    }			


}


