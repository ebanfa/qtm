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
 * CustomerClassification 
 * @author Edward Banfa
 */
@Entity
@Table(name="CUSTOMER_CLASSIFICATION"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CustomerClassification  extends BaseEntity implements java.io.Serializable {

    private String name;
    private String description;
	private Set<Customer> customers = new HashSet<Customer>(0);
	private Set<CustomerClassificationTransactionLimit> customerClassificationTransactionLimits = new HashSet<CustomerClassificationTransactionLimit>(0);
	private Set<CustomerClassificationNotificationChannel> customerClassificationNotificationChannels = new HashSet<CustomerClassificationNotificationChannel>(0);

    public CustomerClassification() {
    }

    public CustomerClassification(String name, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.name = name;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public CustomerClassification(String name, String description, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<Customer> customers, Set<CustomerClassificationTransactionLimit> customerClassificationTransactionLimits, Set<CustomerClassificationNotificationChannel> customerClassificationNotificationChannels ) 
    {
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
		this.customerClassificationTransactionLimits = customerClassificationTransactionLimits;
		this.customerClassificationNotificationChannels = customerClassificationNotificationChannels;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="customerClassification")
    @JsonIgnore
    public Set<Customer> getCustomers() 
    {
        return this.customers;
    }
    
    public void setCustomers(Set<Customer> customers) 
    {
        this.customers = customers;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="customerClassification")
    @JsonIgnore
    public Set<CustomerClassificationTransactionLimit> getCustomerClassificationTransactionLimits() 
    {
        return this.customerClassificationTransactionLimits;
    }
    
    public void setCustomerClassificationTransactionLimits(Set<CustomerClassificationTransactionLimit> customerClassificationTransactionLimits) 
    {
        this.customerClassificationTransactionLimits = customerClassificationTransactionLimits;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="customerClassification")
    @JsonIgnore
    public Set<CustomerClassificationNotificationChannel> getCustomerClassificationNotificationChannels() 
    {
        return this.customerClassificationNotificationChannels;
    }
    
    public void setCustomerClassificationNotificationChannels(Set<CustomerClassificationNotificationChannel> customerClassificationNotificationChannels) 
    {
        this.customerClassificationNotificationChannels = customerClassificationNotificationChannels;
    }			


}


