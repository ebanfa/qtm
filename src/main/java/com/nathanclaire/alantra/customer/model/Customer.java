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
    private String primaryEmail;
    private String secondaryEmail;
    private String primaryMobile;
    private String secondaryMobile;
	private Set<CustomerNotification> customerNotifications = new HashSet<CustomerNotification>(0);
	private Set<Advice> advices = new HashSet<Advice>(0);
	private Set<CustomerAccount> customerAccounts = new HashSet<CustomerAccount>(0);
	private Set<AdviceRequestMessage> adviceRequestMessages = new HashSet<AdviceRequestMessage>(0);
	private Set<CustomerTransactionLimit> customerTransactionLimits = new HashSet<CustomerTransactionLimit>(0);
	private Set<CustomerNotificationChannel> customerNotificationChannels = new HashSet<CustomerNotificationChannel>(0);
	private Set<CustomerMessage> customerMessages = new HashSet<CustomerMessage>(0);
	private Set<CustomerBlacklist> customerBlacklists = new HashSet<CustomerBlacklist>(0);

    public Customer() {
    }

    public Customer(CustomerClassification customerClassification, CustomerType customerType, String code, String pin, String name, String primaryEmail, String primaryMobile, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.pin = pin;
		this.name = name;
		this.primaryEmail = primaryEmail;
		this.primaryMobile = primaryMobile;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public Customer(CustomerClassification customerClassification, CustomerType customerType, String code, String pin, String name, String primaryEmail, String secondaryEmail, String primaryMobile, String secondaryMobile, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<CustomerNotification> customerNotifications, Set<Advice> advices, Set<CustomerAccount> customerAccounts, Set<AdviceRequestMessage> adviceRequestMessages, Set<CustomerTransactionLimit> customerTransactionLimits, Set<CustomerNotificationChannel> customerNotificationChannels, Set<CustomerMessage> customerMessages, Set<CustomerBlacklist> customerBlacklists ) 
    {
		this.customerClassification = customerClassification;
		this.customerType = customerType;
		this.code = code;
		this.pin = pin;
		this.name = name;
		this.primaryEmail = primaryEmail;
		this.secondaryEmail = secondaryEmail;
		this.primaryMobile = primaryMobile;
		this.secondaryMobile = secondaryMobile;
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
		this.customerTransactionLimits = customerTransactionLimits;
		this.customerNotificationChannels = customerNotificationChannels;
		this.customerMessages = customerMessages;
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
		
    @Column(name="PRIMARY_EMAIL" , nullable=false, length=50)
    public String getPrimaryEmail() 
    {
        return this.primaryEmail;
    }
    
    public void setPrimaryEmail(String primaryEmail) 
    {
        this.primaryEmail = primaryEmail;
    }
		
    @Column(name="SECONDARY_EMAIL" , unique=true, length=50)
    public String getSecondaryEmail() 
    {
        return this.secondaryEmail;
    }
    
    public void setSecondaryEmail(String secondaryEmail) 
    {
        this.secondaryEmail = secondaryEmail;
    }
		
    @Column(name="PRIMARY_MOBILE" , nullable=false, length=35)
    public String getPrimaryMobile() 
    {
        return this.primaryMobile;
    }
    
    public void setPrimaryMobile(String primaryMobile) 
    {
        this.primaryMobile = primaryMobile;
    }
		
    @Column(name="SECONDARY_MOBILE" , unique=true, length=35)
    public String getSecondaryMobile() 
    {
        return this.secondaryMobile;
    }
    
    public void setSecondaryMobile(String secondaryMobile) 
    {
        this.secondaryMobile = secondaryMobile;
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
    public Set<CustomerTransactionLimit> getCustomerTransactionLimits() 
    {
        return this.customerTransactionLimits;
    }
    
    public void setCustomerTransactionLimits(Set<CustomerTransactionLimit> customerTransactionLimits) 
    {
        this.customerTransactionLimits = customerTransactionLimits;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="customer")
    @JsonIgnore
    public Set<CustomerNotificationChannel> getCustomerNotificationChannels() 
    {
        return this.customerNotificationChannels;
    }
    
    public void setCustomerNotificationChannels(Set<CustomerNotificationChannel> customerNotificationChannels) 
    {
        this.customerNotificationChannels = customerNotificationChannels;
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
    public Set<CustomerBlacklist> getCustomerBlacklists() 
    {
        return this.customerBlacklists;
    }
    
    public void setCustomerBlacklists(Set<CustomerBlacklist> customerBlacklists) 
    {
        this.customerBlacklists = customerBlacklists;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;
	}			


}


