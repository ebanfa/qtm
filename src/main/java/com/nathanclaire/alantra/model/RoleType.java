/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.model;

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

import com.nathanclaire.alantra.util.DateDeserializer;
import com.nathanclaire.alantra.util.DateSerializer;

/**
 * RoleType 
 * @author Edward Banfa
 */
@Entity
@Table(name="ROLE_TYPE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class RoleType  extends BaseEntity implements java.io.Serializable {

    private String name;
    private String description;
	private Set<PartyRoleType> partyRoleTypes = new HashSet<PartyRoleType>(0);

    public RoleType() {
    }

    public RoleType(String name, String code, Date effectiveDt, char recSt) 
    {
		this.name = name;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public RoleType(String name, String description, String code, Date effectiveDt, char recSt, Set<PartyRoleType> partyRoleTypes ) 
    {
		this.name = name;
		this.description = description;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.partyRoleTypes = partyRoleTypes;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="roleType")
    @JsonIgnore
    public Set<PartyRoleType> getPartyRoleTypes() 
    {
        return this.partyRoleTypes;
    }
    
    public void setPartyRoleTypes(Set<PartyRoleType> partyRoleTypes) 
    {
        this.partyRoleTypes = partyRoleTypes;
    }			


}


