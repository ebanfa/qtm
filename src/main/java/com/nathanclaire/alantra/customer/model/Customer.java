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
import com.nathanclaire.alantra.advice.model.AdviceRequestMessage;
import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.notification.model.CustomerNotification;

/**
 * Customer 
 * @author Edward Banfa
 */
@Entity
@Table(name="CUSTOMER"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Customer  extends BaseEntity implements java.io.Serializable {

	private CustomerClassification customerClassification;
	private CustomerType customerType;
    private String pin;
    private String name;
    private String email;
    private String mobile;
	private Set<CustomerNotification> customerNotifications = new HashSet<CustomerNotification>(0);
	private Set<Advice> advices = new HashSet<Advice>(0);
	private Set<CustomerAccount> customerAccounts = new HashSet<CustomerAccount>(0);
	private Set<AdviceRequestMessage> adviceRequestMessages = new HashSet<AdviceRequestMessage>(0);
	private Set<CustomerMessage> customerMessages = new HashSet<CustomerMessage>(0);
	private Set<CustomerLimit> customerLimits = new HashSet<CustomerLimit>(0);
	private Set<CustomerBlacklist> customerBlacklists = new HashSet<CustomerBlacklist>(0);

    public Customer() {
    }

    public Customer(CustomerClassification customerClassification, CustomerType customerType, String code, String pin, String name, String email, String mobile, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.pin = pin;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public Customer(CustomerClassification customerClassification, CustomerType customerType, String code, String pin, String name, String email, String mobile, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<CustomerNotification> customerNotifications, Set<Advice> advices, Set<CustomerAccount> customerAccounts, Set<AdviceRequestMessage> adviceRequestMessages, Set<CustomerMessage> customerMessages, Set<CustomerLimit> customerLimits, Set<CustomerBlacklist> customerBlacklists ) 
    {
		this.customerClassification = customerClassification;
		this.customerType = customerType;
		this.code = code;
		this.pin = pin;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.customerNotifications = customerNotifications;
		this.advices = advices;
		this.customerAccounts = customerAccounts;
		this.adviceRequestMessages = adviceRequestMessages;
		this.customerMessages = customerMessages;
		this.customerLimits = customerLimits;
		this.customerBlacklists = customerBlacklists;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CUST_CLASS_ID", nullable=false)
    @JsonIgnore
    public CustomerClassification getCustomerClassification() 
    {
        return this.customerClassification;
    }
    
    public void setCustomerClassification(CustomerClassification customerClassification)
    {
        this.customerClassification = customerClassification;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CUST_TY_ID", nullable=false)
    @JsonIgnore
    public CustomerType getCustomerType() 
    {
        return this.customerType;
    }
    
    public void setCustomerType(CustomerType customerType)
    {
        this.customerType = customerType;
    }
		
    @Column(name="PIN" , nullable=false, length=4)
    public String getPin() 
    {
        return this.pin;
    }
    
    public void setPin(String pin) 
    {
        this.pin = pin;
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
		
    @Column(name="EMAIL" , nullable=false, length=50)
    public String getEmail() 
    {
        return this.email;
    }
    
    public void setEmail(String email) 
    {
        this.email = email;
    }
		
    @Column(name="MOBILE" , nullable=false, length=35)
    public String getMobile() 
    {
        return this.mobile;
    }
    
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="customer")
    @JsonIgnore
    public Set<CustomerNotification> getCustomerNotifications() 
    {
        return this.customerNotifications;
    }
    
    public void setCustomerNotifications(Set<CustomerNotification> customerNotifications) 
    {
        this.customerNotifications = customerNotifications;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="customer")
    @JsonIgnore
    public Set<Advice> getAdvices() 
    {
        return this.advices;
    }
    
    public void setAdvices(Set<Advice> advices) 
    {
        this.advices = advices;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="customer")
    @JsonIgnore
    public Set<CustomerAccount> getCustomerAccounts() 
    {
        return this.customerAccounts;
    }
    
    public void setCustomerAccounts(Set<CustomerAccount> customerAccounts) 
    {
        this.customerAccounts = customerAccounts;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="customer")
    @JsonIgnore
    public Set<AdviceRequestMessage> getAdviceRequestMessages() 
    {
        return this.adviceRequestMessages;
    }
    
    public void setAdviceRequestMessages(Set<AdviceRequestMessage> adviceRequestMessages) 
    {
        this.adviceRequestMessages = adviceRequestMessages;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="customer")
    @JsonIgnore
    public Set<CustomerMessage> getCustomerMessages() 
    {
        return this.customerMessages;
    }
    
    public void setCustomerMessages(Set<CustomerMessage> customerMessages) 
    {
        this.customerMessages = customerMessages;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="customer")
    @JsonIgnore
    public Set<CustomerLimit> getCustomerLimits() 
    {
        return this.customerLimits;
    }
    
    public void setCustomerLimits(Set<CustomerLimit> customerLimits) 
    {
        this.customerLimits = customerLimits;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="customer")
    @JsonIgnore
    public Set<CustomerBlacklist> getCustomerBlacklists() 
    {
        return this.customerBlacklists;
    }
    
    public void setCustomerBlacklists(Set<CustomerBlacklist> customerBlacklists) 
    {
        this.customerBlacklists = customerBlacklists;
    }			


}


