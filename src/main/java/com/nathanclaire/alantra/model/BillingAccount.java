/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.util.DateDeserializer;
import com.nathanclaire.alantra.util.DateSerializer;

/**
 * BillingAccount 
 * @author Edward Banfa
 */
@Entity
@Table(name="BILLING_ACCOUNT"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BillingAccount  extends BaseEntity implements java.io.Serializable {

    private int contMechId;
    private String name;
    private String description;
    private Date fromDt;
    private Date thruDt;
	private Set<BillingAccountRole> billingAccountRoles = new HashSet<BillingAccountRole>(0);
	private Set<PaymentApplication> paymentApplications = new HashSet<PaymentApplication>(0);

    public BillingAccount() {
    }

    public BillingAccount(int contMechId, String code, String name, Date fromDt, Date thruDt, Date effectiveDt, char recSt) 
    {
		this.contMechId = contMechId;
		this.code = code;
		this.name = name;
		this.fromDt = fromDt;
		this.thruDt = thruDt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public BillingAccount(int contMechId, String code, String name, String description, Date fromDt, Date thruDt, Date effectiveDt, char recSt, Set<BillingAccountRole> billingAccountRoles, Set<PaymentApplication> paymentApplications ) 
    {
		this.contMechId = contMechId;
		this.code = code;
		this.name = name;
		this.description = description;
		this.fromDt = fromDt;
		this.thruDt = thruDt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.billingAccountRoles = billingAccountRoles;
		this.paymentApplications = paymentApplications;
    }
    
		
    @Column(name="CONT_MECH_ID" , nullable=false)
    public int getContMechId() 
    {
        return this.contMechId;
    }
    
    public void setContMechId(int contMechId) 
    {
        this.contMechId = contMechId;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="billingAccount")
    @JsonIgnore
    public Set<BillingAccountRole> getBillingAccountRoles() 
    {
        return this.billingAccountRoles;
    }
    
    public void setBillingAccountRoles(Set<BillingAccountRole> billingAccountRoles) 
    {
        this.billingAccountRoles = billingAccountRoles;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="billingAccount")
    @JsonIgnore
    public Set<PaymentApplication> getPaymentApplications() 
    {
        return this.paymentApplications;
    }
    
    public void setPaymentApplications(Set<PaymentApplication> paymentApplications) 
    {
        this.paymentApplications = paymentApplications;
    }			


}


