/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.model.party;

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

import com.nathanclaire.alantra.model.BaseEntity;
import com.nathanclaire.alantra.util.DateDeserializer;
import com.nathanclaire.alantra.util.DateSerializer;

/**
 * PartyRelationshipType 
 * @author Edward Banfa
 */
@Entity
@Table(name="PARTY_RELATIONSHIP_TYPE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PartyRelationshipType  extends BaseEntity implements java.io.Serializable {

	private PartyRoleType partyRoleTypeByFromRoleTyId;
	private PartyRoleType partyRoleTypeByToRoleTyId;
    private String name;
    private String description;
	private Set<PartyRelationship> partyRelationships = new HashSet<PartyRelationship>(0);

    public PartyRelationshipType() {
    }

    public PartyRelationshipType(PartyRoleType partyRoleTypeByFromRoleTyId, PartyRoleType partyRoleTypeByToRoleTyId, String code, String name, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public PartyRelationshipType(PartyRoleType partyRoleTypeByFromRoleTyId, PartyRoleType partyRoleTypeByToRoleTyId, String code, String name, String description, Date effectiveDt, char recSt, Set<PartyRelationship> partyRelationships ) 
    {
		this.partyRoleTypeByFromRoleTyId = partyRoleTypeByFromRoleTyId;
		this.partyRoleTypeByToRoleTyId = partyRoleTypeByToRoleTyId;
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.partyRelationships = partyRelationships;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FROM_ROLE_TY_ID", nullable=false)
    @JsonIgnore
    public PartyRoleType getPartyRoleTypeByFromRoleTyId() 
    {
        return this.partyRoleTypeByFromRoleTyId;
    }
    
    public void setPartyRoleTypeByFromRoleTyId(PartyRoleType partyRoleTypeByFromRoleTyId)
    {
        this.partyRoleTypeByFromRoleTyId = partyRoleTypeByFromRoleTyId;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TO_ROLE_TY_ID", nullable=false)
    @JsonIgnore
    public PartyRoleType getPartyRoleTypeByToRoleTyId() 
    {
        return this.partyRoleTypeByToRoleTyId;
    }
    
    public void setPartyRoleTypeByToRoleTyId(PartyRoleType partyRoleTypeByToRoleTyId)
    {
        this.partyRoleTypeByToRoleTyId = partyRoleTypeByToRoleTyId;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyRelationshipType")
    @JsonIgnore
    public Set<PartyRelationship> getPartyRelationships() 
    {
        return this.partyRelationships;
    }
    
    public void setPartyRelationships(Set<PartyRelationship> partyRelationships) 
    {
        this.partyRelationships = partyRelationships;
    }			


}


