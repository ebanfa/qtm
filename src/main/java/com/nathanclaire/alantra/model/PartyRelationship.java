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

import com.nathanclaire.alantra.util.DateDeserializer;
import com.nathanclaire.alantra.util.DateSerializer;

/**
 * PartyRelationship 
 * @author Edward Banfa
 */
@Entity
@Table(name="PARTY_RELATIONSHIP"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PartyRelationship  extends BaseEntity implements java.io.Serializable {

	private PartyRole fromPartyRole;
    private PartyRole toPartyRole;
	private PartyRelationshipType partyRelationshipType;
    private String name;
    private String description;
    private String comment;
    private Date fromDt;
    private Date thruDt;
	private Set<CommunicationEvent> communicationEvents = new HashSet<CommunicationEvent>(0);

    public PartyRelationship() {
    }

    /**
     * @param fromPartyRole
     * @param toPartyRole
     * @param partyRelationshipType
     * @param code
     * @param name
     * @param fromDt
     * @param thruDt
     * @param effectiveDt
     * @param recSt
     */
    public PartyRelationship(PartyRole fromPartyRole, PartyRole toPartyRole, 
    		PartyRelationshipType partyRelationshipType, String code, 
    		String name, Date fromDt, Date thruDt, Date effectiveDt, char recSt) 
    {
		this.fromPartyRole = fromPartyRole;
		this.toPartyRole = toPartyRole;
		this.partyRelationshipType = partyRelationshipType;
		this.code = code;
		this.name = name;
		this.fromDt = fromDt;
		this.thruDt = thruDt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    /**
     * @param fromPartyRole
     * @param toPartyRole
     * @param partyRelationshipType
     * @param code
     * @param name
     * @param description
     * @param comment
     * @param fromDt
     * @param thruDt
     * @param effectiveDt
     * @param recSt
     * @param communicationEvents
     */
    public PartyRelationship(PartyRole fromPartyRole, PartyRole toPartyRole, 
    		PartyRelationshipType partyRelationshipType, String code, String name, String description, 
    		String comment, Date fromDt, Date thruDt, Date effectiveDt, char recSt, Set<CommunicationEvent> communicationEvents ) 
    {
		this.fromPartyRole = fromPartyRole;
		this.toPartyRole = toPartyRole;
		this.partyRelationshipType = partyRelationshipType;
		this.code = code;
		this.name = name;
		this.description = description;
		this.comment = comment;
		this.fromDt = fromDt;
		this.thruDt = thruDt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.communicationEvents = communicationEvents;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FROM_ROLE_ID", nullable=false)
    @JsonIgnore
    public PartyRole getFromPartyRole() 
    {
        return this.fromPartyRole;
    }
    
    public void setFromPartyRole(PartyRole fromPartyRole)
    {
        this.fromPartyRole = fromPartyRole;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RELATIONSHIP_TY_ID", nullable=false)
    @JsonIgnore
    public PartyRelationshipType getPartyRelationshipType() 
    {
        return this.partyRelationshipType;
    }
    
    public void setPartyRelationshipType(PartyRelationshipType partyRelationshipType)
    {
        this.partyRelationshipType = partyRelationshipType;
    }
		
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TO_ROLE_ID", nullable=false)
	@JsonIgnore
    public PartyRole getToPartyRole() 
    {
        return this.toPartyRole;
    }
    
    public void setToPartyRole(PartyRole toPartyRole) 
    {
        this.toPartyRole = toPartyRole;
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
		
    @Column(name="COMMENT" , unique=true)
    public String getComment() 
    {
        return this.comment;
    }
    
    public void setComment(String comment) 
    {
        this.comment = comment;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="FROM_DT" , nullable=false, length=10)
	@JsonSerialize(using = DateSerializer.class)
    public Date getFromDt() 
    {
        return this.fromDt;
    }
    
    @JsonDeserialize(using = DateDeserializer.class)
    public void setFromDt(Date fromDt) 
    {
        this.fromDt = fromDt;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="THRU_DT" , nullable=false, length=10)
	@JsonSerialize(using = DateSerializer.class)
    public Date getThruDt() 
    {
        return this.thruDt;
    }
    
    @JsonDeserialize(using = DateDeserializer.class)
    public void setThruDt(Date thruDt) 
    {
        this.thruDt = thruDt;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyRelationship")
    @JsonIgnore
    public Set<CommunicationEvent> getCommunicationEvents() 
    {
        return this.communicationEvents;
    }
    
    public void setCommunicationEvents(Set<CommunicationEvent> communicationEvents) 
    {
        this.communicationEvents = communicationEvents;
    }			


}


