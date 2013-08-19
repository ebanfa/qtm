/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.messaging.model;

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
import com.nathanclaire.alantra.notification.model.NotificationType;

/**
 * MessageApplicationNotificationMap 
 * @author Edward Banfa
 */
@Entity
@Table(name="MESSAGE_APPLICATION_NOTIFICATION_MAP"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class MessageApplicationNotificationMap  extends BaseEntity implements java.io.Serializable {

	private MessageApplicationAction messageApplicationAction;
    private NotificationType notificationType;
    private String name;
    private String description;
    private String tagVal;
    private char isRegexFg;

    public MessageApplicationNotificationMap() {
    }

    public MessageApplicationNotificationMap(MessageApplicationAction messageApplicationAction, NotificationType notificationType, String code, String name, String tagVal, char isRegexFg, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.notificationType = notificationType;
		this.code = code;
		this.name = name;
		this.tagVal = tagVal;
		this.isRegexFg = isRegexFg;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public MessageApplicationNotificationMap(MessageApplicationAction messageApplicationAction, NotificationType notificationType, String code, String name, String description, String tagVal, char isRegexFg, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
		this.messageApplicationAction = messageApplicationAction;
		this.notificationType = notificationType;
		this.code = code;
		this.name = name;
		this.description = description;
		this.tagVal = tagVal;
		this.isRegexFg = isRegexFg;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MSG_APPL_ACTION_ID", nullable=false)
    @JsonIgnore
    public MessageApplicationAction getMessageApplicationAction() 
    {
        return this.messageApplicationAction;
    }
    
    public void setMessageApplicationAction(MessageApplicationAction messageApplicationAction)
    {
        this.messageApplicationAction = messageApplicationAction;
    }
		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="NOTIFICATION_TY_ID", nullable=false)
    @JsonIgnore
    public NotificationType getNotificationType() 
    {
        return this.notificationType;
    }
    
    public void setNotificationType(NotificationType notificationType) 
    {
        this.notificationType = notificationType;
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
		
    @Column(name="DESCRIPTION" , unique=true, length=150)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
		
    @Column(name="TAG_VAL" , nullable=false, length=75)
    public String getTagVal() 
    {
        return this.tagVal;
    }
    
    public void setTagVal(String tagVal) 
    {
        this.tagVal = tagVal;
    }
		
    @Column(name="IS_REGEX_FG" , nullable=false, length=1)
    public char getIsRegexFg() 
    {
        return this.isRegexFg;
    }
    
    public void setIsRegexFg(char isRegexFg) 
    {
        this.isRegexFg = isRegexFg;
    }


}


