/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.model.BaseEntity;

/**
 * CustomerBlacklist 
 * @author Edward Banfa
 */
@Entity
@Table(name="CUSTOMER_BLACKLIST"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CustomerBlacklist  extends BaseEntity implements java.io.Serializable {

	private Customer customer;
    private String description;

    public CustomerBlacklist() {
    }

    public CustomerBlacklist(Customer customer) 
    {
    }
    public CustomerBlacklist(Customer customer, String description) 
    {
		this.customer = customer;
		this.description = description;
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
		
    @Column(name="DESCRIPTION" , unique=true, length=200)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }


}


