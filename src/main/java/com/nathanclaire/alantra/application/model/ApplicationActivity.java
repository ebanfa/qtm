/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.application.model;

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
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.model.BaseEntity;

/**
 * ApplicationActivity 
 * @author Edward Banfa
 */
@Entity
@Table(name="APPLICATION_ACTIVITY"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ApplicationActivity  extends BaseEntity implements java.io.Serializable {

	private ApplicationActivityGroup applicationActivityGroup;
	private ApplicationModule applicationModule;
	private ApplicationEntity applicationEntity;
	private ApplicationForm applicationForm;
	private ApplicationActivityType applicationActivityType;
    private String name;
    private String description;
    private String activityUrl;
    private int activitySeq;
    private String displayNm;
    private String displayImg;
    private String operationCd;
	private Set<ApplicationRelatedActivity> relatedDestinationActivities = new HashSet<ApplicationRelatedActivity>(0);
	private Set<ApplicationRelatedActivity> relatedSourceActivities = new HashSet<ApplicationRelatedActivity>(0);

    public ApplicationActivity() {
    }

    public ApplicationActivity(ApplicationActivityGroup applicationActivityGroup, ApplicationModule applicationModule, ApplicationActivityType applicationActivityType, String code, String name, int activitySeq, String displayNm, String operationCd, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.activitySeq = activitySeq;
		this.displayNm = displayNm;
		this.operationCd = operationCd;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public ApplicationActivity(ApplicationActivityGroup applicationActivityGroup, ApplicationModule applicationModule, ApplicationEntity applicationEntity, ApplicationForm applicationForm, ApplicationActivityType applicationActivityType, String code, String name, String description, String activityUrl, int activitySeq, String displayNm, String displayImg, String operationCd, Date effectiveDt, char recSt, Set<ApplicationRelatedActivity> applicationRelatedActivitiesForDstActId, Set<ApplicationRelatedActivity> applicationRelatedActivitiesForSrcActId ) 
    {
		this.applicationActivityGroup = applicationActivityGroup;
		this.applicationModule = applicationModule;
		this.applicationEntity = applicationEntity;
		this.applicationForm = applicationForm;
		this.applicationActivityType = applicationActivityType;
		this.code = code;
		this.name = name;
		this.description = description;
		this.activityUrl = activityUrl;
		this.activitySeq = activitySeq;
		this.displayNm = displayNm;
		this.displayImg = displayImg;
		this.operationCd = operationCd;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.relatedDestinationActivities = applicationRelatedActivitiesForDstActId;
		this.relatedSourceActivities = applicationRelatedActivitiesForSrcActId;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="GRP_ID", nullable=false)
    @JsonIgnore
    public ApplicationActivityGroup getApplicationActivityGroup() 
    {
        return this.applicationActivityGroup;
    }
    
    public void setApplicationActivityGroup(ApplicationActivityGroup applicationActivityGroup)
    {
        this.applicationActivityGroup = applicationActivityGroup;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MODULE_ID", nullable=false)
    @JsonIgnore
    public ApplicationModule getApplicationModule() 
    {
        return this.applicationModule;
    }
    
    public void setApplicationModule(ApplicationModule applicationModule)
    {
        this.applicationModule = applicationModule;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ENTITY_ID")
    @JsonIgnore
    public ApplicationEntity getApplicationEntity() 
    {
        return this.applicationEntity;
    }
    
    public void setApplicationEntity(ApplicationEntity applicationEntity)
    {
        this.applicationEntity = applicationEntity;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FORM_ID")
    @JsonIgnore
    public ApplicationForm getApplicationForm() 
    {
        return this.applicationForm;
    }
    
    public void setApplicationForm(ApplicationForm applicationForm)
    {
        this.applicationForm = applicationForm;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ACTIVITY_TY_ID", nullable=false)
    @JsonIgnore
    public ApplicationActivityType getApplicationActivityType() 
    {
        return this.applicationActivityType;
    }
    
    public void setApplicationActivityType(ApplicationActivityType applicationActivityType)
    {
        this.applicationActivityType = applicationActivityType;
    }
		
    @Column(name="NAME" , nullable=false, length=150)
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
		
    @Column(name="ACTIVITY_URL" , unique=true)
    public String getActivityUrl() 
    {
        return this.activityUrl;
    }
    
    public void setActivityUrl(String activityUrl) 
    {
        this.activityUrl = activityUrl;
    }
		
    @Column(name="ACTIVITY_SEQ" , nullable=false)
    public int getActivitySeq() 
    {
        return this.activitySeq;
    }
    
    public void setActivitySeq(int activitySeq) 
    {
        this.activitySeq = activitySeq;
    }
		
    @Column(name="DISPLAY_NM" , nullable=false, length=75)
    public String getDisplayNm() 
    {
        return this.displayNm;
    }
    
    public void setDisplayNm(String displayNm) 
    {
        this.displayNm = displayNm;
    }
		
    @Column(name="DISPLAY_IMG" , unique=true, length=35)
    public String getDisplayImg() 
    {
        return this.displayImg;
    }
    
    public void setDisplayImg(String displayImg) 
    {
        this.displayImg = displayImg;
    }
		
    @Column(name="OPERATION_CD" , nullable=false, length=35)
    public String getOperationCd() 
    {
        return this.operationCd;
    }
    
    public void setOperationCd(String operationCd) 
    {
        this.operationCd = operationCd;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="destinationApplicationActivity")
    @JsonIgnore
    public Set<ApplicationRelatedActivity> getRelatedDestinationActivities() 
    {
        return this.relatedDestinationActivities;
    }
    
    public void setRelatedDestinationActivities(Set<ApplicationRelatedActivity> applicationRelatedActivitiesForDstActId) 
    {
        this.relatedDestinationActivities = applicationRelatedActivitiesForDstActId;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="sourceApplicationActivity")
    @JsonIgnore
    public Set<ApplicationRelatedActivity> getRelatedSourceActivities() 
    {
        return this.relatedSourceActivities;
    }
    
    public void setRelatedSourceActivities(Set<ApplicationRelatedActivity> applicationRelatedActivitiesForSrcActId) 
    {
        this.relatedSourceActivities = applicationRelatedActivitiesForSrcActId;
    }			


}


