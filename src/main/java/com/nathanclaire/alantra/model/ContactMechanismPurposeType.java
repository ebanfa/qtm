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
 * ContactMechanismPurposeType 
 * @author Edward Banfa
 */
@Entity
@Table(name="CONTACT_MECHANISM_PURPOSE_TYPE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ContactMechanismPurposeType  extends BaseEntity implements java.io.Serializable {

    private String name;
    private String description;
	private Set<PartyContactMechanismPurpose> partyContactMechanismPurposes = new HashSet<PartyContactMechanismPurpose>(0);

    public ContactMechanismPurposeType() {
    }

    public ContactMechanismPurposeType(String code, String name, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public ContactMechanismPurposeType(String code, String name, String description, Date effectiveDt, char recSt, Set<PartyContactMechanismPurpose> partyContactMechanismPurposes ) 
    {
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.partyContactMechanismPurposes = partyContactMechanismPurposes;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="contactMechanismPurposeType")
    @JsonIgnore
    public Set<PartyContactMechanismPurpose> getPartyContactMechanismPurposes() 
    {
        return this.partyContactMechanismPurposes;
    }
    
    public void setPartyContactMechanismPurposes(Set<PartyContactMechanismPurpose> partyContactMechanismPurposes) 
    {
        this.partyContactMechanismPurposes = partyContactMechanismPurposes;
    }			


}


