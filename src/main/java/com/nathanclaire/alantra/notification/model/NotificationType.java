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
 * NotificationType 
 * @author Edward Banfa
 */
@Entity
@Table(name="NOTIFICATION_TYPE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class NotificationType  extends BaseEntity implements java.io.Serializable {

	private Template template;
	private NotificationCategory notificationCategory;
    private String name;
    private String description;
    private Character autoRespFg;
	private Set<SystemUserNotification> systemUserNotifications = new HashSet<SystemUserNotification>(0);
	private Set<CustomerNotification> customerNotifications = new HashSet<CustomerNotification>(0);

    public NotificationType() {
    }

    public NotificationType(Template template, NotificationCategory notificationCategory, String code, String name, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public NotificationType(Template template, NotificationCategory notificationCategory, String code, String name, String description, Date effectiveDt, Character autoRespFg, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<SystemUserNotification> systemUserNotifications, Set<CustomerNotification> customerNotifications ) 
    {
		this.template = template;
		this.notificationCategory = notificationCategory;
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.autoRespFg = autoRespFg;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.systemUserNotifications = systemUserNotifications;
		this.customerNotifications = customerNotifications;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TEMPLATE_ID", nullable=false)
    @JsonIgnore
    public Template getTemplate() 
    {
        return this.template;
    }
    
    public void setTemplate(Template template)
    {
        this.template = template;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="NOTIFICATION_CAT_ID", nullable=false)
    @JsonIgnore
    public NotificationCategory getNotificationCategory() 
    {
        return this.notificationCategory;
    }
    
    public void setNotificationCategory(NotificationCategory notificationCategory)
    {
        this.notificationCategory = notificationCategory;
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
		
    @Column(name="AUTO_RESP_FG" , unique=true, length=1)
    public Character getAutoRespFg() 
    {
        return this.autoRespFg;
    }
    
    public void setAutoRespFg(Character autoRespFg) 
    {
        this.autoRespFg = autoRespFg;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="notificationType")
    @JsonIgnore
    public Set<SystemUserNotification> getSystemUserNotifications() 
    {
        return this.systemUserNotifications;
    }
    
    public void setSystemUserNotifications(Set<SystemUserNotification> systemUserNotifications) 
    {
        this.systemUserNotifications = systemUserNotifications;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="notificationType")
    @JsonIgnore
    public Set<CustomerNotification> getCustomerNotifications() 
    {
        return this.customerNotifications;
    }
    
    public void setCustomerNotifications(Set<CustomerNotification> customerNotifications) 
    {
        this.customerNotifications = customerNotifications;
    }			


}


