/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.messaging.model;

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
import com.nathanclaire.alantra.base.util.DateDeserializer;
import com.nathanclaire.alantra.base.util.DateSerializer;

/**
 * CommunicationEventPurpose 
 * @author Edward Banfa
 */
@Entity
@Table(name="COMMUNICATION_EVENT_PURPOSE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CommunicationEventPurpose  extends BaseEntity implements java.io.Serializable {

	private CommunicationEventPurposeType communicationEventPurposeType;
    private String name;
    private String description;
	private Set<CommunicationEvent> communicationEvents = new HashSet<CommunicationEvent>(0);

    public CommunicationEventPurpose() {
    }

    public CommunicationEventPurpose(CommunicationEventPurposeType communicationEventPurposeType, String code, String name, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public CommunicationEventPurpose(CommunicationEventPurposeType communicationEventPurposeType, String code, String name, String description, Date effectiveDt, char recSt, Set<CommunicationEvent> communicationEvents ) 
    {
		this.communicationEventPurposeType = communicationEventPurposeType;
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.communicationEvents = communicationEvents;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PURPOSE_TYPE_ID", nullable=false)
    @JsonIgnore
    public CommunicationEventPurposeType getCommunicationEventPurposeType() 
    {
        return this.communicationEventPurposeType;
    }
    
    public void setCommunicationEventPurposeType(CommunicationEventPurposeType communicationEventPurposeType)
    {
        this.communicationEventPurposeType = communicationEventPurposeType;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="communicationEventPurpose")
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


