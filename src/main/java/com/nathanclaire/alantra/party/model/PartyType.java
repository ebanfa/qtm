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

import com.nathanclaire.alantra.base.BaseEntity;
import com.nathanclaire.alantra.util.DateDeserializer;
import com.nathanclaire.alantra.util.DateSerializer;

/**
 * PartyType 
 * @author Edward Banfa
 */
@Entity
@Table(name="PARTY_TYPE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PartyType  extends BaseEntity implements java.io.Serializable {

	private PartyType partyType;
    private String name;
    private String description;
	private Set<PartyClassification> partyClassifications = new HashSet<PartyClassification>(0);
	private Set<Party> parties = new HashSet<Party>(0);
	private Set<PartyType> partyTypes = new HashSet<PartyType>(0);

    public PartyType() {
    }

    public PartyType(String code, String name, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public PartyType(PartyType partyType, String code, String name, String description, Date effectiveDt, char recSt, Set<PartyClassification> partyClassifications, Set<Party> parties, Set<PartyType> partyTypes ) 
    {
		this.partyType = partyType;
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.partyClassifications = partyClassifications;
		this.parties = parties;
		this.partyTypes = partyTypes;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARENT_TY_ID")
    @JsonIgnore
    public PartyType getPartyType() 
    {
        return this.partyType;
    }
    
    public void setPartyType(PartyType partyType)
    {
        this.partyType = partyType;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyType")
    @JsonIgnore
    public Set<PartyClassification> getPartyClassifications() 
    {
        return this.partyClassifications;
    }
    
    public void setPartyClassifications(Set<PartyClassification> partyClassifications) 
    {
        this.partyClassifications = partyClassifications;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyType")
    @JsonIgnore
    public Set<Party> getParties() 
    {
        return this.parties;
    }
    
    public void setParties(Set<Party> parties) 
    {
        this.parties = parties;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyType")
    @JsonIgnore
    public Set<PartyType> getPartyTypes() 
    {
        return this.partyTypes;
    }
    
    public void setPartyTypes(Set<PartyType> partyTypes) 
    {
        this.partyTypes = partyTypes;
    }			


}


