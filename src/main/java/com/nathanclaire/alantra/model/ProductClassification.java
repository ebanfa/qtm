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
 * ProductClassification 
 * @author Edward Banfa
 */
@Entity
@Table(name="PRODUCT_CLASSIFICATION"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ProductClassification  extends BaseEntity implements java.io.Serializable {

	private ProductCategory productCategory;
	private Product product;
    private String name;
    private String description;
    private String remarks;
    private Date fromDt;
    private Date toDt;
    private char primaryFg;

    public ProductClassification() {
    }

    public ProductClassification(ProductCategory productCategory, Product product, String code, String name, Date fromDt, Date toDt, char primaryFg, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.fromDt = fromDt;
		this.toDt = toDt;
		this.primaryFg = primaryFg;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public ProductClassification(ProductCategory productCategory, Product product, String code, String name, String description, String remarks, Date fromDt, Date toDt, char primaryFg, Date effectiveDt, char recSt) 
    {
		this.productCategory = productCategory;
		this.product = product;
		this.code = code;
		this.name = name;
		this.description = description;
		this.remarks = remarks;
		this.fromDt = fromDt;
		this.toDt = toDt;
		this.primaryFg = primaryFg;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROD_CAT_ID", nullable=false)
    @JsonIgnore
    public ProductCategory getProductCategory() 
    {
        return this.productCategory;
    }
    
    public void setProductCategory(ProductCategory productCategory)
    {
        this.productCategory = productCategory;
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
		
    @Column(name="REMARKS" , unique=true)
    public String getRemarks() 
    {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
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
    @Column(name="TO_DT" , nullable=false, length=10)
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
		
    @Column(name="PRIMARY_FG" , nullable=false, length=1)
    public char getPrimaryFg() 
    {
        return this.primaryFg;
    }
    
    public void setPrimaryFg(char primaryFg) 
    {
        this.primaryFg = primaryFg;
    }


}


