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
 * LimitType 
 * @author Edward Banfa
 */
@Entity
@Table(name="LIMIT_TYPE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class LimitType  extends BaseEntity implements java.io.Serializable {

    private String name;
    private String description;
	private Set<CustomerTypeTransactionLimit> customerTypeTransactionLimits = new HashSet<CustomerTypeTransactionLimit>(0);
	private Set<CustomerCategoryTransactionLimit> customerCategoryTransactionLimits = new HashSet<CustomerCategoryTransactionLimit>(0);
	private Set<CustomerTransactionLimit> customerTransactionLimits = new HashSet<CustomerTransactionLimit>(0);
	private Set<CustomerClassificationTransactionLimit> customerClassificationTransactionLimits = new HashSet<CustomerClassificationTransactionLimit>(0);

    public LimitType() {
    }

    public LimitType(String name, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.name = name;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public LimitType(String name, String description, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<CustomerTypeTransactionLimit> customerTypeTransactionLimits, Set<CustomerCategoryTransactionLimit> customerCategoryTransactionLimits, Set<CustomerTransactionLimit> customerTransactionLimits, Set<CustomerClassificationTransactionLimit> customerClassificationTransactionLimits ) 
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
		this.customerTypeTransactionLimits = customerTypeTransactionLimits;
		this.customerCategoryTransactionLimits = customerCategoryTransactionLimits;
		this.customerTransactionLimits = customerTransactionLimits;
		this.customerClassificationTransactionLimits = customerClassificationTransactionLimits;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="limitType")
    @JsonIgnore
    public Set<CustomerTypeTransactionLimit> getCustomerTypeTransactionLimits() 
    {
        return this.customerTypeTransactionLimits;
    }
    
    public void setCustomerTypeTransactionLimits(Set<CustomerTypeTransactionLimit> customerTypeTransactionLimits) 
    {
        this.customerTypeTransactionLimits = customerTypeTransactionLimits;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="limitType")
    @JsonIgnore
    public Set<CustomerCategoryTransactionLimit> getCustomerCategoryTransactionLimits() 
    {
        return this.customerCategoryTransactionLimits;
    }
    
    public void setCustomerCategoryTransactionLimits(Set<CustomerCategoryTransactionLimit> customerCategoryTransactionLimits) 
    {
        this.customerCategoryTransactionLimits = customerCategoryTransactionLimits;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="limitType")
    @JsonIgnore
    public Set<CustomerTransactionLimit> getCustomerTransactionLimits() 
    {
        return this.customerTransactionLimits;
    }
    
    public void setCustomerTransactionLimits(Set<CustomerTransactionLimit> customerTransactionLimits) 
    {
        this.customerTransactionLimits = customerTransactionLimits;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="limitType")
    @JsonIgnore
    public Set<CustomerClassificationTransactionLimit> getCustomerClassificationTransactionLimits() 
    {
        return this.customerClassificationTransactionLimits;
    }
    
    public void setCustomerClassificationTransactionLimits(Set<CustomerClassificationTransactionLimit> customerClassificationTransactionLimits) 
    {
        this.customerClassificationTransactionLimits = customerClassificationTransactionLimits;
    }			


}


