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
 * ApplicationActivityGroup 
 * @author Edward Banfa
 */
@Entity
@Table(name="APPLICATION_ACTIVITY_GROUP"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ApplicationActivityGroup  extends BaseEntity implements java.io.Serializable {

	private ApplicationActivityGroup applicationActivityGroup;
	private ApplicationActivityGroupType applicationActivityGroupType;
	private ApplicationModule applicationModule;
    private String name;
    private String description;
    private String grpUrl;
    private int grpSeq;
    private String displayNm;
    private String displayImg;
    private char displayFg;
    private char isParent;
	private Set<ApplicationActivityGroup> applicationActivityGroups = new HashSet<ApplicationActivityGroup>(0);
	private Set<ApplicationActivity> applicationActivities = new HashSet<ApplicationActivity>(0);

    public ApplicationActivityGroup() {
    }

    public ApplicationActivityGroup(ApplicationActivityGroupType applicationActivityGroupType, ApplicationModule applicationModule, String code, String name, String grpUrl, int grpSeq, String displayNm, char displayFg, char isParent, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.grpUrl = grpUrl;
		this.grpSeq = grpSeq;
		this.displayNm = displayNm;
		this.displayFg = displayFg;
		this.isParent = isParent;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public ApplicationActivityGroup(ApplicationActivityGroup applicationActivityGroup, ApplicationActivityGroupType applicationActivityGroupType, ApplicationModule applicationModule, String code, String name, String description, String grpUrl, int grpSeq, String displayNm, String displayImg, char displayFg, char isParent, Date effectiveDt, char recSt, Set<ApplicationActivityGroup> applicationActivityGroups, Set<ApplicationActivity> applicationActivities ) 
    {
		this.applicationActivityGroup = applicationActivityGroup;
		this.applicationActivityGroupType = applicationActivityGroupType;
		this.applicationModule = applicationModule;
		this.code = code;
		this.name = name;
		this.description = description;
		this.grpUrl = grpUrl;
		this.grpSeq = grpSeq;
		this.displayNm = displayNm;
		this.displayImg = displayImg;
		this.displayFg = displayFg;
		this.isParent = isParent;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.applicationActivityGroups = applicationActivityGroups;
		this.applicationActivities = applicationActivities;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARENT_GRP_ID")
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
    @JoinColumn(name="GRP_TY_ID", nullable=false)
    @JsonIgnore
    public ApplicationActivityGroupType getApplicationActivityGroupType() 
    {
        return this.applicationActivityGroupType;
    }
    
    public void setApplicationActivityGroupType(ApplicationActivityGroupType applicationActivityGroupType)
    {
        this.applicationActivityGroupType = applicationActivityGroupType;
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
		
    @Column(name="GRP_URL" , nullable=false)
    public String getGrpUrl() 
    {
        return this.grpUrl;
    }
    
    public void setGrpUrl(String grpUrl) 
    {
        this.grpUrl = grpUrl;
    }
		
    @Column(name="GRP_SEQ" , nullable=false)
    public int getGrpSeq() 
    {
        return this.grpSeq;
    }
    
    public void setGrpSeq(int grpSeq) 
    {
        this.grpSeq = grpSeq;
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
		
    @Column(name="DISPLAY_FG" , nullable=false, length=1)
    public char getDisplayFg() 
    {
        return this.displayFg;
    }
    
    public void setDisplayFg(char displayFg) 
    {
        this.displayFg = displayFg;
    }
		
    @Column(name="IS_PARENT" , nullable=false, length=1)
    public char getIsParent() 
    {
        return this.isParent;
    }
    
    public void setIsParent(char isParent) 
    {
        this.isParent = isParent;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="applicationActivityGroup")
    @JsonIgnore
    public Set<ApplicationActivityGroup> getApplicationActivityGroups() 
    {
        return this.applicationActivityGroups;
    }
    
    public void setApplicationActivityGroups(Set<ApplicationActivityGroup> applicationActivityGroups) 
    {
        this.applicationActivityGroups = applicationActivityGroups;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="applicationActivityGroup")
    @JsonIgnore
    public Set<ApplicationActivity> getApplicationActivities() 
    {
        return this.applicationActivities;
    }
    
    public void setApplicationActivities(Set<ApplicationActivity> applicationActivities) 
    {
        this.applicationActivities = applicationActivities;
    }			


}


