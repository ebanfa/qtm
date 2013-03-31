/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.product.model;

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
import com.nathanclaire.alantra.invoice.model.InvoiceItem;
import com.nathanclaire.alantra.order.model.ProductOrderItem;
import com.nathanclaire.alantra.party.model.EstimatedProductCost;

/**
 * Product 
 * @author Edward Banfa
 */
@Entity
@Table(name="PRODUCT"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Product  extends BaseEntity implements java.io.Serializable {

	private ProductType productType;
    private String name;
    private String description;
    private Date introductionDt;
    private Date discountinuedDt;
	private Set<ProductClassification> productClassifications = new HashSet<ProductClassification>(0);
	private Set<EstimatedProductCost> estimatedProductCosts = new HashSet<EstimatedProductCost>(0);
	private Set<ProductComponent> productComponentsForForProdId = new HashSet<ProductComponent>(0);
	private Set<ProductOrderItem> productOrderItems = new HashSet<ProductOrderItem>(0);
	private Set<ProductFeatureApplicability> productFeatureApplicabilities = new HashSet<ProductFeatureApplicability>(0);
	private Set<ProductComponent> productComponentsForInProdId = new HashSet<ProductComponent>(0);
	private Set<InvoiceItem> invoiceItems = new HashSet<InvoiceItem>(0);

    public Product() {
    }

    public Product(ProductType productType, String code, String name, Date introductionDt, Date discountinuedDt, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.introductionDt = introductionDt;
		this.discountinuedDt = discountinuedDt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public Product(ProductType productType, String code, String name, String description, Date introductionDt, Date discountinuedDt, Date effectiveDt, char recSt, Set<ProductClassification> productClassifications, Set<EstimatedProductCost> estimatedProductCosts, Set<ProductComponent> productComponentsForForProdId, Set<ProductOrderItem> productOrderItems, Set<ProductFeatureApplicability> productFeatureApplicabilities, Set<ProductComponent> productComponentsForInProdId, Set<InvoiceItem> invoiceItems ) 
    {
		this.productType = productType;
		this.code = code;
		this.name = name;
		this.description = description;
		this.introductionDt = introductionDt;
		this.discountinuedDt = discountinuedDt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.productClassifications = productClassifications;
		this.estimatedProductCosts = estimatedProductCosts;
		this.productComponentsForForProdId = productComponentsForForProdId;
		this.productOrderItems = productOrderItems;
		this.productFeatureApplicabilities = productFeatureApplicabilities;
		this.productComponentsForInProdId = productComponentsForInProdId;
		this.invoiceItems = invoiceItems;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROD_TY_ID", nullable=false)
    @JsonIgnore
    public ProductType getProductType() 
    {
        return this.productType;
    }
    
    public void setProductType(ProductType productType)
    {
        this.productType = productType;
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
    @Column(name="INTRODUCTION_DT" , nullable=false, length=10)
	@JsonSerialize(using = DateSerializer.class)
    public Date getIntroductionDt() 
    {
        return this.introductionDt;
    }
    
    @JsonDeserialize(using = DateDeserializer.class)
    public void setIntroductionDt(Date introductionDt) 
    {
        this.introductionDt = introductionDt;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="DISCOUNTINUED_DT" , nullable=false, length=10)
	@JsonSerialize(using = DateSerializer.class)
    public Date getDiscountinuedDt() 
    {
        return this.discountinuedDt;
    }
    
    @JsonDeserialize(using = DateDeserializer.class)
    public void setDiscountinuedDt(Date discountinuedDt) 
    {
        this.discountinuedDt = discountinuedDt;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    @JsonIgnore
    public Set<ProductClassification> getProductClassifications() 
    {
        return this.productClassifications;
    }
    
    public void setProductClassifications(Set<ProductClassification> productClassifications) 
    {
        this.productClassifications = productClassifications;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    @JsonIgnore
    public Set<EstimatedProductCost> getEstimatedProductCosts() 
    {
        return this.estimatedProductCosts;
    }
    
    public void setEstimatedProductCosts(Set<EstimatedProductCost> estimatedProductCosts) 
    {
        this.estimatedProductCosts = estimatedProductCosts;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productByForProdId")
    @JsonIgnore
    public Set<ProductComponent> getProductComponentsForForProdId() 
    {
        return this.productComponentsForForProdId;
    }
    
    public void setProductComponentsForForProdId(Set<ProductComponent> productComponentsForForProdId) 
    {
        this.productComponentsForForProdId = productComponentsForForProdId;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    @JsonIgnore
    public Set<ProductOrderItem> getProductOrderItems() 
    {
        return this.productOrderItems;
    }
    
    public void setProductOrderItems(Set<ProductOrderItem> productOrderItems) 
    {
        this.productOrderItems = productOrderItems;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    @JsonIgnore
    public Set<ProductFeatureApplicability> getProductFeatureApplicabilities() 
    {
        return this.productFeatureApplicabilities;
    }
    
    public void setProductFeatureApplicabilities(Set<ProductFeatureApplicability> productFeatureApplicabilities) 
    {
        this.productFeatureApplicabilities = productFeatureApplicabilities;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productByInProdId")
    @JsonIgnore
    public Set<ProductComponent> getProductComponentsForInProdId() 
    {
        return this.productComponentsForInProdId;
    }
    
    public void setProductComponentsForInProdId(Set<ProductComponent> productComponentsForInProdId) 
    {
        this.productComponentsForInProdId = productComponentsForInProdId;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    @JsonIgnore
    public Set<InvoiceItem> getInvoiceItems() 
    {
        return this.invoiceItems;
    }
    
    public void setInvoiceItems(Set<InvoiceItem> invoiceItems) 
    {
        this.invoiceItems = invoiceItems;
    }			


}


