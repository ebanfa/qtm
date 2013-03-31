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
import com.nathanclaire.alantra.util.DateDeserializer;
import com.nathanclaire.alantra.util.DateSerializer;

/**
 * ProductCategory 
 * @author Edward Banfa
 */
@Entity
@Table(name="PRODUCT_CATEGORY"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ProductCategory  extends BaseEntity implements java.io.Serializable {

	private ProductCategoryType productCategoryType;
    private String name;
    private String description;
	private Set<ProductClassification> productClassifications = new HashSet<ProductClassification>(0);

    public ProductCategory() {
    }

    public ProductCategory(ProductCategoryType productCategoryType, String code, String name, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public ProductCategory(ProductCategoryType productCategoryType, String code, String name, String description, Date effectiveDt, char recSt, Set<ProductClassification> productClassifications ) 
    {
		this.productCategoryType = productCategoryType;
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.productClassifications = productClassifications;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROD_CAT_TY_ID", nullable=false)
    @JsonIgnore
    public ProductCategoryType getProductCategoryType() 
    {
        return this.productCategoryType;
    }
    
    public void setProductCategoryType(ProductCategoryType productCategoryType)
    {
        this.productCategoryType = productCategoryType;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productCategory")
    @JsonIgnore
    public Set<ProductClassification> getProductClassifications() 
    {
        return this.productClassifications;
    }
    
    public void setProductClassifications(Set<ProductClassification> productClassifications) 
    {
        this.productClassifications = productClassifications;
    }			


}


