/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.party.model;

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

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.util.DateDeserializer;
import com.nathanclaire.alantra.base.util.DateSerializer;
import com.nathanclaire.alantra.messaging.model.CommunicationEvent;

/**
 * PartyCase 
 * @author Edward Banfa
 */
@Entity
@Table(name="PARTY_CASE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PartyCase  extends BaseEntity implements java.io.Serializable {

	private CaseRole caseRole;
	private CommunicationEvent communicationEvent;
	private CaseStatusType caseStatusType;
    private String name;
    private String description;

    public PartyCase() {
    }

    public PartyCase(CaseRole caseRole, CommunicationEvent communicationEvent, CaseStatusType caseStatusType, String code, String name, String description, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CASE_ROLE_ID", nullable=false)
    @JsonIgnore
    public CaseRole getCaseRole() 
    {
        return this.caseRole;
    }
    
    public void setCaseRole(CaseRole caseRole)
    {
        this.caseRole = caseRole;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="COMM_EVENT_ID", nullable=false)
    @JsonIgnore
    public CommunicationEvent getCommunicationEvent() 
    {
        return this.communicationEvent;
    }
    
    public void setCommunicationEvent(CommunicationEvent communicationEvent)
    {
        this.communicationEvent = communicationEvent;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CASE_ST_ID", nullable=false)
    @JsonIgnore
    public CaseStatusType getCaseStatusType() 
    {
        return this.caseStatusType;
    }
    
    public void setCaseStatusType(CaseStatusType caseStatusType)
    {
        this.caseStatusType = caseStatusType;
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


}


