/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.model.invoice;

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
import com.nathanclaire.alantra.model.payments.PaymentApplication;
import com.nathanclaire.alantra.model.product.Product;
import com.nathanclaire.alantra.model.product.ProductFeature;
import com.nathanclaire.alantra.util.DateDeserializer;
import com.nathanclaire.alantra.util.DateSerializer;

/**
 * InvoiceItem 
 * @author Edward Banfa
 */
@Entity
@Table(name="INVOICE_ITEM"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class InvoiceItem  extends BaseEntity implements java.io.Serializable {

	private InvoiceItemType invoiceItemType;
	private InvoiceItemCategory invoiceItemCategory;
	private Product product;
	private ProductFeature productFeature;
	private Invoice invoice;
    private String name;
    private String description;
    private Character taxableFg;
    private int quantity;
    private BigDecimal amount;
	private Set<OrderItemBilling> orderItemBillings = new HashSet<OrderItemBilling>(0);
	private Set<PaymentApplication> paymentApplications = new HashSet<PaymentApplication>(0);

    public InvoiceItem() {
    }

    public InvoiceItem(InvoiceItemType invoiceItemType, InvoiceItemCategory invoiceItemCategory, Invoice invoice, String code, String name, int quantity, BigDecimal amount, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.quantity = quantity;
		this.amount = amount;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public InvoiceItem(InvoiceItemType invoiceItemType, InvoiceItemCategory invoiceItemCategory, Product product, ProductFeature productFeature, Invoice invoice, String code, String name, String description, Character taxableFg, int quantity, BigDecimal amount, Date effectiveDt, char recSt, Set<OrderItemBilling> orderItemBillings, Set<PaymentApplication> paymentApplications ) 
    {
		this.invoiceItemType = invoiceItemType;
		this.invoiceItemCategory = invoiceItemCategory;
		this.product = product;
		this.productFeature = productFeature;
		this.invoice = invoice;
		this.code = code;
		this.name = name;
		this.description = description;
		this.taxableFg = taxableFg;
		this.quantity = quantity;
		this.amount = amount;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.orderItemBillings = orderItemBillings;
		this.paymentApplications = paymentApplications;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ITEM_TY_ID", nullable=false)
    @JsonIgnore
    public InvoiceItemType getInvoiceItemType() 
    {
        return this.invoiceItemType;
    }
    
    public void setInvoiceItemType(InvoiceItemType invoiceItemType)
    {
        this.invoiceItemType = invoiceItemType;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ITEM_CAT_ID", nullable=false)
    @JsonIgnore
    public InvoiceItemCategory getInvoiceItemCategory() 
    {
        return this.invoiceItemCategory;
    }
    
    public void setInvoiceItemCategory(InvoiceItemCategory invoiceItemCategory)
    {
        this.invoiceItemCategory = invoiceItemCategory;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROD_ID")
    @JsonIgnore
    public Product getProduct() 
    {
        return this.product;
    }
    
    public void setProduct(Product product)
    {
        this.product = product;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROD_FEAT_ID")
    @JsonIgnore
    public ProductFeature getProductFeature() 
    {
        return this.productFeature;
    }
    
    public void setProductFeature(ProductFeature productFeature)
    {
        this.productFeature = productFeature;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="INVOICE_ID", nullable=false)
    @JsonIgnore
    public Invoice getInvoice() 
    {
        return this.invoice;
    }
    
    public void setInvoice(Invoice invoice)
    {
        this.invoice = invoice;
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
		
    @Column(name="TAXABLE_FG" , unique=true, length=1)
    public Character getTaxableFg() 
    {
        return this.taxableFg;
    }
    
    public void setTaxableFg(Character taxableFg) 
    {
        this.taxableFg = taxableFg;
    }
		
    @Column(name="QUANTITY" , nullable=false)
    public int getQuantity() 
    {
        return this.quantity;
    }
    
    public void setQuantity(int quantity) 
    {
        this.quantity = quantity;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="invoiceItem")
    @JsonIgnore
    public Set<OrderItemBilling> getOrderItemBillings() 
    {
        return this.orderItemBillings;
    }
    
    public void setOrderItemBillings(Set<OrderItemBilling> orderItemBillings) 
    {
        this.orderItemBillings = orderItemBillings;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="invoiceItem")
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


