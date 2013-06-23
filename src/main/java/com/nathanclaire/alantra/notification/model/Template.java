/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.notification.model;

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
 * Template 
 * @author Edward Banfa
 */
@Entity
@Table(name="TEMPLATE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Template  extends BaseEntity implements java.io.Serializable {

	private TemplateType templateType;
	private TemplateClassification templateClassification;
    private String name;
    private String subjectTxt;
    private String messageTxt;
    private String description;
	private Set<NotificationType> notificationTypes = new HashSet<NotificationType>(0);

    public Template() {
    }

    public Template(TemplateType templateType, TemplateClassification templateClassification, String code, String name, String subjectTxt, String messageTxt, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.subjectTxt = subjectTxt;
		this.messageTxt = messageTxt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public Template(TemplateType templateType, TemplateClassification templateClassification, String code, String name, String subjectTxt, String messageTxt, String description, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<NotificationType> notificationTypes ) 
    {
		this.templateType = templateType;
		this.templateClassification = templateClassification;
		this.code = code;
		this.name = name;
		this.subjectTxt = subjectTxt;
		this.messageTxt = messageTxt;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.notificationTypes = notificationTypes;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TEMPLATE_TY_ID", nullable=false)
    @JsonIgnore
    public TemplateType getTemplateType() 
    {
        return this.templateType;
    }
    
    public void setTemplateType(TemplateType templateType)
    {
        this.templateType = templateType;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TEMPLATE_CLASS_ID", nullable=false)
    @JsonIgnore
    public TemplateClassification getTemplateClassification() 
    {
        return this.templateClassification;
    }
    
    public void setTemplateClassification(TemplateClassification templateClassification)
    {
        this.templateClassification = templateClassification;
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
		
    @Column(name="SUBJECT_TXT" , nullable=false)
    public String getSubjectTxt() 
    {
        return this.subjectTxt;
    }
    
    public void setSubjectTxt(String subjectTxt) 
    {
        this.subjectTxt = subjectTxt;
    }
		
    @Column(name="MESSAGE_TXT" , nullable=false, length=500)
    public String getMessageTxt() 
    {
        return this.messageTxt;
    }
    
    public void setMessageTxt(String messageTxt) 
    {
        this.messageTxt = messageTxt;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="template")
    @JsonIgnore
    public Set<NotificationType> getNotificationTypes() 
    {
        return this.notificationTypes;
    }
    
    public void setNotificationTypes(Set<NotificationType> notificationTypes) 
    {
        this.notificationTypes = notificationTypes;
    }			


}


