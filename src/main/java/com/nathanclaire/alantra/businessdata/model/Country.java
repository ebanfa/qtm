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
 * Country 
 * @author Edward Banfa
 */
@Entity
@Table(name="COUNTRY"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Country  extends BaseEntity implements java.io.Serializable {

	private Currency currency;
    private String name;
    private String cntryCdIso2;
    private String cntryCdIso3;
    private String remarks;
	private Set<BusinessUnit> businessUnits = new HashSet<BusinessUnit>(0);

    public Country() {
    }

    public Country(String code, String name, String cntryCdIso2, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.cntryCdIso2 = cntryCdIso2;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public Country(Currency currency, String code, String name, String cntryCdIso2, String cntryCdIso3, String remarks, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<BusinessUnit> businessUnits ) 
    {
		this.currency = currency;
		this.code = code;
		this.name = name;
		this.cntryCdIso2 = cntryCdIso2;
		this.cntryCdIso3 = cntryCdIso3;
		this.remarks = remarks;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.businessUnits = businessUnits;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="LOCAL_CRNCY_ID")
    @JsonIgnore
    public Currency getCurrency() 
    {
        return this.currency;
    }
    
    public void setCurrency(Currency currency)
    {
        this.currency = currency;
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
		
    @Column(name="CNTRY_CD_ISO2" , nullable=false, length=2)
    public String getCntryCdIso2() 
    {
        return this.cntryCdIso2;
    }
    
    public void setCntryCdIso2(String cntryCdIso2) 
    {
        this.cntryCdIso2 = cntryCdIso2;
    }
		
    @Column(name="CNTRY_CD_ISO3" , unique=true, length=3)
    public String getCntryCdIso3() 
    {
        return this.cntryCdIso3;
    }
    
    public void setCntryCdIso3(String cntryCdIso3) 
    {
        this.cntryCdIso3 = cntryCdIso3;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="country")
    @JsonIgnore
    public Set<BusinessUnit> getBusinessUnits() 
    {
        return this.businessUnits;
    }
    
    public void setBusinessUnits(Set<BusinessUnit> businessUnits) 
    {
        this.businessUnits = businessUnits;
    }			


}


