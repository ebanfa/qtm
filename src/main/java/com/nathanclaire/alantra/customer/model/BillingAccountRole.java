/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.BaseEntity;
import com.nathanclaire.alantra.base.util.DateDeserializer;
import com.nathanclaire.alantra.base.util.DateSerializer;
import com.nathanclaire.alantra.party.model.Party;

/**
 * BillingAccountRole 
 * @author Edward Banfa
 */
@Entity
@Table(name="BILLING_ACCOUNT_ROLE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BillingAccountRole  extends BaseEntity implements java.io.Serializable {

	private BillingAccountRoleType billingAccountRoleType;
	private Party party;
	private BillingAccount billingAccount;
    private String name;
    private String description;
    private Date fromDt;
    private Date thruDt;

    public BillingAccountRole() {
    }

    public BillingAccountRole(BillingAccountRoleType billingAccountRoleType, Party party, BillingAccount billingAccount, String code, String name, Date fromDt, Date thruDt, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.fromDt = fromDt;
		this.thruDt = thruDt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public BillingAccountRole(BillingAccountRoleType billingAccountRoleType, Party party, BillingAccount billingAccount, String code, String name, String description, Date fromDt, Date thruDt, Date effectiveDt, char recSt) 
    {
		this.billingAccountRoleType = billingAccountRoleType;
		this.party = party;
		this.billingAccount = billingAccount;
		this.code = code;
		this.name = name;
		this.description = description;
		this.fromDt = fromDt;
		this.thruDt = thruDt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ROLE_TY_ID", nullable=false)
    @JsonIgnore
    public BillingAccountRoleType getBillingAccountRoleType() 
    {
        return this.billingAccountRoleType;
    }
    
    public void setBillingAccountRoleType(BillingAccountRoleType billingAccountRoleType)
    {
        this.billingAccountRoleType = billingAccountRoleType;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARTY_ID", nullable=false)
    @JsonIgnore
    public Party getParty() 
    {
        return this.party;
    }
    
    public void setParty(Party party)
    {
        this.party = party;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ACCOUNT_ID", nullable=false)
    @JsonIgnore
    public BillingAccount getBillingAccount() 
    {
        return this.billingAccount;
    }
    
    public void setBillingAccount(BillingAccount billingAccount)
    {
        this.billingAccount = billingAccount;
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
    @Temporal(TemporalType.DATE)
    @Column(name="FROM_DT" , nullable=false, length=10)
	@JsonSerialize(using = DateSerializer.class)
    public Date getFromDt() 
    {
        return this.fromDt;
    }
    
    @JsonDeserialize(using = DateDeserializer.class)
    public void setFromDt(Date fromDt) 
    {
        this.fromDt = fromDt;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="THRU_DT" , nullable=false, length=10)
	@JsonSerialize(using = DateSerializer.class)
    public Date getThruDt() 
    {
        return this.thruDt;
    }
    
    @JsonDeserialize(using = DateDeserializer.class)
    public void setThruDt(Date thruDt) 
    {
        this.thruDt = thruDt;
    }


}


