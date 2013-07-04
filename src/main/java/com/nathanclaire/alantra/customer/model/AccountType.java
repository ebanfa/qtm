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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.model.BaseEntity;

/**
 * AccountType 
 * @author Edward Banfa
 */
@Entity
@Table(name="ACCOUNT_TYPE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AccountType  extends BaseEntity implements java.io.Serializable {

    private String name;
    private String description;
    private String accountNoFormat;
    private String chequeNoFormat;
    private String cardNoFormat;
	private Set<Account> accounts = new HashSet<Account>(0);

    public AccountType() {
    }

    public AccountType(String code, String name, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public AccountType(String code, String name, String description, String accountNoFormat, String chequeNoFormat, String cardNoFormat, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<Account> accounts ) 
    {
		this.code = code;
		this.name = name;
		this.description = description;
		this.accountNoFormat = accountNoFormat;
		this.chequeNoFormat = chequeNoFormat;
		this.cardNoFormat = cardNoFormat;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.accounts = accounts;
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
		
    @Column(name="ACCOUNT_NO_FORMAT" , unique=true, length=15)
    public String getAccountNoFormat() 
    {
        return this.accountNoFormat;
    }
    
    public void setAccountNoFormat(String accountNoFormat) 
    {
        this.accountNoFormat = accountNoFormat;
    }
		
    @Column(name="CHEQUE_NO_FORMAT" , unique=true, length=15)
    public String getChequeNoFormat() 
    {
        return this.chequeNoFormat;
    }
    
    public void setChequeNoFormat(String chequeNoFormat) 
    {
        this.chequeNoFormat = chequeNoFormat;
    }
		
    @Column(name="CARD_NO_FORMAT" , unique=true, length=15)
    public String getCardNoFormat() 
    {
        return this.cardNoFormat;
    }
    
    public void setCardNoFormat(String cardNoFormat) 
    {
        this.cardNoFormat = cardNoFormat;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="accountType")
    @JsonIgnore
    public Set<Account> getAccounts() 
    {
        return this.accounts;
    }
    
    public void setAccounts(Set<Account> accounts) 
    {
        this.accounts = accounts;
    }			


}


