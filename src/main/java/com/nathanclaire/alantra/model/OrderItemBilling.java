/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.model;

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

import com.nathanclaire.alantra.util.DateDeserializer;
import com.nathanclaire.alantra.util.DateSerializer;

/**
 * OrderItemBilling 
 * @author Edward Banfa
 */
@Entity
@Table(name="ORDER_ITEM_BILLING"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class OrderItemBilling  extends BaseEntity implements java.io.Serializable {

	private InvoiceItem invoiceItem;
	private ProductOrderItem productOrderItem;
    private int quantity;
    private BigDecimal amount;
    private String description;

    public OrderItemBilling() {
    }

    public OrderItemBilling(InvoiceItem invoiceItem, ProductOrderItem productOrderItem, int quantity, BigDecimal amount, String code, Date effectiveDt, char recSt) 
    {
		this.quantity = quantity;
		this.amount = amount;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public OrderItemBilling(InvoiceItem invoiceItem, ProductOrderItem productOrderItem, int quantity, BigDecimal amount, String code, String description, Date effectiveDt, char recSt) 
    {
		this.invoiceItem = invoiceItem;
		this.productOrderItem = productOrderItem;
		this.quantity = quantity;
		this.amount = amount;
		this.code = code;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="INVOICE_ITEM_ID", nullable=false)
    @JsonIgnore
    public InvoiceItem getInvoiceItem() 
    {
        return this.invoiceItem;
    }
    
    public void setInvoiceItem(InvoiceItem invoiceItem)
    {
        this.invoiceItem = invoiceItem;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ORDER_ITEM_ID", nullable=false)
    @JsonIgnore
    public ProductOrderItem getProductOrderItem() 
    {
        return this.productOrderItem;
    }
    
    public void setProductOrderItem(ProductOrderItem productOrderItem)
    {
        this.productOrderItem = productOrderItem;
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
		
    @Column(name="DESCRIPTION" , unique=true, length=150)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }


}


