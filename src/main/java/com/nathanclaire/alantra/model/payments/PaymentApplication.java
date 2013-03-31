/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.model.payments;

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

import com.nathanclaire.alantra.model.BaseEntity;
import com.nathanclaire.alantra.model.customer.BillingAccount;
import com.nathanclaire.alantra.model.invoice.InvoiceItem;
import com.nathanclaire.alantra.util.DateDeserializer;
import com.nathanclaire.alantra.util.DateSerializer;

/**
 * PaymentApplication 
 * @author Edward Banfa
 */
@Entity
@Table(name="PAYMENT_APPLICATION"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PaymentApplication  extends BaseEntity implements java.io.Serializable {

	private Payment payment;
	private BillingAccount billingAccount;
	private InvoiceItem invoiceItem;
    private String description;
    private BigDecimal amountAppl;

    public PaymentApplication() {
    }

    public PaymentApplication(Payment payment, String code, BigDecimal amountAppl, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.amountAppl = amountAppl;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public PaymentApplication(Payment payment, BillingAccount billingAccount, InvoiceItem invoiceItem, String code, String description, BigDecimal amountAppl, Date effectiveDt, char recSt) 
    {
		this.payment = payment;
		this.billingAccount = billingAccount;
		this.invoiceItem = invoiceItem;
		this.code = code;
		this.description = description;
		this.amountAppl = amountAppl;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PAYMENT_ID", nullable=false)
    @JsonIgnore
    public Payment getPayment() 
    {
        return this.payment;
    }
    
    public void setPayment(Payment payment)
    {
        this.payment = payment;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BILLING_ACCT_ID")
    @JsonIgnore
    public BillingAccount getBillingAccount() 
    {
        return this.billingAccount;
    }
    
    public void setBillingAccount(BillingAccount billingAccount)
    {
        this.billingAccount = billingAccount;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="INVOICE_ITEM_ID")
    @JsonIgnore
    public InvoiceItem getInvoiceItem() 
    {
        return this.invoiceItem;
    }
    
    public void setInvoiceItem(InvoiceItem invoiceItem)
    {
        this.invoiceItem = invoiceItem;
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
		
    @Column(name="AMOUNT_APPL" , nullable=false)
    public BigDecimal getAmountAppl() 
    {
        return this.amountAppl;
    }
    
    public void setAmountAppl(BigDecimal amountAppl) 
    {
        this.amountAppl = amountAppl;
    }


}


