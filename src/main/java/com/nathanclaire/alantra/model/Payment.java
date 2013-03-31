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

import com.nathanclaire.alantra.util.DateDeserializer;
import com.nathanclaire.alantra.util.DateSerializer;

/**
 * Payment 
 * @author Edward Banfa
 */
@Entity
@Table(name="PAYMENT"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Payment  extends BaseEntity implements java.io.Serializable {

	private PaymentType paymentType;
	private Party partyByToPartyId;
	private Party partyByFromPartyId;
	private PaymentMethodTypeProvider paymentMethodTypeProvider;
	private PaymentMethodType paymentMethodType;
    private String name;
    private String description;
    private String comment;
    private BigDecimal amount;
	private Set<PaymentApplication> paymentApplications = new HashSet<PaymentApplication>(0);

    public Payment() {
    }

    public Payment(PaymentType paymentType, Party partyByToPartyId, Party partyByFromPartyId, PaymentMethodType paymentMethodType, String code, String name, BigDecimal amount, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.amount = amount;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public Payment(PaymentType paymentType, Party partyByToPartyId, Party partyByFromPartyId, PaymentMethodTypeProvider paymentMethodTypeProvider, PaymentMethodType paymentMethodType, String code, String name, String description, String comment, BigDecimal amount, Date effectiveDt, char recSt, Set<PaymentApplication> paymentApplications ) 
    {
		this.paymentType = paymentType;
		this.partyByToPartyId = partyByToPartyId;
		this.partyByFromPartyId = partyByFromPartyId;
		this.paymentMethodTypeProvider = paymentMethodTypeProvider;
		this.paymentMethodType = paymentMethodType;
		this.code = code;
		this.name = name;
		this.description = description;
		this.comment = comment;
		this.amount = amount;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.paymentApplications = paymentApplications;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PAYMENT_TY_ID", nullable=false)
    @JsonIgnore
    public PaymentType getPaymentType() 
    {
        return this.paymentType;
    }
    
    public void setPaymentType(PaymentType paymentType)
    {
        this.paymentType = paymentType;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TO_PARTY_ID", nullable=false)
    @JsonIgnore
    public Party getPartyByToPartyId() 
    {
        return this.partyByToPartyId;
    }
    
    public void setPartyByToPartyId(Party partyByToPartyId)
    {
        this.partyByToPartyId = partyByToPartyId;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FROM_PARTY_ID", nullable=false)
    @JsonIgnore
    public Party getPartyByFromPartyId() 
    {
        return this.partyByFromPartyId;
    }
    
    public void setPartyByFromPartyId(Party partyByFromPartyId)
    {
        this.partyByFromPartyId = partyByFromPartyId;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PAYMNT_PROV_METH_TY_ID")
    @JsonIgnore
    public PaymentMethodTypeProvider getPaymentMethodTypeProvider() 
    {
        return this.paymentMethodTypeProvider;
    }
    
    public void setPaymentMethodTypeProvider(PaymentMethodTypeProvider paymentMethodTypeProvider)
    {
        this.paymentMethodTypeProvider = paymentMethodTypeProvider;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PAYMENT_METHOD_TY_ID", nullable=false)
    @JsonIgnore
    public PaymentMethodType getPaymentMethodType() 
    {
        return this.paymentMethodType;
    }
    
    public void setPaymentMethodType(PaymentMethodType paymentMethodType)
    {
        this.paymentMethodType = paymentMethodType;
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
		
    @Column(name="COMMENT" , unique=true)
    public String getComment() 
    {
        return this.comment;
    }
    
    public void setComment(String comment) 
    {
        this.comment = comment;
    }
		
    @Column(name="AMOUNT" , nullable=false)
    public BigDecimal getAmount() 
    {
        return this.amount;
    }
    
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="payment")
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


