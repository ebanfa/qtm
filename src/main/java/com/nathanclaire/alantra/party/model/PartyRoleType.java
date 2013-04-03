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
 * PartyRoleType 
 * @author Edward Banfa
 */
@Entity
@Table(name="PARTY_ROLE_TYPE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PartyRoleType  extends BaseEntity implements java.io.Serializable {

	private RoleType roleType;
	private PartyRoleType partyRoleType;
    private String name;
    private String description;
	private Set<PartyRole> partyRoles = new HashSet<PartyRole>(0);
	private Set<PartyRoleType> partyRoleTypes = new HashSet<PartyRoleType>(0);
	private Set<PartyRelationshipType> partyRelationshipTypesForFromRoleTyId = new HashSet<PartyRelationshipType>(0);
	private Set<PartyRelationshipType> partyRelationshipTypesForToRoleTyId = new HashSet<PartyRelationshipType>(0);

    public PartyRoleType() {
    }

    public PartyRoleType(RoleType roleType, String code, String name, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public PartyRoleType(RoleType roleType, PartyRoleType partyRoleType, String code, String name, String description, Date effectiveDt, char recSt, Set<PartyRole> partyRoles, Set<PartyRoleType> partyRoleTypes, Set<PartyRelationshipType> partyRelationshipTypesForFromRoleTyId, Set<PartyRelationshipType> partyRelationshipTypesForToRoleTyId ) 
    {
		this.roleType = roleType;
		this.partyRoleType = partyRoleType;
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.partyRoles = partyRoles;
		this.partyRoleTypes = partyRoleTypes;
		this.partyRelationshipTypesForFromRoleTyId = partyRelationshipTypesForFromRoleTyId;
		this.partyRelationshipTypesForToRoleTyId = partyRelationshipTypesForToRoleTyId;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ROLE_TY_ID", nullable=false)
    @JsonIgnore
    public RoleType getRoleType() 
    {
        return this.roleType;
    }
    
    public void setRoleType(RoleType roleType)
    {
        this.roleType = roleType;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARENT_TY_ID")
    @JsonIgnore
    public PartyRoleType getPartyRoleType() 
    {
        return this.partyRoleType;
    }
    
    public void setPartyRoleType(PartyRoleType partyRoleType)
    {
        this.partyRoleType = partyRoleType;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyRoleType")
    @JsonIgnore
    public Set<PartyRole> getPartyRoles() 
    {
        return this.partyRoles;
    }
    
    public void setPartyRoles(Set<PartyRole> partyRoles) 
    {
        this.partyRoles = partyRoles;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyRoleType")
    @JsonIgnore
    public Set<PartyRoleType> getPartyRoleTypes() 
    {
        return this.partyRoleTypes;
    }
    
    public void setPartyRoleTypes(Set<PartyRoleType> partyRoleTypes) 
    {
        this.partyRoleTypes = partyRoleTypes;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyRoleTypeByFromRoleTyId")
    @JsonIgnore
    public Set<PartyRelationshipType> getPartyRelationshipTypesForFromRoleTyId() 
    {
        return this.partyRelationshipTypesForFromRoleTyId;
    }
    
    public void setPartyRelationshipTypesForFromRoleTyId(Set<PartyRelationshipType> partyRelationshipTypesForFromRoleTyId) 
    {
        this.partyRelationshipTypesForFromRoleTyId = partyRelationshipTypesForFromRoleTyId;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyRoleTypeByToRoleTyId")
    @JsonIgnore
    public Set<PartyRelationshipType> getPartyRelationshipTypesForToRoleTyId() 
    {
        return this.partyRelationshipTypesForToRoleTyId;
    }
    
    public void setPartyRelationshipTypesForToRoleTyId(Set<PartyRelationshipType> partyRelationshipTypesForToRoleTyId) 
    {
        this.partyRelationshipTypesForToRoleTyId = partyRelationshipTypesForToRoleTyId;
    }			


}


