/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.advice.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.BaseEntity;

/**
 * AdviceType 
 * @author Edward Banfa
 */
@Entity
@Table(name="ADVICE_TYPE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AdviceType  extends BaseEntity implements java.io.Serializable {

    private String name;
    private String description;
    private Character reqFeedback;
    private String feedbackMsg;
	private Set<Advice> advices = new HashSet<Advice>(0);
	private Set<AdviceTypeTag> adviceTypeTags = new HashSet<AdviceTypeTag>(0);

    public AdviceType() {
    }

    public AdviceType(String code, String name, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public AdviceType(String code, String name, String description, Character reqFeedback, String feedbackMsg, Date effectiveDt, char recSt, Set<Advice> advices, Set<AdviceTypeTag> adviceTypeTags ) 
    {
		this.code = code;
		this.name = name;
		this.description = description;
		this.reqFeedback = reqFeedback;
		this.feedbackMsg = feedbackMsg;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.advices = advices;
		this.adviceTypeTags = adviceTypeTags;
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
		
    @Column(name="REQ_FEEDBACK" , unique=true, length=1)
    public Character getReqFeedback() 
    {
        return this.reqFeedback;
    }
    
    public void setReqFeedback(Character reqFeedback) 
    {
        this.reqFeedback = reqFeedback;
    }
		
    @Column(name="FEEDBACK_MSG" , unique=true)
    public String getFeedbackMsg() 
    {
        return this.feedbackMsg;
    }
    
    public void setFeedbackMsg(String feedbackMsg) 
    {
        this.feedbackMsg = feedbackMsg;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="adviceType")
    @JsonIgnore
    public Set<Advice> getAdvices() 
    {
        return this.advices;
    }
    
    public void setAdvices(Set<Advice> advices) 
    {
        this.advices = advices;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="adviceType")
    @JsonIgnore
    public Set<AdviceTypeTag> getAdviceTypeTags() 
    {
        return this.adviceTypeTags;
    }
    
    public void setAdviceTypeTags(Set<AdviceTypeTag> adviceTypeTags) 
    {
        this.adviceTypeTags = adviceTypeTags;
    }			


}

