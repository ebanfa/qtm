/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.party.model;

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

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.util.DateDeserializer;
import com.nathanclaire.alantra.base.util.DateSerializer;

/**
 * PartyClassificationType 
 * @author Edward Banfa
 */
@Entity
@Table(name="PARTY_CLASSIFICATION_TYPE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PartyClassificationType  extends BaseEntity implements java.io.Serializable {

	private PartyClassificationType partyClassificationType;
    private String name;
    private String description;
	private Set<PartyClassification> partyClassifications = new HashSet<PartyClassification>(0);
	private Set<PartyClassificationType> partyClassificationTypes = new HashSet<PartyClassificationType>(0);

    public PartyClassificationType() {
    }

    public PartyClassificationType(String code, String name, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public PartyClassificationType(PartyClassificationType partyClassificationType, String code, String name, String description, Date effectiveDt, char recSt, Set<PartyClassification> partyClassifications, Set<PartyClassificationType> partyClassificationTypes ) 
    {
		this.partyClassificationType = partyClassificationType;
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.partyClassifications = partyClassifications;
		this.partyClassificationTypes = partyClassificationTypes;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARENT_TY_ID")
    @JsonIgnore
    public PartyClassificationType getPartyClassificationType() 
    {
        return this.partyClassificationType;
    }
    
    public void setPartyClassificationType(PartyClassificationType partyClassificationType)
    {
        this.partyClassificationType = partyClassificationType;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyClassificationType")
    @JsonIgnore
    public Set<PartyClassification> getPartyClassifications() 
    {
        return this.partyClassifications;
    }
    
    public void setPartyClassifications(Set<PartyClassification> partyClassifications) 
    {
        this.partyClassifications = partyClassifications;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyClassificationType")
    @JsonIgnore
    public Set<PartyClassificationType> getPartyClassificationTypes() 
    {
        return this.partyClassificationTypes;
    }
    
    public void setPartyClassificationTypes(Set<PartyClassificationType> partyClassificationTypes) 
    {
        this.partyClassificationTypes = partyClassificationTypes;
    }			


}


