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
 * CustomerCategory 
 * @author Edward Banfa
 */
@Entity
@Table(name="CUSTOMER_CATEGORY"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CustomerCategory  extends BaseEntity implements java.io.Serializable {

    private String name;
    private String description;
	private Set<CustomerCategoryTransactionLimit> customerCategoryTransactionLimits = new HashSet<CustomerCategoryTransactionLimit>(0);
	private Set<CustomerType> customerTypes = new HashSet<CustomerType>(0);
	private Set<CustomerCategoryCommsChannel> customerCategoryCommsChannels = new HashSet<CustomerCategoryCommsChannel>(0);

    public CustomerCategory() {
    }

    public CustomerCategory(String name, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.name = name;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public CustomerCategory(String name, String description, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<CustomerCategoryTransactionLimit> customerCategoryTransactionLimits, Set<CustomerType> customerTypes, Set<CustomerCategoryCommsChannel> customerCategoryCommsChannels ) 
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
		this.customerCategoryTransactionLimits = customerCategoryTransactionLimits;
		this.customerTypes = customerTypes;
		this.customerCategoryCommsChannels = customerCategoryCommsChannels;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="customerCategory")
    @JsonIgnore
    public Set<CustomerCategoryTransactionLimit> getCustomerCategoryTransactionLimits() 
    {
        return this.customerCategoryTransactionLimits;
    }
    
    public void setCustomerCategoryTransactionLimits(Set<CustomerCategoryTransactionLimit> customerCategoryTransactionLimits) 
    {
        this.customerCategoryTransactionLimits = customerCategoryTransactionLimits;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="customerCategory")
    @JsonIgnore
    public Set<CustomerType> getCustomerTypes() 
    {
        return this.customerTypes;
    }
    
    public void setCustomerTypes(Set<CustomerType> customerTypes) 
    {
        this.customerTypes = customerTypes;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="customerCategory")
    @JsonIgnore
    public Set<CustomerCategoryCommsChannel> getCustomerCategoryNotificationChannels() 
    {
        return this.customerCategoryCommsChannels;
    }
    
    public void setCustomerCategoryNotificationChannels(Set<CustomerCategoryCommsChannel> customerCategoryCommsChannels) 
    {
        this.customerCategoryCommsChannels = customerCategoryCommsChannels;
    }			


}


