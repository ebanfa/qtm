/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.security.model;

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
import com.nathanclaire.alantra.notification.model.SystemUserNotification;

/**
 * SystemUser 
 * @author Edward Banfa
 */
@Entity
@Table(name="SYSTEM_USER"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SystemUser  extends BaseEntity implements java.io.Serializable {

	private SystemGroup systemGroup;
    private String name;
    private String username;
    private String password;
    private String email;
    private String mobile;
    private String lockedFg;
    private String multiLoginFg;
	private Set<SystemUserNotification> systemUserNotifications = new HashSet<SystemUserNotification>(0);
	private Set<SystemUserGroup> systemUserGroups = new HashSet<SystemUserGroup>(0);
	private Set<CurrentUserSession> currentUserSessions = new HashSet<CurrentUserSession>(0);
	private Set<SystemUserMessage> systemUserMessages = new HashSet<SystemUserMessage>(0);
	private Set<SystemUserNotificationChannel> systemUserNotificationChannels = new HashSet<SystemUserNotificationChannel>(0);

    public SystemUser() {
    }

    public SystemUser(SystemGroup systemGroup, String code, String name, String username, String password, String email, String mobile, String lockedFg, String multiLoginFg, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.lockedFg = lockedFg;
		this.multiLoginFg = multiLoginFg;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public SystemUser(SystemGroup systemGroup, String code, String name, String username, String password, String email, String mobile, String lockedFg, String multiLoginFg, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<SystemUserNotification> systemUserNotifications, Set<SystemUserGroup> systemUserGroups, Set<CurrentUserSession> currentUserSessions, Set<SystemUserMessage> systemUserMessages, Set<SystemUserNotificationChannel> systemUserNotificationChannels ) 
    {
		this.systemGroup = systemGroup;
		this.code = code;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.lockedFg = lockedFg;
		this.multiLoginFg = multiLoginFg;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.systemUserNotifications = systemUserNotifications;
		this.systemUserGroups = systemUserGroups;
		this.currentUserSessions = currentUserSessions;
		this.systemUserMessages = systemUserMessages;
		this.systemUserNotificationChannels = systemUserNotificationChannels;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="GROUP_ID", nullable=false)
    @JsonIgnore
    public SystemGroup getSystemGroup() 
    {
        return this.systemGroup;
    }
    
    public void setSystemGroup(SystemGroup systemGroup)
    {
        this.systemGroup = systemGroup;
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
		
    @Column(name="USERNAME" , nullable=false, length=75)
    public String getUsername() 
    {
        return this.username;
    }
    
    public void setUsername(String username) 
    {
        this.username = username;
    }
		
    @Column(name="PASSWORD" , nullable=false, length=75)
    public String getPassword() 
    {
        return this.password;
    }
    
    public void setPassword(String password) 
    {
        this.password = password;
    }
		
    @Column(name="EMAIL" , nullable=false, length=50)
    public String getEmail() 
    {
        return this.email;
    }
    
    public void setEmail(String email) 
    {
        this.email = email;
    }
		
    @Column(name="MOBILE" , nullable=false, length=35)
    public String getMobile() 
    {
        return this.mobile;
    }
    
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }
		
    @Column(name="LOCKED_FG" , nullable=false, length=1)
    public String getLockedFg() 
    {
        return this.lockedFg;
    }
    
    public void setLockedFg(String lockedFg) 
    {
        this.lockedFg = lockedFg;
    }
		
    @Column(name="MULTI_LOGIN_FG" , nullable=false, length=1)
    public String getMultiLoginFg() 
    {
        return this.multiLoginFg;
    }
    
    public void setMultiLoginFg(String multiLoginFg) 
    {
        this.multiLoginFg = multiLoginFg;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="systemUser")
    @JsonIgnore
    public Set<SystemUserNotification> getSystemUserNotifications() 
    {
        return this.systemUserNotifications;
    }
    
    public void setSystemUserNotifications(Set<SystemUserNotification> systemUserNotifications) 
    {
        this.systemUserNotifications = systemUserNotifications;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="systemUser")
    @JsonIgnore
    public Set<SystemUserGroup> getSystemUserGroups() 
    {
        return this.systemUserGroups;
    }
    
    public void setSystemUserGroups(Set<SystemUserGroup> systemUserGroups) 
    {
        this.systemUserGroups = systemUserGroups;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="systemUser")
    @JsonIgnore
    public Set<CurrentUserSession> getCurrentUserSessions() 
    {
        return this.currentUserSessions;
    }
    
    public void setCurrentUserSessions(Set<CurrentUserSession> currentUserSessions) 
    {
        this.currentUserSessions = currentUserSessions;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="systemUser")
    @JsonIgnore
    public Set<SystemUserMessage> getSystemUserMessages() 
    {
        return this.systemUserMessages;
    }
    
    public void setSystemUserMessages(Set<SystemUserMessage> systemUserMessages) 
    {
        this.systemUserMessages = systemUserMessages;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="systemUser")
    @JsonIgnore
    public Set<SystemUserNotificationChannel> getSystemUserNotificationChannels() 
    {
        return this.systemUserNotificationChannels;
    }
    
    public void setSystemUserNotificationChannels(Set<SystemUserNotificationChannel> systemUserNotificationChannels) 
    {
        this.systemUserNotificationChannels = systemUserNotificationChannels;
    }			


}


