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

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.util.DateDeserializer;
import com.nathanclaire.alantra.base.util.DateSerializer;
import com.nathanclaire.alantra.invoice.model.InvoiceItem;
import com.nathanclaire.alantra.order.model.ProductOrderItem;
import com.nathanclaire.alantra.party.model.EstimatedProductCost;

/**
 * ProductFeature 
 * @author Edward Banfa
 */
@Entity
@Table(name="PRODUCT_FEATURE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ProductFeature  extends BaseEntity implements java.io.Serializable {

	private ProductFeatureType productFeatureType;
	private ProductFeatureCategory productFeatureCategory;
    private String name;
    private String description;
	private Set<EstimatedProductCost> estimatedProductCosts = new HashSet<EstimatedProductCost>(0);
	private Set<InvoiceItem> invoiceItems = new HashSet<InvoiceItem>(0);
	private Set<ProductFeatureApplicability> productFeatureApplicabilities = new HashSet<ProductFeatureApplicability>(0);
	private Set<ProductOrderItem> productOrderItems = new HashSet<ProductOrderItem>(0);

    public ProductFeature() {
    }

    public ProductFeature(ProductFeatureType productFeatureType, ProductFeatureCategory productFeatureCategory, String code, String name, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public ProductFeature(ProductFeatureType productFeatureType, ProductFeatureCategory productFeatureCategory, String code, String name, String description, Date effectiveDt, char recSt, Set<EstimatedProductCost> estimatedProductCosts, Set<InvoiceItem> invoiceItems, Set<ProductFeatureApplicability> productFeatureApplicabilities, Set<ProductOrderItem> productOrderItems ) 
    {
		this.productFeatureType = productFeatureType;
		this.productFeatureCategory = productFeatureCategory;
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.estimatedProductCosts = estimatedProductCosts;
		this.invoiceItems = invoiceItems;
		this.productFeatureApplicabilities = productFeatureApplicabilities;
		this.productOrderItems = productOrderItems;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROD_FEAT_TY", nullable=false)
    @JsonIgnore
    public ProductFeatureType getProductFeatureType() 
    {
        return this.productFeatureType;
    }
    
    public void setProductFeatureType(ProductFeatureType productFeatureType)
    {
        this.productFeatureType = productFeatureType;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROD_FEAT_CAT", nullable=false)
    @JsonIgnore
    public ProductFeatureCategory getProductFeatureCategory() 
    {
        return this.productFeatureCategory;
    }
    
    public void setProductFeatureCategory(ProductFeatureCategory productFeatureCategory)
    {
        this.productFeatureCategory = productFeatureCategory;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productFeature")
    @JsonIgnore
    public Set<EstimatedProductCost> getEstimatedProductCosts() 
    {
        return this.estimatedProductCosts;
    }
    
    public void setEstimatedProductCosts(Set<EstimatedProductCost> estimatedProductCosts) 
    {
        this.estimatedProductCosts = estimatedProductCosts;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productFeature")
    @JsonIgnore
    public Set<InvoiceItem> getInvoiceItems() 
    {
        return this.invoiceItems;
    }
    
    public void setInvoiceItems(Set<InvoiceItem> invoiceItems) 
    {
        this.invoiceItems = invoiceItems;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productFeature")
    @JsonIgnore
    public Set<ProductFeatureApplicability> getProductFeatureApplicabilities() 
    {
        return this.productFeatureApplicabilities;
    }
    
    public void setProductFeatureApplicabilities(Set<ProductFeatureApplicability> productFeatureApplicabilities) 
    {
        this.productFeatureApplicabilities = productFeatureApplicabilities;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productFeature")
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


