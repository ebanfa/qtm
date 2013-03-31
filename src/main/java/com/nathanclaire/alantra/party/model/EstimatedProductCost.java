/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.party.model;

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

import com.nathanclaire.alantra.base.BaseEntity;
import com.nathanclaire.alantra.businessdata.model.GeoBoundry;
import com.nathanclaire.alantra.product.model.CostComponentType;
import com.nathanclaire.alantra.product.model.Product;
import com.nathanclaire.alantra.product.model.ProductFeature;
import com.nathanclaire.alantra.util.DateDeserializer;
import com.nathanclaire.alantra.util.DateSerializer;

/**
 * EstimatedProductCost 
 * @author Edward Banfa
 */
@Entity
@Table(name="ESTIMATED_PRODUCT_COST"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class EstimatedProductCost  extends BaseEntity implements java.io.Serializable {

	private Product product;
	private CostComponentType costComponentType;
	private ProductFeature productFeature;
	private GeoBoundry geoBoundry;
    private BigDecimal cost;
    private String description;
    private Date fromDt;
    private Date toDt;

    public EstimatedProductCost() {
    }

    public EstimatedProductCost(CostComponentType costComponentType, String code, BigDecimal cost, Date fromDt, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.cost = cost;
		this.fromDt = fromDt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public EstimatedProductCost(Product product, CostComponentType costComponentType, ProductFeature productFeature, GeoBoundry geoBoundry, String code, BigDecimal cost, String description, Date fromDt, Date toDt, Date effectiveDt, char recSt) 
    {
		this.product = product;
		this.costComponentType = costComponentType;
		this.productFeature = productFeature;
		this.geoBoundry = geoBoundry;
		this.code = code;
		this.cost = cost;
		this.description = description;
		this.fromDt = fromDt;
		this.toDt = toDt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
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
    @JoinColumn(name="COST_COMP_TY_ID", nullable=false)
    @JsonIgnore
    public CostComponentType getCostComponentType() 
    {
        return this.costComponentType;
    }
    
    public void setCostComponentType(CostComponentType costComponentType)
    {
        this.costComponentType = costComponentType;
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
    @JoinColumn(name="GEO_BOUNDRY_ID")
    @JsonIgnore
    public GeoBoundry getGeoBoundry() 
    {
        return this.geoBoundry;
    }
    
    public void setGeoBoundry(GeoBoundry geoBoundry)
    {
        this.geoBoundry = geoBoundry;
    }
		
    @Column(name="COST" , nullable=false)
    public BigDecimal getCost() 
    {
        return this.cost;
    }
    
    public void setCost(BigDecimal cost) 
    {
        this.cost = cost;
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
    @Column(name="TO_DT" , length=10)
	@JsonSerialize(using = DateSerializer.class)
    public Date getToDt() 
    {
        return this.toDt;
    }
    
    @JsonDeserialize(using = DateDeserializer.class)
    public void setToDt(Date toDt) 
    {
        this.toDt = toDt;
    }


}


