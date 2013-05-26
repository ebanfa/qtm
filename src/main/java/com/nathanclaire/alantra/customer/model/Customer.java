/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.model;

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
    private String name;
    private String email;
    private String mobile;
	private Set<Advice> advices = new HashSet<Advice>(0);
	private Set<CustomerBlacklist> customerBlacklists = new HashSet<CustomerBlacklist>(0);

    public Customer() {
    }

    public Customer(CustomerClassification customerClassification, CustomerType customerType, String name, String email, String mobile) 
    {
		this.name = name;
		this.email = email;
		this.mobile = mobile;
    }
    public Customer(CustomerClassification customerClassification, CustomerType customerType, String name, String email, String mobile, Set<Advice> advices, Set<CustomerBlacklist> customerBlacklists ) 
    {
		this.customerClassification = customerClassification;
		this.customerType = customerType;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.advices = advices;
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
		
    @Column(name="MOBILE" , nullable=false, length=15)
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
    public Set<CustomerBlacklist> getCustomerBlacklists() 
    {
        return this.customerBlacklists;
    }
    
    public void setCustomerBlacklists(Set<CustomerBlacklist> customerBlacklists) 
    {
        this.customerBlacklists = customerBlacklists;
    }			


}


