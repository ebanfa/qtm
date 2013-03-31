/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.businessdata.model;

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

/**
 * GeoBoundaryType 
 * @author Edward Banfa
 */
@Entity
@Table(name="GEO_BOUNDARY_TYPE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class GeoBoundaryType  extends BaseEntity implements java.io.Serializable {

	private GeoBoundaryType geoBoundaryType;
    private String name;
    private String description;
	private Set<GeoBoundaryType> geoBoundaryTypes = new HashSet<GeoBoundaryType>(0);
	private Set<GeoBoundry> geoBoundries = new HashSet<GeoBoundry>(0);

    public GeoBoundaryType() {
    }

    public GeoBoundaryType(String code, String name, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public GeoBoundaryType(GeoBoundaryType geoBoundaryType, String code, String name, String description, Date effectiveDt, char recSt, Set<GeoBoundaryType> geoBoundaryTypes, Set<GeoBoundry> geoBoundries ) 
    {
		this.geoBoundaryType = geoBoundaryType;
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.geoBoundaryTypes = geoBoundaryTypes;
		this.geoBoundries = geoBoundries;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARENT_TY")
    @JsonIgnore
    public GeoBoundaryType getGeoBoundaryType() 
    {
        return this.geoBoundaryType;
    }
    
    public void setGeoBoundaryType(GeoBoundaryType geoBoundaryType)
    {
        this.geoBoundaryType = geoBoundaryType;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="geoBoundaryType")
    @JsonIgnore
    public Set<GeoBoundaryType> getGeoBoundaryTypes() 
    {
        return this.geoBoundaryTypes;
    }
    
    public void setGeoBoundaryTypes(Set<GeoBoundaryType> geoBoundaryTypes) 
    {
        this.geoBoundaryTypes = geoBoundaryTypes;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="geoBoundaryType")
    @JsonIgnore
    public Set<GeoBoundry> getGeoBoundries() 
    {
        return this.geoBoundries;
    }
    
    public void setGeoBoundries(Set<GeoBoundry> geoBoundries) 
    {
        this.geoBoundries = geoBoundries;
    }			


}


