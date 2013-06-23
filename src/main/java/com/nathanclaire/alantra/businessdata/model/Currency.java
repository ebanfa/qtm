/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.businessdata.model;

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

import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.transaction.model.ServiceTransaction;

/**
 * Currency 
 * @author Edward Banfa
 */
@Entity
@Table(name="CURRENCY"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Currency  extends BaseEntity implements java.io.Serializable {

    private String name;
    private String crncySym;
    private String remarks;
	private Set<Advice> advices = new HashSet<Advice>(0);
	private Set<ServiceTransaction> serviceTransactions = new HashSet<ServiceTransaction>(0);
	private Set<Country> countries = new HashSet<Country>(0);

    public Currency() {
    }

    public Currency(String code, String name, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public Currency(String code, String name, String crncySym, String remarks, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<Advice> advices, Set<ServiceTransaction> serviceTransactions, Set<Country> countries ) 
    {
		this.code = code;
		this.name = name;
		this.crncySym = crncySym;
		this.remarks = remarks;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.advices = advices;
		this.serviceTransactions = serviceTransactions;
		this.countries = countries;
    }
    
		
    @Column(name="NAME" , nullable=false, length=35)
    public String getName() 
    {
        return this.name;
    }
    
    public void setName(String name) 
    {
        this.name = name;
    }
		
    @Column(name="CRNCY_SYM" , unique=true, length=1)
    public String getCrncySym() 
    {
        return this.crncySym;
    }
    
    public void setCrncySym(String crncySym) 
    {
        this.crncySym = crncySym;
    }
		
    @Column(name="REMARKS" , unique=true)
    public String getRemarks() 
    {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="currency")
    @JsonIgnore
    public Set<Advice> getAdvices() 
    {
        return this.advices;
    }
    
    public void setAdvices(Set<Advice> advices) 
    {
        this.advices = advices;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="currency")
    @JsonIgnore
    public Set<ServiceTransaction> getServiceTransactions() 
    {
        return this.serviceTransactions;
    }
    
    public void setServiceTransactions(Set<ServiceTransaction> serviceTransactions) 
    {
        this.serviceTransactions = serviceTransactions;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="currency")
    @JsonIgnore
    public Set<Country> getCountries() 
    {
        return this.countries;
    }
    
    public void setCountries(Set<Country> countries) 
    {
        this.countries = countries;
    }			


}


