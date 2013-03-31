/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.businessdata.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import static javax.persistence.GenerationType.IDENTITY;
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
 * UomConversion 
 * @author Edward Banfa
 */
@Entity
@Table(name="UOM_CONVERSION"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class UomConversion  extends BaseEntity implements java.io.Serializable {

    private int fromUomId;
    private int toUomId;
    private int conversionFactor;
    private String name;
    private String description;

    public UomConversion() {
    }

    public UomConversion(int fromUomId, int toUomId, int conversionFactor, String code, String name, Date effectiveDt, char recSt) 
    {
		this.fromUomId = fromUomId;
		this.toUomId = toUomId;
		this.conversionFactor = conversionFactor;
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public UomConversion(int fromUomId, int toUomId, int conversionFactor, String code, String name, String description, Date effectiveDt, char recSt) 
    {
		this.fromUomId = fromUomId;
		this.toUomId = toUomId;
		this.conversionFactor = conversionFactor;
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    
		
    @Column(name="FROM_UOM_ID" , nullable=false)
    public int getFromUomId() 
    {
        return this.fromUomId;
    }
    
    public void setFromUomId(int fromUomId) 
    {
        this.fromUomId = fromUomId;
    }
		
    @Column(name="TO_UOM_ID" , nullable=false)
    public int getToUomId() 
    {
        return this.toUomId;
    }
    
    public void setToUomId(int toUomId) 
    {
        this.toUomId = toUomId;
    }
		
    @Column(name="CONVERSION_FACTOR" , nullable=false)
    public int getConversionFactor() 
    {
        return this.conversionFactor;
    }
    
    public void setConversionFactor(int conversionFactor) 
    {
        this.conversionFactor = conversionFactor;
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


}


