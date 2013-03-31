/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.model.payments;

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
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.model.BaseEntity;
import com.nathanclaire.alantra.model.party.Party;
import com.nathanclaire.alantra.util.DateDeserializer;
import com.nathanclaire.alantra.util.DateSerializer;

/**
 * PaymentMethodTypeProvider 
 * @author Edward Banfa
 */
@Entity
@Table(name="PAYMENT_METHOD_TYPE_PROVIDER"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PaymentMethodTypeProvider  extends BaseEntity implements java.io.Serializable {

	private PaymentMethodType paymentMethodType;
	private Party party;
    private String name;
    private String description;
    private Date fromDt;
    private Date thruDt;
	private Set<Payment> payments = new HashSet<Payment>(0);

    public PaymentMethodTypeProvider() {
    }

    public PaymentMethodTypeProvider(PaymentMethodType paymentMethodType, Party party, String code, String name, Date fromDt, Date thruDt, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.fromDt = fromDt;
		this.thruDt = thruDt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public PaymentMethodTypeProvider(PaymentMethodType paymentMethodType, Party party, String code, String name, String description, Date fromDt, Date thruDt, Date effectiveDt, char recSt, Set<Payment> payments ) 
    {
		this.paymentMethodType = paymentMethodType;
		this.party = party;
		this.code = code;
		this.name = name;
		this.description = description;
		this.fromDt = fromDt;
		this.thruDt = thruDt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.payments = payments;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PAYMENT_METH_TY_ID", nullable=false)
    @JsonIgnore
    public PaymentMethodType getPaymentMethodType() 
    {
        return this.paymentMethodType;
    }
    
    public void setPaymentMethodType(PaymentMethodType paymentMethodType)
    {
        this.paymentMethodType = paymentMethodType;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROVIDER_ID", nullable=false)
    @JsonIgnore
    public Party getParty() 
    {
        return this.party;
    }
    
    public void setParty(Party party)
    {
        this.party = party;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="paymentMethodTypeProvider")
    @JsonIgnore
    public Set<Payment> getPayments() 
    {
        return this.payments;
    }
    
    public void setPayments(Set<Payment> payments) 
    {
        this.payments = payments;
    }			


}


