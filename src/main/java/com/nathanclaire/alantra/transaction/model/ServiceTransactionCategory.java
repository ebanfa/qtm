/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.transaction.model;

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
 * ServiceTransactionCategory 
 * @author Edward Banfa
 */
@Entity
@Table(name="SERVICE_TRANSACTION_CATEGORY"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ServiceTransactionCategory  extends BaseEntity implements java.io.Serializable {

    private String name;
    private String description;
	private Set<ServiceTransactionType> serviceTransactionTypes = new HashSet<ServiceTransactionType>(0);

    public ServiceTransactionCategory() {
    }

    public ServiceTransactionCategory(String code, String name, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public ServiceTransactionCategory(String code, String name, String description, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<ServiceTransactionType> serviceTransactionTypes ) 
    {
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.serviceTransactionTypes = serviceTransactionTypes;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="serviceTransactionCategory")
    @JsonIgnore
    public Set<ServiceTransactionType> getServiceTransactionTypes() 
    {
        return this.serviceTransactionTypes;
    }
    
    public void setServiceTransactionTypes(Set<ServiceTransactionType> serviceTransactionTypes) 
    {
        this.serviceTransactionTypes = serviceTransactionTypes;
    }			


}


