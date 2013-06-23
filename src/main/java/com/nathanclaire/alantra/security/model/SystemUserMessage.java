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
import com.nathanclaire.alantra.messaging.model.Message;

/**
 * SystemUserMessage 
 * @author Edward Banfa
 */
@Entity
@Table(name="SYSTEM_USER_MESSAGE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SystemUserMessage  extends BaseEntity implements java.io.Serializable {

	private Message message;
	private SystemUser systemUser;

    public SystemUserMessage() {
    }

    public SystemUserMessage(Message message, SystemUser systemUser, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public SystemUserMessage(Message message, SystemUser systemUser, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
		this.message = message;
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
    @JoinColumn(name="MESSAGE_ID", nullable=false)
    @JsonIgnore
    public Message getMessage() 
    {
        return this.message;
    }
    
    public void setMessage(Message message)
    {
        this.message = message;
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


