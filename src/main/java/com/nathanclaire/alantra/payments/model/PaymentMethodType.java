/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.payments.model;

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

import com.nathanclaire.alantra.base.BaseEntity;
import com.nathanclaire.alantra.util.DateDeserializer;
import com.nathanclaire.alantra.util.DateSerializer;

/**
 * PaymentMethodType 
 * @author Edward Banfa
 */
@Entity
@Table(name="PAYMENT_METHOD_TYPE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PaymentMethodType  extends BaseEntity implements java.io.Serializable {

    private String name;
    private String description;
	private Set<Payment> payments = new HashSet<Payment>(0);
	private Set<PaymentMethodTypeProvider> paymentMethodTypeProviders = new HashSet<PaymentMethodTypeProvider>(0);

    public PaymentMethodType() {
    }

    public PaymentMethodType(String code, String name, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public PaymentMethodType(String code, String name, String description, Date effectiveDt, char recSt, Set<Payment> payments, Set<PaymentMethodTypeProvider> paymentMethodTypeProviders ) 
    {
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.payments = payments;
		this.paymentMethodTypeProviders = paymentMethodTypeProviders;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="paymentMethodType")
    @JsonIgnore
    public Set<Payment> getPayments() 
    {
        return this.payments;
    }
    
    public void setPayments(Set<Payment> payments) 
    {
        this.payments = payments;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="paymentMethodType")
    @JsonIgnore
    public Set<PaymentMethodTypeProvider> getPaymentMethodTypeProviders() 
    {
        return this.paymentMethodTypeProviders;
    }
    
    public void setPaymentMethodTypeProviders(Set<PaymentMethodTypeProvider> paymentMethodTypeProviders) 
    {
        this.paymentMethodTypeProviders = paymentMethodTypeProviders;
    }			


}


