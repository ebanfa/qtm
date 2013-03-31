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
 * CaseRole 
 * @author Edward Banfa
 */
@Entity
@Table(name="CASE_ROLE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CaseRole  extends BaseEntity implements java.io.Serializable {

	private Party party;
	private CaseRoleType caseRoleType;
    private String name;
    private String description;
	private Set<PartyCase> partyCases = new HashSet<PartyCase>(0);

    public CaseRole() {
    }

    public CaseRole(Party party, CaseRoleType caseRoleType, String code, String name, String description, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public CaseRole(Party party, CaseRoleType caseRoleType, String code, String name, String description, Date effectiveDt, char recSt, Set<PartyCase> partyCases ) 
    {
		this.party = party;
		this.caseRoleType = caseRoleType;
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.partyCases = partyCases;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARTY_ID", nullable=false)
    @JsonIgnore
    public Party getParty() 
    {
        return this.party;
    }
    
    public void setParty(Party party)
    {
        this.party = party;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CASE_ROLE_TY_ID", nullable=false)
    @JsonIgnore
    public CaseRoleType getCaseRoleType() 
    {
        return this.caseRoleType;
    }
    
    public void setCaseRoleType(CaseRoleType caseRoleType)
    {
        this.caseRoleType = caseRoleType;
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
		
    @Column(name="DESCRIPTION" , nullable=false, length=150)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="caseRole")
    @JsonIgnore
    public Set<PartyCase> getPartyCases() 
    {
        return this.partyCases;
    }
    
    public void setPartyCases(Set<PartyCase> partyCases) 
    {
        this.partyCases = partyCases;
    }			


}

