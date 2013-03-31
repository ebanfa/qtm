/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.model.product;

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
import com.nathanclaire.alantra.util.DateDeserializer;
import com.nathanclaire.alantra.util.DateSerializer;

/**
 * ProductFeatureApplicability 
 * @author Edward Banfa
 */
@Entity
@Table(name="PRODUCT_FEATURE_APPLICABILITY"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ProductFeatureApplicability  extends BaseEntity implements java.io.Serializable {

	private Product product;
	private ProductFeature productFeature;
	private ProductFeatureApplicabilityType productFeatureApplicabilityType;
    private String name;
    private String description;
    private Date fromDt;
    private Date toDt;

    public ProductFeatureApplicability() {
    }

    public ProductFeatureApplicability(Product product, ProductFeature productFeature, ProductFeatureApplicabilityType productFeatureApplicabilityType, String code, Date fromDt, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.fromDt = fromDt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public ProductFeatureApplicability(Product product, ProductFeature productFeature, ProductFeatureApplicabilityType productFeatureApplicabilityType, String code, String name, String description, Date fromDt, Date toDt, Date effectiveDt, char recSt) 
    {
		this.product = product;
		this.productFeature = productFeature;
		this.productFeatureApplicabilityType = productFeatureApplicabilityType;
		this.code = code;
		this.name = name;
		this.description = description;
		this.fromDt = fromDt;
		this.toDt = toDt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROD_ID", nullable=false)
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
    @JoinColumn(name="PROD_FEAT_ID", nullable=false)
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
    @JoinColumn(name="APPLICABITY_TY_ID", nullable=false)
    @JsonIgnore
    public ProductFeatureApplicabilityType getProductFeatureApplicabilityType() 
    {
        return this.productFeatureApplicabilityType;
    }
    
    public void setProductFeatureApplicabilityType(ProductFeatureApplicabilityType productFeatureApplicabilityType)
    {
        this.productFeatureApplicabilityType = productFeatureApplicabilityType;
    }
		
    @Column(name="NAME" , unique=true, length=75)
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


