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
import com.nathanclaire.alantra.party.model.EstimatedProductCost;
import com.nathanclaire.alantra.util.DateDeserializer;
import com.nathanclaire.alantra.util.DateSerializer;

/**
 * GeoBoundry 
 * @author Edward Banfa
 */
@Entity
@Table(name="GEO_BOUNDRY"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class GeoBoundry  extends BaseEntity implements java.io.Serializable {

	private GeoBoundaryType geoBoundaryType;
    private String name;
    private String abbreviation;
    private String description;
	private Set<PostalAddressBoundry> postalAddressBoundries = new HashSet<PostalAddressBoundry>(0);
	private Set<EstimatedProductCost> estimatedProductCosts = new HashSet<EstimatedProductCost>(0);
	private Set<GeoBoundryAssociation> geoBoundryAssociationsForFromGeoId = new HashSet<GeoBoundryAssociation>(0);
	private Set<GeoBoundryAssociation> geoBoundryAssociationsForToGeoId = new HashSet<GeoBoundryAssociation>(0);

    public GeoBoundry() {
    }

    public GeoBoundry(GeoBoundaryType geoBoundaryType, String code, String name, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public GeoBoundry(GeoBoundaryType geoBoundaryType, String code, String name, String abbreviation, String description, Date effectiveDt, char recSt, Set<PostalAddressBoundry> postalAddressBoundries, Set<EstimatedProductCost> estimatedProductCosts, Set<GeoBoundryAssociation> geoBoundryAssociationsForFromGeoId, Set<GeoBoundryAssociation> geoBoundryAssociationsForToGeoId ) 
    {
		this.geoBoundaryType = geoBoundaryType;
		this.code = code;
		this.name = name;
		this.abbreviation = abbreviation;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.postalAddressBoundries = postalAddressBoundries;
		this.estimatedProductCosts = estimatedProductCosts;
		this.geoBoundryAssociationsForFromGeoId = geoBoundryAssociationsForFromGeoId;
		this.geoBoundryAssociationsForToGeoId = geoBoundryAssociationsForToGeoId;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="GEO_BOUNDRY_TY_ID", nullable=false)
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
		
    @Column(name="ABBREVIATION" , unique=true, length=5)
    public String getAbbreviation() 
    {
        return this.abbreviation;
    }
    
    public void setAbbreviation(String abbreviation) 
    {
        this.abbreviation = abbreviation;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="geoBoundry")
    @JsonIgnore
    public Set<PostalAddressBoundry> getPostalAddressBoundries() 
    {
        return this.postalAddressBoundries;
    }
    
    public void setPostalAddressBoundries(Set<PostalAddressBoundry> postalAddressBoundries) 
    {
        this.postalAddressBoundries = postalAddressBoundries;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="geoBoundry")
    @JsonIgnore
    public Set<EstimatedProductCost> getEstimatedProductCosts() 
    {
        return this.estimatedProductCosts;
    }
    
    public void setEstimatedProductCosts(Set<EstimatedProductCost> estimatedProductCosts) 
    {
        this.estimatedProductCosts = estimatedProductCosts;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="geoBoundryByFromGeoId")
    @JsonIgnore
    public Set<GeoBoundryAssociation> getGeoBoundryAssociationsForFromGeoId() 
    {
        return this.geoBoundryAssociationsForFromGeoId;
    }
    
    public void setGeoBoundryAssociationsForFromGeoId(Set<GeoBoundryAssociation> geoBoundryAssociationsForFromGeoId) 
    {
        this.geoBoundryAssociationsForFromGeoId = geoBoundryAssociationsForFromGeoId;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="geoBoundryByToGeoId")
    @JsonIgnore
    public Set<GeoBoundryAssociation> getGeoBoundryAssociationsForToGeoId() 
    {
        return this.geoBoundryAssociationsForToGeoId;
    }
    
    public void setGeoBoundryAssociationsForToGeoId(Set<GeoBoundryAssociation> geoBoundryAssociationsForToGeoId) 
    {
        this.geoBoundryAssociationsForToGeoId = geoBoundryAssociationsForToGeoId;
    }			


}


