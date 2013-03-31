/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.order.model;

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

import com.nathanclaire.alantra.base.BaseEntity;
import com.nathanclaire.alantra.base.util.DateDeserializer;
import com.nathanclaire.alantra.base.util.DateSerializer;
import com.nathanclaire.alantra.party.model.Party;

/**
 * ProductOrder 
 * @author Edward Banfa
 */
@Entity
@Table(name="PRODUCT_ORDER"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ProductOrder  extends BaseEntity implements java.io.Serializable {

	private ProductOrderType productOrderType;
    private String name;
    private String description;
    private Date orderDt;
    private Party fromParty;
    private Party toParty;
    private BigDecimal amount;
	private Set<ProductOrderItem> productOrderItems = new HashSet<ProductOrderItem>(0);

    public ProductOrder() {
    }

    public ProductOrder(ProductOrderType productOrderType, Party fromParty, Party toParty, String code, String name, Date orderDt, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.fromParty = fromParty;
		this.toParty = toParty;
		this.orderDt = orderDt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public ProductOrder(ProductOrderType productOrderType, Party fromParty, Party toParty, String code, String name, String description, Date orderDt, Date effectiveDt, char recSt, Set<ProductOrderItem> productOrderItems ) 
    {
		this.productOrderType = productOrderType;
		this.fromParty = fromParty;
		this.toParty = toParty;
		this.code = code;
		this.name = name;
		this.description = description;
		this.orderDt = orderDt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.productOrderItems = productOrderItems;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROD_ORD_TY_ID", nullable=false)
    @JsonIgnore
    public ProductOrderType getProductOrderType() 
    {
        return this.productOrderType;
    }
    
    public void setProductOrderType(ProductOrderType productOrderType)
    {
        this.productOrderType = productOrderType;
    }
		
    /**
	 * @return the fromParty
	 */	
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FROM_PARTY_ID", nullable=false)
    @JsonIgnore
	public Party getFromParty() {
		return fromParty;
	}

	/**
	 * @param fromParty the fromParty to set
	 */
	public void setFromParty(Party placedBy) {
		this.fromParty = placedBy;
	}

    /**
	 * @return the fromParty
	 */	
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TO_PARTY_ID", nullable=false)
    @JsonIgnore
	public Party getToParty() {
		return toParty;
	}

	/**
	 * @param toParty the toParty to set
	 */
	public void setToParty(Party takenBy) {
		this.toParty = takenBy;
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
    @Column(name="ORDER_DT" , nullable=false, length=10)
	@JsonSerialize(using = DateSerializer.class)
    public Date getOrderDt() 
    {
        return this.orderDt;
    }
    
    @JsonDeserialize(using = DateDeserializer.class)
    public void setOrderDt(Date orderDt) 
    {
        this.orderDt = orderDt;
    }
			
    /**
	 * @return the amount
	 */
    @Column(name="AMOUNT" , nullable=true)
    public BigDecimal getAmount() 
    {
        return this.amount;
    }
    
    /**
     * @param amount
     */
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

	@OneToMany(fetch=FetchType.LAZY, mappedBy="productOrder")
    @JsonIgnore
    public Set<ProductOrderItem> getProductOrderItems() 
    {
        return this.productOrderItems;
    }
    
    public void setProductOrderItems(Set<ProductOrderItem> productOrderItems) 
    {
        this.productOrderItems = productOrderItems;
    }			


}


