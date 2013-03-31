/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.model.product;

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
import com.nathanclaire.alantra.util.DateDeserializer;
import com.nathanclaire.alantra.util.DateSerializer;

/**
 * ProductType 
 * @author Edward Banfa
 */
@Entity
@Table(name="PRODUCT_TYPE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ProductType  extends BaseEntity implements java.io.Serializable {

    private String names;
    private String description;
	private Set<Product> products = new HashSet<Product>(0);

    public ProductType() {
    }

    public ProductType(String code, String names, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.names = names;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public ProductType(String code, String names, String description, Date effectiveDt, char recSt, Set<Product> products ) 
    {
		this.code = code;
		this.names = names;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.products = products;
    }
    
		
    @Column(name="NAMES" , nullable=false, length=75)
    public String getNames() 
    {
        return this.names;
    }
    
    public void setNames(String names) 
    {
        this.names = names;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="productType")
    @JsonIgnore
    public Set<Product> getProducts() 
    {
        return this.products;
    }
    
    public void setProducts(Set<Product> products) 
    {
        this.products = products;
    }			


}


