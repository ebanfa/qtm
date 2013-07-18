/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.security.model;

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
import com.nathanclaire.alantra.datasource.model.DataChannelCategory;

/**
 * SystemUserNotificationChannel 
 * @author Edward Banfa
 */
@Entity
@Table(name="SYSTEM_USER_NOTIFICATION_CHANNEL"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SystemUserNotificationChannel  extends BaseEntity implements java.io.Serializable {

	private DataChannelCategory dataChannelCategory;
	private SystemUser systemUser;

    public SystemUserNotificationChannel() {
    }

    public SystemUserNotificationChannel(DataChannelCategory dataChannelCategory, SystemUser systemUser, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public SystemUserNotificationChannel(DataChannelCategory dataChannelCategory, SystemUser systemUser, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
		this.dataChannelCategory = dataChannelCategory;
		this.systemUser = systemUser;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CHANNEL_CAT_ID", nullable=false)
    @JsonIgnore
    public DataChannelCategory getDataChannelCategory() 
    {
        return this.dataChannelCategory;
    }
    
    public void setDataChannelCategory(DataChannelCategory dataChannelCategory)
    {
        this.dataChannelCategory = dataChannelCategory;
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


}


