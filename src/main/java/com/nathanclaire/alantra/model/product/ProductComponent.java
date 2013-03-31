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
 * ProductComponent 
 * @author Edward Banfa
 */
@Entity
@Table(name="PRODUCT_COMPONENT"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ProductComponent  extends BaseEntity implements java.io.Serializable {

	private Product productByInProdId;
	private Product productByForProdId;
    private Integer locationId;
    private String description;
    private String instruction;
    private String remarks;
    private Integer quantitiyUsed;
    private Date fromDt;
    private Date toDt;

    public ProductComponent() {
    }

    public ProductComponent(Product productByInProdId, String code, Date fromDt, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.fromDt = fromDt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public ProductComponent(Product productByInProdId, Product productByForProdId, Integer locationId, String code, String description, String instruction, String remarks, Integer quantitiyUsed, Date fromDt, Date toDt, Date effectiveDt, char recSt) 
    {
		this.productByInProdId = productByInProdId;
		this.productByForProdId = productByForProdId;
		this.locationId = locationId;
		this.code = code;
		this.description = description;
		this.instruction = instruction;
		this.remarks = remarks;
		this.quantitiyUsed = quantitiyUsed;
		this.fromDt = fromDt;
		this.toDt = toDt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IN_PROD_ID", nullable=false)
    @JsonIgnore
    public Product getProductByInProdId() 
    {
        return this.productByInProdId;
    }
    
    public void setProductByInProdId(Product productByInProdId)
    {
        this.productByInProdId = productByInProdId;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FOR_PROD_ID")
    @JsonIgnore
    public Product getProductByForProdId() 
    {
        return this.productByForProdId;
    }
    
    public void setProductByForProdId(Product productByForProdId)
    {
        this.productByForProdId = productByForProdId;
    }
		
    @Column(name="LOCATION_ID" , unique=true)
    public Integer getLocationId() 
    {
        return this.locationId;
    }
    
    public void setLocationId(Integer locationId) 
    {
        this.locationId = locationId;
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
		
    @Column(name="INSTRUCTION" , unique=true, length=500)
    public String getInstruction() 
    {
        return this.instruction;
    }
    
    public void setInstruction(String instruction) 
    {
        this.instruction = instruction;
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
		
    @Column(name="QUANTITIY_USED" , unique=true)
    public Integer getQuantitiyUsed() 
    {
        return this.quantitiyUsed;
    }
    
    public void setQuantitiyUsed(Integer quantitiyUsed) 
    {
        this.quantitiyUsed = quantitiyUsed;
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


