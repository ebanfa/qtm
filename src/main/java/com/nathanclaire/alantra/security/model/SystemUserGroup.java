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

/**
 * SystemUserGroup 
 * @author Edward Banfa
 */
@Entity
@Table(name="SYSTEM_USER_GROUP"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SystemUserGroup  extends BaseEntity implements java.io.Serializable {

	private SystemUser systemUser;
	private SystemGroup systemGroup;

    public SystemUserGroup() {
    }

    public SystemUserGroup(SystemUser systemUser, SystemGroup systemGroup, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public SystemUserGroup(SystemUser systemUser, SystemGroup systemGroup, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
		this.systemUser = systemUser;
		this.systemGroup = systemGroup;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="USER_ID", nullable=false)
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


}


