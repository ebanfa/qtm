/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.messaging.model;

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

import com.nathanclaire.alantra.base.BaseEntity;
import com.nathanclaire.alantra.util.DateDeserializer;
import com.nathanclaire.alantra.util.DateSerializer;
import com.nathanclaire.alantra.workeffort.model.WorkEffort;

/**
 * CommunicationEventWorkEffort 
 * @author Edward Banfa
 */
@Entity
@Table(name="COMMUNICATION_EVENT_WORK_EFFORT"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CommunicationEventWorkEffort  extends BaseEntity implements java.io.Serializable {

	private WorkEffort workEffort;
	private CommunicationEvent communicationEvent;
    private String description;

    public CommunicationEventWorkEffort() {
    }

    public CommunicationEventWorkEffort(WorkEffort workEffort, CommunicationEvent communicationEvent, String code, String description, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="WORK_EFFORT_ID", nullable=false)
    @JsonIgnore
    public WorkEffort getWorkEffort() 
    {
        return this.workEffort;
    }
    
    public void setWorkEffort(WorkEffort workEffort)
    {
        this.workEffort = workEffort;
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


