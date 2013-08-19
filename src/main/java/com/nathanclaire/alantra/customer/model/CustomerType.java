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

/**
 * CustomerType 
 * @author Edward Banfa
 */
@Entity
@Table(name="CUSTOMER_TYPE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CustomerType  extends BaseEntity implements java.io.Serializable {

	private CustomerCategory customerCategory;
    private String name;
    private String description;
	private Set<Customer> customers = new HashSet<Customer>(0);
	private Set<CustomerTypeTransactionLimit> customerTypeTransactionLimits = new HashSet<CustomerTypeTransactionLimit>(0);
	private Set<CustomerTypeCommsChannel> customerTypeCommsChannels = new HashSet<CustomerTypeCommsChannel>(0);

    public CustomerType() {
    }

    public CustomerType(CustomerCategory customerCategory, String name, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.name = name;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public CustomerType(CustomerCategory customerCategory, String name, String description, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<Customer> customers, Set<CustomerTypeTransactionLimit> customerTypeTransactionLimits, Set<CustomerTypeCommsChannel> customerTypeCommsChannels ) 
    {
		this.customerCategory = customerCategory;
		this.name = name;
		this.description = description;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.customers = customers;
		this.customerTypeTransactionLimits = customerTypeTransactionLimits;
		this.customerTypeCommsChannels = customerTypeCommsChannels;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CUST_CAT_ID", nullable=false)
    @JsonIgnore
    public CustomerCategory getCustomerCategory() 
    {
        return this.customerCategory;
    }
    
    public void setCustomerCategory(CustomerCategory customerCategory)
    {
        this.customerCategory = customerCategory;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="customerType")
    @JsonIgnore
    public Set<Customer> getCustomers() 
    {
        return this.customers;
    }
    
    public void setCustomers(Set<Customer> customers) 
    {
        this.customers = customers;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="customerType")
    @JsonIgnore
    public Set<CustomerTypeTransactionLimit> getCustomerTypeTransactionLimits() 
    {
        return this.customerTypeTransactionLimits;
    }
    
    public void setCustomerTypeTransactionLimits(Set<CustomerTypeTransactionLimit> customerTypeTransactionLimits) 
    {
        this.customerTypeTransactionLimits = customerTypeTransactionLimits;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="customerType")
    @JsonIgnore
    public Set<CustomerTypeCommsChannel> getCustomerTypeNotificationChannels() 
    {
        return this.customerTypeCommsChannels;
    }
    
    public void setCustomerTypeNotificationChannels(Set<CustomerTypeCommsChannel> customerTypeCommsChannels) 
    {
        this.customerTypeCommsChannels = customerTypeCommsChannels;
    }			

}

