/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.workeffort.model;

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
import com.nathanclaire.alantra.messaging.model.CommunicationEventWorkEffort;

/**
 * WorkEffort 
 * @author Edward Banfa
 */
@Entity
@Table(name="WORK_EFFORT"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class WorkEffort  extends BaseEntity implements java.io.Serializable {

	private WorkEffortType workEffortType;
    private String name;
    private String description;
    private Date schedStartDt;
    private Date schedEndDt;
    private Integer estHours;
    private Integer totalHrAllowed;
    private BigDecimal totalAmountAllowed;
	private Set<CommunicationEventWorkEffort> communicationEventWorkEfforts = new HashSet<CommunicationEventWorkEffort>(0);

    public WorkEffort() {
    }

    public WorkEffort(WorkEffortType workEffortType, String code, String name, String description, Date schedStartDt, Date schedEndDt, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.description = description;
		this.schedStartDt = schedStartDt;
		this.schedEndDt = schedEndDt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public WorkEffort(WorkEffortType workEffortType, String code, String name, String description, Date schedStartDt, Date schedEndDt, Integer estHours, Integer totalHrAllowed, BigDecimal totalAmountAllowed, Date effectiveDt, char recSt, Set<CommunicationEventWorkEffort> communicationEventWorkEfforts ) 
    {
		this.workEffortType = workEffortType;
		this.code = code;
		this.name = name;
		this.description = description;
		this.schedStartDt = schedStartDt;
		this.schedEndDt = schedEndDt;
		this.estHours = estHours;
		this.totalHrAllowed = totalHrAllowed;
		this.totalAmountAllowed = totalAmountAllowed;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.communicationEventWorkEfforts = communicationEventWorkEfforts;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="WORK_EFFORT_TY_ID", nullable=false)
    @JsonIgnore
    public WorkEffortType getWorkEffortType() 
    {
        return this.workEffortType;
    }
    
    public void setWorkEffortType(WorkEffortType workEffortType)
    {
        this.workEffortType = workEffortType;
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
    @Temporal(TemporalType.DATE)
    @Column(name="SCHED_START_DT" , nullable=false, length=10)
	@JsonSerialize(using = DateSerializer.class)
    public Date getSchedStartDt() 
    {
        return this.schedStartDt;
    }
    
    @JsonDeserialize(using = DateDeserializer.class)
    public void setSchedStartDt(Date schedStartDt) 
    {
        this.schedStartDt = schedStartDt;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="SCHED_END_DT" , nullable=false, length=10)
	@JsonSerialize(using = DateSerializer.class)
    public Date getSchedEndDt() 
    {
        return this.schedEndDt;
    }
    
    @JsonDeserialize(using = DateDeserializer.class)
    public void setSchedEndDt(Date schedEndDt) 
    {
        this.schedEndDt = schedEndDt;
    }
		
    @Column(name="EST_HOURS" , unique=true)
    public Integer getEstHours() 
    {
        return this.estHours;
    }
    
    public void setEstHours(Integer estHours) 
    {
        this.estHours = estHours;
    }
		
    @Column(name="TOTAL_HR_ALLOWED" , unique=true)
    public Integer getTotalHrAllowed() 
    {
        return this.totalHrAllowed;
    }
    
    public void setTotalHrAllowed(Integer totalHrAllowed) 
    {
        this.totalHrAllowed = totalHrAllowed;
    }
		
    @Column(name="TOTAL_AMOUNT_ALLOWED" , unique=true)
    public BigDecimal getTotalAmountAllowed() 
    {
        return this.totalAmountAllowed;
    }
    
    public void setTotalAmountAllowed(BigDecimal totalAmountAllowed) 
    {
        this.totalAmountAllowed = totalAmountAllowed;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="workEffort")
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


