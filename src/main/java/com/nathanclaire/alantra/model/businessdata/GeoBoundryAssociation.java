/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.model.businessdata;

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
 * GeoBoundryAssociation 
 * @author Edward Banfa
 */
@Entity
@Table(name="GEO_BOUNDRY_ASSOCIATION"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class GeoBoundryAssociation  extends BaseEntity implements java.io.Serializable {

	private GeoBoundry geoBoundryByToGeoId;
	private GeoBoundry geoBoundryByFromGeoId;
    private String description;

    public GeoBoundryAssociation() {
    }

    public GeoBoundryAssociation(GeoBoundry geoBoundryByToGeoId, GeoBoundry geoBoundryByFromGeoId, String code, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public GeoBoundryAssociation(GeoBoundry geoBoundryByToGeoId, GeoBoundry geoBoundryByFromGeoId, String code, String description, Date effectiveDt, char recSt) 
    {
		this.geoBoundryByToGeoId = geoBoundryByToGeoId;
		this.geoBoundryByFromGeoId = geoBoundryByFromGeoId;
		this.code = code;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TO_GEO_ID", nullable=false)
    @JsonIgnore
    public GeoBoundry getGeoBoundryByToGeoId() 
    {
        return this.geoBoundryByToGeoId;
    }
    
    public void setGeoBoundryByToGeoId(GeoBoundry geoBoundryByToGeoId)
    {
        this.geoBoundryByToGeoId = geoBoundryByToGeoId;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FROM_GEO_ID", nullable=false)
    @JsonIgnore
    public GeoBoundry getGeoBoundryByFromGeoId() 
    {
        return this.geoBoundryByFromGeoId;
    }
    
    public void setGeoBoundryByFromGeoId(GeoBoundry geoBoundryByFromGeoId)
    {
        this.geoBoundryByFromGeoId = geoBoundryByFromGeoId;
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


