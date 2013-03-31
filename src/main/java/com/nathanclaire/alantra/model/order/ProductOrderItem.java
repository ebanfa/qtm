/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.model.order;

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
import com.nathanclaire.alantra.model.invoice.OrderItemBilling;
import com.nathanclaire.alantra.model.product.Product;
import com.nathanclaire.alantra.model.product.ProductFeature;
import com.nathanclaire.alantra.util.DateDeserializer;
import com.nathanclaire.alantra.util.DateSerializer;

/**
 * ProductOrderItem 
 * @author Edward Banfa
 */
@Entity
@Table(name="PRODUCT_ORDER_ITEM"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ProductOrderItem  extends BaseEntity implements java.io.Serializable {

	private ProductOrderItemType productOrderItemType;
	private ProductOrder productOrder;
	private Product product;
	private ProductFeature productFeature;
    private String name;
    private String description;
    private int quantity;
    private BigDecimal unitPrice;
    private Date expectDeliveryDt;
    private String instructions;
    private String remarks;
	private Set<OrderItemBilling> orderItemBillings = new HashSet<OrderItemBilling>(0);

    public ProductOrderItem() {
    }

    public ProductOrderItem(ProductOrderItemType productOrderItemType, ProductOrder productOrder, String code, String name, int quantity, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.quantity = quantity;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public ProductOrderItem(ProductOrderItemType productOrderItemType, ProductOrder productOrder, Product product, ProductFeature productFeature, String code, String name, String description, int quantity, BigDecimal unitPrice, Date expectDeliveryDt, String instructions, String remarks, Date effectiveDt, char recSt, Set<OrderItemBilling> orderItemBillings ) 
    {
		this.productOrderItemType = productOrderItemType;
		this.productOrder = productOrder;
		this.product = product;
		this.productFeature = productFeature;
		this.code = code;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.expectDeliveryDt = expectDeliveryDt;
		this.instructions = instructions;
		this.remarks = remarks;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.orderItemBillings = orderItemBillings;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROD_ORD_TY_ID", nullable=false)
    @JsonIgnore
    public ProductOrderItemType getProductOrderItemType() 
    {
        return this.productOrderItemType;
    }
    
    public void setProductOrderItemType(ProductOrderItemType productOrderItemType)
    {
        this.productOrderItemType = productOrderItemType;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROD_ORD_ID", nullable=false)
    @JsonIgnore
    public ProductOrder getProductOrder() 
    {
        return this.productOrder;
    }
    
    public void setProductOrder(ProductOrder productOrder)
    {
        this.productOrder = productOrder;
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
		
    @Column(name="QUANTITY" , nullable=false)
    public int getQuantity() 
    {
        return this.quantity;
    }
    
    public void setQuantity(int quantity) 
    {
        this.quantity = quantity;
    }
		
    @Column(name="UNIT_PRICE" , unique=true)
    public BigDecimal getUnitPrice() 
    {
        return this.unitPrice;
    }
    
    public void setUnitPrice(BigDecimal unitPrice) 
    {
        this.unitPrice = unitPrice;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="EXPECT_DELIVERY_DT" , length=10)
	@JsonSerialize(using = DateSerializer.class)
    public Date getExpectDeliveryDt() 
    {
        return this.expectDeliveryDt;
    }
    
    @JsonDeserialize(using = DateDeserializer.class)
    public void setExpectDeliveryDt(Date expectDeliveryDt) 
    {
        this.expectDeliveryDt = expectDeliveryDt;
    }
		
    @Column(name="INSTRUCTIONS" , unique=true, length=500)
    public String getInstructions() 
    {
        return this.instructions;
    }
    
    public void setInstructions(String instructions) 
    {
        this.instructions = instructions;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productOrderItem")
    @JsonIgnore
    public Set<OrderItemBilling> getOrderItemBillings() 
    {
        return this.orderItemBillings;
    }
    
    public void setOrderItemBillings(Set<OrderItemBilling> orderItemBillings) 
    {
        this.orderItemBillings = orderItemBillings;
    }			


}


