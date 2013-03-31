/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * CommunicationEvent 
 * @author Edward Banfa
 */
@Entity
@Table(name="COMMUNICATION_EVENT"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CommunicationEvent  extends BaseEntity implements java.io.Serializable {

	private CommunicationEventType communicationEventType;
	private CommunicationEventPurpose communicationEventPurpose;
	private ContactMechanismType contactMechanismType;
	private PartyRelationship partyRelationship;
	private CommunicationEventStatusType communicationEventStatusType;
    private String description;
    private String fromEmail;
    private String toEmail;
    private String fromPhone;
    private String toPhone;
    private String subject;
    private String content;
    private Date startDate;
    private Date endDate;
	private Set<PartyCase> partyCases = new HashSet<PartyCase>(0);
	private Set<CommunicationEventWorkEffort> communicationEventWorkEfforts = new HashSet<CommunicationEventWorkEffort>(0);

    public CommunicationEvent() {
    }

    public CommunicationEvent(CommunicationEventType communicationEventType, CommunicationEventPurpose communicationEventPurpose, ContactMechanismType contactMechanismType, CommunicationEventStatusType communicationEventStatusType, String code, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public CommunicationEvent(CommunicationEventType communicationEventType, CommunicationEventPurpose communicationEventPurpose, ContactMechanismType contactMechanismType, PartyRelationship partyRelationship, CommunicationEventStatusType communicationEventStatusType, String code, String description, Date effectiveDt, char recSt, Set<PartyCase> partyCases, Set<CommunicationEventWorkEffort> communicationEventWorkEfforts ) 
    {
		this.communicationEventType = communicationEventType;
		this.communicationEventPurpose = communicationEventPurpose;
		this.contactMechanismType = contactMechanismType;
		this.partyRelationship = partyRelationship;
		this.communicationEventStatusType = communicationEventStatusType;
		this.code = code;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.partyCases = partyCases;
		this.communicationEventWorkEfforts = communicationEventWorkEfforts;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="EVENT_TY_ID", nullable=false)
    @JsonIgnore
    public CommunicationEventType getCommunicationEventType() 
    {
        return this.communicationEventType;
    }
    
    public void setCommunicationEventType(CommunicationEventType communicationEventType)
    {
        this.communicationEventType = communicationEventType;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="EVENT_PURPOSE_ID", nullable=false)
    @JsonIgnore
    public CommunicationEventPurpose getCommunicationEventPurpose() 
    {
        return this.communicationEventPurpose;
    }
    
    public void setCommunicationEventPurpose(CommunicationEventPurpose communicationEventPurpose)
    {
        this.communicationEventPurpose = communicationEventPurpose;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CONTACT_MECH_TY_ID", nullable=false)
    @JsonIgnore
    public ContactMechanismType getContactMechanismType() 
    {
        return this.contactMechanismType;
    }
    
    public void setContactMechanismType(ContactMechanismType contactMechanismType)
    {
        this.contactMechanismType = contactMechanismType;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARTY_REL_ID")
    @JsonIgnore
    public PartyRelationship getPartyRelationship() 
    {
        return this.partyRelationship;
    }
    
    public void setPartyRelationship(PartyRelationship partyRelationship)
    {
        this.partyRelationship = partyRelationship;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="EVENT_ST_TY_ID", nullable=false)
    @JsonIgnore
    public CommunicationEventStatusType getCommunicationEventStatusType() 
    {
        return this.communicationEventStatusType;
    }
    
    public void setCommunicationEventStatusType(CommunicationEventStatusType communicationEventStatusType)
    {
        this.communicationEventStatusType = communicationEventStatusType;
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
			
    /**
	 * @return the fromEmail
	 */
    @Column(name="FROM_EMAIL" , length=35)
	public String getFromEmail() {
		return fromEmail;
	}

	/**
	 * @param fromEmail the fromEmail to set
	 */
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	/**
	 * @return the toEmail
	 */
    @Column(name="TO_EMAIL" , length=35)
	public String getToEmail() {
		return toEmail;
	}

	/**
	 * @param toEmail the toEmail to set
	 */
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	/**
	 * @return the fromPhone
	 */
    @Column(name="FROM_PHONE" , length=35)
	public String getFromPhone() {
		return fromPhone;
	}

	/**
	 * @param fromPhone the fromPhone to set
	 */
	public void setFromPhone(String fromPhone) {
		this.fromPhone = fromPhone;
	}

	/**
	 * @return the toPhone
	 */
    @Column(name="TO_PHONE" , length=35)
	public String getToPhone() {
		return toPhone;
	}

	/**
	 * @param toPhone the toPhone to set
	 */
	public void setToPhone(String toPhone) {
		this.toPhone = toPhone;
	}

	/**
	 * @return the subject
	 */
    @Column(name="SUBJECT" , length=150)
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the content
	 */
    @Column(name="CONTENT" , length=255)
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the startDate
	 */
	@Temporal(TemporalType.DATE)
    @Column(name="START_DT", length=10)
	@JsonSerialize(using = DateSerializer.class)
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
    @JsonDeserialize(using = DateDeserializer.class)
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	@Temporal(TemporalType.DATE)
    @Column(name="END_DT", length=10)
	@JsonSerialize(using = DateSerializer.class)
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
    @JsonDeserialize(using = DateDeserializer.class)
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy="communicationEvent")
    @JsonIgnore
    public Set<PartyCase> getPartyCases() 
    {
        return this.partyCases;
    }
    
    public void setPartyCases(Set<PartyCase> partyCases) 
    {
        this.partyCases = partyCases;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="communicationEvent")
    @JsonIgnore
    public Set<CommunicationEventWorkEffort> getCommunicationEventWorkEfforts() 
    {
        return this.communicationEventWorkEfforts;
    }
    
    public void setCommunicationEventWorkEfforts(Set<CommunicationEventWorkEffort> communicationEventWorkEfforts) 
    {
        this.communicationEventWorkEfforts = communicationEventWorkEfforts;
    }			


}


