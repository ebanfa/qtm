/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.application.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.model.BaseEntity;

/**
 * ApplicationRelatedActivity 
 * @author Edward Banfa
 */
@Entity
@Table(name="APPLICATION_RELATED_ACTIVITY"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ApplicationRelatedActivity  extends BaseEntity implements java.io.Serializable {

    private String name;
    private String description;
	private ApplicationActivity sourceApplicationActivity;
	private ApplicationActivity destinationApplicationActivity;
    private int relActSeq;

    public ApplicationRelatedActivity() {
    }

    public ApplicationRelatedActivity(String code, int relActSeq, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.relActSeq = relActSeq;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public ApplicationRelatedActivity(ApplicationActivity applicationActivityBySrcActId, ApplicationActivity applicationActivityByDstActId, String code, int relActSeq, Date effectiveDt, char recSt) 
    {
		this.sourceApplicationActivity = applicationActivityBySrcActId;
		this.destinationApplicationActivity = applicationActivityByDstActId;
		this.code = code;
		this.relActSeq = relActSeq;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
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
		
    @Column(name="DESCRIPTION" , unique=true, length=100)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }

	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SRC_ACT_ID")
    @JsonIgnore
    public ApplicationActivity getSourceApplicationActivity() 
    {
        return this.sourceApplicationActivity;
    }
    
    public void setSourceApplicationActivity(ApplicationActivity applicationActivityBySrcActId)
    {
        this.sourceApplicationActivity = applicationActivityBySrcActId;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DST_ACT_ID")
    @JsonIgnore
    public ApplicationActivity getDestinationApplicationActivity() 
    {
        return this.destinationApplicationActivity;
    }
    
    public void setDestinationApplicationActivity(ApplicationActivity applicationActivityByDstActId)
    {
        this.destinationApplicationActivity = applicationActivityByDstActId;
    }
		
    @Column(name="REL_ACT_SEQ" , nullable=false)
    public int getRelActSeq() 
    {
        return this.relActSeq;
    }
    
    public void setRelActSeq(int relActSeq) 
    {
        this.relActSeq = relActSeq;
    }


}


