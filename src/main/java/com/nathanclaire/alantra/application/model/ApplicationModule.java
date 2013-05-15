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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.model.BaseEntity;

/**
 * ApplicationModule 
 * @author Edward Banfa
 */
@Entity
@Table(name="APPLICATION_MODULE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ApplicationModule  extends BaseEntity implements java.io.Serializable {

    private String name;
    private String description;
    private int sequenceNo;
    private String displayNm;
    private String displayImg;
    private char displayFg;
	private Set<ApplicationForm> applicationForms = new HashSet<ApplicationForm>(0);
	private Set<ApplicationActivityGroup> applicationActivityGroups = new HashSet<ApplicationActivityGroup>(0);
	private Set<ApplicationEntity> applicationEntities = new HashSet<ApplicationEntity>(0);
	private Set<ApplicationActivity> applicationActivities = new HashSet<ApplicationActivity>(0);

    public ApplicationModule() {
    }

    public ApplicationModule(String code, String name, int sequenceNo, String displayNm, char displayFg, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.sequenceNo = sequenceNo;
		this.displayNm = displayNm;
		this.displayFg = displayFg;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public ApplicationModule(String code, String name, String description, int sequenceNo, String displayNm, String displayImg, char displayFg, Date effectiveDt, char recSt, Set<ApplicationForm> applicationForms, Set<ApplicationActivityGroup> applicationActivityGroups, Set<ApplicationEntity> applicationEntities, Set<ApplicationActivity> applicationActivities ) 
    {
		this.code = code;
		this.name = name;
		this.description = description;
		this.sequenceNo = sequenceNo;
		this.displayNm = displayNm;
		this.displayImg = displayImg;
		this.displayFg = displayFg;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.applicationForms = applicationForms;
		this.applicationActivityGroups = applicationActivityGroups;
		this.applicationEntities = applicationEntities;
		this.applicationActivities = applicationActivities;
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
		
    @Column(name="SEQUENCE_NO" , nullable=false)
    public int getSequenceNo() 
    {
        return this.sequenceNo;
    }
    
    public void setSequenceNo(int sequenceNo) 
    {
        this.sequenceNo = sequenceNo;
    }
		
    @Column(name="DISPLAY_NM" , nullable=false, length=50)
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="applicationModule")
    @JsonIgnore
    public Set<ApplicationForm> getApplicationForms() 
    {
        return this.applicationForms;
    }
    
    public void setApplicationForms(Set<ApplicationForm> applicationForms) 
    {
        this.applicationForms = applicationForms;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="applicationModule")
    @JsonIgnore
    public Set<ApplicationActivityGroup> getApplicationActivityGroups() 
    {
        return this.applicationActivityGroups;
    }
    
    public void setApplicationActivityGroups(Set<ApplicationActivityGroup> applicationActivityGroups) 
    {
        this.applicationActivityGroups = applicationActivityGroups;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="applicationModule")
    @JsonIgnore
    public Set<ApplicationEntity> getApplicationEntities() 
    {
        return this.applicationEntities;
    }
    
    public void setApplicationEntities(Set<ApplicationEntity> applicationEntities) 
    {
        this.applicationEntities = applicationEntities;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="applicationModule")
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


