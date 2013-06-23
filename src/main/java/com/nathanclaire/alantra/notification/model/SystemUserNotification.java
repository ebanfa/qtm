/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.notification.model;

import java.util.Date;

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
import com.nathanclaire.alantra.security.model.SystemUser;

/**
 * SystemUserNotification 
 * @author Edward Banfa
 */
@Entity
@Table(name="SYSTEM_USER_NOTIFICATION"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SystemUserNotification  extends BaseEntity implements java.io.Serializable {

	private SystemUser systemUser;
	private NotificationType notificationType;

    public SystemUserNotification() {
    }

    public SystemUserNotification(SystemUser systemUser, NotificationType notificationType, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public SystemUserNotification(SystemUser systemUser, NotificationType notificationType, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
		this.systemUser = systemUser;
		this.notificationType = notificationType;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SYSTEM_USER_ID", nullable=false)
    @JsonIgnore
    public SystemUser getSystemUser() 
    {
        return this.systemUser;
    }
    
    public void setSystemUser(SystemUser systemUser)
    {
        this.systemUser = systemUser;
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


}


